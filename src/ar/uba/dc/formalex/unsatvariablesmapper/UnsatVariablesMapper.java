package ar.uba.dc.formalex.unsatvariablesmapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

public class UnsatVariablesMapper {

	private Set<String> unsatVariables;	
	private Map<String, Set<String>> cnfVariablesMap;
	private Map<String, Set<String>> convertedVariablesMap;
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
					Set<String> variableSetWithoutDuplicates = new HashSet<String>(Arrays.asList(variableSet.split(",")));
					
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

		Map<String, Set<String>> completedMap = new HashMap<>();
		Map<String, Set<String>> partialMap = new HashMap<>();
		Queue<Tuple> pendingQueue = new LinkedList<>();
		
		// Recorro el conjunto de unsat variables y las que ya se les puede hacer mapeo se las vuelca al map completados.
		// A las que no se la vuelca a la cola de pendientes.
		for (String unsatVariable : unsatVariables) {
			if (cnfVariablesMap.containsKey(unsatVariable)) {
				completedMap.put(unsatVariable, cnfVariablesMap.get(unsatVariable));
			} else {
				pendingQueue.add(new Tuple(unsatVariable, convertedVariablesMap.get(unsatVariable)));
			}
		}

		// Proceso las variables mientras esten pendientes
		while(!pendingQueue.isEmpty()) {
			// Obtengo el primero de la cola de pendientes
			Tuple pendingVariableTuple = pendingQueue.poll();
			String variable = pendingVariableTuple.getVariable();
			Set<String> pendings = pendingVariableTuple.getPendings();
			
			boolean hasToEnqueue = false;
			Set<String> alreadyMappedVariables = new HashSet<String>();
			Set<String> newPendingsVariables = new HashSet<String>();
			
			// Por cada una de las variables pendientes me fijo si se pueden mapear
			for (String pendingVariable : pendings) {
				// Primero me fijo si esta entre las variables cnf. En ese caso agrego el mapeo
				if (cnfVariablesMap.containsKey(pendingVariable)) {
					alreadyMappedVariables.addAll(cnfVariablesMap.get(pendingVariable));
				} else if (completedMap.containsKey(pendingVariable)) {
					// Luego me fijo entre las variables que ya se completaron de mapear. Si la encuentro ahi agrego el mapeo
					alreadyMappedVariables.addAll(completedMap.get(pendingVariable));
				} else {
					// Si no es una variable que ya tiene el mapeo completo entonces la busco entre las que tiene el mapeo parcial.
					if (partialMap.containsKey(pendingVariable)) {
						alreadyMappedVariables.addAll(partialMap.get(pendingVariable));
					} else {
						pendingQueue.add(new Tuple(pendingVariable, convertedVariablesMap.get(pendingVariable)));
					}
					
					// Debo reencolar la variable porque tiene mapeos pendientes.
					hasToEnqueue = true;
					newPendingsVariables.add(pendingVariable);
				}
			}
			
			if (hasToEnqueue) {
				// Si tengo que reencolar la variable porque aun tiene pendientes entonces me fijo si ya estaba entre los parciales. Si la encuentro entonces agrego
				// los mapeos que ya fui realizando anteriormente.
				if (partialMap.containsKey(variable)) {
					alreadyMappedVariables.addAll(partialMap.get(variable));
				}
				// Agrego la variable con el mapeo que ya fui realizando al mapa de parciales.
				partialMap.put(variable, alreadyMappedVariables);		
				// La variable se vuelve a encolar porque tiene penidentes
				pendingQueue.add(new Tuple(variable, newPendingsVariables));
			} else {
				// Si ya complete todos los mapeos de las variabels entonces me fijo si se encontraba entre los pariciales. Si la encuentro ahi entonces agrego los mapeos
				// que realice anteriormente
				if (partialMap.containsKey(variable)) {
					alreadyMappedVariables.addAll(partialMap.get(variable));
				}
				// Agrego la variable al map de completadas
				completedMap.put(variable, alreadyMappedVariables);
				// Elimino la variable del map de parciales
				partialMap.remove(variable);
			}
		}
		
		// Del mapa de variables completadas filtro las que estaban en el conjunto original de unsatVariables
		for (String unsatVariable : unsatVariables) {
			unsatVariablesMap.put(unsatVariable, completedMap.get(unsatVariable));
		}
		
		return unsatVariablesMap;
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


	class Tuple {

		private String variable;
		private Set<String> pendings;

		public Tuple(String variable, Set<String> pendings) {
			this.variable = variable;
			this.pendings = pendings;
		}

		public String getVariable() {
			return variable;
		}

		public void setVariable(String variable) {
			this.variable = variable;
		}

		public Set<String> getPendings() {
			return pendings;
		}

		public void setPendings(Set<String> pendings) {
			this.pendings = pendings;
		}

		@Override
		public String toString() {
			return "Tuple [variable=" + variable + ", pendings=" + pendings + "]";
		}
		
	}
}
