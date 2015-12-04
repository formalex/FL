package ar.uba.dc.formalex.ui;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class TestMain extends FlTest{

	private static String ROOT_RESOURCES = "resources/";
	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
		

	
	@Test
	public void testAcldc() throws Exception  {		
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ACLDC.txt", false, true);
	}
	
	@Test
	public void testAcldcFullConFormulasConAccionesConOutputValuesModificadas() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullConFormulasConAccionesConOutputValuesModificadas.txt", false, true);
	}
		
	@Test
	public void testAcldcFullSinFormulasConAccionesConOutputValues() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "AcldcFullSinFormulasConAccionesConOutputValues.txt", false, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas1.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas5.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas15.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas25.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas28() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas28.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas60() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas60.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas40() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas40.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas1.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas5.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas15.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas25.txt", true, true);
	}
		
	@Test
	public void testCasoDeEstudio2NroDeClausulas28() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas28.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas1.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas5.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas15.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas25.txt", true, true);
	}
	@Test
	public void testCasoDeEstudio3NroDeClausulas32() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas32.txt", true, true);
	}
	
	
	@Test
	public void testCasoDeEstudioVipMemberPass() throws Exception  {
		//Este ejemplo tiene un PP con excepcion
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPass.txt", true, true);
	}
	
	@Test
	public void testCasoDeEstudioVipMemberPassFull() throws Exception  {
		//Este ejemplo tiene dos PP con excepcion
		TestUtils.corridaDeFormaLex(ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPassFull.txt", true, true);
	}
	
	@Test
	public void testRompeNusmvPorqueFormulaMayorTo65Kb() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_FILTROS + "EjConAccionesConOutputValuesQueRompeNusmv.txt", true, true);
	}
	
	@Test
	public void testEjemploActionConOutputValuesConRolesReferenciados() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesConRolesReferenciadosEnRegla.txt", true, true);
	}
	
	@Test
	public void testEjemploActionConOutputValuesSinRolesReferenciados() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjActionConOutputValuesSinRolesReferenciadosEnRegla.txt", true, true);
	}
	
	/**** Estos dos tests los hice para saber si variaba la cantidad de agentes 
	 * generados, segun la cantidad de lineas en las que se divida la definicion
	 * de los roles. La realidad es que no varía. genera en ambos casos
	 * 16 agentes
	 */
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnUnaLinea() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnUnaLinea.txt", false, true);
	}
	
	@Test
	public void testEjemploConstruccionDeGrafo() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjConstruccionDeGrafo.txt", false, true);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentes() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjGeneracionDeAgentes.txt", false, true);
	}
	
	@Test
	public void testEjemploGeneracionDeAgentesConCuatroRolesEnDosLineas() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCuatroRolesEnDosLineas.txt", false, true);
	}
	
	@Test
	public void testEjemploCombinacionRolesTesisChicas() throws Exception  {
		// Lo hice para ver si se seguía comportandose bien despues del cambio de
		// las chicas
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjCombinacionRolesTesisChicas.txt", false, true);
	}
	
	@Test
	public void testEjemploBelongsToTesisChicas() throws Exception  {
		// Lo hice para ver el seguimiento de belongsTo
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "EjBelongsTo.txt", true, true);
	}
	
	@Test
	public void testEjemploPermisoPermanente() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "permisoPermanente.txt", false, true);
	}
	
	@Test
	@Ignore("Hasta que se arregle el tema del synchronize")
	public void testEjemploSynchronize() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_RESOURCES + "ejSynchronize.txt", false, true);
	}
	
	

}
