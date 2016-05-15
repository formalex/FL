package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLInterval;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;

public class FLDiamond extends FLFormula {
    private FLFormula formula;
    private FLInterval interval;

    public FLDiamond(FLFormula formula) {
        this(null,formula);
    }

    public FLDiamond(FLInterval interval, FLFormula formula) {
        this.interval = interval;
        this.formula = formula;
    }

    @Override
    public FLDiamond instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLInterval newInterval = null;
        FLFormula newFormula;
        if (interval != null){
            newInterval = interval.instanciar(variable, agente, bgUtil, forceAgent);
            if (newInterval == null)
                return null;
        }
        newFormula = formula.instanciar(variable, agente,  bgUtil, forceAgent);
        if (newFormula == null)
            return null;

        return new FLDiamond(newInterval,  newFormula);
    }

    public FLFormula getFormula() {
        return formula;
    }

    @Override
    public String translateToLTL(LTLTranslationType anLTLTranslationType) {
        /**
         * Tr (<> f1 )          ?    F Tr(f1)
         * Tr (<>_{i} f1 )      ?     i = activo -> (i = activo U Tr(f1) )
         */
        if (interval != null){
            return interval.translateToLTL(anLTLTranslationType ) + " -> (" + interval.translateToLTL(anLTLTranslationType ) + " U " + formula.translateToLTL(anLTLTranslationType ) + ")";
        }
        else
            return "F(" + formula.translateToLTL(anLTLTranslationType) + ")";
    }
    
    @Override
	public Set<String> getNombresDeComponentes() {
		
    	Set<String> res=this.formula.getNombresDeComponentes();
    	
    	if(this.interval!=null)
    		res.addAll(this.interval.getNombresDeComponentes());
    	
    	return res;
	}

	@Override
	public Set<FLTerminal> getTerminals() {
		Set<FLTerminal> res=this.formula.getTerminals();
    	
    	if(this.interval!=null)
    		res.addAll(this.interval.getTerminals());
    	
    	return res;
	}
}
