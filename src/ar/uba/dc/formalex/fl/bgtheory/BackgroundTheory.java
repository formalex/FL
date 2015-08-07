package ar.uba.dc.formalex.fl.bgtheory;


import java.util.HashSet;
import java.util.Set;

/**
 * User: P_BENEDETTI
 * Date: 19/11/13
 * Time: 23:29
 */
public class BackgroundTheory {
    private Set<Action> actions = new HashSet<Action>();
    private Set<Timer> timers = new HashSet<Timer>();
    private Set<Interval> intervals = new HashSet<Interval>();
    private Set<RoleSpecification> roles = new HashSet<RoleSpecification>();
    private Set<Counter> counters = new HashSet<Counter>();

    private Set<Agente> agentes;
    
    public BackgroundTheory clonar() {
    	BackgroundTheory res = new BackgroundTheory();
        
    	for (Action action : actions) {
			res.add(action.clonar());
		}
    	
    	for (Timer unTimer : timers) {
    		res.add(unTimer.clonar());
		}
    	
    	for (Interval unInterval : intervals) {
			res.add(unInterval.clonar());
		}  	
    	//Los roles como no los voy a tocar, no me interesa clonarlos!
    	for (Counter unCounter : counters) {
			res.add(unCounter.clonar());
		}
    	
    	Set<Agente> agentesClonados = new HashSet<Agente>();    	
    	if(this.agentes!=null){
	    	for (Agente unAgente : this.getAgentes()) {
	    		agentesClonados.add(unAgente.clonar());
			}
    	}	
    	res.setAgentes(agentesClonados);
    	
        return res;
	}

    public void add(Action a){
        actions.add(a);
    }

    public void add(Timer a){
        timers.add(a);
    }

    public void add(Interval interval){
        intervals.add(interval);
    }


    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Timer> getTimers() {
        return timers;
    }

    public void setTimers(Set<Timer> timers) {
        this.timers = timers;
    }

    public Set<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(Set<Interval> intervals) {
        this.intervals = intervals;
    }

    public void add(RoleSpecification rol) {
        roles.add(rol);
    }

    public Set<RoleSpecification>  getRoles() {
        return roles;
    }

    public void setAgentes(Set<Agente> agentes) {
        this.agentes = agentes;
    }

    public Set<Agente> getAgentes() {
        return agentes;
    }

    public Set<Counter> getCounters() {
        return counters;
    }

    public void setCounters(Set<Counter> counters) {
        this.counters = counters;
    }

    public void add(Counter counter) {
        counters.add(counter);
    }

	public void removeActionByName(String name) {

		for (Action unAction : actions) {
			if(unAction.getName().equals(name)){
				actions.remove(unAction);
				break;
			}
		}
		
	}

	public void removeIntervalByName(String name) {
		
		for (Interval unInterval : intervals) {
			if(unInterval.getName().equals(name)){
				intervals.remove(unInterval);
				break;
			}
		}
		
	}

	public void removeCounterByName(String name) {
		
		for (Counter unCounter : counters) {
			if(unCounter.getName().equals(name)){
				counters.remove(unCounter);
				break;
			}
		}
		
	}

	public void removeAgenteByName(String name) {

		if(this.getAgentes()==null)
			return;
		
		for (Agente unAgente : agentes) {
			if(unAgente.getName().equals(name)){
				agentes.remove(unAgente);
				break;
			}
		}
		
	}
}
