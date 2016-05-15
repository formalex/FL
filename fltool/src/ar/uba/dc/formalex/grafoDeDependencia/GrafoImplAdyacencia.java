package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GrafoImplAdyacencia<E> implements Grafo<E> {

    private Hashtable<String, Nodo<E>> nodos;

	public GrafoImplAdyacencia() {
        nodos = new Hashtable<String, Nodo<E>>();
    }
    
	@Override
	public String toString() {
		if (nodos != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Nodos = {\n");
			for (Nodo<E> unNodo : this.nodos.values()) {
					sb.append(unNodo.toString() + ",\n");
			}

			sb.append("}");
			
			sb.append("Aristas= {\n");
			for (Nodo<E> unNodo : this.nodos.values()) {
				if (!unNodo.getVecinos().isEmpty())
					for (Nodo<E> unVecino : unNodo.getVecinos()) {
						sb.append(String.format("(%s -> %s)\n",
								unNodo.getValor(), unVecino.getValor()));
					}
			}

			sb.append("}");

			return "GrafoImplAdyacencia "
					+ sb.toString();
		}
		return "GrafoImplAdyacencia Grafo vacio ";
	}
	
	/**
     * Crea un String con el formato de graphViz
     */
	@Override
	public String toGraphViz() {
		//		digraph structs {
		//			node [shape=Mrecord];
		//			struct1 [shape=Mrecord,label="<f0> agent_1|<f1> AGENTE", style=filled];
		//			struct2 [shape=Mrecord,label="<f0> empieza_censo|<f1> ACTION", style=filled];
		//			struct3 [shape=Mrecord,label="<f0> en_censo|<f1> INTERVAL", style=filled];
		//			struct4 [shape=Mrecord,label="<f0> agent_1.censarse|<f1> ACTION", style=filled];
		//			struct5 [shape=Mrecord,label="<f0> termina_censo|<f1> ACTION", style=filled];
		//			struct3 -> struct5;
		//			struct3 -> struct2;
		//			struct4 -> struct1;
		//			}

		if (nodos != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("digraph structs {\n");
			sb.append("node [shape=Mrecord];\n");
			int i=1;
			Map<String, String> nombreNodos = new HashMap<String, String>();
			//Creo los nodos
			for (Nodo<E> unNodo : this.nodos.values()) { 
				InfoComponenteBgt unaInfoDeComponente = (InfoComponenteBgt)unNodo.getValor();
				String nombreNodoGv = "struct" + i;
				String idNodo = unNodo.getId();

				nombreNodos.put(idNodo, nombreNodoGv);		
				if(unNodo.isMarcado())
					sb.append(String.format("%s [shape=Mrecord,label=\"<f0> %s|<f1> %s\", style=filled];\n", nombreNodoGv, idNodo, unaInfoDeComponente.getTipoDeComponente()) );
				else
					sb.append(String.format("%s [shape=Mrecord,label=\"<f0> %s|<f1> %s\"];\n", nombreNodoGv, idNodo, unaInfoDeComponente.getTipoDeComponente()) );
				i++;
			}

			//Creo las aristas
			for (Nodo<E> unNodo : this.nodos.values()) { 
				String nombreNodoGv = nombreNodos.get(unNodo.getId());	
				if (!unNodo.getVecinos().isEmpty())
					for (Nodo<E> unVecino : unNodo.getVecinos()) {
						sb.append(String.format("%s -> %s;\n", nombreNodoGv, nombreNodos.get(unVecino.getId()) ));
					}
			}
			sb.append("}");

			return sb.toString();

		}

		return "";
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
    public void agregarNodo(String id, E v) {
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


    /** Marca recursivamente los nodos alcanzables desde nodoOrigen
     * recorriendo en orden BFS
     * @param el id del nodoOrigen
     */
    public void marcarNodosEnBfs(String idNodoOrigen){
    	Nodo<E> nodoOrigen = this.getNodoById(idNodoOrigen);
    	
    	Queue<Nodo<E>> paraMarcar = new LinkedList<Nodo<E>>();
		paraMarcar.add(nodoOrigen);
    	nodoOrigen.marcar();
    	
    	while(!paraMarcar.isEmpty()){
    		Nodo<E> v = paraMarcar.poll();
    		Set<Nodo<E>> vecinosDeV = v.getVecinos();
    		for (Nodo<E> unVecino : vecinosDeV) {
				if(!unVecino.isMarcado()){
					unVecino.marcar();
					paraMarcar.add(unVecino);
				}
			}
    	}
    }

	public void resetMarcas() {

		for (Nodo<E> unNodo : this.nodos.values()) {
			unNodo.desmarcar();
		}
		
	}

}


