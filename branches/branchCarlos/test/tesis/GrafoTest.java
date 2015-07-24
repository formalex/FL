package tesis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.uba.dc.formalex.util.GrafoAdyacencia;
import ar.uba.dc.formalex.util.Nodo;

public class GrafoTest {

	private static Nodo<String> prototipoDeNodo;

	@BeforeClass
	public static void setUp(){
		
		prototipoDeNodo = new Nodo<String>("idNodo", "nodo");
	}
	
	@Test
	public void cuandoSeCreaUnGrafoNoTieneNodos() {
		
		GrafoAdyacencia<String> unGrafo = new GrafoAdyacencia<String>();
		
		assertFalse(unGrafo.getNodos().hasNext());
	}
	
	@Test
	public void cuandoSeAgregaUnNodoAlGrafoElGrafoResultanteTieneUnNodo() {
		
		GrafoAdyacencia<String> unGrafo = new GrafoAdyacencia<String>();
		String v = "nodo1";
		
		//2
		unGrafo.agregarNodo(v );
		
		//3
		Nodo<String> nodoEsperado = crearNodo(v);
		Iterator<Nodo<String>> iterNodos = unGrafo.getNodos();
		assertEquals(nodoEsperado, iterNodos.next());
		
		assertFalse(iterNodos.hasNext());
	}
	
	@Test
	public void testAgregarArista() {
		
		GrafoAdyacencia<String> unGrafo = new GrafoAdyacencia<String>();
		String v1 = "nodo1";
		String v2 = "nodo2";		
		unGrafo.agregarNodo(v1);
		unGrafo.agregarNodo(v2);
		
		//2
		unGrafo.agregarArista(v1, v2);
		
		//3
		Nodo<String> nodoEsperado = crearNodo(v1);	
		Nodo<String> nodoUno = unGrafo.getNodoById(v1.toString());
		
		assertEquals(nodoEsperado, nodoUno);
		Set<Nodo<String>> vecinosUno = nodoUno.getVecinos();
		
		Nodo<String> nodoEsperadoDos = crearNodo(v2);
		assertEquals(1, vecinosUno.size());
		assertTrue(vecinosUno.contains(nodoEsperadoDos));

	}

	private Nodo<String> crearNodo(String v1) {
		return new Nodo<String>(v1.toString(),v1);
	}

	
	@Test
	public void cuandoSeCreaUnNodoSeSeteanElIdDelNodoySuValor() {

		String identificadorNodo = "idNodo";
		String valorNodo = "nodo";
		Nodo<String> nuevoNodo = new Nodo<String>(identificadorNodo, valorNodo);
		
		assertEquals(identificadorNodo, nuevoNodo.getId());
		assertEquals(valorNodo, nuevoNodo.getValor());
	}
	
	@Test
	public void cuandoSeCreaUnNodoEstaDesmarcadoSinVecinos() {

		assertFalse(prototipoDeNodo.isMarcado());
		assertTrue(prototipoDeNodo.getVecinos().isEmpty());
	}
	
	@Test
	public void testMarcar() {
		
		Nodo<String> nodoDesmarcado = new Nodo<String>(prototipoDeNodo.getId(), prototipoDeNodo.getValor());
		assertFalse(nodoDesmarcado.isMarcado());	
		
		//2
		nodoDesmarcado.marcar();
		
		//3
		assertTrue(nodoDesmarcado.isMarcado());

	}
	
	@Test
	public void testDesmarcar() {
		
		Nodo<String> nodoMarcado = new Nodo<String>(prototipoDeNodo.getId(), prototipoDeNodo.getValor());
		nodoMarcado.marcar();
		assertTrue(nodoMarcado.isMarcado());
		
		//2
		nodoMarcado.desmarcar();
		
		//3
		assertFalse(nodoMarcado.isMarcado());	

	}
	
	@Test
	public void siUnNodoNoTieneVecinosSiSeLeAgregaEntoncesTieneUno() {

		Nodo<String> nodoSinVecinos = new Nodo<String>(prototipoDeNodo.getId(), prototipoDeNodo.getValor());
		assertTrue(nodoSinVecinos.getVecinos().isEmpty());		
		Nodo<String> nodoVecino = new Nodo<String>("idV", "nodoVecino");
		
		nodoSinVecinos.insertarVecino(nodoVecino);
		
		assertTrue(nodoSinVecinos.getVecinos().contains(nodoVecino));
	}
	


}
