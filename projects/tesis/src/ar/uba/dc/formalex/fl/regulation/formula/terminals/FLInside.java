package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;

public class FLInside extends FLTerminal {
    private FLInterval interval;

    public FLInside(FLInterval interval) {
        this.interval = interval;
    }

    @Override
    public FLInside instanciar(String variable, String agente, BGUtil bgUtil) {
        FLInterval intervaloInstanciado = interval.instanciar(variable, agente, bgUtil);
        if (intervaloInstanciado == null)
            return null;
        return new FLInside(intervaloInstanciado);
    }

    @Override
    public String toString() {
        return interval.getNameWithAgent() + " = ACTIVE ";
    }
}
