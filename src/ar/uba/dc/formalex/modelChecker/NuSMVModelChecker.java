package ar.uba.dc.formalex.modelChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import ar.uba.dc.formalex.automatoncompactor.AutomatonCompactor;
import ar.uba.dc.formalex.automatoncompactor.AutomatonReplacementsGenerator;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.util.Util;
import ar.uba.dc.formalex.util.UtilFile;

/**
 * User: P_BENEDETTI
 * Date: 28/02/14
 * Time: 12:29
 */
public class NuSMVModelChecker {
	
    private static final Logger logger = Logger.getLogger(NuSMVModelChecker.class);
    private static String temp_dir;
    private static String endOfline;
    private static boolean compactAutomaton;
    private static boolean removeComments;
    private static String nusmvExecutable;
    private static String picosatExecutable;

	public static ModelCheckInfo findTrace(BackgroundTheory backgroundTheory, FLFormula formula, LTLTranslationType anLTLTranslationType) {

    	if (System.getProperty("NUSMV_EXE") == null) {
        	throw new RuntimeException("Falta indicar el exe de nusmv (NUSMV_EXE)");
        }

    	ModelCheckInfo modelCheckInfo = new ModelCheckInfo(temp_dir, compactAutomaton);

    	//Niego lo que estoy buscando para que nusmv, si encuentra, me devuelva un contraejemplo
        //Si no encuentra quiere decir que la fórmula es válida
        FLFormula formulaToTest = new FLNeg(formula);

        try {
        	// Se crea el archivo del automata
        	crearAutomata(backgroundTheory, modelCheckInfo.getNusmvAutomataFile(), anLTLTranslationType);

        	// Se crea la expresion
    		String ltlExpr = formulaToTest.translateToLTL(anLTLTranslationType);
    		
    		if (compactAutomaton) {
    			// Se realiza la compactacion de todos los archivos en caso que se requiera.
    			ltlExpr = compactNuSMVFiles(backgroundTheory, modelCheckInfo, ltlExpr);
    		}

    		// Se crea archivo de comandos para ejecutar Nusmv
			crearComandos(modelCheckInfo, ltlExpr);
 
			// Se ejecuta Nusmv y se genera un archivo dimacs
            execNusmv(modelCheckInfo);
            
            // Se ejecuta Picosat con el archivo dimacs generado anteriormente
            execPicosat(modelCheckInfo);

        } catch (InterruptedException e) {
            logger.error("Error al ejecutar el programa");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
		
		return modelCheckInfo;
	}
    
    private static void crearAutomata(BackgroundTheory backgroundTheory, File fileOut, LTLTranslationType anLTLTranslationType){
        PrintWriter writer = null;
        String strCodificacionArchivo = "UTF-8";

        try{
            VelocityContext context = new VelocityContext();
            context.put("acciones", backgroundTheory.getActions());
            context.put("intervalos", backgroundTheory.getIntervals());
            context.put("timers", backgroundTheory.getTimers());
            context.put("agentes", backgroundTheory.getAgentes());
            context.put("contadores", backgroundTheory.getCounters());

            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            
			ve.setProperty("input.encoding", strCodificacionArchivo);      
            ve.setProperty("output.encoding", strCodificacionArchivo);
            ve.init();
            
            String nombreTemplateUsado = Util.getTemplateName(anLTLTranslationType);			
            Template template = ve.getTemplate(nombreTemplateUsado)  ;

            writer = new PrintWriter (fileOut);
            template.merge( context , writer);
        }
        catch( Exception e ){
            logger.error("Error al generar el autómata", e);
        }
        finally {
            if ( writer != null){
                try{
                    writer.flush();
                    writer.close();
                }catch( Exception ee ) {
                    logger.error("Error al generar el autómata", ee);
                }
            }
        }
    }

    private static void crearComandos(ModelCheckInfo modelCheckInfo, String ltlExpr) throws IOException {
        String x = "go;" + endOfline + 
        		"build_boolean_model" + endOfline +
        		"bmc_setup" + endOfline +
        		"gen_ltlspec_bmc -o " + modelCheckInfo.getNusmvOutputPath() + " -p \"" + ltlExpr +
                "\";" + endOfline + "quit;";
        UtilFile.guardar(modelCheckInfo.getNusmvCommandsFile(), x, false);
    }

	private static void execNusmv(ModelCheckInfo modelCheckInfo) throws IOException, InterruptedException {
		
		//Ej: system prompt> NuSMV -source ARCHIVO_CMD ej.nusmv
		// -df Disable the computation of the set of reachable states.
		//Agregado de dynamic: Enables dynamic reordering of variables
		//Maneja mejor la memoria y corre más rapido!
		String command = nusmvExecutable + " -df -dynamic -source " 
										 + modelCheckInfo.getNusmvCommandsPath() + " " + modelCheckInfo.getNusmvAutomataPath();
		
		logger.info("Comienza nusmv. Comando a ejecutar:  " + command);
		logger.info("Corriendo...");
		long ini = System.currentTimeMillis();
		Process child = Runtime.getRuntime().exec(command);

		String salida;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(child.getErrorStream()));

		StringBuilder sb = null;
		boolean isWarning = false;
		while ((salida = stdInput.readLine()) != null) {
		    if (sb == null)
		        sb = new StringBuilder();
		    sb.append(salida);
		    sb.append("\n");
		    if (salida.startsWith("aborting")) //si aborta, lo que aborta es el comando ejecutado,
		    // pero se queda dentro de nusmv. Hay que forzar la salida. Esto puede pasar si hay
		    // algún error sintáctico en lo que se le pas� a nusmv.
		        break;
		    if (salida.startsWith("********   WARNING   ********"))
		        isWarning = true;
		}
		if (sb != null ){
		    if (!isWarning){
		        logger.error("Error al correr nusmv.");
		        logger.error(sb.toString());
		        child.destroy();
		        throw new RuntimeException("Se abortó la ejecución de nusmv. Revisar archivo generado.");
		    }else{// si es un warning => logueo y sigo
		        logger.warn(sb.toString());
		    }
		}

		child.waitFor();

		long fin = System.currentTimeMillis();
		long seg = (fin - ini);
		logger.debug("Demoró " + seg + " ms");
	}

