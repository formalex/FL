package ar.uba.dc.formalex.fl.bgtheory;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cfaciano
 *
 */
@SuppressWarnings({"RedundantIfStatement", "NonFinalFieldReferencedInHashCode"})
public class Action {
    private static final Logger logger = Logger.getLogger(Action.class);
	
    private String name;
	private boolean isImpersonal;

	/**
	 * A list of the output values of performing the action
	 * (null if the Action does not have output values)
	 */
	private Set<String> outputValues;
	
	private Set<Role> performableBy = new HashSet<Role>();

	/**
	 * The Interval in which this action can be performed
	 * (null if it does not apply)
	 */
	private Interval occursIn;
	private int occurrences;

	/**
	 * The Synchronization this Action is involved in
	 */
	private Action sync;
    private boolean isActive = false;
    public static final boolean ALLOW_AUTO_SYNC_DEFAULT = true;
    private boolean allowAutoSync = ALLOW_AUTO_SYNC_DEFAULT;

    //El default es que la acción se represente en el automata con 3 estados
	private ActionRepresentation representation= ActionRepresentation.THREE_STATES;
	
	public Action clonar() {
        return clonar(getName());
    }

	public Action clonar(String nuevoNombre) {
        Action res = new Action();
        res.setName(nuevoNombre);
        res.setImpersonal(isImpersonal);
        res.setOutputValues(getOutputValues());
        res.setPerformableBy(getPerformableBy());
        res.setOccursIn(getOccursIn());
        res.setOccurrences(getOccurrences());
        res.setSync(getSync(), hasActiveSync()); 
        res.setAllowAutoSync(isAllowAutoSync());
        res.setRepresentation(getRepresentation());
        
        
        return res;
	}

	public Action() {
	}

	/**
	 * @return whether the Action must happen in an interval or not
	 */
	public Boolean hasOccursIn(){
		return this.occursIn != null;
	}
	
//	/**
//	 * @return whether or not the Action is the start trigger for some intervals
//	 */
//	public Boolean hasIntervalsBegin(){
//		return !this.intervalsBegin.isEmpty();
//	}
//
//	/**
//	 * @return whether or not the Action is the end trigger for some intervals
//	 */
//	public Boolean hasIntervalsEnd(){
//		return !this.intervalsEnd.isEmpty();
//	}
//
	public Boolean hasOccurrences(){
		return occurrences > 0;
	}
	
	public Boolean hasOutputValues(){
		return outputValues != null && !outputValues.isEmpty();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the outputValues
	 */
	public Set<String> getOutputValues() {
		return outputValues;
	}	/**
	 * @return the outputValues
	 */

	public String getOutputValuesCSV() {
        StringBuilder res = new StringBuilder();
        for (String ov : outputValues) {
            res.append(ov).append(", ");
        }

        String s = res.toString();
        if (!s.isEmpty()){
            s = s.substring(0, s.length() - 2);
        }
        return s;
	}
	/**
	 * @param outputValues the list of output values to set
	 */
	public void setOutputValues(Set<String> outputValues) {
		this.outputValues = outputValues;
	}
	/**
	 * @return the list of roles than can perform the action
	 */
	public Set<Role> getPerformableBy() {
		return performableBy;
	}
	/**
	 * @param performableBy the list of roles able to perform the action to set
	 */
	public void setPerformableBy(Set<Role> performableBy) {
		this.performableBy = performableBy;
	}

	public void addPerformableBy(Role aRole) {
        performableBy.add(aRole);
	}
    /**
     * Una accion sin rol definido es una accion que NO es impersonal
     * y no tiene roles asociados, eso quiere decir que la puede ejecutar
     * cualquier agente
     * @return
     */
    public boolean isSinRolDefinido(){
    	
    	Set<Role> rolesDeLaAccion = this.getPerformableBy();
		return !this.isImpersonal() && rolesDeLaAccion.isEmpty();
    }

	/**
	 * @return the occursIn
	 */
	public Interval getOccursIn() {
		return occursIn;
	}
	/**
	 * @param occursIn the occursIn to set
	 */
	public void setOccursIn(Interval occursIn) {
		this.occursIn = occursIn;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        //noinspection NonFinalFieldReferenceInEquals
        if (name != null ? !name.equals(action.name) : action.name != null) return false;

        return true;
    }
    

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String toString(){
		return name;
	}

	/**
	 * Is true if the action is synchronized (has a sync object)
	 * @return sync!=null
	 */
	public Boolean hasActiveSync(){
		return sync != null && isActive;
	}

	public Boolean hasPasiveSync(){
		return sync != null && !(isActive);
	}

	
	/**Devuelve true si es una accion sincronizada, sin importar
	 * si es Activa o Pasiva
	 * @return
	 */
	public Boolean isSync(){
		return sync != null;
	}

    public Action getSync() {
        return sync;
    }

    public void setSync(Action sync, boolean isActive) {
        this.sync = sync;
        this.isActive = isActive;
    }

    public boolean isAllowAutoSync() {
        return allowAutoSync;
    }

    public void setAllowAutoSync(boolean allowAutoSync) {
        this.allowAutoSync = allowAutoSync;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public boolean isImpersonal() {
        return isImpersonal;
    }

    public void setImpersonal(boolean impersonal) {
        isImpersonal = impersonal;
    }

    public ActionRepresentation getRepresentation() {
		return representation;
	}

	public void setRepresentation(ActionRepresentation representation) {
		this.representation = representation;
	}
	
	public boolean hasTwotSates(){
		
		return ActionRepresentation.TWO_STATES.equals(this.getRepresentation());
		
	}

	public String logFL(){
        StringBuilder sb;
        if (isImpersonal())
            sb = new StringBuilder("impersonal action ");
        else
            sb = new StringBuilder("action ");
        sb.append(getName() + " ");

        if (hasOutputValues()){
            sb.append("output values ").append(getOutputValuesCSV() + " ");
        }
        if (hasOccursIn()){
            sb.append("only occurs in scope ").append(getOccursIn().getName()+ " ");
        }
        if (hasOccurrences())
            sb.append("occurrences " + getOccurrences() + " ");
        if (hasActiveSync()){
            sb.append("synchronizes with ").append(getSync().getName());
            if( allowAutoSync)
                sb.append(" allow autosync ");
            else
                sb.append(" disallow autosync ");
        }

        if (performableBy != null && performableBy.size() > 0){
            sb.append("performable by ");
            for (Role role : performableBy) {
                sb.append(role.getName()).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());

        }

        sb.append(" " + getRepresentation());
        logger.info(sb.toString());
        return sb.toString();
    }
}