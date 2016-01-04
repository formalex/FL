package ar.uba.dc.formalex.ui;

import org.junit.Test;

import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

/**
 * * Acá se prueba el proceso de filtrado  completo(con llamada al Model Checker incluido)
 * @author cfaciano
 *
 */
public class FiltradoTest extends FlTest {

	@Test
	public void testEjemploFiltroConIntervalOnlyOccursInScope() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosConOnlyOccursInScopeQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConActionsOnlyOccursInScope() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConActionsOnlyOccursInScope.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConAccionesQueNoSeReferencian() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConAccionesQueNoSeReferencian.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueNoSeUsan() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosQueNoSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConIntervalosQueSeUsan() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConIntervalosQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueNoSeUsan() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConCountersQueNoSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConCountersQueSeUsan() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConCountersQueSeUsan.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploFiltroConPpConExcepcion() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConPpConExcepcion.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploConAccionesConOvsQueNoSeReferencian() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConAccionesConOvsQueNoSeReferencian.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	
	@Test
	public void testEjemploConAccionesConOvsQueNoSeReferencianTodos() throws Exception  {
		
		TestUtils.corridaDeFormaLex(FlTest.ROOT_EJS_FILTROS + "EjConAccionesConOvsQueNoSeReferencianTodosLosOvs.txt", true, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR);
	}
	

}
