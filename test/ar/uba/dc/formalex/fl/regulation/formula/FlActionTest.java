package ar.uba.dc.formalex.fl.regulation.formula;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLAction;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLActionRepresentation;

public class FlActionTest {

	@Test
	public void elDefaultDeLaRepresentationEsTresEstados() {

		//Si no se le setea la flActionRepresentacion, el default es tres estados!
		FLAction unFlActionConVariableAndAction = getUnFlActionConVariableAndAction();
		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		
		//El default es TRES ESTADOS
		assertEquals(FLActionRepresentation.TRES_ESTADOS, unFlActionConVariableAndAction.getRepresentation());
		assertEquals(FLActionRepresentation.TRES_ESTADOS, unFlActionConVariableAndAgentAndAction.getRepresentation());
		
	}
	
	@Test
	public void ejemploConRepresentationDeDosEstados() {

		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		FLAction unFlActionConDosEstados = new FLAction(unFlActionConVariableAndAgentAndAction.getVariable(), 
																unFlActionConVariableAndAgentAndAction.getAgent(), 
																unFlActionConVariableAndAgentAndAction.getName(), FLActionRepresentation.DOS_ESTADOS);
			
		//Se chequea la FLActionRepresentation
		assertEquals(FLActionRepresentation.DOS_ESTADOS, unFlActionConDosEstados.getRepresentation());

		
	}
	
	@Test
	public void ejemploConRepresentationDeTresEstados() {

		FLAction unFlActionConVariableAndAgentAndAction = getUnFlActionConVariableAndAgentAndAction();
		FLAction unFlActionConDosEstados = new FLAction(unFlActionConVariableAndAgentAndAction.getVariable(), 
																unFlActionConVariableAndAgentAndAction.getAgent(), 
																unFlActionConVariableAndAgentAndAction.getName(), FLActionRepresentation.TRES_ESTADOS);
			
		//Se chequea la FLActionRepresentation
		assertEquals(FLActionRepresentation.TRES_ESTADOS, unFlActionConDosEstados.getRepresentation());

		
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
