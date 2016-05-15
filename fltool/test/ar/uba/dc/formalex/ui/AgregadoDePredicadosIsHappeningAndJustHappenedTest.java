package ar.uba.dc.formalex.ui;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

/**
 * Con este test se quiere comprobar que las formulas que contienen estos nuevos predicados 
 * son validas para el Model Checker
 * @author cfaciano
 *
 */
public class AgregadoDePredicadosIsHappeningAndJustHappenedTest extends FlTest {

	@Test
	public void testEjTerminalWithIsHappeningWithIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifier.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithIsHappeningWithIdentifierDotIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithIsHappeningWithIdentifierDotIdentifier.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifier.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjTerminalWithJustHappenedWithIdentifierDotIdentifier() throws Exception  {
		Formalex.run(FlTest.ROOT_EJS_PARSER_CLAUSES + "EjTerminalWithJustHappenedWithIdentifierDotIdentifier.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}

}
