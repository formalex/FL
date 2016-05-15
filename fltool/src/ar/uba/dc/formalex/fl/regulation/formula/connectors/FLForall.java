package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLFalse;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;

public class FLForall extends FLQuantifier {
	private boolean yaInstanciada = false; //usada para loguear y debug
    private static final Logger logger = Logger.getLogger(FLForall.class);

	public FLForall(String variable, String role, FLFormula formula) {
		super(variable, role, formula);
	}

	@Override
	public String translateToLTL(LTLTranslationType anLTLTranslationType) {
		if (yaInstanciada)
			return getFormula().translateToLTL(anLTLTranslationType );
		else {
			String rol = "";
			if (getRole() != null)
				rol = ":" + getRole();
			return "FORALL (" + getVariable() + rol + " ; " + getFormula().translateToLTL(anLTLTranslationType ) + " )";
		}
	}

	@Override
	public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
		FLFormula newFormula;
		if (variable != null)
			//Primero instancio la fórmula interna con la variable externa
			newFormula = getFormula().instanciar(variable, agente, bgUtil, forceAgent);
		else
			newFormula = getFormula();

		if(newFormula == null)
			return null;

		//Luego instancio con los roles del exists
		Set<Agente> agentes;
		if (getRole() == null)//si no se indica el rol se usan todos => se usan todos los agentes que pueden realizar la acción
			agentes = bgUtil.getAgentes();
		else
			agentes = bgUtil.getAgentes(getRole());

		FLFormula andFormula = null;
		int cont = 0;
		for (Iterator iterator = agentes.iterator(); iterator.hasNext();) {
			Agente agenteE = (Agente) iterator.next();
			FLFormula izq = newFormula.instanciar(getVariable(), agenteE.getName(), bgUtil, forceAgent);
			if (agente != null){
				if(agente.equals(agenteE.getName()))
					return izq;
				else if(!iterator.hasNext()) 
					return new FLFalse();
			} else {            
				if (izq != null){
					cont++;
					izq = exceptionsInstantiator(izq, agenteE, bgUtil);
					if (andFormula != null) 
						andFormula = new FLAnd(izq, andFormula);
					else
						andFormula = new FLAnd(izq, new FLTrue());
				}
			}
		}
		logger.info("Al expandir el FLForall quedó una fórmula con '" + cont + "' AND");
        
		yaInstanciada = true;
		return andFormula;
	}

}
