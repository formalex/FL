package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.Map;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;


public interface ConstructorDeGrafo {

	public Grafo ejecutar(BackgroundTheory bgt,  Map<Action, Agente> accionesYAgentes); 
}
