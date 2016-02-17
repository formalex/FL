package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;

public class FLCounter extends FLTerminal {

    private final FLCounterRelation comparator;
    private final int valueToCompare;

    public FLCounter(String variable, String counterName, FLCounterRelation comparator, int valueToCompare){
        super(variable, counterName);
        this.comparator = comparator;
        this.valueToCompare = valueToCompare;
    }

    @Override
    public String translateToLTL(LTLTranslationType anLTLTranslationType) {
        return getNameWithAgent()
                + comparator.toString() + valueToCompare ;
    }

    @Override
    public FLTerminal instanciar(String variableName, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLCounter res = new FLCounter(getVariable(), getName(), comparator, valueToCompare);
        if (res.setVariable(variableName, agente, forceAgent)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea v�lida (o sea, que exista la combinaci�n
            // agente.name), si no lo es devuelve null.
            if (!bgUtil.isValid(res.getNameWithAgent()))
                return null;
        }

        return res;
    }
}
