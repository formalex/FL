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
import ar.uba.dc.formalex.util.Fechas;
import ar.uba.dc.formalex.util.Util;
import ar.uba.dc.formalex.util.UtilFile;

/**
 * User: P_BENEDETTI
 * Date: 28/02/14
 * Time: 12:29
 */
@SuppressWarnings("BreakStatement")
public class NuSMVModelChecker {
    private static final Logger logger = Logger.getLogger(NuSMVModelChecker.class);
    private static String endOfline = System.getProperty("line.separator");

    //crea un archivo con los comando que se le pasarán a nusvm. Incluyen la expresión a validar.
    private static void crearComandos(File commandFile, String outputFile, String ltlExpr) throws IOException {
        String x = "go;" + endOfline + "check_ltlspec -o " + outputFile + " -p \"" + ltlExpr +
                "\";" + endOfline + "quit;";
        UtilFile.guardar(commandFile, x, false);
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
//            context.put("acciones", Util.ordenar(backgroundTheory.getActions()));
//            context.put("intervalos", Util.ordenar(backgroundTheory.getIntervals()));
//            context.put("timers", Util.ordenar(backgroundTheory.getTimers()));
//            context.put("agentes", Util.ordenar(backgroundTheory.getAgentes()));
//            context.put("contadores", Util.ordenar(backgroundTheory.getCounters()));

            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            
			ve.setProperty("input.encoding", strCodificacionArchivo);      
            ve.setProperty("output.encoding", strCodificacionArchivo);
            ve.init();
            
            
            String nombreTemplateUsado = Util.getTemplateName(anLTLTranslationType);			
            Template template = ve.getTemplate(nombreTemplateUsado)  ;
          //Template template = ve.getTemplate( System.getProperty("TEMPLATE_VELOCITY") )  ;


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

	//Devuelve el archivo de salida
    public static File findTrace(BackgroundTheory backgroundTheory, FLFormula formula, LTLTranslationType anLTLTranslationType) {
        final String nusmvExecutable = System.getProperty("NUSMV_EXE");
        if (nusmvExecutable == null)
            throw new RuntimeException("Falta indicar el exe de nusmv (NUSMV_EXE)");

        //Niego lo que estoy buscando para que nusmv, si encuentra, me devuelva un contraejemplo
        //Si no encuentra quiere decir que la fórmula es válida
        FLFormula formulaToTest = new FLNeg(formula);

        String ts = Fechas.getAAAAMMDD_HHMMSS();
        String nusmvAutomataFileName = "automata_" + ts + ".nusmv";
        String nusmvCommandsFileName = "nusmvCommands_" + ts + ".nusmv";
        String nusvmOutputFileName = "nusmvOut_" + ts + ".nusmv";

        String temp_dir = System.getProperty("TEMP_DIR");
        File nusmvInput = new File(temp_dir, nusmvAutomataFileName);
        File nusmvCommands = new File(temp_dir, nusmvCommandsFileName);
        File nusmvOutput = new File(temp_dir, nusvmOutputFileName);
        
        File nusmvCommandsReduced = null;
        AutomatonCompactor automatonCompactor = null;
        
        int ind = 1;
        while (nusmvCommands.exists()) {
            nusmvCommandsFileName = "nusmvCommands_" + ts + "_" + ind++ + ".nusmv";
            nusmvCommands = new File(temp_dir, nusmvCommandsFileName );
        }
        ind = 1;
        while (nusmvOutput.exists()) {
            nusvmOutputFileName = "nusmvOut_" + ts + "_" + ind++ + ".nusmv";
            nusmvOutput = new File(temp_dir, nusvmOutputFileName );
        }
        try {
            crearAutomata(backgroundTheory, nusmvInput, anLTLTranslationType);

            boolean compactAutomaton = false;
    		String strCompactAutomaton = System.getProperty("NUSMV_COMPACTADO");
    		if(strCompactAutomaton != null){
    			compactAutomaton = Boolean.parseBoolean(strCompactAutomaton);
    		}
            
    		String command = null;
//            String ltlExpr = formulaToTest.toString() + " & !X X X X X TRUE";
    		String ltlExpr = formulaToTest.translateToLTL(anLTLTranslationType);
    		
            if (!compactAutomaton) {
            	crearComandos(nusmvCommands, nusmvOutput.getAbsolutePath(), ltlExpr);
                //Ej: system prompt> NuSMV -source ARCHIVO_CMD ej.nusmv
            	// -df Disable the computation of the set of reachable states.
//                String command = nusmvExecutable +" -df -source " + nusmvCommands.getAbsolutePath() +
//                        " " + nusmvInput.getAbsolutePath();
            	//Agregado de dynamic: Enables dynamic reordering of variables
            	//Maneja mejor la memoria y corre más rapido!
                command = nusmvExecutable +" -df -dynamic -source " + nusmvCommands.getAbsolutePath() +
                        " " + nusmvInput.getAbsolutePath();
            } else {
            	//Si se eligio compactar el automata se generan todos los archivos necesarios.
                // Genero los reemplazos de variables y estados 
                AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(backgroundTheory, temp_dir, "automata_" + ts);
                Map<String, String> variableAndStatesReplacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
                // Se crea el archivo del automata con las reducciones.
                automatonCompactor = new AutomatonCompactor(temp_dir, "automata_" + ts, ".nusmv", variableAndStatesReplacements);;
                
                boolean removeComments = false;
        		String strSinComentarios = System.getProperty("NUSMV_SIN_COMENTARIOS");
        		if(strSinComentarios != null){
        			removeComments = Boolean.parseBoolean(strSinComentarios);
        		}
    			automatonCompactor.compact(removeComments);
    			automatonCompactor.reduceExpression(ltlExpr);
    			// Se crean los comandos con la expresion reducida
                String nusmvCommandsReducedFileName = "nusmvCommandsReduced" + ts + ".nusmv";
                nusmvCommandsReduced = new File(temp_dir, nusmvCommandsReducedFileName);    
                crearComandos(nusmvCommandsReduced, nusmvOutput.getAbsolutePath(), automatonCompactor.getReducedExpression());
            	
                command = nusmvExecutable +" -df -dynamic -source " + nusmvCommandsReduced.getAbsolutePath() +
    	        " " + automatonCompactor.getCompactedAutomatonFile().getAbsolutePath();
            }
        	
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

        } catch (InterruptedException e) {
            logger.error("Error al ejecutar el programa");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return nusmvOutput;
    }

}

