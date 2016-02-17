package ar.uba.dc.formalex.ui.casosDeEstudio;

import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class CasosDeEstudioFiltradoTest extends FlTest {

	//Los casos de tests del filtrado se deben correr sin el reductor!!!!
	private static final boolean CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO = false;
	@Test
	public void testCasoDeEstudio1NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas1.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas5.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas15.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas25.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testCasoDeEstudio1NroDeClausulas60() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas60.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testCasoDeEstudio2NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas1.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas5.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas15.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas25.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testCasoDeEstudio2NroDeClausulas28() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas28.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas1() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas1.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas5() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas5.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas15() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas15.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas25.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	@Test
	public void testCasoDeEstudio3NroDeClausulas32() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas32.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	
	@Test
	public void testCasoDeEstudioVipMemberPass() throws Exception  {
		//Este ejemplo tiene un PP con excepcion
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPass.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudioVipMemberPassFull() throws Exception  {
		//Este ejemplo tiene dos PP con excepcion
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO + "casoDeEstudioVipMemberPassFull.txt", FlTest.CORRER_CON_MODEL_CHECKER, true, CON_REDUCTOR_EN_PRUEBAS_DEL_FILTRADO, LTLTranslationType.WITH_JH);
	}

}
