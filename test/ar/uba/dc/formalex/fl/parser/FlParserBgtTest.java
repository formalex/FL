package ar.uba.dc.formalex.fl.parser;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class FlParserBgtTest extends FlTest{

	private static String ROOT_EJS_ACTIONS_PRODUCTIONS = ROOT_RESOURCES + "EjemplosParserActionsProduction/";
	private static String ROOT_EJS_ACTION_PRODUCTIONS = ROOT_RESOURCES + "EjemplosParserActionProduction/";
	
//	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	

	@Test
	public void testEjActionsProductionWithOneAction() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneAction.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActions() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActions.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursIn.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursIn.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences.txt", false, true);
	}

	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable.txt", false, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable.txt", false, true);
	}
	

	/*************************** EJS ACTION PRODUCTIONS 
	 * @throws Exception *********************/
	@Test
	public void testEjActionProductionWithIdentifier() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifier.txt", false, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValues() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValues.txt", false, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOnlyOccursIn() throws Exception {
		//TODO ver si puedo crear una impersonal Action con 'only occurs in scope' de un intervalo LOCAL. NOOO
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOnlyOccursIn.txt", false, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumber() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumber.txt", false, true);
	}
	
	@Test
	@Ignore("Hasta que tome el parche del synchronize")
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithSynchro() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithSynchro.txt", false, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithOnlyPerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithOnlyPerformable.txt", false, true);
	}
	
	

	//TODO Agregar tests que rompan cuando procesa un action
}
