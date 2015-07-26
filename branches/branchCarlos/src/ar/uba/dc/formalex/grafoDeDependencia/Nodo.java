package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.HashSet;
import java.util.Set;


/**
 * Cada nodo tiene un identificador y un valor dentro del nodo
 */
public class Nodo<E> {
    /**
     * El identificador es un string que se utiliza para poder encontrar al 
     * nodode manera rapida en la lista de nodos.
     */
    private String id;

	private E valor;

    private Set<Nodo<E>> vecinos;

    private boolean marcado;

    /**
     * El constructos de un nodo de un grafo. Se necesita obligatoriamente un
     * nombre de nodo (identificador) que NO se encuentre en el grafo ya.
     * @param identificador
     * @param v
     */
    public Nodo(String identificador, E v) {
        this.id = identificador;
        this.valor = v;
        this.vecinos = new HashSet<Nodo<E>>();
        this.marcado = false;
    }
    
    /**
     * Desmarca el nodo
     */
    public void marcar() {
        this.marcado = true;
    }
    
    /**
     * Desmarca el nodo
     */
    public void desmarcar() {
        this.marcado = false;
    }
    
    /**
     * Inserta un vecino en el nodo.
     * @param objNodoDestino El objeto nodo de destino
     */
    public void insertarVecino(Nodo<E> objNodoDestino) {
        vecinos.add(objNodoDestino);
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
	public String toString() {
		return "Nodo [valor=" + valor + ", marcado=" + marcado + "]";
	}

	public String getId() {
        return id;
    }

    public E getValor() {
        return valor;
    }
    
    public boolean isMarcado() {
        return marcado;
    }

    public Set<Nodo<E>> getVecinos() {
        return vecinos;
    }


}
