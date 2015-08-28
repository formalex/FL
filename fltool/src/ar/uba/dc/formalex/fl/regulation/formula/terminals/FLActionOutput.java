package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

public class FLActionOutput extends FLAction {
	private String output;

	public FLActionOutput(String variable, String actionName, String output) {
        super(variable, actionName);
		this.output = output;
	}
	
	public FLActionOutput(String variable, String agent, String actionName, String output) {
		super(variable, agent,  actionName);
		this.output = output;
	}


    @Override
    public String toString() {
        return getNameWithAgent() + "_OUTPUT = " + output;
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLActionOutput res = new FLActionOutput(getVariable(), getName(), output);
        if (res.setVariable(variable, agente, forceAgent)){
            //Si no la puede instanciar, no pasa nada y queda con el valor en null, pero si la
            // instancia hay que validar que sea válida (o sea, que exista la combinación
            // agente.name), si no lo es devuelve null.

        	//Si la acción es sincronizada hay que representarla como una disyunción de posibles acciones
        	return instanciarSincronizadasHelper(variable, agente, bgUtil, res);
        }

        return res;
    }
    
    @Override
    protected FLAction getDisyunto(String agente, String name, FLTerminal modelo)
    {
    	return new FLActionOutput(null, agente, name, ((FLActionOutput)modelo).output);
    }
}
