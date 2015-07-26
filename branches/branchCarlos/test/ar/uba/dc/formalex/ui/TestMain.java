package ar.uba.dc.formalex.ui;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoImpl;
import ar.uba.dc.formalex.grafoDeDependencia.Grafo;
import ar.uba.dc.formalex.modelChecker.NuSMVModelChecker;
import ar.uba.dc.formalex.parser.FLParser;
import ar.uba.dc.formalex.parser.TokenMgrError;
import ar.uba.dc.formalex.util.LaAplanadora;

public class TestMain {

	private static final Logger logger = Logger.getLogger(Main.class);
	
	@BeforeClass
	public static void setUp(){

		System.setProperty("NUSMV_EXE", "C:/Program Files/NuSMV/2.5.4/bin/NuSMV.exe");
		System.setProperty("TEMPLATE_VELOCITY", "fl.vm");
		System.setProperty("TEMP_DIR", "C:/propsFl/salida");
	}
	@Test
	public void testEjemploPermisoPermanente() {
		
		corridaDeFormaLex("resources/permisoPermanente.txt");
	}
	
	@Test
	public void testEjemploSynchronize() {
		
		corridaDeFormaLex("resources/ejSynchronize.txt");
	}
	
	private void corridaDeFormaLex(String rutaArchivoDeEjemplo) {
		try {
			new FLParser(new java.io.FileInputStream(rutaArchivoDeEjemplo));
			
			 try {
		            long ini = System.currentTimeMillis();
		            FLParser.start();

		            long fin = System.currentTimeMillis();
		            long seg = (fin - ini);
		            logger.debug("Tiempo del parser: " + seg + " ms");
		            
		            FLInput flInput = FLParser.getFLInput();
		            LaAplanadora divididos = new LaAplanadora();
		            Grafo<String> elgrafoDeDependenciasBgt = divididos.explotarYAplanar(flInput, new ConstructorDeGrafoImpl());
//		          loguearEntSal(flInput);
		          validar(flInput);

		          logger.debug("Listo");
		        }
		        catch(TokenMgrError e){
		        	logger.error ("FormaLex: Ha ocurrido un error de tokens durante el parseo.");
		            logger.error(e.getMessage(), e);
		            System.exit(-1);
		        } catch (ar.uba.dc.formalex.parser.ParseException e) {
		            logger.error("FormaLex: Ha ocurrido un error durante el parseo.");
		            logger.error(e.getMessage(), e);
		            System.exit(-1);
				}
		} catch (FileNotFoundException e) {
			System.out.println ("El parser no se pudo instanciar");
		}
	}
	
	private static void validar(FLInput flInput) {
        boolean b = true;
        if (flInput.getRules().size() > 0)
            b = validarReglas(flInput);

        if (b){
            validarPermisos(flInput);
        }else{
            if (flInput.getPermissions().size() > 0){
                logger.debug("No se validan los permisos");
            }
        }
	}

	private static boolean validarReglas(FLInput flInput) {
        FLFormula aValidar = new FLTrue();

        String flRules = "TRUE";
        int ind = 0;
        //Uno todas las prohibiciones y obligaciones en una fórmula con conjunciones.
        for(FLFormula formula : flInput.getRules()) {
            aValidar = new FLAnd(aValidar, formula);
            flRules += " & " + flInput.getFlRule().get(ind++);
        }

        logger.info("Buscando trace para la formula:");
        logger.info("FL: " + flRules);
        logger.info("NUSMV: " + aValidar.toString());
        File file = NuSMVModelChecker.findTrace(flInput.getBackgroundTheory(), aValidar);
        if (!file.exists()){
        //Si no se generó el archivo es porque el output del proceso está vacío. Eso suele pasar cuando hubo un error con nusmv.
            logger.error("Error al correr nusmv. Intentar correr a mano el comando previamente logueado.");
            throw new RuntimeException("Se abortó la ejecución de nusmv. Revisar archivo generado.");
        }
        boolean encontroTrace = encontroTrace(file);
        if (encontroTrace){
            logger.info("Se ha encontrado un comportamiento legal para las normas.");
            logger.info("Se puede ver el trace en: " + file.getAbsolutePath());
        }else
            logger.info("No se ha encontrado un comportamiento legal.");
        logger.info("");
        return encontroTrace;
	}

	private static void validarPermisos(FLInput flInput) {
        FLFormula aValidar = new FLTrue();
        //Uno todas las prohibiciones y obligaciones en una fórmula con conjunciones.
        for(FLFormula formula : flInput.getRules()) {
            aValidar = new FLAnd(aValidar, formula);
        }

        int ind = 0;
        //Valido todas las normas junto con cada uno de los permisos (por separado)
        for(FLFormula formula : flInput.getPermissions()) {
            FLFormula conPermiso = new FLAnd(aValidar, formula);
            logger.info("Buscando trace para el permiso:");
            logger.info("FL: " + flInput.getFlPermission().get(ind++));
            logger.info("Nusmv: " + conPermiso.toString());
            File file = NuSMVModelChecker.findTrace(flInput.getBackgroundTheory(), conPermiso);
            boolean encontroTrace = encontroTrace(file);
            if (encontroTrace){
                logger.info("Se ha encontrado un comportamiento legal para el permiso.");
                logger.info("Se puede ver el trace en: " + file.getAbsolutePath());
            }else{
                logger.info("No se ha encontrado un comportamiento legal para el permiso.");
            }
            logger.info("");

        }
	}

    private static boolean encontroTrace(File file){
        BufferedReader bf= null;

        try {
            // Open the file as buffered text
            bf = new BufferedReader(new FileReader(file));
            String line;

            if (( line = bf.readLine()) != null)
                return line.endsWith("is false");
            else
                return false;
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        finally {
            if (bf != null)
                try {
                    bf.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
        }
    }

}
