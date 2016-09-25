package ar.uba.dc.formalex.fl.regulation;

import ar.uba.dc.formalex.fl.bgtheory.Counter;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLAnd;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLForall;
import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLQuantifier;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.fl.regulation.permission.Permission;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;


import java.lang.reflect.Array;
import java.util.*;

public class Regulation {
	private static final Logger logger = Logger.getLogger(Counter.class);
	private List<FLFormula> permissions = new ArrayList<FLFormula>();
	private List<FLFormula> rules = new ArrayList<FLFormula>();
	private Map<String,FLFormula> tagged_formulas = new HashMap<String,FLFormula>();

	public List<FLFormula> getPermissions() {
		return getPermissions(false);
	}

	public List<FLFormula> getPermissions(boolean normalized) {
		if(normalized) {
			return getNormalizedPermissions(getMappedPermissions(this.permissions));
		}
		return this.permissions;
	}

    public List<FLFormula> getRules() {
        return rules;
    }

    public void addRule(FLFormula p){
    	boolean contain = rules.contains(p);
    	if(contain){
    		int index = rules.indexOf(p);
    		//Se reemplaza en la misma posición por la nueva fórmula.
    		rules.remove(index);
    		rules.add(index, p);    		
    	}else{
    		rules.add(p);
    	}
    }

    public void setRules(List<FLFormula> rules) {
        this.rules = rules;
    }

    public void setPermissions(List<FLFormula> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(FLFormula p) {
        permissions.add(p);
    }

	public Map<String, FLFormula> getTaggedFormulas() {
		return tagged_formulas;
	}

	public void setTaggedFormulas(Map<String, FLFormula> tagged_formulas) {
		this.tagged_formulas = tagged_formulas;
	}
	
	public void addTaggedFormula(String tag, FLFormula formula) throws RuntimeException {
		if (tagged_formulas.containsKey(tag))
			throw new RuntimeException("El tag: " + tag + " ya está siendo utilizado");
		else
			tagged_formulas.put(tag, formula);
	}
	
	public FLFormula getFLFormulaFromTag(String tag){
		return tagged_formulas.get(tag);
	}

	private void agregar(Map<String, List<FLFormula>> map, FLFormula k, FLFormula v) {
		List<FLFormula> list = map.get(k.toString());
		if (list == null) {
			list = new ArrayList<>();
			map.put(k.toString(), list);
		}

		list.add(v);
	}

	private Map<String, List<FLFormula>> getMappedPermissions(List<FLFormula> permissions) {
		List<FLFormula> universalPermissions =  new ArrayList<>();
		List<FLFormula> qtyPermissions =  new ArrayList<>();
		for(FLFormula permission : permissions) {
			if(permission instanceof FLQuantifier) {
				qtyPermissions.add(permission);
			} else {
				universalPermissions.add(permission);
			}
		}

		HashMap<String, List<FLFormula>> mappedPermissions =  new HashMap<>();
		for (FLFormula permission : universalPermissions) {
			if(permission instanceof Permission) {
				agregar(mappedPermissions, ((Permission) permission).getExpresion(), permission);
			} else {
				agregar(mappedPermissions, permission, permission);
			}
		}

		HashMap<String, List<FLFormula>> mappedQtyPermissions =  new HashMap<>();
		for (FLFormula permission : qtyPermissions) {
			FLFormula qtyP = ((FLQuantifier) permission).getFormula();
			if(qtyP instanceof Permission) {
				agregar(mappedQtyPermissions, ((Permission) qtyP).getExpresion(), permission);
			} else {
				agregar(mappedQtyPermissions, permission, permission);
			}
		}

		mappedPermissions.putAll(mappedQtyPermissions);
        return mappedPermissions;
	}

	private List<FLFormula> getNormalizedPermissions(Map<String, List<FLFormula>> permissions) {
		List<FLFormula> ret = new ArrayList<>();
		for(Map.Entry<String, List<FLFormula>> entry : permissions.entrySet()) {
			List<FLFormula> permissionList = entry.getValue();
			if(permissionList.size() > 1) {
				if(permissionList.get(0) instanceof Permission) {
					ret.add(getNormalizedUniversalPermission(permissionList));
				} else {
					ret.add(getNormalizedQtyPermission(permissionList));
				}
			} else {
				ret.add(permissionList.get(0));
			}
		}
        return ret;
	}

	// permisions es una lista de permisos universales que hay que unir sus condiciones
	private FLFormula getNormalizedUniversalPermission(List<FLFormula> permissions) {
		FLFormula condition = new FLTrue();
		for(FLFormula permissionsToCheck : permissions) {
			Permission p = (Permission)permissionsToCheck;
            if(p.isMandatory()) {
                throw new RuntimeException("El permiso " + p + " no puede definirse junto a otro permisos condicionales para la misma acción");
            }
			condition = new FLAnd(condition, p.getCondition());
		}
		return new Permission(((Permission)permissions.get(0)).getExpresion(), condition);
	}

	// permisions es una lista de permisos quantificables
	private FLFormula getNormalizedQtyPermission(List<FLFormula> permissions) {
		List<FLFormula> permissionOfQty = new ArrayList<>();
		for(FLFormula permission : permissions) {
			permissionOfQty.add(((FLQuantifier)permission).getFormula());
		}
		FLQuantifier tmp = (FLQuantifier)permissions.get(0);
		return tmp.createForFormula(getNormalizedUniversalPermission(permissionOfQty));
	}
}
