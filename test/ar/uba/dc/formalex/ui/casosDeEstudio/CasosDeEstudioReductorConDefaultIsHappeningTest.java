package ar.uba.dc.formalex.ui.casosDeEstudio;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

public class CasosDeEstudioReductorConDefaultIsHappeningTest extends FlTest {

	@Test
	public void testCasoDeEstudio1NroDeClausulas60WithDefaultIsHappening() throws Exception  {
		
		Formalex.run(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio1NroDeClausulas60.txt", FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testCasoDeEstudio2NroDeClausulas28WithDefaultIsHappening() throws Exception  {
		
		Formalex.run(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio2NroDeClausulas28.txt", FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
			
	@Test
	public void testCasoDeEstudio3NroDeClausulas32WithDefaultIsHappening() throws Exception  {
		
		Formalex.run(FlTest.ROOT_CDE_FILTRADO + "CasoDeEstudio3NroDeClausulas32.txt", FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
}
