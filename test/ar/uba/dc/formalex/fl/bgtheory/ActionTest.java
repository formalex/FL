package ar.uba.dc.formalex.fl.bgtheory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


public class ActionTest {

	private Action actionPasiva;
	private Action actionActiva;

	@Test
	public void testAccionesSincronizadas() {

		crearAccionesActivayPasiva();
			
		validaQueEsSincronizada(actionActiva, true);
		validaQueEsSincronizada(actionPasiva, false);
	}
	
	@Test
	public void siUnaAccionEsActivaOrPasivaEntoncesEsSincronizada() {

		crearAccionesActivayPasiva();
			
		assertTrue(actionActiva.isSync());
		assertTrue(actionPasiva.isSync());
	}
	
	@Test
	public void testAccionSinRolDefinido() {
		
		//Creo una accion sin roles
		Action acccionSinRolDefinido = getAccionSinRolDefinido();
		
		assertTrue(acccionSinRolDefinido.isSinRolDefinido());
		
	}

	
	@Test
	public void siLeAgregoAlMenosUnRolNoEsUnaAccionSinRolDefinido() {
		
		//Creo una accion sin roles
		Action acccionConRol = getAccionSinRolDefinido();
		
		/*Le agrego un rol a la acción */
		Role aRole = new Role("role1");
		acccionConRol.addPerformableBy(aRole );
		
		assertFalse(acccionConRol.isSinRolDefinido());
		
	}

	private void crearAccionesActivayPasiva() {
		actionPasiva = new Action();
		actionPasiva.setName("pasiva");
		actionPasiva.setImpersonal(false);
		actionPasiva.setOccurrences(0);
		actionPasiva.setOccursIn(null);
		
		actionPasiva.setAllowAutoSync(false);
		
		actionActiva = new Action();
		actionActiva.setName("activa");
		actionActiva.setImpersonal(false);
		actionActiva.setOccurrences(0);
		actionActiva.setOccursIn(null);
		
		actionActiva.setSync(actionPasiva, true);
		actionActiva.setAllowAutoSync(false);
		
		actionPasiva.setSync(actionActiva, !actionActiva.hasActiveSync());
	}
	
	//TODO Agregar tests con ActionRepresentation!
	//TODO Si hay tiempo agregar test del clonar()
	
	private Action getAccionSinRolDefinido() {
		Action acccionSinRolDefinido = new Action();
		acccionSinRolDefinido.setName("Accion sin rol definido");
		acccionSinRolDefinido.setImpersonal(false);
		return acccionSinRolDefinido;
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
