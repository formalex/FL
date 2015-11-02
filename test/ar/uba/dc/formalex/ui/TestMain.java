package ar.uba.dc.formalex.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.bgtheory.Interval;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoFake;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoImpl;
import ar.uba.dc.formalex.grafoDeDependencia.Grafo;
import ar.uba.dc.formalex.grafoDeDependencia.InfoComponenteBgt;
import ar.uba.dc.formalex.grafoDeDependencia.Nodo;
import ar.uba.dc.formalex.modelChecker.NuSMVModelChecker;
import ar.uba.dc.formalex.parser.FLParser;
import ar.uba.dc.formalex.parser.TokenMgrError;
import ar.uba.dc.formalex.util.LaAplanadora;

public class TestMain {

	private static final Logger logger = Logger.getLogger(Main.class);
	private static String ROOT_RESOURCES = "resources/";
	private static String ROOT_EJS_FILTROS = ROOT_RESOURCES + "EjemplosParaFiltrar/";
	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	
	private static int nroDeCorridas = 0;
	@BeforeClass
	public static void setUp(){

		boolean estoyEnWindows = true;
		
		if(estoyEnWindows){	
			System.setProperty("NUSMV_EXE", "C:/Program Files/NuSMV/2.5.4/bin/NuSMV.exe");		
			System.setProperty("TEMP_DIR", "C:/propsFl/salida");
		}else{	//Linux
			System.setProperty("NUSMV_EXE", "/Data/JAVA/NuSMV-2.5.4-x86_64-unknown-linux-gnu/bin/NuSMV");
			System.setProperty("TEMP_DIR", "/media/charly/WINDOWS/propsFl/salida");
		}
		System.setProperty("TEMPLATE_VELOCITY", "fl.vm");
	}
	
	@Test
	public void testAcldc() {		
		corridaDeFormaLex(ROOT_RESOURCES + "ACLDC.txt");
	}
	
