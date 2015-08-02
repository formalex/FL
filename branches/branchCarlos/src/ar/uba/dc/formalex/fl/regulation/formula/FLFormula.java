package ar.uba.dc.formalex.fl.regulation.formula;

import java.util.HashSet;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.BGUtil;

public abstract class FLFormula {
	
	protected Set<FLFormula> exceptions = new HashSet<FLFormula>();	
	
	/**
	 * @return Devuelve los nombres de las componentes de la bgt que intervienen
	 *         en una formula. Esto es util para filtrar componentes que NO se
	 *         usan en formulas
	 */
	public abstract Set<String> getNombresDeComponentes();
	
    /**
     * devuelve el string que representa a esta fórmula con el formato del model checker
     */
	public abstract String toString();

    /**
     * Crea y devuelve una nueva fórmula.
     * Si el nombre de variable recibido no coincide con ninguna variable de esta fórmula => se
     * devuelve la nueva fórmula similar a this.
     * Si el nombre de variable recibido coincide con alguna variable de esta instancia => reemplaza
     * en la nueva fórmula la variable por el agente recibido. Luego, valida que la combinación de
     * agente.accion (intervalo o contador) sea válida (o sea, que ese agente puede realizar la
     * acción o tenga asociado el intervalo o contador). Si no es válida, devuelve null
     * Si se indica que se debe forzar el agente, se reemplaza en la nueva fórmula la variable 
     * por el agente recibido.
     * @param forceAgent TODO
     */
    public abstract FLFormula instanciar(String variable, String agente, BGUtil bgUtil, Boolean forceAgent);
    
    public Set<FLFormula> getExceptions() {
		return exceptions;
	}

	public void setExceptions(Set<FLFormula> exceptions) {
		this.exceptions = exceptions;
	}
	
	public void addException(FLFormula formula){
		this.exceptions.add(formula);	
	}	     

}