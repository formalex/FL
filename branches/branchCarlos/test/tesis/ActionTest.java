package tesis;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.formalex.fl.bgtheory.Action;

public class ActionTest {

	@Test
	public void testAccionesSincronizadas() {

		Action actionPasiva = new Action();
		actionPasiva.setName("pasiva");
		actionPasiva.setImpersonal(false);
		actionPasiva.setOccurrences(0);
		actionPasiva.setOccursIn(null);
		
		actionPasiva.setAllowAutoSync(false);
		
		Action actionActiva = new Action();
		actionActiva.setName("activa");
		actionActiva.setImpersonal(false);
		actionActiva.setOccurrences(0);
		actionActiva.setOccursIn(null);
		
		actionActiva.setSync(actionPasiva, true);
		actionActiva.setAllowAutoSync(false);
		
		actionPasiva.setSync(actionActiva, !actionActiva.hasActiveSync());
		
		
		validaQueEsSincronizada(actionActiva, true);
		validaQueEsSincronizada(actionPasiva, false);
	}

	private void validaQueEsSincronizada(Action unaAction, boolean esActiva) {
		assertNotNull(unaAction.getSync());
		assertNotNull(unaAction.isAllowAutoSync());
		
		if(esActiva)
			assertTrue(unaAction.hasActiveSync());
		else
			assertTrue(unaAction.hasPasiveSync());
	}

}
