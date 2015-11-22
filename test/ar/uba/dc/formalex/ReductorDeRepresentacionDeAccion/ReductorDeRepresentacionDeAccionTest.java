package ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

/**
 * Pruebo el reductor sin llamar al model Checker
 * En este test me interesa ver la representacion de las acciones
 * Despues de ejecutar el reductor
 * @author cfaciano
 *
 */
public class ReductorDeRepresentacionDeAccionTest extends FlTest {

	@Test
	public void reducePorQueTodasLasReferenciasSonConIsHappening() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueReduce.txt", false, true);
	}
	
	@Test
	public void noReducePorqueHayRefecerenciasEnUnContador() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueHayRefecerenciasEnUnContador.txt", false, true);
	}
	
	@Test
	public void noReducePorqueTieneReferenciaEnUnIntervalo() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneReferenciaEnUnIntervalo.txt", false, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaAlDefaultJH() throws Exception {
		
		//Hay una referencia default a la accion, es decir sin ninguno de los dos 
		//nuevos predicados
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaAlDefaultJH.txt", false, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaEnJH() throws Exception {
		
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaEnJH.txt", false, true);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaConResultsIn() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaConResultsIn.txt", false, true);
	}

	

}
