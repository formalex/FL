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

    private FLFormula expresion;
    private FLFormula condition;
    private boolean mandatory = false;

    public Permission(FLFormula formula) {
        //P( fórmula ) =  !  F( fórmula )
        Forbidden prohibition = new Forbidden(formula);
        this.formula =  new FLNeg(prohibition);
        this.expresion = formula;
    }
    
    public Permission(FLFormula formula, FLFormula condition) {
        //P( formula, condition ) = condition -> ! F(formula)
        this(new FLAnd(condition, formula));
        this.expresion = formula;
        this.condition = condition;
    }

    public Permission(FLFormula formula, FLFormula condition, boolean mandatory) {
        //P( formula, condition ) = condition -> ! F(formula)
        this(formula, condition);
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return this.mandatory;
    }

    public FLFormula getMandatoryRule() {
        return new Forbidden(new FLAnd(new FLNeg(this.condition), this.expresion));
    }

    public FLFormula getExpresion() {
        return this.expresion;
    }

    public FLFormula getCondition() {
        return this.condition;
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
