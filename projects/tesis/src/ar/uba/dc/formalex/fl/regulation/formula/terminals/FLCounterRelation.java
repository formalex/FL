package ar.uba.dc.formalex.fl.regulation.formula.terminals;

/**
 * User: P_BENEDETTI
 * Date: 08/02/14
 * Time: 19:39
 */
public enum FLCounterRelation {
    EQUAL, GREATER_OR_EQUAL, GREATER, LESS, LESS_OR_EQUAL;

    public String toString(){
        switch(this) {
            case EQUAL:   return " = ";
            case GREATER:  return " > ";
            case GREATER_OR_EQUAL:  return " >= ";
            case LESS: return " < ";
            case LESS_OR_EQUAL: return " <= ";
        }
        return "error";
    }

}
