package ar.uba.dc.formalex.unsatvariablesmapper;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UnsatVariablesMapperTest {
	
	private String resourcesDir = "./resources/unsatvariablesmapper";
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
			put("53", new HashSet<String> (Arrays.asList("32","52")));
			put("52", new HashSet<String> (Arrays.asList("27","51","33","44")));
			put("51", new HashSet<String> (Arrays.asList("33","44")));
			put("44", new HashSet<String> (Arrays.asList("28","34")));
			put("40", new HashSet<String> (Arrays.asList("26","39")));
			put("39", new HashSet<String> (Arrays.asList("21","38","27","37")));
			put("38", new HashSet<String> (Arrays.asList("27","37")));
			put("37", new HashSet<String> (Arrays.asList("22","28")));
			put("36", new HashSet<String> (Arrays.asList("23","24")));
			put("50", new HashSet<String> (Arrays.asList("29","30")));
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

	
	@Test
	public void unsatMapperShouldParseFileIntoAVariableNameSet() {
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName );
		
		Set<String> unsatVariables = unsatVariablesMapper.getUnsatVariables();
		
		assertTrue(unsatVariables.containsAll(unsatVariables1));
			
 	}
	
	@Test
	public void unsatMapperShouldParseCNFVariablesIntoAMap() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName );
		
		Map<String,Set<String>> cnfVariablesMappingObtained = unsatVariablesMapper.getCnfVariablesMap();
		
		assertTrue(compareMappingsByEquality(cnfVariablesMapping1, cnfVariablesMappingObtained));
		
	}

	@Test
	public void unsatMapperShouldParseConvertedVariablesIntoAMap() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName );
		
		Map<String,Set<String>> convertedVariablesMap = unsatVariablesMapper.getConvertedVariablesMap();
		
		assertTrue(compareMappingsByEquality(convertedVariablesMap1, convertedVariablesMap));
		
	}

	@Test
	public void unsatMapperShouldMapUnsatVariablesWithModelVariables() {
		
		String unsatVariablesFileName = "unsat1.variables";
		String modelFileName = "model1.dimacs";
		UnsatVariablesMapper unsatVariablesMapper = new UnsatVariablesMapper(resourcesDir +"/" + unsatVariablesFileName, resourcesDir +"/" + modelFileName );
		
		Map<String,Set<String>> unsatVariablesMap = unsatVariablesMapper.getUnsatVariablesMap();
		
		assertTrue(compareMappingsByEquality(unsatVariablesMapping1, unsatVariablesMap));
		
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
				} else {
					return false;
				}
			}
		}
		return ret;
	}

}
