package ar.uba.dc.formalex.ui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.regulation.formula.FLFormula;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoFake;
import ar.uba.dc.formalex.parser.FLParser;
import ar.uba.dc.formalex.parser.TokenMgrError;
import ar.uba.dc.formalex.util.LaAplanadora;
import ar.uba.dc.formalex.util.UtilFile;

public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);

    @SuppressWarnings("FieldCanBeLocal")
    private static String file = "D:\\P\\tesis\\proyecto\\tesis-jpb\\apuntesTesis\\test.txt";
    private static String fileSalida = "D:\\P\\tesis\\proyecto\\tesis-jpb\\apuntesTesis\\test_salida.txt";
    public static void main(String args []) throws ParseException, IOException {

        try {
            new FLParser(new java.io.FileInputStream(file));
        }
        catch(java.io.FileNotFoundException e) {
            System.out.println ("FormaLex: File " + args[0] + " not found. Nothing to parse. Ending.");
            return;
        }

		
		// ***********************
		// We parse the input file
		// ***********************
      	try {
        	FLParser.start();
        	System.out.println ("FormaLex: Parsing succeed");        	
        }
        catch(TokenMgrError e){
        	System.out.println ("FormaLex: Ha ocurrido un error de tokens durante el parseo.");
        	System.out.println (e.getMessage());
            return ;
        } catch (ar.uba.dc.formalex.parser.ParseException e) {
        	System.out.println ("FormaLex: Ha ocurrido un error durante el parseo.");
        	System.out.println (e.getMessage());
			e.printStackTrace();
            return ;
		}
        
      	// ************************************************
    	// Now we create a BackGroundTheory internal object
      	// ************************************************
    	FLInput flInput = FLParser.getFLInput();

        LaAplanadora divididos = new LaAplanadora();
        divididos.explotarYAplanar(flInput, new ConstructorDeGrafoFake(), LTLTranslationType.WITH_JH);



        List<String> salidaOK = UtilFile.getArchivoPorLinea(new File(fileSalida), "--", true);
        int ind = 0;
        int contMal = 0;

        //"Reglas:");
        for(FLFormula formula : flInput.getRules()) {
            String nueva = formula.translateToLTL(LTLTranslationType.WITH_JH );
            String vieja = salidaOK.get(ind++);
            if (!nueva.equals(vieja)){
                contMal++;
                System.out.println("vieja = " + vieja);
                System.out.println("nueva = " + nueva);
                System.out.println("");
            }
        }
        //("Permisos:");
        for(FLFormula formula : flInput.getPermissions()) {
            String nueva = formula.translateToLTL(LTLTranslationType.WITH_JH );
            String vieja = salidaOK.get(ind++);
            if (!nueva.equals(vieja)){
                contMal++;
                System.out.println("vieja = " + vieja);
                System.out.println("nueva = " + nueva);
                System.out.println("");

            }
        }
        if (ind != salidaOK.size()){
            System.out.println("No coincide la cantidad de entrada con la de salida");
        }
        else if (contMal == 0){
            System.out.println("TODO BIEN");
            System.out.println("Cantidad de reglas = " + flInput.getRules().size());
            System.out.println("Cantidad de permisos = " + flInput.getPermissions().size());
        }
        else {
            System.out.println("Diferencias en cant: " + contMal);
        }
    }

}
