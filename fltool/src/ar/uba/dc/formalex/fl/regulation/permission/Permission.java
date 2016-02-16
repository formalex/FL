package ar.uba.dc.formalex.fl.regulation.permission;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;


public class Permission extends FLFormula {
	private FLFormula formula;
    
    public Permission(FLFormula formula) {
        //P( fórmula ) =  !  F( fórmula )
        Forbidden prohibition = new Forbidden(formula);
        this.formula =  new FLNeg(prohibition);        
    }
    
    public Permission(FLFormula formula, FLFormula condition) {
        //P( formula, condition ) = ! F(condition & formula)        
        FLAnd permissionAndCondition = new FLAnd(condition, formula);
        this.formula = new Permission(permissionAndCondition);        
    }

    @Override
    public String toString() {
        return formula.toString();
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
    	FLFormula res = formula.instanciar(variable, agente, bgUtil, forceAgent);
        return res;
    }

}
