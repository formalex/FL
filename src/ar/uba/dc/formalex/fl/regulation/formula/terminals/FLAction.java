package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;

public class FLAction extends FLTerminal {

	//Sirve para saber de qué forma se referencia a la acción en una formula
	//Por ahora este es el default
	ActionReferencedState referencedState = ActionReferencedState.JUST_HAPPENED;
	
	public FLAction(String variable, String actionName) {
		super(variable, actionName);
	}

	public FLAction(String variable, String agent, String actionName) {
		super(variable, agent,  actionName);
	}
	
	public FLAction(String variable, String agent, String actionName, ActionReferencedState anReferencedState) {
		super(variable, agent,  actionName);
		referencedState = anReferencedState;
	}

    @Override
    public FLTerminal instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLAction res = new FLAction(getVariable(), getAgent(), getName(), getReferencedState());
        if (res.setVariable(variable, agente, forceAgent)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea válida (o sea, que exista la combinación
            // agente.name), si no lo es devuelve null.
            if (!bgUtil.isValid(res.getNameWithAgent()))
                return null;
        }

        return res;
    }

	@Override
    public String toString() {
		
		return getNameWithAgent() + " = " + this.getReferencedState().getValueInLtlFormula();
    }
	
    public ActionReferencedState getReferencedState() {
		return referencedState;
	}

	public void setReferencedState(ActionReferencedState referencedState) {
		this.referencedState = referencedState;
	}
}
