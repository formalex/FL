package ar.uba.dc.formalex.automatoncompactor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.bgtheory.Interval;

public class AutomatonCompactorTest {
	
	private String dir = "./resources/automatonVariablesCompactor";

	@Test
	// Test que verifica que a partir de una background theory con una accion se genera correctamente un archivo de reemplazos de variables. 
	public void anAutomatonReplacementsGeneratorMustGenerateVariablesAndStateReplacementsAsExpected() throws IOException {
		
		String replacementFileName = "automaton1Replacements";
		File resultReplacementFile = new File(dir + "/" + replacementFileName);
		Files.deleteIfExists(resultReplacementFile.toPath());
		
		BackgroundTheory bt = new BackgroundTheory();
		Action action1 = new Action();
		action1.setName("empieza_censo");
		bt.add(action1);
		
		AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(bt, dir, replacementFileName);
		Map<String, String> replacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
		
		assertEquals("NH", replacements.get("NOT_HAPPENING"));
		assertEquals("i0", replacements.get("empieza_censo"));
		
		File expectedReplacementFile = new File(dir + "/" +"expectedReplacement1");
		assertAllFilesLinesEquals(expectedReplacementFile, resultReplacementFile);
		
	}
	
	@Test
	// Test que verifica que a partir de un archivo de automata y un map de reemplazos se realizan correctamente la compactacion del automata.
	public void anAutomatonCompactorShouldReplaceVariablesAndStatesAsExpected() throws IOException {

		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "A");
		    put("INACTIVE", "I");
		    put("agent_1.censarse","i2");
		    put("empieza_censo","i1");
		    put("termina_censo","i0");
		    put("en_censo","i3");
		}};		
		
		String automatonName = "automaton1";
		String automatonExtension = ".nusmv";
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted" + automatonExtension);
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		boolean removeComments = false;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		File expectedAutomaton = new File(dir + "/" +"expectedAutomaton1.nusmv");

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);

	}
	
	@Test
	// Test que verifica que la compactacion de un archivo de 100 MB demora menos de 10000 milisegundos
	public void aBigFileAutomatonVariablesAndStatesReplacementShouldNotTakeMoreThan10000Milliseconds() throws IOException {
		
		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "A");
		    put("INACTIVE", "I");
		    put("agent_1.censarse","i2");
		    put("empieza_censo","i1");
		    put("termina_censo","i0");
		    put("en_censo","i3");
		}};		
		
		String automatonName = "automaton2";
		String automatonExtension = ".nusmv";
		boolean removeComments = false;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);

		long init = System.currentTimeMillis();
		
		automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		long end = System.currentTimeMillis();
		long elapsed = end - init;
		System.out.println("elapsed time in miliseconds:" + elapsed);
		
		assertTrue(elapsed < 100000);
	}
	
	@Test
	// Test que verifica que se eliminan comentarios y lineas vacias de un archivo de automata
	public void anAutomatonCompactorShouldRemoveCommentsAsExpected() throws IOException {
		
		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "A");
		    put("INACTIVE", "I");
		    put("agent_1.censarse","i2");
		    put("empieza_censo","i1");
		    put("termina_censo","i0");
		    put("en_censo","i3");
		}};		
		
		String automatonName = "automatonWithComments1";
		String automatonExtension = ".nusmv";
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted" + automatonExtension);
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		boolean removeComments = true;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		File expectedAutomaton = new File(dir + "/" +"expectedWithoutComments1.nusmv");

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);
		
	}

	@Test
	// Test que verifica que se eliminan comentarios en medio de una linea y lineas vacias de un archivo de automata
	public void anAutomatonCompactorShouldRemoveCommentsInTheMiddleOfALineAsExpected() throws IOException {
		
		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "A");
		    put("INACTIVE", "I");
		    put("agent_1.censarse","i2");
		    put("empieza_censo","i1");
		    put("termina_censo","i0");
		    put("en_censo","i3");
		}};		
		
		String automatonName = "automatonWithComments2";
		String automatonExtension = ".nusmv";
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted" + automatonExtension);
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		boolean removeComments = true;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		File expectedAutomaton = new File(dir + "/" +"expectedWithoutComments2.nusmv");

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);
		
	}

	
	@Test
	// Test que verifica que a partir de una background theory con un acciones e intervalos y un archivo de automata
	// se realiza correctamente la eliminacion de cometarios, la generacion de archivo de reemplazos y la compactacion del automata.
	public void anAutomatonCompactorShouldRemoveCommentsReplaceVariablesAndStatesAsExpected() throws IOException {

		String automatonName = "automaton3";
		String automatonExtension = ".nusmv";
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted" + automatonExtension);
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		boolean removeComments = true;

		BackgroundTheory bt = new BackgroundTheory();

		Set<String> actionNames = new HashSet<>(Arrays.asList("agent_without_role.elegir_ganadores", 
				"agent_1.entrar","agent_without_role.postularse","comienza_semana","agent_1.elegir_ganadores","agent_1.elegir_ganadores_OUTPUT","agent_1.abrir_concurso", 
				"agent_1.postularse", "agent_without_role.entrar","termina_semana","agent_without_role.abrir_concurso"));
		Set<String> intervalNames = new HashSet<>(Arrays.asList("semana","concurso"));
		
		for (String actionName : actionNames) {
			Action action = new Action();
			action.setName(actionName);
			bt.add(action);
		} 

		for (String intervalName : intervalNames) {
			Interval interval = new Interval();
			interval.setName(intervalName);
			bt.add(interval);
		} 
		
		AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(bt, dir, automatonName);
		Map<String, String> replacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		File expectedAutomaton = new File(dir + "/" +"expectedAutomaton3.nusmv");

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);

	}

	@Test
	// Test que verifica para automatas con mas de 10 variables se hacen correctamente los reemplazos
	public void anAutomatonCompactorWithMoreThan10VariablesShouldReplaceVariablesAndStatesAsExpected() throws IOException {

		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "AC");
		    put("INACTIVE", "IA");
		    put("agent_1.censarse","i0");
		    put("empieza_1_censo","i1");
		    put("termina_1_censo","i2");
		    put("en_1_censo","i3");

		    put("agent_2.censarse","i4");
		    put("empieza_2_censo","i5");
		    put("termina_2_censo","i6");
		    put("en_2_censo","i7");
		    
		    put("agent_3.censarse","i8");
		    put("empieza_3_censo","i9");
		    put("termina_3_censo","i10");
		    put("en_3_censo","i11");

		}};		
		
		String automatonName = "automaton4";
		String automatonExtension = ".nusmv";
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted");
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		boolean removeComments = false;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		File expectedAutomaton = new File(dir + "/" +"expectedAutomaton4.nusmv");

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);

	}
	
	@Test	
	public void aFileCompactedAndDecompactedShoudBeEqualsOriginal() throws IOException {

		Map<String, String> replacements = new HashMap<String, String>() {{
		    put("NOT_HAPPENING", "NH");
		    put("HAPPENING", "H");
		    put("JUST_HAPPENED", "JH");
		    put("ACTIVE", "AC");
		    put("INACTIVE", "IA");
		    put("agent_1.censarse","i0");
		    put("empieza_1_censo","i1");
		    put("termina_1_censo","i2");
		    put("en_1_censo","i3");

		    put("agent_2.censarse","i4");
		    put("empieza_2_censo","i5");
		    put("termina_2_censo","i6");
		    put("en_2_censo","i7");
		    
		    put("agent_3.censarse","i8");
		    put("empieza_3_censo","i9");
		    put("termina_3_censo","i10");
		    put("en_3_censo","i11");

		}};		
		
		String automatonName = "automaton4";
		String automatonExtension = ".nusmv";
		File originalAutomatonFile = new File(dir + "/" + automatonName + automatonExtension);
		
		File compactedAutomatonFile = new File(dir + "/" + automatonName + "Compacted" + automatonExtension);
		Files.deleteIfExists(compactedAutomatonFile.toPath());
		
		String decompactedFileName = automatonName + "Decompacted" + automatonExtension;
		File decompactedFile = new File(dir + "/" + decompactedFileName);
		Files.deleteIfExists(decompactedFile.toPath());
		
		boolean removeComments = false;
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		compactedAutomatonFile = automatonCompactor.compact(removeComments, automatonName + "Compacted");
		
		AutomatonCompactor automatonDecompactor = new AutomatonCompactor(dir, automatonName + "Compacted", automatonExtension, replacements);
		
		decompactedFile = automatonDecompactor.decompact(decompactedFileName);
		
		assertAllFilesLinesEquals(originalAutomatonFile, decompactedFile);
	}
	
	@Test
	public void anAutomatonCompactorShouldDecompactNusmvOutAsExpected() throws IOException {
		String replacementsFileName = "replacements";
		String nusmvOutReducedName = "nusmvOutReduced";
		String nusmvOutReducedExtension = ".nusmv";
		String nusmvOutFileName = "nusmvOutReducedDecompacted";
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, nusmvOutReducedName, nusmvOutReducedExtension, replacementsFileName);
		File nusmvOut = null;
		File nusmvOutExpected = new File(dir + "/nusmvOutExpected.nusmv");
		
		nusmvOut = automatonCompactor.decompact(nusmvOutFileName);
		
		assertAllFilesLinesEquals(nusmvOutExpected, nusmvOut);
		
	}
	
	@Test
	public void test01() throws IOException {
		String replacementsFileName = "replacements";
		String nusmvOutReducedName = "nusmvOutExpected";
		String nusmvOutReducedExtension = ".nusmv";
		String nusmvOutFileName = "nusmvOutReducedDecompacted";
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, nusmvOutReducedName, nusmvOutReducedExtension, replacementsFileName);
		File nusmvOut = null;
		
		nusmvOut = automatonCompactor.compact(true, nusmvOutFileName);
		
		File nusmvOutExpected = new File(dir + "/nusmvOutExpected.nusmv");
		
	}
	
	// Funcion auxiliar que verifica si dos archivos tienen el mismo contenido linea a linea.
	private void assertAllFilesLinesEquals(File expectedFile, File resultFile) throws IOException {
		BufferedReader expectedBufferedReader = Files.newBufferedReader(expectedFile.toPath());
		BufferedReader resultBufferedReader = Files.newBufferedReader(resultFile.toPath());
		String expectedLine = null;
		String resultLine = null;
		while ((expectedLine = expectedBufferedReader.readLine()) != null) {
			resultLine = resultBufferedReader.readLine();
			assertEquals(expectedLine.trim(), resultLine.trim());
		}
	}

}
