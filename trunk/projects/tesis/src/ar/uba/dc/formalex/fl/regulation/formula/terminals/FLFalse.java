package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

/**
 * User: P_BENEDETTI
 * Date: 11/02/14
 * Time: 00:51
 */
public class FLFalse extends FLTerminal {
    public static final String SIMBOLO = " FALSE ";

    @Override
    public String toString() {
        return SIMBOLO;
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        return this;
    }
}