	@Test
	public void testAcldcFullConFormulasConAccionesConOutputValuesModificadas() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullConFormulasConAccionesConOutputValuesModificadas.txt");
	}
		
	@Test
	public void testAcldcFullSinFormulasConAccionesConOutputValues() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullSinFormulasConAccionesConOutputValues.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas1() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas1.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas5() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas5.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas15() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas15.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas25.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas28() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas28.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas60() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas60.txt");
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas40() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas40.txt");
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas1() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas1.txt");
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas5() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas5.txt");
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas15() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas15.txt");
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas25() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas25.txt");
	}
		
	@Test
	public void testCasoDeEstudio2NroDeClausulas28() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas28.txt");
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas1() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas1.txt");
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas5() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas5.txt");
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas15() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas15.txt");
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas25.txt");
	}
	@Test
	public void testCasoDeEstudio3NroDeClausulas32() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas32.txt");
	}
	
	
	@Test
	public void testCasoDeEstudioVipMemberPass() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPass.txt");
	}
	
	@Test
	public void testCasoDeEstudioVipMemberPassFull() {
		
		corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPassFull.txt");
	}
	
	@Test
	public void testRompeNusmvPorqueFormulaMayorTo65Kb() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesConOutputValuesQueRompeNusmv.txt");
	}
	
	@Test
	public void testEjemploActionConOutputValuesConRolesReferenciados() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesConRolesReferenciadosEnRegla.txt");
	}
	
	@Test
	public void testEjemploActionConOutputValuesSinRolesReferenciados() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesSinRolesReferenciadosEnRegla.txt");
	}
	
	/**** Estos dos tests los hice para saber si variaba la cantidad de agentes 
	 * generados, segun la cantidad de lineas en las que se divida la definicion
	 * de los roles. La realidad es que no varía. genera en ambos casos
	 * 16 agentes
	 */
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnUnaLinea() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnUnaLinea.txt");
	}
	
	@Test
	public void testEjemploConstruccionDeGrafo() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjConstruccionDeGrafo.txt");
	}
	
	@Test
	public void testEjemploGeneracionDeAgentes() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjGeneracionDeAgentes.txt");
	}
	
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnDosLineas() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnDosLineas.txt");
	}
	
	@Test
	public void testEjemploCombinacionRolesTesisChicas() {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		corridaDeFormaLex(ROOT_RESOURCES + "EjCombinacionRolesTesisChicas.txt");
	}
	
	@Test
	public void testEjemploBelongsToTesisChicas() {
		// Lo hice para ver el seguimiento de belongsTo
		corridaDeFormaLex(ROOT_RESOURCES + "EjBelongsTo.txt");
	}
	
	@Test
	public void testEjemploPermisoPermanente() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "permisoPermanente.txt");
	}
	
	@Test
	@Ignore("Hasta que se arregle el tema del synchronize")
	public void testEjemploSynchronize() {
		
		corridaDeFormaLex(ROOT_RESOURCES + "ejSynchronize.txt");
	}
	
	@Test
	public void testEjemploFiltroConIntervalOnlyOccursInScope() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosConOnlyOccursInScopeQueSeUsan.txt");
	}
	
	@Test
	public void testEjemploFiltroConActionsOnlyOccursInScope() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConActionsOnlyOccursInScope.txt");
	}
	
	@Test
	public void testEjemploFiltroConAccionesQueNoSeReferencian() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesQueNoSeReferencian.txt");
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueNoSeUsan() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosQueNoSeUsan.txt");
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueSeUsan() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosQueSeUsan.txt");
	}
	
	@Test
	public void testEjemploFiltroConCountersQueNoSeUsan() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConCountersQueNoSeUsan.txt");
	}
	
	@Test
	public void testEjemploFiltroConCountersQueSeUsan() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConCountersQueSeUsan.txt");
	}
	
	@Test
	public void testEjemploFiltroConPpConExcepcion() {
		
		corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConPpConExcepcion.txt");
	}
	
	private void corridaDeFormaLex(String rutaArchivoDeEjemplo) {
		try {
			java.io.FileInputStream streamFile = new java.io.FileInputStream(rutaArchivoDeEjemplo);
			
			if(nroDeCorridas==0)
				new FLParser(streamFile);
			else{
				FLParser.ReInit(streamFile);
				FLParser.clean();
			}
			
			nroDeCorridas++;

			try {
				long ini = System.currentTimeMillis();
				FLParser.start();

				long fin = System.currentTimeMillis();
				long seg = (fin - ini);
				logger.debug("Tiempo del parser: " + seg + " ms");

				FLInput flInput = FLParser.getFLInput();
				LaAplanadora divididos = new LaAplanadora();
				Grafo<InfoComponenteBgt> elgrafoDeDependenciasBgt = divididos
						.explotarYAplanar(flInput, new ConstructorDeGrafoImpl());
				// loguearEntSal(flInput);
				validar(flInput, elgrafoDeDependenciasBgt);

				logger.debug("Listo");
			} catch (TokenMgrError e) {
				logger.error("FormaLex: Ha ocurrido un error de tokens durante el parseo.");
				logger.error(e.getMessage(), e);
				System.exit(-1);
			} catch (ar.uba.dc.formalex.parser.ParseException e) {
				logger.error("FormaLex: Ha ocurrido un error durante el parseo.");
				logger.error(e.getMessage(), e);
				System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			System.out.println("El parser no se pudo instanciar");
		}
	}

	private static void validar(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias) {
        boolean b = true;
        if (flInput.getRules().size() > 0)
            b = validarReglas(flInput, elGrafoDeDependencias);

        if (b){
            validarPermisos(flInput, elGrafoDeDependencias);
        }else{
            if (flInput.getPermissions().size() > 0){
                logger.debug("No se validan los permisos");
            }
        }
	}

	private static boolean validarReglas(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias) {
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
        logger.info("# de Operadores Modales : " + contadorDeOperadoresModales(aValidar.toString()));
        logger.info("# de '=' : " + contadorDeSignosIgual(aValidar.toString()));
        
        BackgroundTheory unaBgtFiltrada= filtrar(flInput.getBackgroundTheory(), aValidar, elGrafoDeDependencias);        
        logger.info("# bgt despues del filtro de la formula: "+ aValidar.toString());
        loguearBgt(unaBgtFiltrada);
		
        //TODO comentado para no correr el model checker. ve como se puede hacer
        //para no correrlo de una manera mas clara
        //return true;
        
        File file = NuSMVModelChecker.findTrace(unaBgtFiltrada, aValidar);
        
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
	
	private static String contadorDeSignosIgual(String stringDeLaFormula) {
		
		int countMatches = StringUtils.countMatches(stringDeLaFormula, "=");
		return String.valueOf(countMatches);
	}

	private static void validarPermisos(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias) {
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
            logger.info("# de '=' : " + contadorDeSignosIgual(conPermiso.toString()));
            logger.info("# de Operadores Modales : " + contadorDeOperadoresModales(conPermiso.toString()));
            
            
            
            BackgroundTheory unaBgtFiltrada= filtrar(flInput.getBackgroundTheory(), conPermiso, elGrafoDeDependencias);        
            logger.info("# bgt despues del filtro del permiso: "+  conPermiso.toString());
            loguearBgt(unaBgtFiltrada);
            
          //TODO comentado para no correr el model checker. ve como se puede hacer
            //para no correrlo de una manera mas clara
            File file = NuSMVModelChecker.findTrace(unaBgtFiltrada, conPermiso);
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

    private static String contadorDeOperadoresModales(String stringDeLaFormula) {
		
    	int countGs = StringUtils.countMatches(stringDeLaFormula, "G (");  
    	int countFs = StringUtils.countMatches(stringDeLaFormula, "F(");
    	
    	return String.valueOf(countGs + countFs);
    	
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
    
	private static void loguearBgt(BackgroundTheory unaBgtFiltrada) {
		logger.info("# agentes: " + unaBgtFiltrada.getAgentes().size());
		logger.info("# acciones: " + unaBgtFiltrada.getActions().size());
		logger.info("# contadores: " + unaBgtFiltrada.getCounters().size());
		logger.info("# intervalos: " + unaBgtFiltrada.getIntervals().size());
		logger.info("");
		logger.info("Acciones");
		for (Action a : unaBgtFiltrada.getActions()) {
			a.logFL();
		}
		logger.info("");
		logger.info("Intervalos");
		for (Interval i : unaBgtFiltrada.getIntervals()) {
			i.logFL();
		}
	}

	private static BackgroundTheory filtrar(BackgroundTheory backgroundTheory,
			FLFormula aValidar, Grafo<InfoComponenteBgt> elGrafoDeDependencias) {

		BackgroundTheory res;
		//Se hace la copia DE LA BGT solo si es necesario
		if(elGrafoDeDependencias!=null){
			res = backgroundTheory.clonar();		
			//Marcar los componentes que aparecen en la formula
			Set<String> componentesParaMarcar = aValidar.getNombresDeComponentes();
			for (String nombreDeComponente : componentesParaMarcar) {
				elGrafoDeDependencias.marcarNodosEnBfs(nombreDeComponente);
			}
			
			//Eliminarlos de la bgt
			limpiarBgt(res, elGrafoDeDependencias);
			
			//limpiar las marcas del grafo
			elGrafoDeDependencias.resetMarcas();
		}else
			res = backgroundTheory;
				
		return res;
	}
	private static void limpiarBgt(BackgroundTheory bgt,
			Grafo<InfoComponenteBgt> elGrafoDeDependencias) {
		
		Iterator<Nodo<InfoComponenteBgt>> iterNodos = elGrafoDeDependencias.getNodos();
		while (iterNodos.hasNext()) {
			Nodo<InfoComponenteBgt> unNodo = iterNodos.next();
			
			if(!unNodo.isMarcado()){
				//Hay que borrar
				InfoComponenteBgt unaInfoComponente = unNodo.getValor();
				switch (unaInfoComponente.getTipoDeComponente()) {
				case ACTION:
					bgt.removeActionByName(unaInfoComponente.getName());
					break;				
				case INTERVAL:
					bgt.removeIntervalByName(unaInfoComponente.getName());
					break;	
				case COUNTER:
					bgt.removeCounterByName(unaInfoComponente.getName());
					break;	
				case AGENTE:		
					bgt.removeAgenteByName(unaInfoComponente.getName());
					break;
				default:
					throw new RuntimeException("Tipo de componente inesperado!");
				}
			}
			
		}	
	}

}
