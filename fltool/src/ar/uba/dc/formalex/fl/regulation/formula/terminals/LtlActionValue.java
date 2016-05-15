package ar.uba.dc.formalex.fl.regulation.formula.terminals;

/**
 * Este Enum se usa para indicar los posibles valores que puede tomar
 * una acción en una fórmula LTL
 * @author cfaciano
 *
 */
public enum LtlActionValue {

	NOT_HAPPENING("NOT_HAPPENING"),
	HAPPENING("HAPPENING"),
	JUST_HAPPENED("JUST_HAPPENED");
	
	String value;
	
	private LtlActionValue(String anValueInLtlFormula){
		
		this.value = anValueInLtlFormula;
		
	}

	public String getValue() {
		return value;
	}
	
}
