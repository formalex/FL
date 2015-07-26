package ar.uba.dc.formalex.fl.regulation.formula;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.connectors.FLNeg;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTerminal;
import ar.uba.dc.formalex.fl.regulation.formula.terminals.FLTrue;
import ar.uba.dc.formalex.fl.regulation.permission.PermanentPermission;
import ar.uba.dc.formalex.fl.regulation.permission.Permission;
import ar.uba.dc.formalex.fl.regulation.rules.Forbidden;
import ar.uba.dc.formalex.fl.regulation.rules.Obligation;

public class FlFormulaTest {

	@Test
	public void unPermisoPermanenteEsUnaObligacionDeUnPermiso() {
		FLTerminal flTerminal = getUnTerminal();
		
		PermanentPermission unPermisoPermanente = new PermanentPermission(flTerminal);
		
		assertTrue(unPermisoPermanente.getFormula() instanceof Obligation);
		assertTrue(((Obligation) unPermisoPermanente.getFormula()).getFormula() instanceof Permission);
	}

	@Test
	public void unaProhibicionEsLaObligacionDeLaNegacion() {
		
		Forbidden unaProhibicion = new Forbidden(getUnTerminal());
		
		assertTrue(unaProhibicion.getFormula() instanceof Obligation);
		assertTrue(((Obligation) unaProhibicion.getFormula()).getFormula() instanceof FLNeg);
			
	}
	
	@Test
	public void unPermisoEsAlgoQueNoEstaProhibido() {
		
		Permission unPermiso = new Permission(getUnTerminal());
		
		assertTrue(unPermiso.getFormula() instanceof FLNeg);
		assertTrue(((FLNeg) unPermiso.getFormula()).getFormula() instanceof Forbidden);
			
	}
	private FLTerminal getUnTerminal() {
		FLTerminal flTerminal = new FLTrue();
		return flTerminal;
	}

}
