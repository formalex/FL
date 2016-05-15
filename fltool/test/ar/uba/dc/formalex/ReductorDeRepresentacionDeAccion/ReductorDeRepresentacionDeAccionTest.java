package ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

/**
 * Pruebo el reductor sin llamar al model Checker
 * En este test me interesa ver la representacion de las acciones
 * Despues de ejecutar el reductor
 * @author cfaciano
 *
 */
public class ReductorDeRepresentacionDeAccionTest extends FlTest {

	//Las pruebas DEL MODELO se corren con el REDUCTOR ACTIVADO
	private static final boolean CON_REDUCTOR = true;
	//Las pruebas DEL MODELO hay que correrlas SIEMPRE SIN el model checker
	private static final boolean CON_MODEL_CHECKER_MODELO = false;

	@Test
	public void reducePorQueTodasLasReferenciasSonConIsHappening() throws Exception {
		
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueReduce.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueHayReferenciasEnUnContador() throws Exception {
		
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueHayReferenciasEnUnContador.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneReferenciaEnUnIntervalo() throws Exception {
		
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneReferenciaEnUnIntervalo.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaAlDefaultJH() throws Exception {
		
		//Hay una referencia default a la accion, es decir sin ninguno de los dos 
		//nuevos predicados
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaAlDefaultJH.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaEnJH() throws Exception {
		
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaEnJH.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void noReducePorqueTieneUnaReferenciaConResultsIn() throws Exception {
		Formalex.run(ROOT_EJS_PARA_EL_REDUCTOR + "ejQueNoReducePorqueTieneUnaReferenciaConResultsIn.txt", CON_MODEL_CHECKER_MODELO, CORRER_CON_FILTRADO, CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	

}
