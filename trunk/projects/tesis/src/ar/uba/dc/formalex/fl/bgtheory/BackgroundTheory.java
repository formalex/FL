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
    private Set<Roles> roles = new HashSet<Roles>();
    private Set<Counter> counters = new HashSet<Counter>();

    private Set<Agente> agentes;

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

    public void add(Roles rol) {
        roles.add(rol);
    }

    public Set<Roles>  getRoles() {
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
}
