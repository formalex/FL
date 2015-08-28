package ar.uba.dc.formalex.fl.bgtheory;

import java.util.HashMap;
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
    private Map<Action, Set<Action>> accionesSyncActivo;
    private Map<Action, Set<Action>> accionesSyncPasivo;
    private Map<Action, Set<Action>> accionesSincronizadas;
    
    //[serge] No entiendo bien la utilidad de esta clase. A la altura en la que se invoca ya están calculadas todas las acciones.
    // Esta clase expone agentes y acciones en forma de strings planos...
    // Agregando acciones con sync activo y pasivo para pode instanciar correctamente acciones sincronizadas como parche para evitar cambiar mucho más.
    public BGUtil(Map<String, Set<Agente>> agentesPorRol, Set<String> instanciasValidas, 
    		Map<Action, Set<Action>> accionesSyncActivo, Map<Action, Set<Action>> accionesSyncPasivo) {
        this.agentesPorRol = agentesPorRol;
        this.instanciasValidas = instanciasValidas;
        this.accionesSyncActivo = accionesSyncActivo;
        this.accionesSyncPasivo = accionesSyncPasivo;
        this.updateAcciones();
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


    /**
     * Devuelve la unión de las acciones sincronizadas activas y pasivas
     * */
    public Map<Action, Set<Action>> getAccionesSincronizadas() {
    	return accionesSincronizadas;
	}

    
    public Map<Action, Set<Action>> getAccionesSyncActivo() {
		return accionesSyncActivo;
	}

	public void setAccionesSyncActivo(Map<Action, Set<Action>> accionesSyncActivo) {
		this.accionesSyncActivo = accionesSyncActivo;
		this.updateAcciones();
	}

	public Map<Action, Set<Action>> getAccionesSyncPasivo() {
		return accionesSyncPasivo;
	}

	public void setAccionesSyncPasivo(Map<Action, Set<Action>> accionesSyncPasivo) {
		this.accionesSyncPasivo = accionesSyncPasivo;
		this.updateAcciones();
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
    
    /**
     * Construye la unión de todas las acciones sincronizadas
     * */
    private void updateAcciones()
    {
    	accionesSincronizadas = new HashMap<Action, Set<Action>>();
    	accionesSincronizadas.putAll(accionesSyncActivo);
    	accionesSincronizadas.putAll(accionesSyncPasivo);
    }
}
