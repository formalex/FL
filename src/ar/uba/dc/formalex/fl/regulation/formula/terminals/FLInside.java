package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;

public class FLInside extends FLTerminal {
    private FLInterval interval;

    public FLInside(FLInterval interval) {
        this.interval = interval;
    }

    @Override
    public FLInside instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLInterval intervaloInstanciado = interval.instanciar(variable, agente, bgUtil, forceAgent);
        if (intervaloInstanciado == null)
            return null;
        return new FLInside(intervaloInstanciado);
    }

    @Override
    public String translateToLTL(LTLTranslationType anLTLTranslationType) {
        return interval.getNameWithAgent() + " = ACTIVE ";
    }
    
    @Override
	public Set<String> getNombresDeComponentes() {
		
		return this.interval.getNombresDeComponentes();
	}
}
