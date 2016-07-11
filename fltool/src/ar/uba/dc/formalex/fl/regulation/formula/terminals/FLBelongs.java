package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import java.util.HashSet;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;

public class FLBelongs extends FLTerminal {

    public FLBelongs(String variable, String roleName) {
    	super(variable, roleName);    	
    }

    public FLBelongs(String variable, String agent, String roleName) {
    	super(variable, agent, roleName);    	
    }

    @Override
	public String translateToLTL(LTLTranslationType anLTLTranslationType) {
		return getNameWithAgent() + "belongsTo:" + getName();
	}

	@Override
	public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
		FLFormula res = new FLFalse();
		if(bgUtil.belongsToRole(agente, this.getName())){
			res = new FLTrue();
		}		
		return res;
	}
	
	@Override
	public Set<String> getNombresDeComponentes() {		
    	Set<String> res=new HashSet<String>();	
    	return res;
	}

}