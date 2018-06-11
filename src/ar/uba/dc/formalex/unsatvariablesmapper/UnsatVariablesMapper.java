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
	private Map<String, Set<String>> convertedToModelVariablesMap = new HashMap<>();
	private Map<String, Set<String>> unsatVariablesMap = new HashMap<>();

	public UnsatVariablesMapper(String unsatVariablesFilePath) {
		unsatVariables = parseUnsatVariables(unsatVariablesFilePath);
	}

	public UnsatVariablesMapper(String unsatVariablesFilePath, String modelFilePath) {
		unsatVariables = parseUnsatVariables(unsatVariablesFilePath);
		cnfVariablesMap = parseCnfVariables(modelFilePath);
		convertedVariablesMap = parseConvertedVariables(modelFilePath);
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
	public Map<String, Set<String>> parseCnfVariables(String modelFilePath) {
		
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
	private Map<String, Set<String>> parseConvertedVariables(String modelFilePath) {
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

	/*
	 * Por cada una de las variables unsat mapeo las variables del modelo correspondiente
	 */
	public Map<String, Set<String>> mapUnsatVariables() {
		Set<String> convertedUnsatVariables = new HashSet<String>();
		// Si la variable unsat esta entre las mapeadas por cnf las vuelco en el resultado final
		// De lo contrario lo guardo en un set para tratarlo aparte
		for (String unsatVariable : unsatVariables) {
			// Si la variable unsat esta entre las cnf uso el mismo mapeo
			if(cnfVariablesMap.containsKey(unsatVariable)) {
				unsatVariablesMap.put(unsatVariable, cnfVariablesMap.get(unsatVariable));
			} else {
			// de lo contrario lo agrego a un set para tratarlo aparte
				convertedUnsatVariables.add(unsatVariable);
			}
		}
		
		// Se recorren las variables unsat que no estan entre las cnf y obtengo el mapeo de cada una  
		for (String convertedUnsatVariable : convertedUnsatVariables) {
			unsatVariablesMap.put(convertedUnsatVariable, mapConvertedUnsatToModelVariable(convertedUnsatVariable));
		}
		
		return unsatVariablesMap;
	}

	// Toma una variable unsat que fue convertida y devuelve su mapeo con variables del modelo.
	public Set<String> mapConvertedUnsatToModelVariable(String unsatVariable) {
		Set<String> ret = new HashSet<>();
		
		// Obtengo las variables que componen la conversion de la variable unsat.
		Set<String> listVariables = convertedVariablesMap.get(unsatVariable);
		
		// Recorro todas las variables que componen la conversion
		for (String variable : listVariables) {
			// Si la variable era una cnf entonces la agrego a la respuesta
			if (cnfVariablesMap.containsKey(variable)) {
				ret.addAll(cnfVariablesMap.get(variable));
			} else if (convertedToModelVariablesMap.containsKey(variable)){
				// Si la variable no era una cnf y ya la habia mapeado anteriormente la utilizo para agregar a la respuesta
				ret.addAll(convertedToModelVariablesMap.get(variable));
			} else {
				// Si no la encuentro se vuelve a llamar recursivamente.
				Set<String> convertedToModelVariable = mapConvertedUnsatToModelVariable(variable);
				// Lo guardo por si lo vuelvo a utilizar
				convertedToModelVariablesMap.put(variable, convertedToModelVariable);
				// Lo agrego al resultado
				ret.addAll(convertedToModelVariable);
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
