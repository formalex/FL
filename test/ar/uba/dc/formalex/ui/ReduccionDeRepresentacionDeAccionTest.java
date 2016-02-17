package ar.uba.dc.formalex.ui;

import org.junit.Ignore;
import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

/**
 * Acá se prueba el proceso de reduccion  completo(con llamada al Model Checker incluida)
 * @author cfaciano
 *
 */
public class ReduccionDeRepresentacionDeAccionTest extends FlTest {

	@Test
	@Ignore("El ejemplo tarda mucho, fue una prueba aparte de la tesis")
	public void casoDeEstudio2NroDeClausulas5WithIsHappeningWithInterval() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "CasoDeEstudio2NroDeClausulas5WithIsHappeningWithInterval.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void reducePorQueTodasLasReferenciasSonConIsHappening() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueReduce.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueHayReferenciasEnUnContador() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueHayReferenciasEnUnContador.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneReferenciaEnUnIntervalo() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneReferenciaEnUnIntervalo.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaAlDefaultJH() throws Exception {
		
		//Hay una referencia default a la accion, es decir sin ninguno de los dos 
		//nuevos predicados
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaAlDefaultJH.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaEnJH() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaEnJH.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaConResultsIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaConResultsIn.txt", true, CORRER_CON_FILTRADO, true, LTLTranslationType.WITH_JH);
	}

}
