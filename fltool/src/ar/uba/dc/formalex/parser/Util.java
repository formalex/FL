package ar.uba.dc.formalex.parser;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.*;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;
import ar.uba.dc.formalex.fl.regulation.permission.PermanentPermission;
import ar.uba.dc.formalex.fl.regulation.permission.Permission;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;
import ar.uba.dc.formalex.fl.regulation.rules.Obligation;

/**
 * User: P_BENEDETTI
 * Date: 17/02/14
 * Time: 13:05
 */
public class Util {


    public static boolean hasPermission(FLFormula f){
        if (f instanceof Permission || f instanceof PermanentPermission)
            return true;
        else if (f instanceof FLTerminal || f instanceof Forbidden || f instanceof Obligation)
            return false;
        else if (f instanceof FLQuantifier)
            return hasPermission( ((FLQuantifier)f).getFormula() );
        else if (f instanceof FormulaConnectorBinary)
            return hasPermission( ((FormulaConnectorBinary)f).getLeftFormula() )
                    | hasPermission( ((FormulaConnectorBinary)f).getRightFormula() );
        else  if (f instanceof FLNeg)
            return hasPermission( ((FLNeg)f).getFormula() );
        else  if (f instanceof FLBox)
            return hasPermission( ((FLBox)f).getFormula() );
        else  if (f instanceof FLDiamond)
            return hasPermission( ((FLDiamond)f).getFormula() );

        throw new RuntimeException("Util.hasPermission: falta implementar......: " + f.getClass());
    }
    
}
