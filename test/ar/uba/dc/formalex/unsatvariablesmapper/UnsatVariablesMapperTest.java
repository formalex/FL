package ar.uba.dc.formalex.unsatvariablesmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UnsatVariablesMapperTest {
	
	private String resourcesDir = "./resources/unsatvariablesmapper";
	
	@Test
	public void unsatMapperShouldParseFileIntoAVariableNameSet() {
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		Set<String> unsatVariables = unsatVariablesMapper.getUnsatVariables();
		
		assertTrue(unsatVariables.containsAll(unsatVariables1));
			
 	}
	
	@Test
	public void unsatMapperShouldParseCNFVariablesIntoAMap() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		Map<String,Set<String>> cnfVariablesMappingObtained = unsatVariablesMapper.getCnfVariablesMap();
		
		assertTrue(compareMappingsByEquality(cnfVariablesMapping1, cnfVariablesMappingObtained));
		
	}

	@Test
	public void unsatMapperShouldParseConvertedVariablesIntoAMap() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		Map<String,Set<String>> convertedVariablesMap = unsatVariablesMapper.getConvertedVariablesMap();
		
		assertTrue(compareMappingsByEquality(convertedVariablesMap1, convertedVariablesMap));
		
	}

	@Test
	public void unsatMapperShouldMapUnsatVariablesWithModelVariables() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model2.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMapping1, unsatVariablesMap));
		
	}

	@Test
	public void model3() {
		
		String unsatVariablesFileName = "unsat3.variables";
		String modelFileName = "model3.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMapping3, unsatVariablesMap));
		
	}

	@Test
	public void model4() {
		
		String unsatVariablesFileName = "unsat4.variables";
		String modelFileName = "model4.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMapping4, unsatVariablesMap));
		
	}

	@Test
	public void modelA() {
		
		String unsatVariablesFileName = "unsatA.variables";
		String modelFileName = "modelA.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMappingA, unsatVariablesMap));
		
	}

	@Test
	public void modelB() {
		
		String unsatVariablesFileName = "unsatB.variables";
		String modelFileName = "modelB.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMappingB, unsatVariablesMap));
		
	}

	@Test
	public void modelC() {
		
		String unsatVariablesFileName = "unsatC.variables";
		String modelFileName = "modelC.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMappingC, unsatVariablesMap));
		
	}
	
	@Test
	public void modelD() {
		
		String unsatVariablesFileName = "unsatD.variables";
		String modelFileName = "modelD.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMappingD, unsatVariablesMap));
		
	}
	
	@Test
	public void modelE() {
		
		String unsatVariablesFileName = "unsatE.variables";
		String modelFileName = "modelE.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMappingE, unsatVariablesMap));
		
	}

	@Test
	public void modelF() {
		
		String unsatVariablesFileName = "unsatF.variables";
		String modelFileName = "modelF.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName);
		
		unsatVariablesMapper.mapUnsatVariables();
		Set<String> entities = unsatVariablesMapper.getFLEntities();
		
		assertEquals(flEntities, entities);
	}
	
	private boolean compareMappingsByEquality(Map<String, Set<String>> unsatVariablesMappingExpected,
			Map<String, Set<String>> unsatVariablesMappingObtained) {
		boolean ret =  true;
		ret &= unsatVariablesMappingExpected.keySet().containsAll(unsatVariablesMappingObtained.keySet());
		ret &= unsatVariablesMappingObtained.keySet().containsAll(unsatVariablesMappingExpected.keySet());
		if(ret) {
			for (String variable : unsatVariablesMappingObtained.keySet()) {
				Set<String> variableMapExpected = unsatVariablesMappingExpected.get(variable);
				Set<String> variableMapObtained = unsatVariablesMappingObtained.get(variable);
				if (variableMapExpected != null && variableMapObtained != null) {
					ret &= variableMapObtained.containsAll(variableMapExpected);
					ret &= variableMapExpected.containsAll(variableMapObtained);
				} else {
					return false;
				}
			}
		}
		return ret;
	}

	private Set<String> unsatVariables1 = new HashSet<String> (Arrays.asList("19","20","21","22","23","24","50","51","52","53"));
	private Map<String,Set<String>> cnfVariablesMapping1 = new HashMap<String,Set<String>>() {
		{
			put("13", new HashSet<String> (Arrays.asList("y")));
			put("14", new HashSet<String> (Arrays.asList("y")));
			put("15", new HashSet<String> (Arrays.asList("y")));
			put("16", new HashSet<String> (Arrays.asList("y")));
			put("17", new HashSet<String> (Arrays.asList("z")));
			put("18", new HashSet<String> (Arrays.asList("z")));
			put("19", new HashSet<String> (Arrays.asList("y")));
			put("20", new HashSet<String> (Arrays.asList("y")));
			put("21", new HashSet<String> (Arrays.asList("y")));
			put("22", new HashSet<String> (Arrays.asList("y")));
			put("23", new HashSet<String> (Arrays.asList("z")));
			put("24", new HashSet<String> (Arrays.asList("z")));
			put("25", new HashSet<String> (Arrays.asList("y")));
			put("26", new HashSet<String> (Arrays.asList("y")));
			put("27", new HashSet<String> (Arrays.asList("y")));
			put("28", new HashSet<String> (Arrays.asList("y")));
			put("29", new HashSet<String> (Arrays.asList("z")));
			put("30", new HashSet<String> (Arrays.asList("z")));
			put("31", new HashSet<String> (Arrays.asList("y")));
			put("32", new HashSet<String> (Arrays.asList("y")));
			put("33", new HashSet<String> (Arrays.asList("y")));
			put("34", new HashSet<String> (Arrays.asList("y")));
			put("45", new HashSet<String> (Arrays.asList("z")));
			put("46", new HashSet<String> (Arrays.asList("z")));
		}
	};
	
	private Map<String,Set<String>> convertedVariablesMap1 = new HashMap<String,Set<String>>() {
		{
			put("36", new HashSet<String> (Arrays.asList("23","24")));
			put("37", new HashSet<String> (Arrays.asList("22","28")));
			put("38", new HashSet<String> (Arrays.asList("27","37")));
			put("39", new HashSet<String> (Arrays.asList("21","38","27","37")));
			put("40", new HashSet<String> (Arrays.asList("26","39")));
			put("44", new HashSet<String> (Arrays.asList("13")));
			put("47", new HashSet<String> (Arrays.asList("14")));
			put("48", new HashSet<String> (Arrays.asList("15")));
			put("49", new HashSet<String> (Arrays.asList("16")));
			put("50", new HashSet<String> (Arrays.asList("29","30")));
			put("51", new HashSet<String> (Arrays.asList("33","44")));
			put("52", new HashSet<String> (Arrays.asList("27","51","33","44")));
			put("53", new HashSet<String> (Arrays.asList("32","52")));
			put("54", new HashSet<String> (Arrays.asList("17")));
			put("55", new HashSet<String> (Arrays.asList("18")));
		}
	};

	private Map<String,Set<String>> unsatVariablesMapping1 = new HashMap<String,Set<String>>() {
		{
			put("19", new HashSet<String> (Arrays.asList("y")));
			put("20", new HashSet<String> (Arrays.asList("y")));
			put("21", new HashSet<String> (Arrays.asList("y")));
			put("22", new HashSet<String> (Arrays.asList("y")));
			put("23", new HashSet<String> (Arrays.asList("z")));
			put("24", new HashSet<String> (Arrays.asList("z")));
			put("50", new HashSet<String> (Arrays.asList("z")));
			put("51", new HashSet<String> (Arrays.asList("y")));
			put("52", new HashSet<String> (Arrays.asList("y")));
			put("53", new HashSet<String> (Arrays.asList("y")));			
		}
	};

	private Map<String,Set<String>> unsatVariablesMapping3 = new HashMap<String,Set<String>>() {
		{
			put("15", new HashSet<String> (Arrays.asList("y")));
			put("16", new HashSet<String> (Arrays.asList("y")));
			put("17", new HashSet<String> (Arrays.asList("z")));
			put("18", new HashSet<String> (Arrays.asList("z")));
			put("50", new HashSet<String> (Arrays.asList("y","z")));
			put("51", new HashSet<String> (Arrays.asList("y","z")));
			put("52", new HashSet<String> (Arrays.asList("y","z")));
			put("53", new HashSet<String> (Arrays.asList("y","z")));			
			put("54", new HashSet<String> (Arrays.asList("y")));
		}
	};

	private Map<String,Set<String>> unsatVariablesMapping4 = new HashMap<String,Set<String>>() {
		{
			put("15", new HashSet<String> (Arrays.asList("y")));
			put("16", new HashSet<String> (Arrays.asList("y")));
			put("17", new HashSet<String> (Arrays.asList("z")));
			put("18", new HashSet<String> (Arrays.asList("z")));
			put("50", new HashSet<String> (Arrays.asList("y","z")));
			put("51", new HashSet<String> (Arrays.asList("y","z")));
			put("52", new HashSet<String> (Arrays.asList("y","z")));
			put("53", new HashSet<String> (Arrays.asList("y","z")));			
			put("54", new HashSet<String> (Arrays.asList("y","z")));
		}
	};
	
	private Map<String,Set<String>> unsatVariablesMappingA = new HashMap<String,Set<String>>() {
		{
			put("1", new HashSet<String> (Arrays.asList("a")));
			put("2", new HashSet<String> (Arrays.asList("b")));
			put("3", new HashSet<String> (Arrays.asList("c")));
			put("4", new HashSet<String> (Arrays.asList("d")));
			put("5", new HashSet<String> (Arrays.asList("e")));
			put("6", new HashSet<String> (Arrays.asList("f")));
			put("7", new HashSet<String> (Arrays.asList("g")));
			put("8", new HashSet<String> (Arrays.asList("h")));			
			put("9", new HashSet<String> (Arrays.asList("i")));
			put("10", new HashSet<String> (Arrays.asList("j")));
		}
	};

	private Map<String,Set<String>> unsatVariablesMappingB = new HashMap<String,Set<String>>() {
		{
			put("21", new HashSet<String> (Arrays.asList("a")));
			put("22", new HashSet<String> (Arrays.asList("b")));
			put("23", new HashSet<String> (Arrays.asList("c")));
			put("24", new HashSet<String> (Arrays.asList("d")));
			put("25", new HashSet<String> (Arrays.asList("e")));
			put("26", new HashSet<String> (Arrays.asList("f")));
			put("27", new HashSet<String> (Arrays.asList("g")));
			put("28", new HashSet<String> (Arrays.asList("h")));			
			put("29", new HashSet<String> (Arrays.asList("i")));
			put("30", new HashSet<String> (Arrays.asList("j")));
		}
	};
	
	private Map<String,Set<String>> unsatVariablesMappingC = new HashMap<String,Set<String>>() {
		{
			put("1", new HashSet<String> (Arrays.asList("a")));
			put("2", new HashSet<String> (Arrays.asList("b")));
			put("3", new HashSet<String> (Arrays.asList("c")));
			put("4", new HashSet<String> (Arrays.asList("d")));
			put("5", new HashSet<String> (Arrays.asList("e")));
			put("6", new HashSet<String> (Arrays.asList("f")));
			put("7", new HashSet<String> (Arrays.asList("g")));
			put("8", new HashSet<String> (Arrays.asList("h")));			
			put("9", new HashSet<String> (Arrays.asList("i")));
			put("10", new HashSet<String> (Arrays.asList("j")));
			put("21", new HashSet<String> (Arrays.asList("a","b")));
			put("22", new HashSet<String> (Arrays.asList("b")));
			put("23", new HashSet<String> (Arrays.asList("c","d","g","h","i","j")));
			put("24", new HashSet<String> (Arrays.asList("d","g","h","i","j")));
			put("25", new HashSet<String> (Arrays.asList("e","b")));
			put("26", new HashSet<String> (Arrays.asList("f")));
			put("27", new HashSet<String> (Arrays.asList("g","h","i","j")));
			put("28", new HashSet<String> (Arrays.asList("h")));			
			put("29", new HashSet<String> (Arrays.asList("i")));
			put("30", new HashSet<String> (Arrays.asList("j")));
		}
	};
	
	private Map<String,Set<String>> unsatVariablesMappingD = new HashMap<String,Set<String>>() {
		{
			put("27", new HashSet<String> (Arrays.asList("agent_1.comprar")));
			put("28", new HashSet<String> (Arrays.asList("agent_1.comprar")));			
			put("29", new HashSet<String> (Arrays.asList("agent_3.comprar")));
			put("30", new HashSet<String> (Arrays.asList("agent_3.comprar")));
			put("77", new HashSet<String> (Arrays.asList("agent_2.comprar")));
			put("78", new HashSet<String> (Arrays.asList("agent_4.comprar")));
			put("79", new HashSet<String> (Arrays.asList("agent_3.comprar")));
			put("80", new HashSet<String> (Arrays.asList("agent_1.comprar")));
		}
	};
	
	private Map<String,Set<String>> unsatVariablesMappingE = new HashMap<String,Set<String>>() {
		{
			put("52", new HashSet<String> (Arrays.asList("y","z")));
			put("53", new HashSet<String> (Arrays.asList("y")));
			put("54", new HashSet<String> (Arrays.asList("y","z")));
		}
	};
	
	private Map<String,Set<String>> unsatVariablesMappingF = new HashMap<String,Set<String>>() {
		{	
			put("135", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("136", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("137", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("138", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("139", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("171", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("172", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("173", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("174", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("175", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("392", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("394", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("422", new HashSet<String> (Arrays.asList("ventas_realizadas")));
			put("140", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("141", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("142", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("143", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("144", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("176", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("177", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("178", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("179", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("180", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("386", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("396", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("423", new HashSet<String> (Arrays.asList("compras_realizadas")));
			put("398", new HashSet<String> (Arrays.asList("ventas_realizadas", "compras_realizadas")));
			put("424", new HashSet<String> (Arrays.asList("agent_6.vender", "agent_4.vender", "agent_8.vender", "agent_7.vender", "agent_9.comprar", "agent_4.comprar", "agent_5.comprar", "ventas_realizadas", "agent_6.comprar", "agent_1.vender", "compras_realizadas", "agent_3.comprar", "agent_8.comprar", "agent_7.comprar", "agent_2.comprar")));
			put("425", new HashSet<String> (Arrays.asList("agent_6.vender", "agent_4.vender", "agent_8.vender", "agent_7.vender", "agent_9.comprar", "agent_4.comprar", "agent_5.comprar", "ventas_realizadas", "agent_6.comprar", "agent_1.vender", "compras_realizadas", "agent_3.comprar", "agent_8.comprar", "agent_7.comprar", "agent_2.comprar")));
		}
	};
	
	private Set<String> flEntities = new HashSet<String> (Arrays.asList(
			"agent_6.vender",
			"agent_4.vender",
			"agent_8.vender",
			"agent_7.vender",
			"agent_9.comprar",
			"agent_4.comprar",
			"agent_5.comprar", 
			"ventas_realizadas",
			"agent_6.comprar",
			"agent_1.vender",
			"compras_realizadas",
			"agent_3.comprar",
			"agent_8.comprar",
			"agent_7.comprar",
			"agent_2.comprar"
			));
}
