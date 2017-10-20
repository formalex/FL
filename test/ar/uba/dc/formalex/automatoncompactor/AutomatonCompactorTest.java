package ar.uba.dc.formalex.automatoncompactor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;

public class AutomatonCompactorTest {
	
	private String dir = "./resources/automatonVariablesCompactor";

	@Test 
	public void anAutomatonReplacementsGeneratorMustGenerateVariablesAndStateReplacementsAsExpected() throws IOException {
		
		String automatonName = "automaton1";
		
		BackgroundTheory bt = new BackgroundTheory();
		Action action1 = new Action();
		action1.setName("empieza_censo");
		bt.add(action1);
		
		AutomatonReplacementsGenerator automatonReplacementsGenerator = new AutomatonReplacementsGenerator(bt, dir, automatonName);
		Map<String, String> replacements = automatonReplacementsGenerator.createVariableAndStatesReplacements();
		
		assertEquals("NH", replacements.get("NOT_HAPPENING"));
		assertEquals("i0", replacements.get("empieza_censo"));
		
		File expectedReplacementFile = new File(dir + "/" +"expectedReplacement1");;
		File resultReplacementFile = automatonReplacementsGenerator.getReplacementFile();
		assertAllFilesLinesEquals(expectedReplacementFile, resultReplacementFile);
		
	}
	
	@Test
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
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);
		
		automatonCompactor.compact();
		
		assertTrue(automatonCompactor.isAlreadyCompacted());
		
		File expectedAutomaton = new File(dir + "/" +"expectedAutomaton1.nusmv");
		File compactedAutomatonFile = automatonCompactor.getCompactedAutomatonFile();

		assertAllFilesLinesEquals(expectedAutomaton, compactedAutomatonFile);

	}
	
	@Test
	public void test02() throws IOException {
		
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
		
		AutomatonCompactor automatonCompactor = new AutomatonCompactor(dir, automatonName, automatonExtension, replacements);

		long init = System.currentTimeMillis();
		
		automatonCompactor.compact();
		
		long end = System.currentTimeMillis();
		long elapsed = end - init;
		System.out.println("elapsed time in miliseconds:" + elapsed);
		
		assertTrue(elapsed < 6000);
	}
	
	
	private void assertAllFilesLinesEquals(File expectedFile, File resultFile) throws IOException {
//		List<String> expectedLines = FileUtils.readLines(expectedFile, "default");
//		List<String> automatonCompactedLines = FileUtils.readLines(resultFile, "default");
//		
//		for (int i = 0; i < expectedLines.size(); i++) {
//			assertEquals(expectedLines.get(i), automatonCompactedLines.get(i));
//		}
		BufferedReader expectedBufferedReader = Files.newBufferedReader(expectedFile.toPath());
		BufferedReader resultBufferedReader = Files.newBufferedReader(resultFile.toPath());
		String expectedLine = null;
		String resultLine = null;
		while ((expectedLine = expectedBufferedReader.readLine()) != null) {
			resultLine = resultBufferedReader.readLine();
			assertEquals(expectedLine, resultLine);
		}
	}

}
