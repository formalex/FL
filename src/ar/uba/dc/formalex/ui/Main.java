package ar.uba.dc.formalex.ui;

import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoImpl;
import ar.uba.dc.formalex.grafoDeDependencia.Grafo;
import ar.uba.dc.formalex.grafoDeDependencia.InfoComponenteBgt;
import ar.uba.dc.formalex.modelChecker.NuSMVModelChecker;
import ar.uba.dc.formalex.parser.FLParser;
import ar.uba.dc.formalex.parser.TokenMgrError;
import ar.uba.dc.formalex.util.LaAplanadora;
import ar.uba.dc.formalex.util.Util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.ParseException;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String args []) throws ParseException, FileNotFoundException {
        long iniT = System.currentTimeMillis();

        if(args.length < 1 || args.length > 2){
            System.out.println ("FormaLex:  You should use");
            System.out.println ("         java ar.uba.dc.formalex.ui.Main configFile");
            System.out.println ("or       java ar.uba.dc.formalex.ui.Main configFile flFile");
            return ;
        }

        if(args.length > 0){
            Util.loadProperties(args[0]);
      	}

        if(args.length == 2){
            System.setProperty("FLINPUT", args[1]);
      	}
        String input = System.getProperty("FL_INPUT");

        logger.debug("Se inicia el parser con el archivo de entrada: " + input);
        new FLParser(new java.io.FileInputStream(input));

        try {
            long ini = System.currentTimeMillis();
            FLParser.start();

            long fin = System.currentTimeMillis();
            long seg = (fin - ini);
            logger.debug("Tiempo del parser: " + seg + " ms");
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

        //Se generan los agentes, los contadores e intervalos locales, las acciones, contadores
        //e intervalos con agentes, se expanden las fórmulas con cuantificadores y se instancian
        //con los agentes creados.

      	FLInput flInput = FLParser.getFLInput();
        LaAplanadora divididos = new LaAplanadora();
        Grafo<InfoComponenteBgt> elgrafoDeDependenciasBgt = divididos.explotarYAplanar(flInput, new ConstructorDeGrafoImpl());
//        loguearEntSal(flInput);
        validar(flInput);

        logger.debug("Listo");
        long finT = System.currentTimeMillis();
        long segT = (finT - iniT);
        logger.debug("Tiempo total: " + segT + " milisegundos");
    }

    private static void loguearEntSal(FLInput flInput) {
        for(String s : flInput.getFlRule()) {
            System.out.println(s);
        }
        System.out.println("");
        for(String s : flInput.getFlPermission()) {
            System.out.println(s);
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
        logger.info("NUSMV: " + aValidar.translateToLTL());
        File file = NuSMVModelChecker.findTrace(flInput.getBackgroundTheory(), aValidar, false);
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
            logger.info("Nusmv: " + conPermiso.translateToLTL());
            File file = NuSMVModelChecker.findTrace(flInput.getBackgroundTheory(), conPermiso, false);
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
