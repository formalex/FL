package ar.uba.dc.formalex.ui.casosDeEstudio;

import org.junit.Test;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.fl.utils.FlTest;

public class CasosDeEstudioSinJhTest extends FlTest {

	private static final String ROOT_CDE_REDUCTOR = ROOT_RESOURCES
			+ "CasosDeEstudioReductor/";

	/* Ejs con #JH en la formula > 0   */

	@Test
	public void testCasoDeEstudio1NroDeClausulas1() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas1.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

	@Test
	//Con JH en el server de Lisan no trajo resultados en menos de 3 horas
	public void testCasoDeEstudio1NroDeClausulas5() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas5.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas15() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas15.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

	@Test
	//Con JH mas de 48 minutos
	//Sin JH 42 minutos se queda sin memoria
	public void testCasoDeEstudio1NroDeClausulas25WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas25() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio1NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	//SIN JH en el server de Lisan no trajo resultados en 2h 10. 
	//CON JH 
	public void testCasoDeEstudio2NroDeClausulas25WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio2NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio2NroDeClausulas25() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio2NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas1() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas1.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas5WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas5WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas5() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas5.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas15WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas15WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas15() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas15.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas25WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio3NroDeClausulas25WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas25() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas25.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}

	@Test
	public void testCasoDeEstudio3NroDeClausulas32() throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas32.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO, CORRER_SIN_REDUCTOR,
				LTLTranslationType.WITH_NEXT_FOR_JH);
	}




	/* Ejs con #JH en la formula == 0   */

	//Tiene 0 JH y 7/30. Entonces la mejora opera SOLO sobre la codificacion del automata
	//TODO ver si amerita incluirlo
	@Test
	public void casoDeEstudioVipMemberPassConTresPps() throws Exception {
		Formalex.run(ROOT_CDE_REDUCTOR
				+ "casoDeEstudioVipMemberPassConTresPps.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	public void testCasoDeEstudio1NroDeClausulas15WithIsHappening()
			throws Exception {

		Formalex.run(ROOT_CDE_REDUCTOR
				+ "CasoDeEstudio1NroDeClausulas15WithIsHappening.txt",
				CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

//	@Test
//	public void testCasoDeEstudio1NroDeClausulas60WithDefaultIsHappening()
//			throws Exception {
//
//		Formalex.run(FlTest.ROOT_CDE_FILTRADO
//				+ "CasoDeEstudio1NroDeClausulas60.txt",
//				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
//				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
//	}



	@Test
	//O JH 111/842
	//No termina
	public void testCasoDeEstudio2NroDeClausulas28WithDefaultIsHappening()
			throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio2NroDeClausulas28.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_JH);
	}

	@Test
	//Tiene JH =0 3st= 73/ 336
	//Sigue sin terminar
	public void testCasoDeEstudio3NroDeClausulas32WithDefaultIsHappening()
			throws Exception {

		Formalex.run(FlTest.ROOT_CDE_FILTRADO
				+ "CasoDeEstudio3NroDeClausulas32.txt",
				FlTest.CORRER_CON_MODEL_CHECKER, CORRER_CON_FILTRADO,
				CORRER_CON_REDUCTOR, LTLTranslationType.WITH_NEXT_FOR_JH);
	}


}
