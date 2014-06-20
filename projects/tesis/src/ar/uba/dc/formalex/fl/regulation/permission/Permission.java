package ar.uba.dc.formalex.fl.regulation.permission;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;


public class Permission extends FLFormula {
	private FLFormula formula;

    public Permission(FLFormula formula) {
        //P( fórmula ) =  !  F( fórmula )
        Forbidden prohibition = new Forbidden(formula);
        this.formula =  new FLNeg(prohibition);
    }

    @Override
    public String toString() {
        return formula.toString();
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil) {
        return formula.instanciar(variable, agente, bgUtil);
    }

}
