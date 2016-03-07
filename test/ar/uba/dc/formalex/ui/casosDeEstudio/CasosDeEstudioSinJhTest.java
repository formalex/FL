package ar.uba.dc.formalex.ui.casosDeEstudio;

import org.junit.Test;

import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;
import ar.uba.dc.formalex.fl.utils.TestUtils;

public class CasosDeEstudioSinJhTest extends FlTest {

	private static final String ROOT_CDE_REDUCTOR = ROOT_RESOURCES
			+ "CasosDeEstudioReductor/";

	/* Ejs con #JH en la formula > 0   */

	@Test
	//SIN JH en 4 minutos consumió 6Gb
	//CON JH
	public void testCasoDeEstudio1NroDeClausulas1() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas1.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//SIN JH en 4 minutos consumió 6Gb
	//CON JH
	public void testCasoDeEstudio1NroDeClausulas5() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas5.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas15() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas15.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//Con JH mas de 48 minutos
	//Sin JH 42 minutos se queda sin memoria
	public void testCasoDeEstudio1NroDeClausulas25WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas25() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//SIN JH. En 21 llega a 6 gb
	//CON JH igual
	public void testCasoDeEstudio2NroDeClausulas25WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio2NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio2NroDeClausulas25() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio2NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//SIN JH NO TERMINAAAAAAAAAAAAAAAAAAAAAAA
	public void testCasoDeEstudio3NroDeClausulas1() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas1.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//Tiene 23 JH y 46 3st/162
	//Sigue sin terminar
	public void testCasoDeEstudio3NroDeClausulas5WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas5WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas5() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas5.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//SIN JH TODO OJO CON EL MSJ QUE TIRA EL MODEL CHECKER
//	********   WARNING   ********
//	The initial states set of the finite state machine is empty.
//	This might make results of model checking not trustable.
//	******** END WARNING ********
	public void testCasoDeEstudio3NroDeClausulas15WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas15WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas15() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas15.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

	@Test
	//SIN JH TODO OJO CON EL MSJ QUE TIRA EL MODEL CHECKER
//	********   WARNING   ********
//	The initial states set of the finite state machine is empty.
//	This might make results of model checking not trustable.
//	******** END WARNING ********
	public void testCasoDeEstudio3NroDeClausulas25WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas25() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas32() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas32.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}




	@Test
	//7 formulas + 6 permisos
	//sin jh 1155 10 veces
	//con jh 1441 10 veces
	public void testCasoDeEstudioVipMemberPass() throws Exception {
		// Este ejemplo tiene un PP con excepcion

		for(int i= 0;i<10;i++){
			TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
					+ "casoDeEstudioVipMemberPass.txt",
					FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
					LTLTranslationType.WITH_JH);
		}
	}

	@Test
	//OJO QUE ESTE NO TIENE LOS IS HAPPENING Y NO ES PARTE DE NINGUN CASO DE ESTUDIO
	//PORQUE TARDA BASTANTE
	//CON JH 6621 SEGUNDOS
	//SIN JH 
	public void testCasoDeEstudioVipMemberPassFull() throws Exception {
		// Este ejemplo tiene dos PP con excepcion
		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "casoDeEstudioVipMemberPassFull.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}



	/* Ejs con #JH en la formula == 0   */

	//Tiene 0 JH y 7/30. Entonces la mejora opera SOLO sobre la codificacion del automata
	@Test
	public void casoDeEstudioVipMemberPassConTresPps() throws Exception {
		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "casoDeEstudioVipMemberPassConTresPps.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas15WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas15WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas60WithDefaultIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas60.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test	
	// 0 JH 65/491
	//No termina
	public void testCasoDeEstudio2NroDeClausulas15WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio2NroDeClausulas15WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	//O JH 111/842
	//No termina
	public void testCasoDeEstudio2NroDeClausulas28WithDefaultIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio2NroDeClausulas28.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//Tiene JH =0 3st= 73/ 336
	//Sigue sin terminar
	public void testCasoDeEstudio3NroDeClausulas32WithDefaultIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas32.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	/* Ejs que DESAPARECEN XQ ya se redujeron TODAS las acciones a 2 estados  */
	@Test
	public void testCasoDeEstudio1NroDeClausulas1WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas1WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas5WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas5WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio2NroDeClausulas1WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio2NroDeClausulas1WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	// 3 st = 0
	public void testCasoDeEstudio2NroDeClausulas5WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio2NroDeClausulas5WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//Tiene JH= 0 . tarda tambien un segundo
	public void testCasoDeEstudio3NroDeClausulas1WithIsHappening()
			throws Exception {

		TestUtils.corridaDeFormaLex(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas1WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				true, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas60() throws Exception {

		TestUtils.corridaDeFormaLex(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas60.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, true, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

}
