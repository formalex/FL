package ar.uba.dc.formalex.fl.parser;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class FlParserTest extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	private static String ROOT_EJS_ACTIONS_PRODUCTIONS = ROOT_RESOURCES + "EjemplosParserActionsProduction/";
//	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	

	@Test
	public void testEjActionsProductionWithOneAction() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneAction.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActions() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActions.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursIn.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursIn() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursIn.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences.txt", false);
	}

	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable.txt", false);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable() {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable.txt", false);
	}
}
