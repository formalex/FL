package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLOr;

public class FLAction extends FLTerminal {

	public FLAction(String variable, String actionName) {
		super(variable, actionName);
	}

	public FLAction(String variable, String agent, String actionName) {
		super(variable, agent,  actionName);
	}

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        
    	FLAction res = new FLAction(getVariable(), getAgent(), getName());
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
    public String toString() {
        return getNameWithAgent() + " = JUST_HAPPENED";
    }

    /**
	 * Instancia una fórmula teniendo en cuenta si es una acción sincronizada. Se usa @res de modelo.
	 */
	protected FLFormula instanciarSincronizadasHelper(String variable, String agente, BGUtil bgUtil, FLTerminal res) {
		Action a = new Action();
		a.setName(res.getNameWithAgent());
		
		if (bgUtil.getAccionesSincronizadas().containsKey(a))
		{
			Set<Action> acciones = bgUtil.getAccionesSincronizadas().get(a);
			return buildSyncAction(res, acciones, variable, agente);
		}
		else
		{
		    if (!bgUtil.isValid(res.getNameWithAgent()))
		        return null;
		    else
		    	return res;
		}
	}

    
    /**
     * Una referencia a una acción sincronizada se traduce en la disyunción de todas las posibles acciones aplanadas
     * que representan al par concreto agenteA.accionA-agenteB.accionB
     * */
    private FLFormula buildSyncAction(FLTerminal res, Set<Action> accionesSync, String variable, String agente)
    {
		FLFormula syncRes = null;
		List<FLFormula> disyuntos = new LinkedList<FLFormula>();
		
		for (Action a: accionesSync)
		{
			FLAction disyunto = getDisyunto(agente, a.getName(), res);
			disyuntos.add(disyunto);
		}
		
		Stream<FLFormula> st = disyuntos.stream();
		syncRes = st.reduce(new FLFalse(), FLOr::new);

		return syncRes;
	}

    /**
     * Devuelve el disyunto usando @modelo de modelo
     * */
    protected FLAction getDisyunto(String agente, String name, FLTerminal modelo)
    {
    	return new FLAction(null, agente, name);
    }
    
}
