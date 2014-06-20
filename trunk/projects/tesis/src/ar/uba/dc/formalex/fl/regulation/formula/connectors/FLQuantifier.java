package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;


public abstract class FLQuantifier extends FLFormula{
	private String role;
	private String variable;
	private FLFormula formula;

    public String getRole() {
		return role;
	}

	public String getVariable() {
		return variable;
	}

    public FLFormula getFormula() {
        return formula;
    }

    public FLQuantifier(String variable, String role, FLFormula formula) {
		this.role = role;
		this.variable = variable;
        this.formula = formula;
	}
}
