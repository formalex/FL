package ar.uba.dc.formalex.fl.utils;

import org.junit.BeforeClass;


/** Esta es la clase padre de todos los tests que necesitan correr el Model checker
 * @author cfaciano
 *
 */
public class FlTest {
	
	public static String ROOT_RESOURCES = "resources/";
	public static String ROOT_EJS_PARSER_CLAUSES = FlTest.ROOT_RESOURCES + "EjemplosParserClauses/";
	protected static String ROOT_EJS_PARA_EL_REDUCTOR = FlTest.ROOT_RESOURCES + "EjemplosParaElReductor/";
	//	private static String ROOT_CDE_FILTRADO = ROOT_RESOURCES + "CasosDeEstudioFiltrado/";
	

	public enum SistemaOperativo {
		LINUX,
		MAC,
		WINDOWS;
		
		private String tipo;

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		
	}
	
	@BeforeClass
	public static void setUp(){

		SistemaOperativo soEnElQueCorre = SistemaOperativo.LINUX;
		
		switch (soEnElQueCorre) {
		case LINUX:
			System.setProperty("NUSMV_EXE", "/Data/JAVA/NuSMV-2.5.4-x86_64-unknown-linux-gnu/bin/NuSMV");
			System.setProperty("TEMP_DIR", "/media/charly/WINDOWS/propsFl/salida");
			break;
		case WINDOWS:
			System.setProperty("NUSMV_EXE", "C:/Program Files/NuSMV/2.5.4/bin/NuSMV.exe");		
			System.setProperty("TEMP_DIR", "C:/propsFl/salida");
			break;

		default:
			break;
		}
		
		System.setProperty("TEMPLATE_VELOCITY", "fl.vm");
	}

}
