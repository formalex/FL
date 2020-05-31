package ar.uba.dc.formalex.automatoncompactor;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class AutomatonCompactorBenchmarkTest {
	
	private static String NUSMV_EXE = "/home/debianadmin/Facultad/Tesis/NuSMV-2.6.0.modificado/NuSMV/build/bin/./NuSMV";
	private String dir = "./resources/automatonVariablesCompactor/benchmark/";

	@Before
	public void setup() throws IOException {		
		//"Si está corriendo NuSMV elimino el proceso"
		Runtime.getRuntime().exec("pkill NuSMV");
	}

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
//		String nusmvCommandsPath = dir + "caso_01_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_01_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_01_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 02	  
	 	Nombre: LDC1_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 457.7 Kb
		Tamaño de la fórmula LTL: 65.5 Kb
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
	 	Nombre: LDC1_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 174.7 Kb
		Tamaño de la fórmula LTL: 27.9 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso02Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_02_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_02_nusmvCommands_Compacted_gen.nusmv";
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
//		String nusmvCommandsPath = dir + "caso_03_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_03_nusmvCommands_Compacted_gen.nusmv";
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
//		String nusmvCommandsPath = dir + "caso_04_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_04_nusmvCommands_Compacted_gen.nusmv";
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
//		String nusmvCommandsPath = dir + "caso_05_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_05_nusmvCommands_Compacted_gen.nusmv";
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
		Tamaño del automata: 185.8 Kb
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
		Tamaño del automata: 79.4 Kb
		Tamaño de la fórmula LTL: 33.7 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso06Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_06_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_06_nusmvCommands_Compacted_gen.nusmv";
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
		Tamaño del automata: 107.9 Kb
		Tamaño de la fórmula LTL: 27.9 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso07Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_07_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_07_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_07_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 08	  
	 	Nombre: LDC1_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 193.3 Kb
		Tamaño de la fórmula LTL: 118.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso08() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_08_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_08_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 08 Compacted  
	 	Nombre: LDC1_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 85.4 Kb
		Tamaño de la fórmula LTL: 47.8 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso08Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_08_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_08_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_08_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 09	  
	 	Nombre: LDC2_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 175.3 Kb
		Tamaño de la fórmula LTL: 113.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso09() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_09_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_09_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 09 Compacted	  
	 	Nombre: LDC2_25
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas25.txt
	 	Cláusulas: 25
		Tamaño del automata: 73.3 Kb
		Tamaño de la fórmula LTL: 45.8 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso09Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_09_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_09_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_09_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 10	  
	 	Nombre: LDC1_25'	
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio1NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata: 193.6 Kb
		Tamaño de la fórmula LTL: 65.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso10() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_10_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_10_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 10 Compacted  
	 	Nombre: LDC1_25'
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio1NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata: 85.5 Kb
		Tamaño de la fórmula LTL: 29.5 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso10Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_10_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_10_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_10_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 11	  
	 	Nombre: LDC2_25'
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio2NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata: 175.6 Kb
		Tamaño de la fórmula LTL: 64.4 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso11() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_11_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_11_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 11 Compacted	  
	 	Nombre: LDC2_25'
		FL_INPUT = resources/CasosDeEstudioReductor/CasoDeEstudio2NroDeClausulas25WithIsHappening.txt
	 	Cláusulas: 25
		Tamaño del automata: 73.5 Kb
		Tamaño de la fórmula LTL: 28.3 Kb
		CON_FILTRO = true
		CON_REDUCTOR = true
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso11Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_11_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_11_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_11_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 12	  
	 	Nombre: LDC2_28
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas28.txt
	 	Cláusulas: 28
		Tamaño del automata: 179.5 Kb
		Tamaño de la fórmula LTL: 122.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso12() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_12_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_12_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 12 Compacted	  
	 	Nombre: LDC2_28
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas28.txt
	 	Cláusulas: 28
		Tamaño del automata: 74.4 Kb
		Tamaño de la fórmula LTL: 48.4 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso12Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_12_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_12_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_12_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 13	  
	 	Nombre: LDC3_32
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio3NroDeClausulas32.txt
	 	Cláusulas: 32
		Tamaño del automata: 105.8 Kb
		Tamaño de la fórmula LTL: 70.4 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso13() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_13_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_13_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 13 Compacted	  
	 	Nombre: LDC3_32
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio3NroDeClausulas32.txt
	 	Cláusulas: 32
		Tamaño del automata: 55.5 Kb
		Tamaño de la fórmula LTL: 41.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = true
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso13Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_13_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_13_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_13_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	/*
	 Caso 14	  
	 	Nombre: LDC1_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas5.txt
	 	Cláusulas: 
		Tamaño del automata: 84.8 Kb
		Tamaño de la fórmula LTL: 9.6 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso14() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_14_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_14_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 14 Compacted 
	 	Nombre: LDC1_5
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio1NroDeClausulas5.txt
	 	Cláusulas: 
		Tamaño del automata: 27.5 Kb
		Tamaño de la fórmula LTL: 3.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso14Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_14_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_14_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_14_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}

	
	/*
	 Caso 15	  
	 	Nombre: LDC2_15
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas15.txt
	 	Cláusulas: 
		Tamaño del automata: 270.7 Kb
		Tamaño de la fórmula LTL: 34.2 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = false
	 */
	@Test
	public void caso15() throws IOException, InterruptedException {
		String nusmvCommandsPath = dir + "caso_15_nusmvCommands.nusmv";
		String nusmvAutomataPath = dir + "caso_15_automata.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
	/*
	 Caso 15 Compacted 
	 	Nombre: LDC2_15
		FL_INPUT = resources/CasosDeEstudioFiltrado/CasoDeEstudio2NroDeClausulas15.txt
	 	Cláusulas: 
		Tamaño del automata: 96.2 Kb
		Tamaño de la fórmula LTL: 12.9 Kb
		CON_FILTRO = true
		CON_REDUCTOR = false
		SIN_JH = false
		NUSMV_COMPACTADO = true
	 */
	@Test
	public void caso15Compacted() throws IOException, InterruptedException {
//		String nusmvCommandsPath = dir + "caso_15_nusmvCommands_Compacted.nusmv";
		String nusmvCommandsPath = dir + "caso_15_nusmvCommands_Compacted_gen.nusmv";
		String nusmvAutomataPath = dir + "caso_15_automata_Compacted.nusmv";
		execNusmv(nusmvCommandsPath, nusmvAutomataPath);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////	
	
	private static void execNusmv(String nusmvCommandsPath, String nusmvAutomataPath) throws IOException, InterruptedException {
		
		System.out.println("Caso de test: " + obtenerMetodoYClaseQueLlama().getMethodName());
		String command = NUSMV_EXE + " -df -dynamic -source " 
										+ nusmvCommandsPath + " " + nusmvAutomataPath;
		
		System.out.println("Comienza NuSMV ejecutando el comando:  " + command);
		System.out.println(LocalDateTime.now() + " Corriendo...");
		long ini = System.currentTimeMillis();
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		long seg = (System.currentTimeMillis() - ini);
		System.out.println("El resultado de la ejecucion: " + process.exitValue());
		System.out.println(LocalDateTime.now() + " Demoró " + seg/1000 + " seg");
	}

    private static StackTraceElement obtenerMetodoYClaseQueLlama() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[3];
        return stackTraceElement;
    }

}
