package ar.uba.dc.formalex.ui;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

public class TestMain extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	
	@Test
	public void testAcldc() throws Exception  {		
		
		Formalex.run(ROOT_RESOURCES + "ACLDC.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testAcldcFullConFormulasConAccionesConOutputValuesModificadas() throws Exception  {
		
		Formalex.run(ROOT_EJS_FILTROS + "AcldcFullConFormulasConAccionesConOutputValuesModificadas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testAcldcFullSinFormulasConAccionesConOutputValues() throws Exception  {
		
		Formalex.run(ROOT_EJS_FILTROS + "AcldcFullSinFormulasConAccionesConOutputValues.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
		
	@Test
	public void testRompeNusmvPorqueFormulaMayorTo65Kb() throws Exception  {
		
		Formalex.run(ROOT_EJS_FILTROS + "EjConAccionesConOutputValuesQueRompeNusmv.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploActionConOutputValuesConRolesReferenciados() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjActionConOutputValuesConRolesReferenciadosEnRegla.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploActionConOutputValuesSinRolesReferenciados() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjActionConOutputValuesSinRolesReferenciadosEnRegla.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploConstruccionDeGrafo() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjConstruccionDeGrafo.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentes() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjGeneracionDeAgentes.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	/**** Estos dos tests los hice para saber si variaba la cantidad de agentes 
	 * generados, segun la cantidad de lineas en las que se divida la definicion
	 * de los roles. La realidad es que no varía. genera en ambos casos
	 * 16 agentes
	 */
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnUnaLinea() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjCuatroRolesEnUnaLinea.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnDosLineas() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "EjCuatroRolesEnDosLineas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploCombinacionRolesTesisChicas() throws Exception  {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		Formalex.run(ROOT_RESOURCES + "EjCombinacionRolesTesisChicas.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploMaximaCombinacionRolesSinDisjoint() throws Exception  {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		Formalex.run(ROOT_RESOURCES + "EjMaximaCombinacionDeRolesSinDisjoint.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploBelongsToTesisChicas() throws Exception  {
		// Lo hice para ver el seguimiento de belongsTo
		Formalex.run(ROOT_RESOURCES + "EjBelongsTo.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploPermisoPermanente() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "permisoPermanente.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjCounterWithImpersonalActions() throws Exception  {
		//rompe xq tiene un contador con todas las acciones impersonales!
		Formalex.run(ROOT_RESOURCES + "EjCounterWithImpersonalActions.txt", true, true, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	@Ignore("Hasta que se arregle el tema del synchronize")
	public void testEjemploSynchronize() throws Exception  {
		
		Formalex.run(ROOT_RESOURCES + "ejSynchronize.txt", false, true, true, LTLTranslationType.WITH_JH);
	}
	
	
	/********************* EJEMPLOS DE SIN JH ********************************/
	@Test
//	********   WARNING   ********
//	The initial states set of the finite state machine is empty.
//	This might make results of model checking not trustable.
//	******** END WARNING ********
	public void testResultsInConAndTiraWarning()
			throws Exception {

		Formalex.run(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjResultsInConAndTiraWarning.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				false, LTLTranslationType.WITH_JH);
	}
	
	@Test
	// formulas + 1 permiso permanente
	public void testEjemploVipMemberPass1F1PP() throws Exception {
		// Este ejemplo tiene un PP con excepcion
		Formalex.run(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjVipMemberPass1F1PP.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}
	
	@Test
	// formulas + 1 permiso permanente
	//Simplificado los roles
	public void testEjemploVipMemberPass1F1PPSimplificado() throws Exception {
		// Este ejemplo tiene un PP con excepcion
		Formalex.run(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjVipMemberPass1F1PPSimplificado.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//Tiene JH = 0, 23/116
	// sin JH : 339 segundos, 10 corridas
	//con JH : 444, 10 corridas
	public void testCasoDeEstudio3NroDeClausulas4() throws Exception {

		for(int i= 0;i<10;i++){
		Formalex.run(FlTest.ROOT_RESOURCES + "EjemplosSinJH/"
				+ "EjCasoDeEstudio3NroDeClausulas4WithIsHappening.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, true,
				LTLTranslationType.WITH_JH);
		}
	}
	
	@Test
	//7 formulas + 6 permisos
	//sin jh 1155 10 veces
	//con jh 1441 10 veces
	public void testCasoDeEstudioVipMemberPass() throws Exception {
		// Este ejemplo tiene un PP con excepcion

		for(int i= 0;i<10;i++){
			Formalex.run(FlTest.ROOT_CDE_FILTRADO
					+ "casoDeEstudioVipMemberPass.txt",
					FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
					LTLTranslationType.WITH_JH);
		}
	}

	@Test
	//OJO QUE ESTE NO TIENE LOS IS HAPPENING Y NO ES PARTE DE NINGUN CASO DE ESTUDIO
	//PORQUE TARDA BASTANTE
	//CON JH 6621 SEGUNDOS
	//SIN JH 
	public void testCasoDeEstudioVipMemberPassFull() throws Exception {
		// Este ejemplo tiene dos PP con excepcion
		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "casoDeEstudioVipMemberPassFull.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

}
