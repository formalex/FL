package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;

public class FLActionOutput extends FLTerminal {
	private String output;

	public FLActionOutput(String variable, String actionName, String output) {
        super(variable, actionName);
		this.output = output;
	}

    @Override
    public String toString() {
        return getNameWithAgent() + "_OUTPUT = " + output;
    }

    @Override
    public FLActionOutput instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLActionOutput res = new FLActionOutput(getVariable(), getName(), output);
        if (res.setVariable(variable, agente, forceAgent)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea válida (o sea, que exista la combinación
            // agente.name), si no lo es devuelve null.
            if (!bgUtil.isValid(res.getNameWithAgent()))
                return null;
        }

        return res;
    }

	public String getOutput() {
		return output;
	}
}
