package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.fl.regulation.permission.Permission;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;
import ar.uba.dc.formalex.fl.regulation.rules.Obligation;

public class FLAnd extends FormulaConnectorBinary {

    private static final String CONNECTOR = " & ";

    public FLAnd(FLFormula leftFormula, FLFormula rightFormula) {
		super(leftFormula, rightFormula);
	}

    @Override
    public String translateToLTL() {
        FLFormula lf = getLeftFormula();
        String izq = lf.translateToLTL();
        FLFormula rf = getRightFormula();
        String der = rf.translateToLTL();
        //Si la parte izq no es terminal ni FLAnd -> pongo esa parte entre paréntesis.
        //Lo mismo con la parte derecha
        if (ponerParentesis(lf))
            izq =  "( " + izq + " )";

        if (ponerParentesis(rf))
            der =  "( " + der + " )";


        //si es ( TRUE & form ) devuelve form
        if (izq.trim().equals(FLTrue.SIMBOLO.trim()))
            return der;
        else if (der.trim().equals(FLTrue.SIMBOLO.trim()))
            return izq;

        return izq + CONNECTOR + der;
    }

    private boolean ponerParentesis(FLFormula lf) {
        return !(lf instanceof FLNeg || lf instanceof FLAnd || lf instanceof FLTerminal || lf instanceof Permission
                || lf instanceof Forbidden || lf instanceof Obligation);
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLFormula newLeftFormula = getLeftFormula().instanciar(variable, agente, bgUtil, forceAgent);
        FLFormula newRightFormula = getRightFormula().instanciar(variable, agente, bgUtil, forceAgent);
        if (newLeftFormula == null || newRightFormula == null)
            return null;

        return new FLAnd(newLeftFormula, newRightFormula);
    }
	
	
}
