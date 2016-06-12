package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.Map;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;

public class ConstructorDeGrafoFake implements ConstructorDeGrafo {

	@Override
	public Grafo iniciar(BackgroundTheory bgt,
			Map<Action, Agente> accionesYAgentes) {
		return new GrafoImplAdyacencia();
	}

	@Override
	public String toString() {
		
		return "SIN Filtro";
	}
	

}
