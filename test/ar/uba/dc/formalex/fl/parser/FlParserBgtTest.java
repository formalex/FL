package ar.uba.dc.formalex.fl.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;
import ar.uba.dc.formalex.parser.ParseException;

public class FlParserBgtTest extends FlTest{

	private static String ROOT_EJS_ACTIONS_PRODUCTIONS = ROOT_RESOURCES + "EjemplosParserActionsProduction/";
	private static String ROOT_EJS_ACTION_PRODUCTIONS = ROOT_RESOURCES + "EjemplosParserActionProduction/";
	
//	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	

	@Test
	public void testEjActionsProductionWithOneAction() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneAction.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActions() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActions.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursIn.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursIn.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrences.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrences.txt", false, CORRER_SIN_FILTRADO, true);
	}

	@Test
	public void testEjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithOneActionAndOnlyOccursInAndOccurrencesAndOnePerformable.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTIONS_PRODUCTIONS + "EjActionsProductionWithSomeActionsAndOnlyOccursInAndOccurrencesAndSomePerformable.txt", false, CORRER_SIN_FILTRADO, true);
	}
	

	/*************************** EJS ACTION PRODUCTIONS  *********************/
	@Test
	public void testEjActionProductionWithIdentifier() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifier.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValues() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValues.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOnlyOccursIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOnlyOccursIn.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumber() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumber.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	@Ignore("Hasta que tome el parche del synchronize")
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithSynchro() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithSynchro.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithOnlyPerformable() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithIdentifierWithOutputValuesWithOcurrencesNumberWithOnlyPerformable.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjImpersonalActionLikeTriggerInLocalInterval() throws Exception {
		// SE PUEDE USAR UNA IMPERSONAL ACTION COMO TRIGGER DE UN INTERVALO LOCAL!
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjImpersonalActionLikeTriggerInLocalInterval.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	@Test
	public void testEjActionProductionWithImpersonalActionWithOnlyOccursInLocalIntervalFail() throws Exception {
		//NO se puede crear una impersonal Action con 'only occurs in scope' de un intervalo LOCAL. 
		//se metió un parche para que NO rompa asquerosamente con un null pointer exception
		TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithImpersonalActionWithOnlyOccursInLocalIntervalFail.txt", false, CORRER_SIN_FILTRADO, true);
	}
	
	//TODO Agregar tests que rompan cuando procesa un action
	
	@Test
	public void testEjActionProductionWithOutputValuesWithZeroValueFail()  throws Exception {
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithOutputValuesWithZeroValueFail.txt", false, CORRER_SIN_FILTRADO, true);
		} catch (ParseException pe) {
			
			//TODO ver si puedo poner en el assert algo mas descriptivo sobre el error
			assertTrue(pe.getMessage().startsWith("Encountered "));
		}
	} 
	
	
	@Test
	public void testEjActionProductionWithOutputValuesWithOnlyOneValueFail()  throws Exception {
		try {
			TestUtils.corridaDeFormaLex(ROOT_EJS_ACTION_PRODUCTIONS + "EjActionProductionWithOutputValuesWithOnlyOneValueFail.txt", false, CORRER_SIN_FILTRADO, true);
			fail();
		} catch (RuntimeException re) {
			assertTrue(re.getMessage().endsWith("con output values tiene definido un unico valor de salida"));
		}
	} 
	
	
}
