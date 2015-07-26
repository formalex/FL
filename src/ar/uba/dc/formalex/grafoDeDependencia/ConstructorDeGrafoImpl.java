package ar.uba.dc.formalex.grafoDeDependencia;

import java.util.Map;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.bgtheory.Counter;
import ar.uba.dc.formalex.fl.bgtheory.Interval;

public class ConstructorDeGrafoImpl implements ConstructorDeGrafo {

	private Grafo<InfoComponenteBgt> grafoDeDependencias;

	@Override
	public Grafo ejecutar(BackgroundTheory bgt,
			Map<Action, Agente> accionesYAgentes) {
		if (accionesYAgentes == null)
			throw new RuntimeException(
					"No se puede ejecutar la construcción del Grafo");

		grafoDeDependencias = new GrafoImplAdyacencia<InfoComponenteBgt>();
		agregarNodos(bgt);
		agregarAristasConOrigenAccion(bgt, accionesYAgentes);
		agregarAristasConOrigenIntervalo(bgt);
		agregarAristasConOrigenContador(bgt);

		return grafoDeDependencias;
	}

	private void agregarAristasConOrigenContador(BackgroundTheory bgt) {
		// Si un counter c tiene acciones que lo incrementan a1..an entonces se
		// agrega una arista (c,ai).
		// Si un counter c tiene acciones que le setean valores a1..an entonces
		// se agrega una arista (c,ai).

		for (Counter unContador : bgt.getCounters()) {

			if (!unContador.getIncreaseActions().isEmpty()) {
				for (Action unaAccionDeIncremento : unContador
						.getIncreaseActions().keySet()) {
					grafoDeDependencias.agregarArista(
							generarIdNodoCounter(unContador),
							generarIdNodoAction(unaAccionDeIncremento));
				}
			}

			if (!unContador.getSetValueActions().isEmpty()) {
				for (Action unaAccionDeSeteo : unContador.getSetValueActions()
						.keySet()) {
					grafoDeDependencias.agregarArista(
							generarIdNodoCounter(unContador),
							generarIdNodoAction(unaAccionDeSeteo));
				}
			}
		}
	}

	private void agregarAristasConOrigenIntervalo(BackgroundTheory bgt) {
		// Si un intervalo i tiene acciones que lo inician a1..an entonces se
		// agrega una arista (i,ai).
		// Si un intervalo i tiene acciones que lo finalizan a1..an entonces se
		// agrega una arista (i,ai).
		// Si un intervalo i ocurre dentro de un intervalo j, entonces se agrega
		// una arista (i,j).

		for (Interval unIntervalo : bgt.getIntervals()) {
			if (!unIntervalo.getStartTriggers().isEmpty()) {
				for (Action unaAccionDeInicio : unIntervalo.getStartTriggers()) {
					grafoDeDependencias.agregarArista(
							generarIdNodoInterval(unIntervalo),
							generarIdNodoAction(unaAccionDeInicio));
				}
			}

			if (!unIntervalo.getEndTriggers().isEmpty()) {
				for (Action unaAccionDeFin : unIntervalo.getEndTriggers()) {
					grafoDeDependencias.agregarArista(
							generarIdNodoInterval(unIntervalo),
							generarIdNodoAction(unaAccionDeFin));
				}
			}

			if (unIntervalo.hasOccursIn())
				grafoDeDependencias.agregarArista(
						generarIdNodoInterval(unIntervalo),
						generarIdNodoInterval(unIntervalo.getOccursIn()));

		}
	}

	private void agregarAristasConOrigenAccion(BackgroundTheory bgt,
			Map<Action, Agente> accionesYAgentes) {
		// Si una acción a ocurre en un intervalo i, entonces se agrega una
		// arista (a,i).
		// Si una acción a tiene una acción sincronizada b, entonces se agrega
		// una arista (a,b).
		// Si una acción a es ejecutada por una agente g, entonces se agrega una
		// arista (a,g).
		for (Action unaAccion : bgt.getActions()) {
			if (unaAccion.hasOccursIn())
				grafoDeDependencias.agregarArista(
						generarIdNodoAction(unaAccion),
						generarIdNodoInterval(unaAccion.getOccursIn()));

			if (unaAccion.isSync())
				grafoDeDependencias.agregarArista(
						generarIdNodoAction(unaAccion),
						generarIdNodoAction(unaAccion.getSync()));

			if (accionesYAgentes.containsKey(unaAccion)) {
				Agente agenteQueEjecutaLaAccion = accionesYAgentes
						.get(unaAccion);
				grafoDeDependencias.agregarArista(
						generarIdNodoAction(unaAccion),
						generarIdNodoAgente(agenteQueEjecutaLaAccion));
			}
		}
	}

	private void agregarNodos(BackgroundTheory bgt) {
		for (Action unaAccion : bgt.getActions()) {
			grafoDeDependencias.agregarNodo(crearNodoAction(unaAccion));
		}

		for (Agente unAgente : bgt.getAgentes()) {
			grafoDeDependencias.agregarNodo(crearNodoAgente(unAgente));
		}

		for (Interval unIntervalo : bgt.getIntervals()) {
			grafoDeDependencias.agregarNodo(crearNodoInterval(unIntervalo));
		}

		for (Counter unContador : bgt.getCounters()) {
			grafoDeDependencias.agregarNodo(crearNodoCounter(unContador));
		}
	}

	private InfoComponenteBgt crearNodoCounter(Counter unContador) {
		return new InfoComponenteBgt(unContador.getName(),
				TipoDeComponenteBgt.COUNTER);
	}

	private InfoComponenteBgt crearNodoInterval(Interval unIntervalo) {
		return new InfoComponenteBgt(unIntervalo.getName(),
				TipoDeComponenteBgt.INTERVAL);
	}

	private InfoComponenteBgt crearNodoAgente(Agente unAgente) {
		return new InfoComponenteBgt(unAgente.getName(),
				TipoDeComponenteBgt.AGENTE);
	}

	private InfoComponenteBgt crearNodoAction(Action unaAccion) {
		return new InfoComponenteBgt(unaAccion.getName(),
				TipoDeComponenteBgt.ACTION);
	}

	private String generarIdNodoCounter(Counter unContador) {
		return crearNodoCounter(unContador).toString();
	}

	private String generarIdNodoAgente(Agente agenteQueEjecutaLaAccion) {
		return crearNodoAgente(agenteQueEjecutaLaAccion).toString();
	}

	private String generarIdNodoInterval(Interval interval) {
		return crearNodoInterval(interval).toString();
	}

	private String generarIdNodoAction(Action unaAccion) {
		return crearNodoAction(unaAccion).toString();
	}

}
