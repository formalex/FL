package ar.uba.dc.formalex.fl.regulation.formula.terminals;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

/**
 * User: P_BENEDETTI
 * Date: 08/02/14
 * Time: 18:20
 */
public abstract class FLTerminal extends FLFormula{
    private String name;
    private String variable;
    protected String agent; //es el que instancia la variable
    private boolean local;

    protected FLTerminal() {

    }

    protected FLTerminal(String variable, String name) {
        this(variable, null, name);
    }

    protected FLTerminal(String variable, String agent, String name) {
        this.name = name;
        this.agent = agent;
        this.variable = variable;
    }

    public String getName() {
        return name;
    }

    public String getNameWithAgent() {
        String prefijo;
        if (variable == null)      //si es impersonal o local
            prefijo = "";
        else if (agent == null)    //si todav�a no fue instanciado
            prefijo = getVariable() + ".";
        else
            prefijo = agent  + ".";
        return prefijo + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Usado para facilitar el toString
    protected String getAPerformer(){
        if (agent == null) //esto deber�a pasar con las impersonal action y con los intervalos y contadores locales
            return "";
        else
            return agent + ".";
    }

    public String getVariable() {
        return variable;
    }

    public String getAgent(){
        return agent;
    }

    /*
    * Si puede setear la variable devuelve true, si no, false
    * Si está indicado el forzado de agentes ignora la variable.
    */
    public boolean setVariable (String variable, String agent, Boolean forceAgent){
        if (this.variable == null) //es null si es impersonal action, contador o intervalo global
            return true;
        if (forceAgent || this.variable.equals(variable)){
            this.agent = agent;
            return true;
        }
        return false;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

}
