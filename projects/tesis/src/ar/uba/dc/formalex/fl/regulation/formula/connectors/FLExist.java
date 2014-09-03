package ar.uba.dc.formalex.fl.regulation.formula.connectors;

import ar.uba.dc.formalex.fl.bgtheory.Agente;
import ar.uba.dc.formalex.fl.bgtheory.BGUtil;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLFalse;

import java.util.Set;

public class FLExist extends FLQuantifier {
    private boolean yaInstanciada = false; //usada para loguear y debug

    public FLExist(String variable, String role, FLFormula formula) {
        super(variable, role, formula);
    }

    @Override
    public String toString() {
        if (yaInstanciada)
            return getFormula().toString();
        else {
            //usada para loguear y debug
            String rol = "";
            if (getRole() != null)
                rol = ":" + getRole();
            return "EXISTS (" + getVariable() + rol + " ; " + getFormula().toString() + " )";
        }
    }

    @Override
    public FLFormula instanciar(String variable, String agente, BGUtil bgUtil) {
        FLFormula newFormula;
        if (variable != null)
            //Primero instancio la fórmula interna con la variable externa
            newFormula = getFormula().instanciar(variable, agente, bgUtil);
        else
            newFormula = getFormula();

        if (newFormula == null)
            return null;

        //Luego instancio con los roles del exists
        Set<Agente> agentes;
        if (getRole() == null)//si no se indica el rol se usan todos => se usan todos los agentes que pueden realizar la acción
            agentes = bgUtil.getAgentes();
        else
            agentes = bgUtil.getAgentes(getRole());

        FLFormula orFormula = null;
        for(Agente agenteE : agentes) {
            FLFormula orDer = newFormula.instanciar(getVariable(), agenteE.getName(), bgUtil);
            if (orDer != null){
                if (orFormula != null)
                    orFormula = new FLOr(orFormula, orDer);
                else
                    orFormula = new FLOr(new FLFalse(), orDer);
            }
        }
        yaInstanciada = true;

        return orFormula;
    }
}