package ar.uba.dc.formalex.fl.regulation.rules;

import java.util.HashSet;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;


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
    public String toString() {
        //    O( fórmula ) repaired by  rep → G ( !fórmula -> rep)
        //    O( fórmula ) → G ( fórmula )
        if (hasRepair())
            return "G ( !" + formula.toString() + " -> (" + repair.toString() + ") )";
        else

            return "G ( " + formula.toString() + " )";
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil) {
        FLFormula rep = null;
        FLFormula f = formula.instanciar(variable, agente, bgUtil);
        if (f == null)
            return null;
        if (repair != null)
            rep = repair.instanciar(variable, agente, bgUtil);
        return new Obligation(f, rep);
    }

	public FLFormula getFormula() {
		return formula;
	}

	public void setFormula(FLFormula formula) {
		this.formula = formula;
	}	       
}
