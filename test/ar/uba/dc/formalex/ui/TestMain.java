package ar.uba.dc.formalex.ui;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class TestMain extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	
	@Test
	public void testAcldc() throws Exception  {		
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ACLDC.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testAcldcFullConFormulasConAccionesConOutputValuesModificadas() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullConFormulasConAccionesConOutputValuesModificadas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testAcldcFullSinFormulasConAccionesConOutputValues() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullSinFormulasConAccionesConOutputValues.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
		
	@Test
	public void testRompeNusmvPorqueFormulaMayorTo65Kb() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesConOutputValuesQueRompeNusmv.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploActionConOutputValuesConRolesReferenciados() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesConRolesReferenciadosEnRegla.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploActionConOutputValuesSinRolesReferenciados() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesSinRolesReferenciadosEnRegla.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	/**** Estos dos tests los hice para saber si variaba la cantidad de agentes 
	 * generados, segun la cantidad de lineas en las que se divida la definicion
	 * de los roles. La realidad es que no varía. genera en ambos casos
	 * 16 agentes
	 */
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnUnaLinea() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnUnaLinea.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploConstruccionDeGrafo() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjConstruccionDeGrafo.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentes() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjGeneracionDeAgentes.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnDosLineas() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnDosLineas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploCombinacionRolesTesisChicas() throws Exception  {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCombinacionRolesTesisChicas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploBelongsToTesisChicas() throws Exception  {
		// Lo hice para ver el seguimiento de belongsTo
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjBelongsTo.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploPermisoPermanente() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "permisoPermanente.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	@Ignore("Hasta que se arregle el tema del synchronize")
	public void testEjemploSynchronize() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ejSynchronize.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	// formulas + 1 permiso permanente
	public void testEjemploVipMemberPass1F1PP() throws Exception {
		// Este ejemplo tiene un PP con excepcion
		TestUtils.corridaDeFormaLex(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjVipMemberPass1F1PP.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}
	
	@Test
	// formulas + 1 permiso permanente
	//Simplificado los roles
	public void testEjemploVipMemberPass1F1PPSimplificado() throws Exception {
		// Este ejemplo tiene un PP con excepcion
		TestUtils.corridaDeFormaLex(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjVipMemberPass1F1PPSimplificado.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

}
