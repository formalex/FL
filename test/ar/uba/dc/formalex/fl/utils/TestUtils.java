package ar.uba.dc.formalex.fl.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion.ReductorDeRepresentacionDeAccionADosEstados;
import ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion.ReductorDeRepresentacionDeAccionADosEstadosFake;
import ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion.ReductorDeRepresentacionDeAccionADosEstadosImpl;
import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.bgtheory.Interval;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLActionOutput;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoFake;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoImpl;
import ar.uba.dc.formalex.grafoDeDependencia.Grafo;
import ar.uba.dc.formalex.grafoDeDependencia.InfoComponenteBgt;
import ar.uba.dc.formalex.grafoDeDependencia.Nodo;
import ar.uba.dc.formalex.modelChecker.NuSMVModelChecker;
import ar.uba.dc.formalex.parser.FLParser;
import ar.uba.dc.formalex.parser.ParseException;
import ar.uba.dc.formalex.parser.TokenMgrError;
import ar.uba.dc.formalex.ui.Main;
import ar.uba.dc.formalex.util.LaAplanadora;


public class TestUtils {
	
	private static final String CORRIENDO_SIN_MODEL_CHECKER = "Corriendo sin Model Checker";
	
	private static final Logger logger = Logger.getLogger(Main.class);
	private static int nroDeCorridas = 0;
	
