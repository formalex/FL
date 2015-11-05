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
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithDotBelongsToIdentifier() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotBelongsToIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithIdentifierDotIdentifierWithResultsIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierDotIdentifierWithResultsIn.txt", true);
	}
	
	@Test
	public void testEjTerminalWithIdentifierWithResultsIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIdentifierWithResultsIn.txt", true);
	}
	
	//TODO Agregar tests para Inside
	
	//TODO Agregar tests que rompan cuando procesa terminal
	//TODO Ver si en el assert puedo meter una regular Expression para saltear loas acentos escapeados x jj
	/******************* TESTS QUE ROMPEN AL PARSEAR UN TERMINAL *************/
	@Test
	public void testEjTerminalWithUndefinedRoleFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedRoleFail.txt", true);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().contains("rmula se hace referencia a un rol no definido:"));
			//re.printStackTrace();
		}
		
	}
	
	@Test
	public void testEjTerminalWithUndefinedActionFail(){
		
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithUndefinedActionFail.txt", true);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().contains("rmula se hace referencia a una acci"));
			
		}
		
	}
	
}
