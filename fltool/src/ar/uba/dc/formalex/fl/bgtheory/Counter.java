package ar.uba.dc.formalex.fl.bgtheory;


import org.apache.log4j.Logger;

import java.util.*;
import java.util.Map.Entry;

public class Counter {
    private static final Logger logger = Logger.getLogger(Counter.class);
    private String name;
    private String type;
    private Role roleForCounter = null;

    private int initValue = 0;
    private int minValue = 0;
    private int maxValue = 20;
    private boolean minImpedesActions = false;
    private boolean maxImpedesActions = false;

    private Map<Action, Integer> increaseActions = new HashMap<Action, Integer>();
    private Map<Action, Map<Role, Integer>> increaseActionsByRole = new HashMap<>();
    private Map<Action, Integer> setValueActions = new HashMap<Action, Integer>();

    private Map<Action, String> conditions = new HashMap<Action, String>();

    public Counter() {
    }

    public Counter(String name) {
        this.name = name;
    }

    public Counter clonar() {
        Counter res = new Counter(getName());
        res.type = this.type;
        res.setInitValue(this.getInitValue());
        res.setMinValue(this.getMinValue());
        res.setMaxValue(this.getMaxValue());
        res.setMinImpedesActions(this.isMinImpedesActions());
        res.setMaxImpedesActions(this.isMaxImpedesActions());
        res.setRoleForCounter(this.getRoleForCounter());

        for (Entry<Action, Integer> anIncreseActionEntry : increaseActions.entrySet()) {
            Action anAction = anIncreseActionEntry.getKey();
            res.addIncreaseAction(anAction.clonar(), anIncreseActionEntry.getValue(), this.getCondition(anAction));
        }

        for (Entry<Action, Integer> anSetValueActionEntry : setValueActions.entrySet()) {
            Action anAction = anSetValueActionEntry.getKey();
            res.addSetValueAction(anAction.clonar(), anSetValueActionEntry.getValue(), this.getCondition(anAction));
        }

        return res;
    }

    //Devuelve todas las acciones que pueden modificar al contador
    public Set<Action> getAllActions() {
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

    public void addSetValueAction(Action a, Integer value, String providedThat) {
        setValueActions.put(a, value);
        addProvidedThat(a, providedThat);
    }

    private void addProvidedThat(Action action, String providedThat) {
        if (providedThat != null) {
            String oldValue = conditions.put(action, providedThat);
            if (oldValue != null) {
                throw new RuntimeException("La misma acción no se puede usar con dos provided distintos. Action = " + action.getName());
            }
        }
    }

    public void addIncreaseAction(Action a, Integer value) {
        addIncreaseAction(a, value, null);
    }

    public void addIncreaseAction(Action a, Integer value, String providedThat) {
        increaseActions.put(a, value);
        addProvidedThat(a, providedThat);
    }

    public void addIncreaseActionForRole(Action a, Role role, Integer value, String providedThat) {
        Map<Role, Integer> valueForRoles = increaseActionsByRole.get(a);
        if(valueForRoles == null) {
            valueForRoles = new HashMap<>();
            increaseActionsByRole.put(a, valueForRoles);
        }
        if(valueForRoles.containsKey(role)) {
            throw new RuntimeException("El valor del contador " + getName() + " para el rol " + role.getName() +
                    "en la accion " + a.getName() +  " fue previamente definido");
        }
        valueForRoles.put(role, value);
        addProvidedThat(a, providedThat);
    }

    public void addDecreaseActionForRole(Action a, Role role, Integer value, String providedThat) {
        addIncreaseActionForRole(a, role, value * -1, providedThat);
    }

    public void addDecreaseAction(Action a, Integer value) {
        addDecreaseAction(a, value, null);
    }

    public void addDecreaseAction(Action a, Integer value, String providedThat) {
        addIncreaseAction(a, -1 * value, providedThat);
    }

    public void addResetAction(Action a, String providedThat) {
        addSetValueAction(a, initValue, providedThat);
    }

    public void addResetAction(Action a) {
        addResetAction(a, null);
    }

    public boolean isLocal() {
        return Objects.equals(this.type, "local");
    }

    public boolean isShared() {
        return Objects.equals(this.type, "shared");
    }

    public boolean isGlobal() {
        return Objects.equals(this.type, "global");
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

    public Role getRoleForCounter() {
        return this.roleForCounter;
    }

    public void setRoleForCounter(Role role) {
        this.roleForCounter = role;
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

    public boolean hasCondition(Action action) {
        return null != conditions.get(action);
    }

    public String getCondition(Action action) {
        return conditions.get(action);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isMinImpedesActions() {
        return minImpedesActions;
    }

    public void setMinImpedesActions(boolean minImpedesActions) {
        this.minImpedesActions = minImpedesActions;
    }

    public boolean isMaxImpedesActions() {
        return maxImpedesActions;
    }

    public void setMaxImpedesActions(boolean maxImpedesActions) {
        this.maxImpedesActions = maxImpedesActions;
    }

    public void normalizeSharedCounter() {
        for(Action action : increaseActionsByRole.keySet()) {
            if(!getIncreaseActions().containsKey(action)) {
                addIncreaseAction(action, null);
            }
        }
    }

    public Integer getIncreaseValueForAgent(Action action, Agente agent) {
        Integer value = null;
        Integer maxDepth = 0;
        Map<Role, Integer> valueForRole = increaseActionsByRole.get(action);

        if(valueForRole != null) {
            for(Role role : agent.getRoles()) {
                if(valueForRole.containsKey(role)) {
                    if(maxDepth < role.getRoleSpecification().getDepth()) {
                        maxDepth = role.getRoleSpecification().getDepth();
                        value = valueForRole.get(role);
                    }
                }
            }
        }
        if(value != null) {
            return value;
        }
        if(getIncreaseActions().get(action) == null) {
            throw new RuntimeException("No se puede calcular el modificador del contador " + getName() + " para el " +
                agent.getName() + " en la accion " + action.getName());
        }
        return getIncreaseActions().get(action);
    }

    //usado para logueo y debug
    public void logFL() {
        StringBuilder sb = new StringBuilder("counter ");
        sb.append(getName());
        sb.append(" init value ");
        sb.append(getInitValue()).append(" ");
        sb.append(" min value ");
        sb.append(getMinValue()).append(" ");
        sb.append(" max value ");
        sb.append(getMaxValue()).append(", ");

        for (Action a : getIncreaseActions().keySet()) {
            sb.append(" increases with action " + a.getName() + " by " +
                    getIncreaseActions().get(a).toString() + ", ");
        }

        if (isMinImpedesActions())
            sb.append(" reaching min impedes actions ");

        if (isMaxImpedesActions())
            sb.append(" reaching max impedes actions ");

        for (Action a : getSetValueActions().keySet()) {  //sets with action SET_NEG_3 to value -3
            sb.append(" sets with action " + a.getName() + " to value " +
                    getSetValueActions().get(a).toString() + ", ");
        }
        if (sb.toString().endsWith(", "))
            sb.delete(sb.length() - 2, sb.length());
        logger.info(sb.toString());
    }


}
