package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

public class FLInterval extends FLTerminal {

	public FLInterval(String intervalName) {
        this(null, intervalName);
	}

	public FLInterval(String variable, String intervalName) {
        super(variable, intervalName);
	}

    @Override
    public FLInterval instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLInterval res = new FLInterval(getVariable(), getName());
        if (res.setVariable(variable, agente, forceAgent)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea v�lida (o sea, que exista la combinaci�n
            // agente.name), si no lo es devuelve null.
            if (!bgUtil.isValid(res.getNameWithAgent()))
                return null;
        }
        return res;
    }

    @Override
    public String translateToLTL() {
        return getNameWithAgent() + " = ACTIVE ";
    }
}
