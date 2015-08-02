package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLInterval;

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
    public String toString() {
        /**
         * Tr (<> f1 )          ?    F Tr(f1)
         * Tr (<>_{i} f1 )      ?     i = activo -> (i = activo U Tr(f1) )
         */
        if (interval != null){
            return interval.toString() + " -> (" + interval.toString() + " U " + formula.toString() + ")";
        }
        else
            return "F(" + formula.toString() + ")";
    }
    
    @Override
	public Set<String> getNombresDeComponentes() {
		
    	Set<String> res=this.formula.getNombresDeComponentes();
    	
    	if(this.interval!=null)
    		res.addAll(this.interval.getNombresDeComponentes());
    	
    	return res;
	}
}
