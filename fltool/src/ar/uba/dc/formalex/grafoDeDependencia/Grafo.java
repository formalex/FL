package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.Iterator;

public interface Grafo<E> {

	public void agregarNodo(E v);
	public void agregarNodo(String id, E v);
    public void agregarArista(String idNodoOrigen, String idNodoDestino);
	public Iterator<Nodo<E>> getNodos();
	public Nodo<E> getNodoById(String idNodo);
	public void marcarNodosEnBfs(String idNodoOrigen);
	public void resetMarcas();
	String toGraphViz();
}
