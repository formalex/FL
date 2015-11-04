package ar.uba.dc.formalex.ui;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class TestMain extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	private static String ROOT_EJS_FILTROS = ROOT_RESOURCES + "EjemplosParaFiltrar/";
	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
		

	
	@Test
	public void testAcldc() {		
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ACLDC.txt", false);
	}
	
	@Test
	public void testAcldcFullConFormulasConAccionesConOutputValuesModificadas() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullConFormulasConAccionesConOutputValuesModificadas.txt", false);
	}
		
	@Test
	public void testAcldcFullSinFormulasConAccionesConOutputValues() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullSinFormulasConAccionesConOutputValues.txt", false);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas1() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas1.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas5() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas5.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas15() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas15.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas25.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas28() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas28.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas60() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas60.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas40() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas40.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas1() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas1.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas5() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas5.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas15() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas15.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas25() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas25.txt", true);
	}
		
	@Test
	public void testCasoDeEstudio2NroDeClausulas28() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas28.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas1() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas1.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas5() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas5.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas15() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas15.txt", true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas25.txt", true);
	}
	@Test
	public void testCasoDeEstudio3NroDeClausulas32() {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas32.txt", true);
	}
	
	
	@Test
	public void testCasoDeEstudioVipMemberPass() {
		//Este ejemplo tiene un PP con excepcion
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPass.txt", true);
	}
	
	@Test
	public void testCasoDeEstudioVipMemberPassFull() {
		//Este ejemplo tiene dos PP con excepcion
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPassFull.txt", true);
	}
	
	@Test
	public void testRompeNusmvPorqueFormulaMayorTo65Kb() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesConOutputValuesQueRompeNusmv.txt", true);
	}
	
	@Test
	public void testEjemploActionConOutputValuesConRolesReferenciados() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesConRolesReferenciadosEnRegla.txt", true);
	}
	
	@Test
	public void testEjemploActionConOutputValuesSinRolesReferenciados() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesSinRolesReferenciadosEnRegla.txt", true);
	}
	
	/**** Estos dos tests los hice para saber si variaba la cantidad de agentes 
	 * generados, segun la cantidad de lineas en las que se divida la definicion
	 * de los roles. La realidad es que no varía. genera en ambos casos
	 * 16 agentes
	 */
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnUnaLinea() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnUnaLinea.txt", false);
	}
	
	@Test
	public void testEjemploConstruccionDeGrafo() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjConstruccionDeGrafo.txt", false);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentes() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjGeneracionDeAgentes.txt", false);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnDosLineas() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnDosLineas.txt", false);
	}
	
	@Test
	public void testEjemploCombinacionRolesTesisChicas() {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCombinacionRolesTesisChicas.txt", false);
	}
	
	@Test
	public void testEjemploBelongsToTesisChicas() {
		// Lo hice para ver el seguimiento de belongsTo
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjBelongsTo.txt", true);
	}
	
	@Test
	public void testEjemploPermisoPermanente() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "permisoPermanente.txt", false);
	}
	
	@Test
	@Ignore("Hasta que se arregle el tema del synchronize")
	public void testEjemploSynchronize() {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ejSynchronize.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConIntervalOnlyOccursInScope() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosConOnlyOccursInScopeQueSeUsan.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConActionsOnlyOccursInScope() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConActionsOnlyOccursInScope.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConAccionesQueNoSeReferencian() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesQueNoSeReferencian.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueNoSeUsan() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosQueNoSeUsan.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueSeUsan() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConIntervalosQueSeUsan.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueNoSeUsan() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConCountersQueNoSeUsan.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueSeUsan() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConCountersQueSeUsan.txt", false);
	}
	
	@Test
	public void testEjemploFiltroConPpConExcepcion() {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConPpConExcepcion.txt", false);
	}
	

}
