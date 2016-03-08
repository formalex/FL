package ar.uba.dc.formalex.ui;

import ar.uba.dc.formalex.fl.Formalex;
import ar.uba.dc.formalex.fl.regulation.formula.LTLTranslationType;
import ar.uba.dc.formalex.util.Util;

public class Main {

    private static final String CONST_FL_INPUT = "FL_INPUT";
    
	private static boolean conModelChecker = true;
	private static boolean conFiltrado;
	private static boolean conReductor;
	private static LTLTranslationType ltlTranslationType;

	public static void main(String args []) throws Exception {
    	
        if(args.length < 1 || args.length > 2){
            System.out.println ("FormaLex:  You should use");
            System.out.println ("         java ar.uba.dc.formalex.ui.Main configFile");
            System.out.println ("or       java ar.uba.dc.formalex.ui.Main configFile flFile");
            return ;
        }

        if(args.length > 0){
            Util.loadProperties(args[0]);
      	}

        if(args.length == 2){
            System.setProperty(CONST_FL_INPUT, args[1]);
      	}
        
        String input = readPropertiesToRun();
		
		Formalex.run(input, conModelChecker, conFiltrado, conReductor, ltlTranslationType);
		
    }

	private static String readPropertiesToRun() {
		String input = System.getProperty(CONST_FL_INPUT);
        
		//Valores default
        conFiltrado = true;
        conReductor = true;
		ltlTranslationType = LTLTranslationType.WITH_JH;
		
		String strConFiltro = System.getProperty("CON_FILTRO");		
		if(strConFiltro!=null){
			conFiltrado = Boolean.parseBoolean(strConFiltro);
		}
		
		String strConReductor = System.getProperty("CON_REDUCTOR");
		if(strConReductor!=null){
			conReductor = Boolean.parseBoolean(strConReductor);
		}
		
		String strSinJh = System.getProperty("SIN_JH");
		if(strSinJh!=null){
			if(Boolean.parseBoolean(strSinJh))
				ltlTranslationType = LTLTranslationType.WITH_NEXT_FOR_JH;
		}
		return input;
	}

}
