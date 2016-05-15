package ar.uba.dc.formalex.fl.bgtheory;

import java.util.HashSet;
import java.util.Iterator;
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

    /**
     * Obtiene los agentes que cumplen con role. Adicionalmente, si la fórmula contiene un rol extra,
     * retorna los agentes que cumplan con ambos roles.
     * @param role
     * @param belongsRole
     * @return
     */
    public Set<Agente> getAgentes(String role) {
    	Set<Agente> agents = agentesPorRol.get(role);    	
        return agents;
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
    
  //Dado un agente y un rol, verifica si el agente tiene dicho rol.
    public boolean belongsToRole(String agent, String role){
    	if(role != null && this.agentesPorRol.get(role) != null){
    		Iterator<Agente> agentes = this.agentesPorRol.get(role).iterator();
    		while(agentes.hasNext()){
    			Agente currentAgente = agentes.next();
    			if(currentAgente.getName().equals(agent)){
    				return true;
    			}
    		}
    	}
    	return false;		
    }
}
