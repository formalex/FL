package ar.uba.dc.formalex.ui.casosDeEstudio;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

public class CasosDeEstudioReductorTest extends FlTest {

	private static final String ROOT_CDE_REDUCTOR = ROOT_RESOURCES + "CasosDeEstudioReductor/";

	@Test
	public void casoDeEstudioVipMemberPassConTresPps() throws Exception {
		Formalex.run(ROOT_CDE_REDUCTOR + "casoDeEstudioVipMemberPassConTresPps.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas1WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio1NroDeClausulas1WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas5WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio1NroDeClausulas5WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas15WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio1NroDeClausulas15WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio1NroDeClausulas25WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas1WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio2NroDeClausulas1WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas5WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio2NroDeClausulas5WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas15WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio2NroDeClausulas15WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas25WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio2NroDeClausulas25WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
		
	@Test
	public void testCasoDeEstudio3NroDeClausulas1WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio3NroDeClausulas1WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas5WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio3NroDeClausulas5WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas15WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio3NroDeClausulas15WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25WithIsHappening() throws Exception  {
		
		Formalex.run(ROOT_CDE_REDUCTOR + "CasoDeEstudio3NroDeClausulas25WithIsHappening.txt", CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
		
}
