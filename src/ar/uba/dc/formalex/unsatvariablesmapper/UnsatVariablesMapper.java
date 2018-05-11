package ar.uba.dc.formalex.unsatvariablesmapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnsatVariablesMapper {

	private Set<String> unsatVariables;	
	private Map<String, Set<String>> cnfVariablesMap;
	private Map<String, Set<String>> convertedVariablesMap;
	private Map<String, Set<String>> unsatVariablesMap;

	public UnsatVariablesMapper(String unsatVariablesFilePath) {
		unsatVariables = parseUnsatVariables(unsatVariablesFilePath);
	}

	public UnsatVariablesMapper(String unsatVariablesFilePath, String modelFilePath) {
		unsatVariables = parseUnsatVariables(unsatVariablesFilePath);
		cnfVariablesMap = mapCnfVariables(modelFilePath);
		convertedVariablesMap = mapConvertedVariables(modelFilePath);
		unsatVariablesMap = mapUnsatVariables();
	}

	/*
	 * Parsea el archivo de variables unsat y las vuelva a una lista de nombre de variables 
	 */
	public Set<String> parseUnsatVariables(String unsatVariablesFilePath) {
		
		Set<String> ret = new HashSet<String>();
		try {
			BufferedReader unsatVariablesBufferReader = new BufferedReader(new FileReader(unsatVariablesFilePath));
			String line;
			while(( line = unsatVariablesBufferReader.readLine()) != null) {
				ret.add(line);
			}
			unsatVariablesBufferReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/*
	 * Parsea el archivo BMC generado por NuSMV y genera el mapa de variable CNF a variable del Model
	 */
	public Map<String, Set<String>> mapCnfVariables(String modelFilePath) {
		
		Map<String, Set<String>> ret = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(modelFilePath));
			String line;
			while((line = br.readLine())!=null){
				// Solo me quedo con las lineas que especifican el mapeo que estoy realizando
				if(line.matches(".*CNF variable .*")) {
					String cnfVariableName = substringBetweenTwoDelimiters(line, "CNF variable ", " =>");
					String modelVariableName = substringBetweenTwoDelimiters(line, "Model Variable ", ".");
					ret.put(cnfVariableName, new HashSet<String> (Arrays.asList(modelVariableName)));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/*
	 * Parsea el archivo BMC generado por NuSMV y genera el mapa de variable convertida a variables originales
	 */
	private Map<String, Set<String>> mapConvertedVariables(String modelFilePath) {
		Map<String, Set<String>> ret = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(modelFilePath));
			String line;
			while((line = br.readLine())!=null) {
				// Solo me quedo con las lineas que especifican el mapeo que estoy realizando
				if(line.matches(".*converted .*")) {
					// Elimino los espacios y las negaciones 
					line = line.replaceAll("-| ", "");

					// Obtengo el nombre de la variable convertida
					String convertedVariableName = substringBetweenTwoDelimiters(line, "converted", ":[");

					// Obtengo el Set de las variables originales correspondientes a la variable convertida
					String variableSet = substringBetweenTwoDelimiters(line, "[", "]");
					
					// Elimino los duplicados de los nombres de variables
					Set<String> variableSetWithoutDuplicates = new HashSet(Arrays.asList(variableSet.split(",")));
					
					ret.put(convertedVariableName, variableSetWithoutDuplicates);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Map<String, Set<String>> mapUnsatVariables() {
		Map<String, Set<String>> ret = new HashMap<>();
		
		for (String unsatVariable : unsatVariables) {
			
			Set<String> listVariables = null;
			
			// Si la variable unsat esta entre las cnf uso el mismo mapeo
			if(cnfVariablesMap.containsKey(unsatVariable)) {
				listVariables = cnfVariablesMap.get(unsatVariable);
			} else {
			// si no debo buscar en las conversiones
				listVariables = obtenerListaDeVariables(unsatVariable);
				
			}
			ret.put(unsatVariable, listVariables);
		}
		return ret;
	}

	
	private Set<String> obtenerListaDeVariables(String unsatVariable) {
		Set<String> ret = new HashSet<>();
		
		Set<String> listVariables = convertedVariablesMap.get(unsatVariable);
		
		for (String variable : listVariables) {
			if (cnfVariablesMap.containsKey(variable)) {
				ret.addAll(cnfVariablesMap.get(variable));
			} else {
				ret.addAll(obtenerListaDeVariables(variable));
			}
		}
		return ret;
	}

	private String substringBetweenTwoDelimiters(String string, String startDelimiter, String endDelimiter) {
		int startIndex = string.indexOf(startDelimiter) + startDelimiter.length();
		int endIndex = string.indexOf(endDelimiter);
		return string.substring(startIndex, endIndex);
	}

	public Set<String> getUnsatVariables() {
		return unsatVariables;
	}

	public Map<String, Set<String>> getCnfVariablesMap() {
		return cnfVariablesMap;
	}

	public Map<String, Set<String>> getConvertedVariablesMap() {
		return convertedVariablesMap;
	}

	public Map<String, Set<String>> getUnsatVariablesMap() {
		return unsatVariablesMap;
	}


}
