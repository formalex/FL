package ar.uba.dc.formalex.fl.regulation.permission;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLThen;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;


public class Permission extends FLFormula {
	private FLFormula formula;

    private FLFormula mandatoryRule = null;

    public Permission(FLFormula formula) {
        //P( fórmula ) =  !  F( fórmula )
        Forbidden prohibition = new Forbidden(formula);
        this.formula =  new FLNeg(prohibition);
    }

    public Permission(FLFormula formula, FLFormula condition) {
        //P( formula, condition ) = condition -> ! F(formula) = P(C & f)
        this(new FLAnd(condition, formula));
    }

    public Permission(FLFormula formula, FLFormula condition, boolean mandatory) {
        this(formula, condition);
        if(mandatory) {
            this.mandatoryRule = new Forbidden(new FLAnd(new FLNeg(condition), formula));
        }
    }

    public boolean isMandatory() {
        return this.mandatoryRule != null;
    }

    public FLFormula getMandatoryRule() {
        return this.mandatoryRule;
    }

    @Override
    public String translateToLTL(LTLTranslationType anLTLTranslationType) {
        return formula.translateToLTL(anLTLTranslationType );
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        return formula.instanciar(variable, agente, bgUtil, forceAgent);
    }

	public FLFormula getFormula() {
		return formula;
	}
	
	@Override
	public Set<String> getNombresDeComponentes() {
		
		return this.formula.getNombresDeComponentes();
	}
	
	@Override
	public Set<FLTerminal> getTerminals() {
		
		return this.formula.getTerminals();
	}

}
