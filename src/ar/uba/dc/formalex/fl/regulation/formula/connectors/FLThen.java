package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;

public class FLThen extends FormulaConnectorBinary {
    private static final String CONNECTOR = " -> ";

    public FLThen(FLFormula leftFormula, FLFormula rightFormula) {
        super(leftFormula, rightFormula);
    }

    @Override
    public String translateToLTL() {
        String izq = getLeftFormula().translateToLTL();
        String der = getRightFormula().translateToLTL();

        //Si la parte izq no es terminal -> pongo esa parte entre paréntesis.
        //Lo mismo con la parte derecha
        if (ponerParentesis(getLeftFormula()))
            izq =  "( " + izq + " )";

        if (ponerParentesis(getRightFormula()))
            der =  "( " + der + " )";

        return izq + CONNECTOR + der;
    }

    private boolean ponerParentesis(FLFormula lf) {
        return !(lf instanceof FLNeg || lf instanceof FLTerminal );
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLFormula newLeftFormula = getLeftFormula().instanciar(variable, agente, bgUtil, forceAgent);
        FLFormula newRightFormula = getRightFormula().instanciar(variable, agente, bgUtil, forceAgent);
        if (newLeftFormula == null || newRightFormula == null)
            return null;

        return new FLThen(newLeftFormula, newRightFormula);
    }
}
