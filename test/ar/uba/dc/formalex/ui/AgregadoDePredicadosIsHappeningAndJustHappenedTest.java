package ar.uba.dc.formalex.ui;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

/**
 * Con este test se quiere comprobar que las formulas que contienen estos nuevos predicados 
 * son validas para el Model Checker
 * @author cfaciano
 *
 */
public class AgregadoDePredicadosIsHappeningAndJustHappenedTest extends FlTest {

	@Test
	public void testEjTerminalWithIsHappeningWithIdentifier() throws Exception  {
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifierDotIdentifier() throws Exception  {
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifierDotIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifier() throws Exception  {
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifier.txt", true);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifierDotIdentifier() throws Exception  {
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifierDotIdentifier.txt", true);
	}

}
