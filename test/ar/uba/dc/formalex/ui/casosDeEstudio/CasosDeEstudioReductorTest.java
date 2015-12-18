package ar.uba.dc.formalex.ui.casosDeEstudio;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class CasosDeEstudioReductorTest extends FlTest {

	private static final String ROOT_CDE_REDUCTOR = ROOT_RESOURCES + "CasosDeEstudioReductor/";

	@Test
	public void casoDeEstudioVipMemberPassConTresPps() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR + "casoDeEstudioVipMemberPassConTresPps.txt", CON_MODEL_CHECKER, true);
	}
	
	@Test
	public void testCasoDeEstudio1NroDeClausulas25WithIsHappening() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR + "CasoDeEstudio1NroDeClausulas25WithIsHappening.txt", CON_MODEL_CHECKER, true);
	}

	@Test
	public void testCasoDeEstudio2NroDeClausulas25WithIsHappening() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR + "CasoDeEstudio2NroDeClausulas25WithIsHappening.txt", CON_MODEL_CHECKER, true);
	}
	
	@Test
	public void testCasoDeEstudio3NroDeClausulas25WithIsHappening() throws Exception  {
		
		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR + "CasoDeEstudio3NroDeClausulas25WithIsHappening.txt", CON_MODEL_CHECKER, true);
	}
}
