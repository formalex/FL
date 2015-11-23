package ar.uba.dc.formalex.ui;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

/**
 * Acá se prueba el proceso de reduccion  completo(con llamada al Model Checker incluida)
 * @author cfaciano
 *
 */
public class ReduccionDeRepresentacionDeAccionTest extends FlTest {

	@Test
	public void reducePorQueTodasLasReferenciasSonConIsHappening() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueReduce.txt", true, true);
	}
	
	@Test
	public void noReducePorqueHayReferenciasEnUnContador() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueHayReferenciasEnUnContador.txt", true, true);
	}
	
	@Test
	public void noReducePorqueTieneReferenciaEnUnIntervalo() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneReferenciaEnUnIntervalo.txt", true, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaAlDefaultJH() throws Exception {
		
		//Hay una referencia default a la accion, es decir sin ninguno de los dos 
		//nuevos predicados
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaAlDefaultJH.txt", true, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaEnJH() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaEnJH.txt", true, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaConResultsIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaConResultsIn.txt", true, true);
	}

}
