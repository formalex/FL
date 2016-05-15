package ar.uba.dc.formalex.fl.regulation.permission;

import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;
import ar.uba.dc.formalex.fl.regulation.rules.Obligation;

public class PermanentPermission extends FLFormula {
	private FLFormula formula;
	
	public PermanentPermission(FLFormula formula) {
        //O(P( fórmula ))
        Permission permission = new Permission(formula);
        this.formula =  new Obligation(permission);        
    }

	public PermanentPermission(FLFormula formula, FLFormula condition) {
        //O(P( fórmula ))
        Permission permission = new Permission(formula, condition);
        this.formula =  new Obligation(permission);        
    }

	@Override
	public String translateToLTL(LTLTranslationType anLTLTranslationType) {
		return formula.translateToLTL(anLTLTranslationType );
	}

	@Override
	public FLFormula instanciar(String variable, String agente, BGUtil bgUtil,
			Boolean forceAgent) {
		return formula.instanciar(variable, agente, bgUtil, forceAgent);
	}

	public FLFormula getFormula() {
		return formula;
	}
	
	@Override
	public Set<String> getNombresDeComponentes() {
		
		return this.formula.getNombresDeComponentes();
	}
	
	@Override
	public Set<FLTerminal> getTerminals() {
		
		return this.formula.getTerminals();
	}

}
