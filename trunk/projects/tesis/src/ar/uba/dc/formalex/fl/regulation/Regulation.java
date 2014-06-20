package ar.uba.dc.formalex.fl.regulation;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Regulation {
	private List<FLFormula> permissions = new ArrayList<FLFormula>();
	private List<FLFormula> rules = new ArrayList<FLFormula>();

	public List<FLFormula> getPermissions() {
		return permissions;
	}

    public List<FLFormula> getRules() {
        return rules;
    }

    public void addRule(FLFormula p){
        rules.add(p);
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
}
