package ar.uba.dc.formalex.ReductorDeRepresentacionDeAccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.ActionRepresentation;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.ActionReferencedState;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLAction;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLActionOutput;

public class ReductorDeRepresentacionDeAccionADosEstadosImpl implements
		ReductorDeRepresentacionDeAccionADosEstados {

	private BackgroundTheory bgt;
	//private FLFormula unaFormula;
	private Set<String> actionNamesReferencedByCounter;
	private Set<String> actionNamesReferencedByInterval;
	private Map<String, List<FLAction>> actionReferencesGroupByActionName;
	private Map<String, List<FLActionOutput>> actionWithResultsInReferencesGroupByActionName;
	
	public ReductorDeRepresentacionDeAccionADosEstadosImpl(
			BackgroundTheory bgt, FLFormula unaFormula) {
		this.bgt = bgt;
		//this.unaFormula = unaFormula;
		
		//Se obtienen las referencias a las acciones y a las acciones con Output values
		//que son referenciadas con 'results in' de la formula
		Set<FLAction> referencedActionsInFormula = unaFormula.getReferencedActions();
		Set<FLActionOutput> referencedActionsWithResultsInInFormula = unaFormula.getReferencedActionsWithResultsIn();
		
		//Se arman los diccionarios agrupando las referencias para cada accion		
		agruparActionReferences(referencedActionsInFormula);		
		agruparActionWithResultsInReferences(referencedActionsWithResultsInInFormula);
		
		actionNamesReferencedByCounter = bgt.getActionNamesReferencedByCounter();
		actionNamesReferencedByInterval = bgt.getActionNamesReferencedByInterval();
		
	}
	
	@Override
	public BackgroundTheory ejecutar() {

		BackgroundTheory res = bgt.clonar();
		
		for (Action unaAccion : res.getActions()) {
			if(sePuedeReducir(unaAccion))
				unaAccion.setRepresentation(ActionRepresentation.TWO_STATES);
		}
		
		return res;
	}

	private boolean sePuedeReducir(Action unaAccion) {

		String anActionName = unaAccion.getName();

		//1- La acción no puede estar referenciada NI por intervalos, 
		//NI por contadores
		boolean res= !actionNamesReferencedByInterval.contains(anActionName)
				&& !actionNamesReferencedByCounter.contains(anActionName);

		if(res==false)
			return res;

		//2- El campo referencedState tiene que tener el valor ActionReferencedState.IS_HAPPENING 
		//para todas las referencias de la acción  en FLAction	
		List<FLAction> anActionReferencesList = actionReferencesGroupByActionName.get(anActionName);
		res = allReferencesAreIsHappening(anActionReferencesList);

		if(res==false)
			return res;

		//3- NO tener referencias en FlActionOuput
		List<FLActionOutput> anActionWithResultsInReferences = actionWithResultsInReferencesGroupByActionName.get(anActionName);
		return anActionWithResultsInReferences== null || anActionWithResultsInReferences.isEmpty();
	}
	
	private boolean allReferencesAreIsHappening(
			List<FLAction> anActionReferencesList) {
		boolean res= true;
		
		if(anActionReferencesList==null)
			return res;
		
		int i=0;
		while(i< anActionReferencesList.size() && res){
			res= ActionReferencedState.IS_HAPPENING.equals(anActionReferencesList.get(i).getReferencedState());
			i++;
		}
		
		return res;
	}
	
	/** Se agrupan por nombre de accion, las referencias a las acciones en una formula
	 * @param referencedActionsInFormula referencias a las acciones en una formula
	 */
	private void agruparActionReferences(
			Set<FLAction> referencedActionsInFormula) {
		
		actionReferencesGroupByActionName = new HashMap<String, List<FLAction>>();	
		
		for (FLAction flAction : referencedActionsInFormula) {
			String mapKey = flAction.getNameWithAgent();
			List<FLAction> actionReferencesList;
			if(actionReferencesGroupByActionName.containsKey(mapKey)){
				actionReferencesList = actionReferencesGroupByActionName.get(mapKey);
			}else {
				actionReferencesList = new ArrayList<FLAction>();
			}

			actionReferencesList.add(flAction);
			actionReferencesGroupByActionName.put(mapKey, actionReferencesList);

		}
	}
	
	/**Se agrupan por nombre de accion, las referencias a las acciones con output values 
	 * mediante 'results in' en una formula
	 * @param referencedActionsWithResultsInInTheFormula referencias a las 
	 * acciones con output values mediante 'results in' en una formula
	 */
	private void agruparActionWithResultsInReferences(
			Set<FLActionOutput> referencedActionsWithResultsInInTheFormula) {
		actionWithResultsInReferencesGroupByActionName = new HashMap<String, List<FLActionOutput>>();
		
		for (FLActionOutput flActionOutput : referencedActionsWithResultsInInTheFormula) {
			String mapKey = flActionOutput.getNameWithAgent();
			List<FLActionOutput> actionReferencesWithResultsInList;
			if(actionWithResultsInReferencesGroupByActionName.containsKey(mapKey)){
				actionReferencesWithResultsInList = actionWithResultsInReferencesGroupByActionName.get(mapKey);
			}else {
				actionReferencesWithResultsInList = new ArrayList<FLActionOutput>();
			}

			actionReferencesWithResultsInList.add(flActionOutput);
			actionWithResultsInReferencesGroupByActionName.put(mapKey, actionReferencesWithResultsInList);
		}
	}


}
