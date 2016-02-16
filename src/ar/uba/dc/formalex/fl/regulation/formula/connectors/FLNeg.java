package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;

public class FLNeg extends FLFormula {

    private FLFormula formula;

    public FLNeg(FLFormula formula) {
        this.formula = formula;
    }

    public FLFormula getFormula(){
        return formula;
    }

    @Override
    public String translateToLTL() {
        return "!( " + formula.translateToLTL() + " )";
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLFormula newFormula = formula.instanciar(variable, agente, bgUtil, forceAgent);
        if (newFormula == null )
            return null;

        return new FLNeg(newFormula);
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
