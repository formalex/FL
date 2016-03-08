package ar.uba.dc.formalex.ui;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

/**
 * * Acá se prueba el proceso de filtrado  completo(con llamada al Model Checker incluido)
 * @author cfaciano
 *
 */
public class FiltradoTest extends FlTest {

	@Test
	public void testEjemploFiltroConIntervalOnlyOccursInScope() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosConOnlyOccursInScopeQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConActionsOnlyOccursInScope() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConActionsOnlyOccursInScope.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConAccionesQueNoSeReferencian() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConAccionesQueNoSeReferencian.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueNoSeUsan() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosQueNoSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueSeUsan() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueNoSeUsan() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConCountersQueNoSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueSeUsan() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConCountersQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploFiltroConPpConExcepcion() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConPpConExcepcion.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploConAccionesConOvsQueNoSeReferencian() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConAccionesConOvsQueNoSeReferencian.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	
	@Test
	public void testEjemploConAccionesConOvsQueNoSeReferencianTodos() throws Exception  {
		
		Formalex.run(FlTest.ROOT_EJS_FILTROS + "EjConAccionesConOvsQueNoSeReferencianTodosLosOvs.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR, LTLTranslationType.WITH_JH);
	}
	

}
