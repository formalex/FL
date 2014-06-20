package ar.uba.dc.formalex.fl.bgtheory;


import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Counter {
    private static final Logger logger = Logger.getLogger(Counter.class);
    private String name;
    private  boolean local;
    private int initValue = 0;
    private Map<Action, Integer> increaseActions = new HashMap<Action, Integer>();
    private Map<Action, Integer> setValueActions = new HashMap<Action, Integer>();

    private Map<Action, String> conditions = new HashMap<Action, String>();

    public Counter() {
    }

    public Counter(String name) {
        this.name = name;
    }

    //Devuelve todas las acciones que pueden modificar al contador
    public Set<Action> getAllActions(){
        Set<Action> res = new HashSet<Action>();

        res.addAll(increaseActions.keySet());
        res.addAll(setValueActions.keySet());

        return res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    public void addSetValueAction(Action a, Integer value, String providedThat){
        setValueActions.put(a, value);
        addProvidedThat(a, providedThat);
    }

    private void addProvidedThat(Action action, String providedThat) {
        if (providedThat != null){
            String oldValue = conditions.put(action, providedThat);
            if (oldValue != null){
                throw new RuntimeException("La misma acción no se puede usar con dos provided distintos. Action = " + action.getName());
            }
        }
    }

    public void addIncreaseAction(Action a, Integer value){
        addIncreaseAction(a, value, null);
    }

    public void addIncreaseAction(Action a, Integer value, String providedThat){
        increaseActions.put(a, value);
        addProvidedThat(a, providedThat);
    }

    public void addDecreaseAction(Action a, Integer value){
        addDecreaseAction(a, value, null);
    }

    public void addDecreaseAction(Action a, Integer value, String providedThat){
        addIncreaseAction(a, -1 * value, providedThat);
    }

    public void addResetAction (Action a, String providedThat){
        addSetValueAction(a, initValue, providedThat);
    }

    public void addResetAction (Action a){
        addResetAction(a, null);
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String toString() {
        return getName();
    }

    public Map<Action, Integer> getIncreaseActions() {
        return increaseActions;
    }

    public Map<Action, Integer> getSetValueActions() {
        return setValueActions;
    }

    public boolean hasCondition(Action action){
        return null != conditions.get(action);
    }

    public String getCondition(Action action){
        return conditions.get(action);
    }

    //usado para logueo y debug
    public void logFL(){
        StringBuilder sb = new StringBuilder("counter ");
        sb.append(getName());
        sb.append(" init value ");
        sb.append(getInitValue()).append(", ");

        for (Action a : getIncreaseActions().keySet()) {
            sb.append(" increases with action " + a.getName() + " by "  +
                    getIncreaseActions().get(a).toString() + ", ");
        }

        for (Action a : getSetValueActions().keySet()) {  //sets with action SET_NEG_3 to value -3
            sb.append(" sets with action " + a.getName() + " to value "  +
                    getSetValueActions().get(a).toString() + ", ");
        }
        if (sb.toString().endsWith(", "))
            sb.delete(sb.length() - 2, sb.length() );
        logger.info(sb.toString());
    }
}
