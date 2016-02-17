package ar.uba.dc.formalex.fl.regulation.formula;

/**
 * Este Enum se usa para indicar de qué forma se va a traducir 
 * Una flAction a LTL
 * Al momento hay dos formas: 
 * 1- Que la acción esté en el estado JUST_HAPPENED. accion = JUST_HAPPENED
 * 2- Reescribir el estado JH para prescindir de él. accion = HAPPENING & next(accion) = NOT_HAPPENING
 * @author cfaciano
 *
 */
public enum LTLTranslationType {
	WITH_JH("WITH JUST HAPPENED"),
	WITH_NEXT_FOR_JH(" WITH NEXT FOR JUST HAPPENED");
	
	/**
	 * Mantiene una descripción mas legible para el humano
	 */
	String descripcion;
	
	private LTLTranslationType(String unaDescripcion){
		
		this.descripcion = unaDescripcion;
		
	}

	public String getDescripcion() {
		return descripcion;
	}
}
