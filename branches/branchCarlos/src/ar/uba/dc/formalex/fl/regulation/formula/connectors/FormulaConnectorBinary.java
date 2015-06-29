package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;


public abstract class FormulaConnectorBinary extends FLFormula {
	private FLFormula leftFormula;
	private FLFormula rightFormula;

	public FormulaConnectorBinary(FLFormula leftFormula, FLFormula rightFormula) {
		super();
		this.leftFormula = leftFormula;
		this.rightFormula = rightFormula;
	}

    public FLFormula getLeftFormula() {
		return leftFormula;
	}

    public FLFormula getRightFormula() {
		return rightFormula;
	}

}
