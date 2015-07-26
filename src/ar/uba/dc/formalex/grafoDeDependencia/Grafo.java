package ar.uba.dc.formalex.grafoDeDependencia;

public interface Grafo<E> {

	public void agregarNodo(E v);
    public void agregarArista(String idNodoOrigen, String idNodoDestino);
}
