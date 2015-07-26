package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Queue;

public class GrafoImplAdyacencia<E> implements Grafo<E> {

    private Hashtable<String, Nodo<E>> nodos;

    @Override
	public String toString() {
    	if(nodos!=null){
    		StringBuilder sb= new StringBuilder();
    		sb.append("Aristas= {\n");
    		for (Nodo<E> unNodo : this.nodos.values()) {
				if(!unNodo.getVecinos().isEmpty())
					for (Nodo<E> unVecino : unNodo.getVecinos()) {
						sb.append(String.format("(%s -> %s)\n", unNodo.getId(), unVecino.getId()));
					}				
			}

    		sb.append("}");
    		
    		//TODO ver si los nodos se pueden imprimir mejor
    		return "GrafoImplAdyacencia [nodos=" + nodos + "] " + sb.toString();
    	}
		return "GrafoImplAdyacencia [nodos=" + nodos + "]";
	}

	public GrafoImplAdyacencia() {
        nodos = new Hashtable<String, Nodo<E>>();
    }
    
    /**
     * Agrega un nuevo nodo en el grafo.
     * @param v
     */
    public void agregarNodo(E v) {
        agregarNodo(v.toString(), v);
    }
    
    /**
     * Crea una arista entre los dos nodos.
     * @param idNodoOrigen El identifcador del nodo origen
     * @param idNodoDestino El identificador del nodo destino
     */
    public void agregarArista(String idNodoOrigen, String idNodoDestino) {
        Nodo<E> objNodoOrigen = this.nodos.get(idNodoOrigen);
        Nodo<E> objNodoDestino = this.nodos.get(idNodoDestino);

        if(objNodoOrigen==null || objNodoDestino==null )
        	return;
        	
        objNodoOrigen.insertarVecino(objNodoDestino);

    }

    /**
     * Agrega un nuevo nodo en el grafo.
     * @param id Identificador del nodo
     * @param v El objeto que va dentro del nodo
     */
    private void agregarNodo(String id, E v) {
        Nodo<E> objNodo = new Nodo<E>(id, v);
        
        //Para evitar pisadas accidentales
        if(nodos.containsKey(id))
        	return;
        
        nodos.put(id, objNodo);
    }

    public Iterator<Nodo<E>> getNodos() {
    	
        return nodos.values().iterator();
    }
    
    public Nodo<E> getNodoById(String id) {
    	
        return nodos.get(id);
    }


    /**
     * Este metodo es el mas importante en el algoritmo de BFS. SE coloca todos
     * los nodos a visitar en la cola para tal efecto y se van cambiando los 
     * valores del visitado de acuerdo como corresponda
     * @param objVisitado El nodo a visitar.
     * @param tocaVisitar La cola donde se colocaran los vecinos no visitados
     * @param objDestino El nodo que se testea para saber cuando parar.
     * @return Verdadreo o falso para saber si lo encuentra.
     */
    private boolean visitarNodo(Nodo<E> objVisitado, Queue<Nodo<E>> tocaVisitar, 
                                Nodo<E> objDestino) {
             
        for (Nodo<E> vecino : objVisitado.getVecinos()) {
        	// Si es el que estamos buscando devuelve true y le coloca el numero
            // de visitado que corresponda.
            if (vecino == objDestino) {
                vecino.marcar();
                return true;
            }

            // Solamente lo coloca si no esta en la cola (si no ha sido visitado)
            if (vecino.isMarcado() == false)
                tocaVisitar.add(vecino);

            // Coloca el visitado de acuerdo a lo que corresponde
            vecino.marcar();
		}
        // Si no lo encuentra entre los vecinos de este nodo
        return false;
    }
    
//    public void bfs(Node root)
//    {
//        //Since queue is a interface
//        Queue<Node> queue = new LinkedList<Node>();
//
//        if(root == null) return;
//
//        root.state = State.Visited;
//         //Adds to end of queue
//        queue.add(root);
//
//        while(!queue.isEmpty())
//        {
//            //removes from front of queue
//            Node r = queue.remove(); 
//            System.out.print(r.getVertex() + "\t");
//
//            //Visit child first before grandchild
//            for(Node n: r.getChild())
//            {
//                if(n.state == State.Unvisited)
//                {
//                    queue.add(n);
//                    n.state = State.Visited;
//                }
//            }
//        }
//
//
//    }
}


