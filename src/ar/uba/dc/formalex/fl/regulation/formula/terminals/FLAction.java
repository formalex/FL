package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;

public class FLAction extends FLTerminal {

	//Por ahora este es el default
	FLActionRepresentation representation = FLActionRepresentation.TRES_ESTADOS;
	
	public FLAction(String variable, String actionName) {
		super(variable, actionName);
	}

	public FLAction(String variable, String agent, String actionName) {
		super(variable, agent,  actionName);
	}
	
	public FLAction(String variable, String agent, String actionName, FLActionRepresentation anRepresentation) {
		super(variable, agent,  actionName);
		representation = anRepresentation;
	}

    @Override
    public FLTerminal instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLAction res = new FLAction(getVariable(), getAgent(), getName(), getRepresentation());
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
    	
    	if(this.getRepresentation().equals(FLActionRepresentation.TRES_ESTADOS))
    		return getNameWithAgent() + " = JUST_HAPPENED";
    	
    	return getNameWithAgent() + " = HAPPENING";
    }
    
	public FLActionRepresentation getRepresentation() {
		return representation;
	}

	public void setRepresentation(FLActionRepresentation representation) {
		this.representation = representation;
	}
}
