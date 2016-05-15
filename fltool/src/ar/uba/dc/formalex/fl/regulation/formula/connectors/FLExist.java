package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLFalse;

import java.util.Iterator;
import java.util.Set;

public class FLExist extends FLQuantifier {
    private boolean yaInstanciada = false; //usada para loguear y debug

    public FLExist(String variable, String role, FLFormula formula) {
        super(variable, role, formula);
    }

    @Override
    public String translateToLTL(LTLTranslationType anLTLTranslationType) {
        if (yaInstanciada)
            return getFormula().translateToLTL(anLTLTranslationType );
        else {
            //usada para loguear y debug
            String rol = "";
            if (getRole() != null)
                rol = ":" + getRole();
            return "EXISTS (" + getVariable() + rol + " ; " + getFormula().translateToLTL(anLTLTranslationType ) + " )";
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

        if (newFormula == null)
            return null;

        //Luego instancio con los roles del exists
        Set<Agente> agentes;
        if (getRole() == null)//si no se indica el rol se usan todos => se usan todos los agentes que pueden realizar la acción
            agentes = bgUtil.getAgentes();
        else
            agentes = bgUtil.getAgentes(getRole());

        FLFormula orFormula = null;
        for (Iterator iterator = agentes.iterator(); iterator.hasNext();) {
			Agente agenteE = (Agente) iterator.next();
    		FLFormula orDer = newFormula.instanciar(getVariable(), agenteE.getName(), bgUtil, forceAgent);
        	if (agente != null){
        		if(agente.equals(agenteE.getName()))
        			return orDer;
        		else if(!iterator.hasNext()) 
        			return new FLFalse();
        	} else {
        		if (orDer != null){
                	orDer = exceptionsInstantiator(orDer, agenteE, bgUtil);
        			if (orFormula != null)
        				orFormula = new FLOr(orFormula, orDer);
        			else
        				orFormula = new FLOr(new FLFalse(), orDer);
        		}
        	}
        }
        yaInstanciada = true;

        return orFormula;
    }
}
