package ar.uba.dc.formalex.automatoncompactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import org.junit.Test;

public class AutomatonCompactorBenchmarkTest {
	
	private String dir = "./resources/automatonVariablesCompactor/benchmark/";

	/*
	 Caso 01	  
	 	Nombre: LDC1_1
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas1.txt
	 	Cláusulas: 1
		Cantidad de operadores modales (F y G): 23
		Cantidad de signos = : 92
		Tamaño del automata: 39.1 Kb
		Tamaño de la fórmula LTL: 4.1 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso01() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_01_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_01_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 01 Compacted	  
	 	Nombre: LDC1_1
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas1.txt
	 	Cláusulas: 1
		Cantidad de operadores modales (F y G): 23
		Cantidad de signos = : 92
		Tamaño del automata: 13 Kb
		Tamaño de la fórmula LTL: 1.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso01Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_01_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_01_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 02	  
	 	Nombre: LDC1_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas5.txt
	 	Cláusulas: 5
		Cantidad de operadores modales (F y G): 95
		Cantidad de signos = : 190
		Tamaño del automata: 84.9 Kb
		Tamaño de la fórmula LTL: 9.6 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso02() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_02_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_02_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 02 Compacted  
	 	Nombre: LDC1_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas5.txt
	 	Cláusulas: 5
		Cantidad de operadores modales (F y G): 95
		Cantidad de signos = : 190
		Tamaño del automata:  27.5 Kb
		Tamaño de la fórmula LTL: 3.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso02Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_02_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_02_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 03	  
	 	Nombre: LDC2_1
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas1.txt
	 	Cláusulas: 1
		Cantidad de operadores modales (F y G): 23
		Cantidad de signos = : 92
		Tamaño del automata: 39.2 Kb
		Tamaño de la fórmula LTL: 4.1 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso03() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_03_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_03_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 03 Compacted	  
	 	Nombre: LDC2_1
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas1.txt
	 	Cláusulas: 1
		Cantidad de operadores modales (F y G): 23
		Cantidad de signos = : 92
		Tamaño del automata: 13 Kb
		Tamaño de la fórmula LTL: 1.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso03Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_03_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_03_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}	
	

	/*
	 Caso 04	  
	 	Nombre: LDC2_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas5.txt
	 	Cláusulas: 5
		Cantidad de operadores modales (F y G): 95
		Cantidad de signos = : 190
		Tamaño del automata: 84.9 Kb
		Tamaño de la fórmula LTL: 9.6 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso04() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_04_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_04_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 04 Compacted
	 	Nombre: LDC2_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas5.txt
	 	Cláusulas: 5
		Cantidad de operadores modales (F y G): 95
		Cantidad de signos = : 190
		Tamaño del automata: 27.5 Kb
		Tamaño de la fórmula LTL: 3.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso04Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_04_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_04_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 05	  
	 	Nombre: LDC2_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas25.txt
	 	Cláusulas: 25
		Cantidad de operadores modales (F y G): 557
		Cantidad de signos = : 1254
		Tamaño del automata: 438.8 Kb
		Tamaño de la fórmula LTL: 61.8 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso05() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_05_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_05_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 05 Compacted
	 	Nombre: LDC2_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas25.txt
	 	Cláusulas: 25
		Cantidad de operadores modales (F y G): 557
		Cantidad de signos = : 1254
		Tamaño del automata:  163.2 Kb
		Tamaño de la fórmula LTL: 25.9 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso05Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_05_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_05_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 06	  
	 	Nombre: LDC3_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio3NroDeClausulas25.txt
	 	Cláusulas: 25
		Cantidad de operadores modales (F y G): 348
		Cantidad de signos = : 779
		Tamaño del automata: 1143 Kb
		Tamaño de la fórmula LTL: 47 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso06() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_06_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_06_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 06 Compacted	  
	 	Nombre: LDC3_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio3NroDeClausulas25.txt
	 	Cláusulas: 25
		Cantidad de operadores modales (F y G): 348
		Cantidad de signos = : 779
		Tamaño del automata:  Kb
		Tamaño de la fórmula LTL:  Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso06Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_06_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_06_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}	
	
	/*
	 Caso 07	  
	 	Nombre: LDC1_25’
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio1NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata: 254.6 Kb
		Tamaño de la fórmula LTL: 61.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso07() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_07_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_07_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 07 Compacted
	 	Nombre: LDC1_25’
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio1NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata:  Kb
		Tamaño de la fórmula LTL:  Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso07Compacted() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_07_nusmvCommands_Compacted.nusmv";
		String nusmvAutomataPath = dir + "caso_07_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	private static void execNusmv(String nusmvCommandsPath, String nusmvAutomataPath) throws IOException, InterruptedException {
		
		String command = "/home/debianadmin/Facultad/Tesis/NuSMV-2.6.0.modificado/NuSMV/build/bin/./NuSMV -df -dynamic -source " 
										+ nusmvCommandsPath + " " + nusmvAutomataPath;
		
		System.out.println("Comienza nusmv. Comando a ejecutar:  " + command);
		System.out.println(LocalDateTime.now() + " Corriendo...");
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
		    	System.out.println("Error al correr nusmv.");
		        System.out.println(sb.toString());
		        child.destroy();
		        throw new RuntimeException("Se abortó la ejecución de nusmv. Revisar archivo generado.");
		    }else{// si es un warning => logueo y sigo
		    	System.out.println(sb.toString());
		    }
		}

		child.waitFor();

		long fin = System.currentTimeMillis();
		long seg = (fin - ini);
		System.out.println(LocalDateTime.now() + " Demoró " + seg + " ms");
	}

}
