package ar.uba.dc.formalex.fl.regulation.formula.terminals;

/**
 * Este Enum se usa para indicar de qué forma es referenciada una accion
 * en una formula.
 * Al momento hay dos formas: que la accion esté en el estado HAPPENING
 *  o qué la acción esté en el estado JUST_HAPPENED
 * @author cfaciano
 *
 */
public enum ActionReferencedState {
	IS_HAPPENING("HAPPENING"),
	JUST_HAPPENED("JUST_HAPPENED");
	
	String valueInLtlFormula;
	
	private ActionReferencedState(String anValueInLtlFormula){
		
		this.valueInLtlFormula = anValueInLtlFormula;
		
	}

	public String getValueInLtlFormula() {
		return valueInLtlFormula;
	}
}
