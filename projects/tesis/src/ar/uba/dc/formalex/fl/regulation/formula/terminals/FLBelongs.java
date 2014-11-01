package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

public class FLBelongs extends FLTerminal {

    public FLBelongs(String variable, String roleName) {
    	super(variable, roleName);
    	setAditionalRole(roleName);
    }

    public FLBelongs(String variable, String agent, String roleName) {
    	super(variable, agent, roleName);
    	setAditionalRole(roleName);
    }

    @Override
	public String toString() {
		return getNameWithAgent() + "belongsTo:" + getName();
	}

	@Override
	public FLFormula instanciar(String variable, String agente, BGUtil bgUtil) {
		FLBelongs res = new FLBelongs(getVariable(), getAgent(), getName());
        if (res.setVariable(variable, agente)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea válida (o sea, que exista la combinación
            // agente.name), si no lo es devuelve null.
            if (!bgUtil.isValid(res.getNameWithAgent()))
                return null;
        }
		return res;
	}

}
