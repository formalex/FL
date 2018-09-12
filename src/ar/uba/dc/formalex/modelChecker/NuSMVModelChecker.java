package ar.uba.dc.formalex.modelChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import ar.uba.dc.formalex.automatoncompactor.AutomatonCompactor;
import ar.uba.dc.formalex.automatoncompactor.AutomatonReplacementsGenerator;
import ar.uba.dc.formalex.fl.bgtheory.Agente;
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
    
	public static boolean encontroTrace(BackgroundTheory backgroundTheory, FLFormula formula, LTLTranslationType anLTLTranslationType) {
		
		String traceFile = findTrace(backgroundTheory, formula, anLTLTranslationType);
		
		return encontroTrace(traceFile);
	}

	//Devuelve el nombre del archivo de salida
    public static String findTrace(BackgroundTheory backgroundTheory, FLFormula formula, LTLTranslationType anLTLTranslationType) {
        
    	if (System.getProperty("NUSMV_EXE") == null) {
        	throw new RuntimeException("Falta indicar el exe de nusmv (NUSMV_EXE)");
        }

    	String output = null;

    	//Niego lo que estoy buscando para que nusmv, si encuentra, me devuelva un contraejemplo
        //Si no encuentra quiere decir que la fórmula es válida
        FLFormula formulaToTest = new FLNeg(formula);

        String ts = Fechas.getAAAAMMDD_HHMMSS();
        String automataName = "automata_" + ts;
		String automataExtension = ".nusmv";
		String nusmvAutomataFileName = automataName + automataExtension;
        String nusmvCommandsName = "nusmvCommands_" + ts;
		String nusmvCommandsFileName = nusmvCommandsName + automataExtension;
        String nusmvOutputName = "nusmvOut_" + ts;
		String nusvmOutputFileName = nusmvOutputName;
        
        String temp_dir = System.getProperty("TEMP_DIR");
        File nusmvInput = new File(temp_dir, nusmvAutomataFileName);
        File nusmvCommands = new File(temp_dir, nusmvCommandsFileName);
        File nusmvOutput = new File(temp_dir, nusvmOutputFileName);
        
        int ind = 1;
        while (nusmvCommands.exists()) {
            nusmvCommandsFileName = "nusmvCommands_" + ts + "_" + ind++ + automataExtension;
            nusmvCommands = new File(temp_dir, nusmvCommandsFileName );
        }
        ind = 1;
        while (nusmvOutput.exists()) {
            nusvmOutputFileName = "nusmvOut_" + ts + "_" + ind++ + automataExtension;
            nusmvOutput = new File(temp_dir, nusvmOutputFileName );
        }
        try {
        	// Se crea el archivo en donde se guarda la correspondencia entre los agentes y los roles. Se utiliza luego para determinar las entidades unsat
        	createAgentsFile(backgroundTheory, ts, temp_dir);
        	
            crearAutomata(backgroundTheory, nusmvInput, anLTLTranslationType);

            boolean compactAutomaton = false;
    		String strCompactAutomaton = System.getProperty("NUSMV_COMPACTADO");
    		if(strCompactAutomaton != null){
    			compactAutomaton = Boolean.parseBoolean(strCompactAutomaton);
    		}
            
    		String ltlExpr = formulaToTest.translateToLTL(anLTLTranslationType);

    		if (compactAutomaton) {
    			ltlExpr = compactNuSMVFiles(backgroundTheory, temp_dir, automataName, automataExtension, nusmvCommandsName, nusmvOutputName, ltlExpr);
    			nusmvInput = new File(temp_dir + "/" + automataName + "Compacted" + automataExtension);
    		}

        	crearComandosDimacs(nusmvCommands, nusmvOutput.getAbsolutePath(), ltlExpr);
 
            execNusmv(nusmvInput, nusmvCommands);
            
            // Se ejecuta Picosat con el archivo dimacs generado anteriormente
            output = execPicosat(ts, temp_dir, nusmvOutput);
            

        } catch (InterruptedException e) {
            logger.error("Error al ejecutar el programa");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return output;
    }
    
    //crea un archivo con los comando que se le pasarán a nusvm. Incluyen la expresión a validar.
    private static void crearComandos(File commandFile, String outputFile, String ltlExpr) throws IOException {
        String x = "go;" + endOfline + "check_ltlspec -o " + outputFile + " -p \"" + ltlExpr +
                "\";" + endOfline + "quit;";
        UtilFile.guardar(commandFile, x, false);
    }
    
    private static void crearComandosDimacs(File commandFile, String outputFile, String ltlExpr) throws IOException {
        String x = "go;" + endOfline + 
        		"build_boolean_model" + endOfline +
        		"bmc_setup" + endOfline +
        		"gen_ltlspec_bmc -o " + outputFile + " -p \"" + ltlExpr +
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

	private static String execPicosat(String ts, String temp_dir, File nusmvOutput) throws IOException, InterruptedException {
		
		final String picosatExecutable = System.getProperty("PICOSAT_EXE");
		String usatVariables = temp_dir + "/unsatVar_" + ts +".var";
		String outputPicosat = temp_dir + "/outputPicosat_" + ts;
		String command = picosatExecutable + " -V " + usatVariables + " " + nusmvOutput.getAbsolutePath() + ".dimacs -o " + outputPicosat;
		
		logger.info("Comienza Picosat. Comando a ejecutar:  " + command);
		logger.info("Corriendo...");
		long ini = System.currentTimeMillis();
		Process child = Runtime.getRuntime().exec(command);
		
		child.waitFor();

		long fin = System.currentTimeMillis();
		long seg = (fin - ini);
		logger.debug("Demoró " + seg + " ms");

		return outputPicosat;
	}

	private static void execNusmv(File nusmvInput, File nusmvCommands) throws IOException, InterruptedException {
		
		String nusmvExecutable = System.getProperty("NUSMV_EXE");
		
		//Ej: system prompt> NuSMV -source ARCHIVO_CMD ej.nusmv
		// -df Disable the computation of the set of reachable states.
		//Agregado de dynamic: Enables dynamic reordering of variables
		//Maneja mejor la memoria y corre más rapido!
		String command = nusmvExecutable +" -df -dynamic -source " + nusmvCommands.getAbsolutePath() + " " + nusmvInput.getAbsolutePath();
		
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

	private static void createAgentsFile(BackgroundTheory backgroundTheory, String ts, String temp_dir) throws IOException {
		// Se genera un archivo cuyo nombre comienza con el prefijo agents_ en donde se van a guardar los agentes y los roles correspondientes. 
		// Ejemplo:
		// agent_1: vendedor
		// agent_2: minorista, comprador
		// agent_3: mayorista, comprador
		// agent_4: vendedor, comprador
			
		StringBuffer agentsBuffer = new StringBuffer();
		
		for (Agente agente : backgroundTheory.getAgentes()) {
			agentsBuffer.append(agente + ": "+ agente.getRolesCSV() + "\n");
		}
		
		UtilFile.guardar(new File(temp_dir, "agents_" + ts), agentsBuffer.toString(), true);
	}

	private static String compactNuSMVFiles(BackgroundTheory backgroundTheory, String temp_dir,
			String automataFileName, String automataExtension, String commandsFileName, String nusvmOutputFileName, String ltlExpr) throws IOException {

		// Genero el archivo de reemplazos de variables y estados a partir de la backgroundTheory. 
		String replacementsFileName = automataFileName + "Replacements";
		AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(backgroundTheory, temp_dir, replacementsFileName);
		
		// Obtengo el mapa de los reemplazos generados
		Map<String, String> variableAndStatesReplacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
		
		// Se crea el compactador del automata con las reducciones.
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(temp_dir, automataFileName, automataExtension, variableAndStatesReplacements);

		// Verifico si se requiere eliminar comentarios del archivo del automata para la compactacion.
		boolean removeComments = false;
		String strSinComentarios = System.getProperty("NUSMV_SIN_COMENTARIOS");
		if(strSinComentarios != null){
			removeComments = Boolean.parseBoolean(strSinComentarios);
		}
		
		// Se realiza la compactacion del archivo del automata con la opcion de eliminar comentarios
		automatonCompactor.compact(removeComments, automataFileName + "Compacted");
		
		// Se realiza la compactacion de la expresion
		String reduceExpression = automatonCompactor.reduceExpression(ltlExpr, variableAndStatesReplacements);

		return reduceExpression;
	}
	
	private static boolean encontroTrace(String fileName){
		boolean encontroTrace = false;
		try {
			Pattern p = Pattern.compile("(?m)^s.*$");
			Matcher m;
				m = p.matcher(fromFile(fileName));
	        while (m.find()) {
	            encontroTrace = m.group().contains(" SATISFIABLE");
	        }    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return encontroTrace;
		
	}

    private static CharSequence fromFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();
     
        // Create a read-only CharBuffer on the file
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
        CharBuffer cbuf = Charset.forName("UTF-8").newDecoder().decode(bbuf);
        input.close();
        return cbuf;
    }
}

