package ar.uba.dc.formalex.fl.regulation.rules;

import java.util.HashSet;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;


public class Forbidden extends FLFormula {
    private FLFormula formula;

    public Forbidden(FLFormula formula, FLFormula repair) {
        //F( fórmula ) = O( !fórmula )
        //F( fórmula ) repaired by reparación = O( !fórmula ) repaired by  reparación
        this.formula = new Obligation(new FLNeg(formula), repair);
        this.exceptions = new HashSet<FLFormula>();       
    }

	public Forbidden(FLFormula formula) {
		this(formula, null);
	}

    @Override
    public String translateToLTL() {
        return formula.translateToLTL();
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
