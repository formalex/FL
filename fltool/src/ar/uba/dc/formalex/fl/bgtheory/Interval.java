package ar.uba.dc.formalex.fl.bgtheory;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings({"NonFinalFieldReferenceInEquals", "NonFinalFieldReferencedInHashCode", "RedundantIfStatement"})
public class Interval {
    public static final String DEFAULT_TYPE = "local";
    private static final Logger logger = Logger.getLogger(Interval.class);

	private String name;
    private Set<Action> startTriggers = new HashSet<Action>();
    private Set<Action> endTriggers = new HashSet<Action>();
    private Set<Action> startTriggersWithoutRefinement = new HashSet<Action>();
    private Set<Action> endTriggersWithoutRefinement = new HashSet<Action>();
	private Interval occursIn;
	private int occurrences;
    private String type = DEFAULT_TYPE;
    private Role roleForInterval = null;
    private boolean startActive = false; //si es true => el estado inicial del intervalo es activo
    private boolean endActive = false;//si es true =>  una vez que el intervalo es activo, conserva ese valor

    //usado para logueo y debug
    public void logFL(){
        StringBuilder sb = new StringBuilder("interval ");
        sb.append(getName());
        sb.append(" defined by actions ");
        for (Action st : startTriggers) {
            sb.append(st.getName()).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());

        sb.append(" - ");

        for (Action a : endTriggers) {
            sb.append(a.getName()).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());

        if (hasOccursIn()){
            sb.append(" only occurs in scope ");
            sb.append(getOccursIn().getName());
        }
        sb.append(" ").append(type).append(" ");
        logger.info(sb.toString());
    }

    /**
	 * Creates a blank Interval
	 */
	public Interval() {
		super();
		occurrences = 0;
	}

    public Interval clonar() {
        return clonar(getName());
    }

    public Interval clonar(String name) {
        Interval res = new Interval();

        res.setName(name);
        res.type = type;
        res.setOccursIn(getOccursIn());
        res.setStartTriggers(getStartTriggers());
        res.setEndTriggers(getEndTriggers());
        res.setEndActive(isEndActive());
        res.setStartActive(isStartActive());
        res.setOccurrences(getOccurrences());
        res.setRoleForInterval(this.getRoleForInterval());

        return res;
    }

	public Boolean hasOccurrences(){
		return occurrences > 0;
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

    public void addStartTrigger(Action a){
        startTriggers.add(a);
    }
    public void addStartTriggerWithoutRefinement(Action a){
        startTriggersWithoutRefinement.add(a);
    }
    public void addEndTrigger(Action a){
        endTriggers.add(a);
    }
    public void addEndTriggerWithoutRefinement(Action a){
        endTriggersWithoutRefinement.add(a);
    }

    public Set<Action> getStartTriggers() {
        return startTriggers;
    }

    public Set<Action> getStartTriggersWithoutRefinement() {
        return startTriggersWithoutRefinement;
    }

    public Set<Action> getEndTriggersWithoutRefinement() {
        return endTriggersWithoutRefinement;
    }

    public Set<Action> getEndTriggers() {
        return endTriggers;
    }

    public void setStartTriggers(Set<Action> startTriggers) {
        this.startTriggers = startTriggers;
    }

    public void setEndTriggers(Set<Action> endTriggers) {
        this.endTriggers = endTriggers;
    }

    /**
	 * @return the occursIn
	 */
	public Interval getOccursIn() {
		return occursIn;
	}

    public boolean hasOccursIn(){
        return occursIn != null;
    }
	/**
	 * @param occursIn the occursIn to set
	 */
	public void setOccursIn(Interval occursIn) {
		this.occursIn = occursIn;
	}

    public boolean isLocal() {
        return Objects.equals(type, "local");
    }

    public boolean isShared() {
        return Objects.equals(type, "shared");
    }

    public boolean isGlobal() {
        return Objects.equals(type, "global");
    }

    public Role getRoleForInterval() {
        return this.roleForInterval;
    }

    public void setRoleForInterval(Role role) {
        this.roleForInterval = role;
    }

    public void setLocal() {
        this.type = "local";
    }

    public void setShared() {
        this.type = "shared";
    }

    public void setGlobal() {
        this.type = "global";
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }


	public String toString(){
		return name;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (!name.equals(interval.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean isStartActive() {
        return startActive;
    }

    public void setStartActive(boolean startActive) {
        this.startActive = startActive;
    }

    public boolean isEndActive() {
        return endActive;
    }

    public void setEndActive(boolean endActive) {
        this.endActive = endActive;
    }
}
