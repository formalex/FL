package ar.uba.dc.formalex.fl.bgtheory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: P_BENEDETTI
 * Date: 11/02/14
 * Time: 00:22
 */
public class BGUtil {
    private Map<String, Set<Agente>> agentesPorRol;
    private Set<String> instanciasValidas;

    public BGUtil(Map<String, Set<Agente>> agentesPorRol, Set<String> instanciasValidas) {
        this.agentesPorRol = agentesPorRol;
        this.instanciasValidas = instanciasValidas;
    }

    public Set<Agente> getAgentes(String role) {
        return agentesPorRol.get(role);
    }

    public Set<Agente> getAgentes() {
        Set<Agente> res = new HashSet<Agente>();
        for(Set<Agente> setAgentes : agentesPorRol.values()) {
            res.addAll(setAgentes);
        }
        return res;
    }

    public boolean isValid(String nameWithAgent){
        return instanciasValidas.contains(nameWithAgent);
    }

}
