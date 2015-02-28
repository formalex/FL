package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import java.util.HashSet;
import java.util.Iterator;

import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;


public abstract class FLQuantifier extends FLFormula{
	private String role;
	private String variable;
	private FLFormula formula;

    public String getRole() {
		return role;
	}

	public String getVariable() {
		return variable;
	}

    public FLFormula getFormula() {
        return formula;
    }

    public FLQuantifier(String variable, String role, FLFormula formula) {
		this.role = role;
		this.variable = variable;
        this.formula = formula;
        this.exceptions = new HashSet();
	}
    
    protected FLFormula exceptionsInstantiator(FLFormula rule, Agente agent, BGUtil bgUtil){
    	if (exceptions != null && !exceptions.isEmpty()){ // se realiza la disyunción de todos los Permisos que son excepciones a la regla
        	Iterator<FLFormula> exceptionsFormulas = exceptions.iterator();
        	FLFormula exceptionFormOr = exceptionsFormulas.next(); 
        	while (exceptionsFormulas.hasNext()){
        		FLFormula next = exceptionsFormulas.next();
        		exceptionFormOr = new FLOr(exceptionFormOr, next);
        	}
        	//instancio con los agentes la conjunción de los Permisos que representan la excepción.
        	FLFormula exceptionFormInst = exceptionFormOr.instanciar(variable, agent.getName(), bgUtil, true);     	        	
        	rule = new FLOr(rule, exceptionFormInst);
        }
    	return rule;
    }
}
