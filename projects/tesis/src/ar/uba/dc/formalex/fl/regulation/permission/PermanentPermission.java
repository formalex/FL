package ar.uba.dc.formalex.fl.regulation.permission;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.rules.Obligation;

public class PermanentPermission extends FLFormula {
	private FLFormula formula;
	
	public PermanentPermission(FLFormula formula) {
        //O(P( fórmula ))
        Permission permission = new Permission(formula);
        this.formula =  new Obligation(permission);        
    }

	public PermanentPermission(FLFormula formula, FLFormula condition) {
        //O(P( fórmula ))
        Permission permission = new Permission(formula, condition);
        this.formula =  new Obligation(permission);        
    }

	@Override
	public String toString() {
		return formula.toString();
	}

	@Override
	public FLFormula instanciar(String variable, String agente, BGUtil bgUtil,
			Boolean forceAgent) {
		return formula.instanciar(variable, agente, bgUtil, forceAgent);
	}

}
