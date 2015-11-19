package ar.uba.dc.formalex.fl.regulation.formula;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.terminals.ActionReferencedState;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLAction;

public class FlActionTest {

	@Test
	public void elDefaultDelEstadoReferenciadoEsJH() {

		//Si no se le setea el estado referenciado, el default es JH!
		FLAction unFlActionConVariableAndAction = getUnFlActionConVariableAndAction();
		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		
		//El default es JH
		assertEquals(ActionReferencedState.JUST_HAPPENED, unFlActionConVariableAndAction.getReferencedState());
		assertEquals(ActionReferencedState.JUST_HAPPENED, unFlActionConVariableAndAgentAndAction.getReferencedState());
		
	}
	
	@Test
	public void ejemploReferenciaConIsHappening() {

		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		FLAction unFlActionReferenciadoConIsHappening = new FLAction(unFlActionConVariableAndAgentAndAction.getVariable(), 
																unFlActionConVariableAndAgentAndAction.getAgent(), 
																unFlActionConVariableAndAgentAndAction.getName(), ActionReferencedState.IS_HAPPENING);
			
		//Se chequea el estado referenciado
		assertEquals(ActionReferencedState.IS_HAPPENING, unFlActionReferenciadoConIsHappening.getReferencedState());
		
	}
	
	@Test
	public void ejemploReferenciaConJH() {

		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		FLAction unFlActionConJH = new FLAction(unFlActionConVariableAndAgentAndAction.getVariable(), 
																unFlActionConVariableAndAgentAndAction.getAgent(), 
																unFlActionConVariableAndAgentAndAction.getName(), ActionReferencedState.JUST_HAPPENED);
			
		//Se chequea el estado referenciado
		assertEquals(ActionReferencedState.JUST_HAPPENED, unFlActionConJH.getReferencedState());
		
	}

	private FLAction getUnFlActionConVariableAndAction() {
		FLAction flActionDefault = new FLAction("i", "correr");
		return flActionDefault;
	}
	
	private FLAction getUnFlActionConVariableAndAgentAndAction() {
		FLAction flActionDefault = new FLAction("i", "agent1", "correr");
		return flActionDefault;
	}


}
