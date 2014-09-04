package ar.uba.dc.formalex.fl.regulation;

import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;





import java.util.*;

public class Regulation {
	private List<FLFormula> permissions = new ArrayList<FLFormula>();
	private List<FLFormula> rules = new ArrayList<FLFormula>();
	private Map<String,FLFormula> tagged_formulas = new HashMap<String,FLFormula>();

	public List<FLFormula> getPermissions() {
		return permissions;
	}

    public List<FLFormula> getRules() {
        return rules;
    }

    public void addRule(FLFormula p){
    	boolean contain = rules.contains(p);
    	if(contain){
    		int index = rules.indexOf(p);
    		rules.add(index, p);
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
}