	public static void corridaDeFormaLex(String rutaArchivoDeEjemplo, boolean conModelChecker, boolean conReductor) throws Exception {
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
				
				validar(flInput, elgrafoDeDependenciasBgt, conModelChecker, conReductor);

				logger.debug("Listo");
			} catch (TokenMgrError e) {
				logger.error("FormaLex: Ha ocurrido un error de tokens durante el parseo.");
				logger.error(e.getMessage(), e);
				System.exit(-1);
			} catch (ar.uba.dc.formalex.parser.ParseException e) {
				logger.error("FormaLex: Ha ocurrido un error durante el parseo.");
				logger.error(e.getMessage(), e);
				throw new ParseException(e.getMessage());
				//System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			throw new Exception("No se pudo encontrar el archivo a parsear");
		}
	}
	
	private static void validar(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias, boolean conModelChecker, boolean conReductor) {
        boolean b = true;
        
        if (flInput.getRules().size() > 0)
            b = validarReglas(flInput, elGrafoDeDependencias, conModelChecker, conReductor);

        if (b){
            validarPermisos(flInput, elGrafoDeDependencias, conModelChecker, conReductor);
        }else{
            if (flInput.getPermissions().size() > 0){
                logger.debug("No se validan los permisos");
            }
        }
	}

	private static boolean validarReglas(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias, boolean conModelChecker, boolean conReductor) {
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
        
      //Se crea e invoca al reductor
        BackgroundTheory bgtConRepresentacionDeAccionesReducidas = invocarReductor(
				conReductor, aValidar, unaBgtFiltrada);
		
        boolean encontroTrace = true;
        if(conModelChecker){
        	File file = NuSMVModelChecker.findTrace(bgtConRepresentacionDeAccionesReducidas, aValidar);

        	if (!file.exists()){
        		//Si no se generó el archivo es porque el output del proceso está vacío. Eso suele pasar cuando hubo un error con nusmv.
        		logger.error("Error al correr nusmv. Intentar correr a mano el comando previamente logueado.");
        		throw new RuntimeException("Se abortó la ejecución de nusmv. Revisar archivo generado.");
        	}
        	encontroTrace = encontroTrace(file);
        	if (encontroTrace){
        		logger.info("Se ha encontrado un comportamiento legal para las normas.");
        		logger.info("Se puede ver el trace en: " + file.getAbsolutePath());
        	}else
        		logger.info("No se ha encontrado un comportamiento legal.");
        }else
        	logger.info(CORRIENDO_SIN_MODEL_CHECKER);
        
        logger.info("");
        
        return encontroTrace;
        
	}

	private static void validarPermisos(FLInput flInput, Grafo<InfoComponenteBgt> elGrafoDeDependencias, boolean conModelChecker, boolean conReductor) {
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
            logger.info("bgt despues del filtro del permiso: "+  conPermiso.toString());
            loguearBgt(unaBgtFiltrada);
            
            //Se crea e invoca al reductor
            BackgroundTheory bgtConRepresentacionDeAccionesReducidas = invocarReductor(
					conReductor, conPermiso, unaBgtFiltrada);
            
            if(conModelChecker){
	            File file = NuSMVModelChecker.findTrace(bgtConRepresentacionDeAccionesReducidas, conPermiso);
	            boolean encontroTrace = encontroTrace(file);
	            if (encontroTrace){
	                logger.info("Se ha encontrado un comportamiento legal para el permiso.");
	                logger.info("Se puede ver el trace en: " + file.getAbsolutePath());
	            }else{
	                logger.info("No se ha encontrado un comportamiento legal para el permiso.");
	            }
            }else
            	logger.info(CORRIENDO_SIN_MODEL_CHECKER);
            
            logger.info("");

        }
	}

	private static BackgroundTheory invocarReductor(boolean conReductor,
			FLFormula aFormula, BackgroundTheory unaBgtFiltrada) {
		ReductorDeRepresentacionDeAccionADosEstados reductorDeAccionADosEstados = getReductor(conReductor, aFormula, unaBgtFiltrada);
		BackgroundTheory bgtConRepresentacionDeAccionesReducidas = reductorDeAccionADosEstados.ejecutar();            
		logger.info(" Acciones DESPUES de la REDUCCION con F: "+  aFormula.toString());
		logOnlyActions(bgtConRepresentacionDeAccionesReducidas);
		return bgtConRepresentacionDeAccionesReducidas;
	}

	private static ReductorDeRepresentacionDeAccionADosEstados getReductor(boolean conReductor, FLFormula aValidar,
			BackgroundTheory unaBgtFiltrada) {
		ReductorDeRepresentacionDeAccionADosEstados reductorDeAccionADosEstados;
        if(conReductor){
        	reductorDeAccionADosEstados = new ReductorDeRepresentacionDeAccionADosEstadosImpl(unaBgtFiltrada, aValidar);
        }else {
        	reductorDeAccionADosEstados = new ReductorDeRepresentacionDeAccionADosEstadosFake(unaBgtFiltrada);
        }
        
        return reductorDeAccionADosEstados;
	}
	
	private static String contadorDeSignosIgual(String stringDeLaFormula) {
		
		int countMatches = StringUtils.countMatches(stringDeLaFormula, "=");
		return String.valueOf(countMatches);
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
		logOnlyActions(unaBgtFiltrada);
		logger.info("Intervalos");
		for (Interval i : unaBgtFiltrada.getIntervals()) {
			i.logFL();
		}
	}

	private static void logOnlyActions(BackgroundTheory unaBgt) {
		logger.info("Acciones");
		for (Action a : unaBgt.getActions()) {
			a.logFL();
		}
		logger.info("");
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
			
			// filtrar Output values
			filtrarOutputValues(res, aValidar);
			
			//limpiar las marcas del grafo
			elGrafoDeDependencias.resetMarcas();
		}else
			res = backgroundTheory;
				
		return res;
	}
	
	private static void filtrarOutputValues(BackgroundTheory res,
			FLFormula aValidar) {
		// Hay que armar el diccionario con las referencias a output values
		Set<FLActionOutput> referencedActionsWithResultsInInFormula = aValidar.getReferencedActionsWithResultsIn();
		Map<String, List<FLActionOutput>> actionWithResultsInReferencesGroupByActionName = agruparActionWithResultsInReferences(referencedActionsWithResultsInInFormula);

		for (Action anAction : res.getActions()) {

			if(anAction.hasOutputValues()){
				String anActionName = anAction.getName();
				List<FLActionOutput> anActionWithResultsInReferences = actionWithResultsInReferencesGroupByActionName.get(anActionName);
				Set<String> outputsEnLaformula = new HashSet <String>();
				
				//Si no tiene referencias a los output values en la formula, le limpio los output values a la action
				if(anActionWithResultsInReferences== null || anActionWithResultsInReferences.isEmpty() ){
				}else{ //Si hay referencias , hay que ver si se utilizan todas, y las que NO se utilizan agruparlas en una sola
					Set<String> referenciasOvs = getOuputValues(anActionWithResultsInReferences);
					for (String unOv : anAction.getOutputValues()) {
						if(referenciasOvs.contains(unOv)){
							outputsEnLaformula.add(unOv);
						}
					}
					
					//Si hay mas outputs definidos que referencias a estos en la formula
					if(anAction.getOutputValues().size()>outputsEnLaformula.size()){
						//Hay que crear un output ficticio por todas los outputs que NO se referencian en las formulas
						outputsEnLaformula.add("ningunoDeLosOtrosValores");
					}
				}
				anAction.setOutputValues(outputsEnLaformula);
			}
		}

	}

	private static Set<String> getOuputValues(
			List<FLActionOutput> anActionWithResultsInReferences) {
		
		Set<String> res = new HashSet <String> ();
		for (FLActionOutput unaReferenciaConOv : anActionWithResultsInReferences) {
			res.add(unaReferenciaConOv.getOutput());
		}
		
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
	
	/**Se agrupan por nombre de accion, las referencias a las acciones con output values 
	 * mediante 'results in' en una formula
	 * @param referencedActionsWithResultsInInTheFormula referencias a las 
	 * acciones con output values mediante 'results in' en una formula
	 */
	private static Map<String, List<FLActionOutput>> agruparActionWithResultsInReferences(
			Set<FLActionOutput> referencedActionsWithResultsInInTheFormula) {
		HashMap<String, List<FLActionOutput>> actionWithResultsInReferencesGroupByActionName = new HashMap<String, List<FLActionOutput>>();
		
		for (FLActionOutput flActionOutput : referencedActionsWithResultsInInTheFormula) {
			String mapKey = flActionOutput.getNameWithAgent();
			List<FLActionOutput> actionReferencesWithResultsInList;
			if(actionWithResultsInReferencesGroupByActionName.containsKey(mapKey)){
				actionReferencesWithResultsInList = actionWithResultsInReferencesGroupByActionName.get(mapKey);
			}else {
				actionReferencesWithResultsInList = new ArrayList<FLActionOutput>();
			}

			actionReferencesWithResultsInList.add(flActionOutput);
			actionWithResultsInReferencesGroupByActionName.put(mapKey, actionReferencesWithResultsInList);
		}
		
		return actionWithResultsInReferencesGroupByActionName;
	}

}
