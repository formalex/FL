package ar.uba.dc.formalex.fl.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

public class FlParserClausesTest extends FlTest{

	@Test
	public void characterEncoding(){
		//System.setProperty("file.encoding", "UTF-16");
	 System.out.println(System.getProperty("file.encoding"));
	}
	
	@Test
	public void testEjTerminalWithIdentifier() throws Exception {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithDotBelongsToIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotBelongsToIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifierWithResultsIn() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifierWithResultsIn.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithResultsIn() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierWithResultsIn.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifierDotIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifierDotIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifierDotIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifierDotIdentifier.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	//TODO Agregar tests para Inside	

	/******************* TESTS QUE ROMPEN AL PARSEAR UN TERMINAL *************/
	@Test
	public void testEjTerminalWithUndefinedRoleFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedRoleFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un rol no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionReferredToResultsInFail() throws Exception {
 
		try {
			//System.out.println("\u00e1\u00e9\u00ed\u00f3\u00fa");
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionReferredToResultsInFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));
			
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedCounterFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedCounterFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithLocalCounterWithoutAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithLocalCounterWithoutAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador local sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithGlobalCounterWithAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithGlobalCounterWithAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador global usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionFail() throws Exception {
 
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithPersonalActionWithoutAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithPersonalActionWithoutAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithImpersonalActionWithAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithImpersonalActionWithAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithoutIntervalFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithoutIntervalFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithLocalIntervalWithoutAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithLocalIntervalWithoutAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo local sin usar variable: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithGlobalIntervalWithAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithGlobalIntervalWithAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo global usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithUndefinedActionFail() throws Exception {
 
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithUndefinedActionFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithUndefinedActionFail() throws Exception {
 
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithUndefinedActionFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithPersonalActionWithoutAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithPersonalActionWithoutAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithPersonalActionWithoutAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithPersonalActionWithoutAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithImpersonalActionWithAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithImpersonalActionWithAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithImpersonalActionWithAgentFail() throws Exception {
		
		try {
			Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithImpersonalActionWithAgentFail.txt", false, CORRER_SIN_FILTRADO, true, LTLTranslationType.WITH_JH);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
		
}
