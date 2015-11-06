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
	
	//TODO Agregar tests para Inside	

	/******************* TESTS QUE ROMPEN AL PARSEAR UN TERMINAL *************/
	//TODO Agregar tests que rompan cuando procesa terminal
	@Test
	public void testEjTerminalWithUndefinedRoleFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedRoleFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a un rol no definido: ("));
			//re.printStackTrace();
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionFail(){
 
		try {
			//System.out.println("\u00e1\u00e9\u00ed\u00f3\u00fa");
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionFail.txt", false);
			fail();
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
			assertTrue(re.getMessage().startsWith("En una fórmula se hace referencia a una acción no definida: ("));
			
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