	private static void execPicosat(ModelCheckInfo modelCheckInfo) throws IOException, InterruptedException {
		
		String command = picosatExecutable + " -V " + modelCheckInfo.getUnsatVariablesPath() + " "
										   + modelCheckInfo.getNusmvOutputPath() + ".dimacs -o " + modelCheckInfo.getOutputPicosatPath();
		
		logger.info("Comienza Picosat. Comando a ejecutar:  " + command);
		logger.info("Corriendo...");
		long ini = System.currentTimeMillis();
		Process child = Runtime.getRuntime().exec(command);
		
		child.waitFor();

		long fin = System.currentTimeMillis();
		long seg = (fin - ini);
		logger.debug("Demoró " + seg + " ms");
		
	}

	private static String compactNuSMVFiles(BackgroundTheory backgroundTheory, ModelCheckInfo modelCheckInfo, String ltlExpr) throws IOException {

		// Genero el archivo de reemplazos de variables y estados a partir de la backgroundTheory. 
		AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(backgroundTheory, modelCheckInfo.getTempDir(), modelCheckInfo.getAutomataReplacementName());
		
		// Obtengo el mapa de los reemplazos generados
		Map<String, String> variableAndStatesReplacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(modelCheckInfo.getTempDir(), modelCheckInfo.getAutomataName(), modelCheckInfo.getAutomataExtension(), variableAndStatesReplacements);

		modelCheckInfo.setAutomatonCompactor(automatonCompactor);
		
		// Se realiza la compactacion del archivo del automata con la opcion de eliminar comentarios
		File automataCompactedFile = automatonCompactor.compact(removeComments, modelCheckInfo.getAutomataCompactedName());
		modelCheckInfo.setNusmvAutomataFile(automataCompactedFile);
		
		// Se realiza la compactacion de la expresion
		String reduceExpression = automatonCompactor.reduceExpression(ltlExpr, variableAndStatesReplacements);
		
		return reduceExpression;
	}
	
	static {
		endOfline = System.getProperty("line.separator");	
		
		temp_dir = System.getProperty("TEMP_DIR");
		
		String strCompactAutomaton = System.getProperty("NUSMV_COMPACTADO");
		compactAutomaton = false;
		if(strCompactAutomaton != null){
			compactAutomaton = Boolean.parseBoolean(strCompactAutomaton);
		}
		
		// Verifico si se requiere eliminar comentarios del archivo del automata para la compactacion.
		String strSinComentarios = System.getProperty("NUSMV_SIN_COMENTARIOS");
		removeComments = false;
		if(strSinComentarios != null){
			removeComments = Boolean.parseBoolean(strSinComentarios);
		}
		
		nusmvExecutable = System.getProperty("NUSMV_EXE");
		
		picosatExecutable = System.getProperty("PICOSAT_EXE");

	}
}

