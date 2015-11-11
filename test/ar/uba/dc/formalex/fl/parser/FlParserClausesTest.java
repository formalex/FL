package ar.uba.dc.formalex.fl.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class FlParserClausesTest extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	private static String ROOT_EJS_PARSER_CLAUSES = ROOT_RESOURCES + "EjemplosParserClauses/";
//	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	

	@Test
	public void testEjTerminalWithIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithDotBelongsToIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotBelongsToIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifierWithResultsIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifierWithResultsIn.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithResultsIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierWithResultsIn.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifierDotIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifierDotIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithJustsHappenedgWithIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustsHappenedgWithIdentifier.txt", false);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifierDotIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifierDotIdentifier.txt", false);
	}
	
	//TODO Agregar tests para Inside	

	/******************* TESTS QUE ROMPEN AL PARSEAR UN TERMINAL *************/
	//TODO AGREGAR TESTS QUE ROMPAN EL ISHAPPENING Y EL JUSTHAPPENED
	@Test
	public void testEjTerminalWithUndefinedRoleFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedRoleFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un rol no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionReferredToResultsInFail(){
 
		try {
			//System.out.println("\u00e1\u00e9\u00ed\u00f3\u00fa");
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionReferredToResultsInFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));
			
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedCounterFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedCounterFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithLocalCounterWithoutAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithLocalCounterWithoutAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador local sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithGlobalCounterWithAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithGlobalCounterWithAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un contador global usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionFail(){
 
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithPersonalActionWithoutAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithPersonalActionWithoutAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithImpersonalActionWithAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithImpersonalActionWithAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithoutIntervalFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithoutIntervalFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo no definido: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithLocalIntervalWithoutAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithLocalIntervalWithoutAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo local sin usar variable: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithInsideWithGlobalIntervalWithAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithInsideWithGlobalIntervalWithAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un intervalo global usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithUndefinedActionFail(){
 
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithUndefinedActionFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithUndefinedActionFail(){
 
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithUndefinedActionFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));			
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithPersonalActionWithoutAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithPersonalActionWithoutAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithPersonalActionWithoutAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithPersonalActionWithoutAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción sin usar agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithImpersonalActionWithAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithImpersonalActionWithAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithImpersonalActionWithAgentFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithImpersonalActionWithAgentFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción impersonal usando agente: ("));
		}
		
	}
	

	
	private String unescape(String s) {
	    int i=0, len=s.length();
	    char c;
	    StringBuffer sb = new StringBuffer(len);
	    while (i < len) {
	        c = s.charAt(i++);
	        if (c == '\\') {
	            if (i < len) {
	                c = s.charAt(i++);
	                if (c == 'u') {
	                    // TODO: check that 4 more chars exist and are all hex digits
	                    c = (char) Integer.parseInt(s.substring(i, i+4), 16);
	                    i += 4;
	                } // add other cases here as desired...
	            }
	        } // fall through: \ escapes itself, quotes any character but u
	        sb.append(c);
	    }
	    return sb.toString();
	}
	

	
}
