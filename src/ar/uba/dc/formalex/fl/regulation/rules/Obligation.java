package ar.uba.dc.formalex.fl.regulation.rules;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLOr;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;


public class Obligation extends FLFormula{
    private FLFormula formula;
    private FLFormula repair;

    public Obligation(FLFormula formula) {
        this(formula, null);
    }

    public Obligation(FLFormula formula, FLFormula repair) {
        this.formula = formula;
        this.repair = repair;
        this.exceptions = new HashSet<FLFormula>();        
    }

    public Boolean hasRepair(){
        return repair != null;
    }

    @Override
    public String translateToLTL() {
        //    O( fórmula ) repaired by  rep → G ( !fórmula -> rep)
        //    O( fórmula ) → G ( fórmula )
        if (hasRepair())
            return "G ( !" + formula.translateToLTL() + " -> (" + repair.translateToLTL() + ") )";
        else
            return "G ( " + formula.translateToLTL() + " )";
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent) {
        FLFormula rep = null;
        FLFormula f = formula.instanciar(variable, agente, bgUtil, forceAgent);
        if (f == null)
            return null;
        if (repair != null)
            rep = repair.instanciar(variable, agente, bgUtil, forceAgent);
        if (exceptions != null && !exceptions.isEmpty()){ // se realiza la disyunción de todos los Permisos que son excepciones a la regla
        	Iterator<FLFormula> exceptionsFormulas = exceptions.iterator();
        	FLFormula exceptionFormOr = exceptionsFormulas.next(); 
        	while (exceptionsFormulas.hasNext()){
        		FLFormula next = exceptionsFormulas.next();
        		exceptionFormOr = new FLOr(exceptionFormOr, next);
        	}
        	/* Se instancia con los agentes la conjunción de los Permisos que representan la excepción. Los
        	 * agentes son forzados para evitar errores de instanciacion.*/
        	FLFormula exceptionFormInst = exceptionFormOr.instanciar(variable, agente, bgUtil, true);        	        	
        	return new Obligation(new FLOr(f, exceptionFormInst), rep);
        }else{
        	return new Obligation(f, rep);
        }        	        
    }

	public FLFormula getFormula() {
		return formula;
	}

	public void setFormula(FLFormula formula) {
		this.formula = formula;
	}
	
	@Override
	public Set<String> getNombresDeComponentes() {
		
    	Set<String> res=this.formula.getNombresDeComponentes();
    	
    	if(this.hasRepair())
    		res.addAll(this.repair.getNombresDeComponentes());
    	
    	return res;
	}
	
	@Override
	public Set<FLTerminal> getTerminals() {
		Set<FLTerminal> res=this.formula.getTerminals();
    	
    	if(this.hasRepair())
    		res.addAll(this.repair.getTerminals());
    	
    	return res;
	}
	
}
