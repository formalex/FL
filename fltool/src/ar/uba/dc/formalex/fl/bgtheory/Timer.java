package ar.uba.dc.formalex.fl.bgtheory;

import java.util.ArrayList;
import java.util.List;

/**
 * Acciones temporales
 */
public class Timer {

	private List<String > eventos;

    public void addEvento(String evento){
        if (eventos == null)
            eventos = new ArrayList<String>();
        eventos.add(evento);
    }

    public List<String> getEventos() {
        return eventos;
    }

    public void setEventos(List<String> eventos) {
        this.eventos = eventos;
    }
}