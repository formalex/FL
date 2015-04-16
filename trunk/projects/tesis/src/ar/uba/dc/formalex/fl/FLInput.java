package ar.uba.dc.formalex.fl;


import ar.uba.dc.formalex.fl.bgtheory.*;
import ar.uba.dc.formalex.fl.bgtheory.Timer;
import ar.uba.dc.formalex.fl.regulation.Regulation;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

import java.util.*;

/**
 * User: P_BENEDETTI
 * Date: 19/11/13
 * Time: 23:29
 */
public class FLInput {
    private BackgroundTheory backgroundTheory = new BackgroundTheory();
    private Regulation regulation = new Regulation();
    private List<String> flPermission = new ArrayList<String>();
    private List<String> flRule = new ArrayList<String>();
    private Map<String, Set<Agente>> agentesPorRol;

    public void add(Action a){
        backgroundTheory.add(a);
    }

    public void add(Timer a){
        backgroundTheory.add(a);
    }

    public void add(Interval interval){
        backgroundTheory.add(interval);
    }

    public void addFLPermission(String f){
        flPermission.add(f);
    }

    public List<String> getFlPermission() {
        return flPermission;
    }

    public List<String> getFlRule() {
        return flRule;
    }

    public void addFLRule(String f){
        flRule.add(f);
    }

    public void add(RoleSpecification roles) {
        backgroundTheory.add(roles);
    }

    public void add(Counter counter) {
        backgroundTheory.add(counter);
    }

    public void addRule(FLFormula p) {
        regulation.addRule(p);
    }

    public void addPermission(FLFormula p) {
        regulation.addPermission(p);
    }

    public BackgroundTheory getBackgroundTheory() {
        return backgroundTheory;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setActions(Set<Action> actions) {
        backgroundTheory.setActions(actions);
    }

    public void setTimers(Set<Timer> timers) {
        backgroundTheory.setTimers(timers);
    }

    public void setCounters(Set<Counter> counters) {
        backgroundTheory.setCounters(counters);
    }

    public void setIntervals(Set<Interval> intervals) {
        backgroundTheory.setIntervals(intervals);
    }

    public Set<Interval> getIntervals() {
        return backgroundTheory.getIntervals();
    }

    public Set<Action> getActions() {
        return backgroundTheory.getActions();
    }

    public void setAgentes(Set<Agente> agentes) {
        generarRolesPorAgente(agentes);
        backgroundTheory.setAgentes(agentes);
    }

    public Map<String, Set<Agente>> getAgentesPorRol(){
        return agentesPorRol;
    }

    private void generarRolesPorAgente(Set<Agente> agentes) {
        agentesPorRol = new HashMap<String, Set<Agente>>();

        for(Agente agente : agentes) {
            Set<Role> roles = agente.getRoles();
            for(Role role : roles) {
                Set<Agente> agentesXRol = agentesPorRol.get(role.getName());
                if (agentesXRol == null){
                    agentesXRol = new HashSet<Agente>();
                    agentesPorRol. put(role.getName(), agentesXRol);
                }
                agentesXRol.add(agente);
            }
        }
    }

    public Set<RoleSpecification> getRoles() {
        return backgroundTheory.getRoles();
    }

    public Set<Counter> getCounters() {
        return backgroundTheory.getCounters();
    }

    public Set<Agente> getAgentes() {
        return backgroundTheory.getAgentes();
    }


    public Set<Timer> getTimers() {
        return backgroundTheory.getTimers();
    }


    public Set<String> getNamesWithAgent() {

        //todo: ver qu� pasa con impersonal_actions e intervalos y contadores globales
        Set<String> namesWithAgent = new HashSet<String>();
        for(Action action : getActions()) {
            boolean sinRepetir = namesWithAgent.add(action.getName());
            if (!sinRepetir){
                throw new RuntimeException("Nombre con agente repetido. Revisar");
            }
        }

        for(Interval interval: getIntervals()) {
            boolean sinRepetir = namesWithAgent.add(interval.getName());
            if (!sinRepetir){
                throw new RuntimeException("Nombre con agente repetido. Revisar");
            }
        }

        for(Counter counter: getCounters()) {
            boolean sinRepetir = namesWithAgent.add(counter.getName());
            if (!sinRepetir){
                throw new RuntimeException("Nombre con agente repetido. Revisar");
            }
        }
        return namesWithAgent;
    }

    public List<FLFormula> getRules() {
        return regulation.getRules();
    }

    public List<FLFormula> getPermissions() {
        return regulation.getPermissions();
    }

    public void setPermissions(List<FLFormula> p) {
        regulation.setPermissions(p);
    }

    public void setRules(List<FLFormula> rules) {
        regulation.setRules(rules);
    }
    
	public void addTaggedFormula(String tag, FLFormula formula) throws RuntimeException {
		regulation.addTaggedFormula(tag, formula);
	}
	
	public FLFormula getFLFormulaFromTag(String tag){
		FLFormula formula = regulation.getFLFormulaFromTag(tag);
		if (formula == null){
			throw new RuntimeException("La fórmula a la que hace referencia no existe. Revisar");
		}
		return formula;
	}
}
