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

		// Mapa en donde se almacenan las variables que ya tienen completo el mapeo
		Map<String, Set<String>> completedMap = new HashMap<>();
		// Mapa en donde se almacenan las variables aun no tienen completo el mapeo. Se almacenan los mapeos parciales
		Map<String, Set<String>> partialMap = new HashMap<>();
		// Cola que almacena las variables que no tienen completo el mapeo con las variables pendientes de mapear.
		Queue<Tuple> pendingQueue = new LinkedList<>();
		
		// Recorro el conjunto de unsat variables y las que ya se les puede hacer mapeo completo se las vuelca al map completados.
		// A las que no se la vuelca al map de parciales y a la cola de pendientes.
		for (String unsatVariable : unsatVariables) {
			if (cnfVariablesMap.containsKey(unsatVariable)) {
				completedMap.put(unsatVariable, cnfVariablesMap.get(unsatVariable));
			} else {
				partialMap.put(unsatVariable, new HashSet<>());
				pendingQueue.add(new Tuple(unsatVariable, convertedVariablesMap.get(unsatVariable)));
			}
		}

		// Proceso las variables mientras esten pendientes
		while(!pendingQueue.isEmpty()) {
			// Obtengo la primer tupla de la cola de pendientes
			Tuple pendingVariableTuple = pendingQueue.poll();
			String variable = pendingVariableTuple.getVariable();
			Set<String> pendings = pendingVariableTuple.getPendings();
			
			boolean hasToEnqueue = false;
			Set<String> alreadyMappedVariables = new HashSet<String>();
			Set<String> newPendingsVariables = new HashSet<String>();
			
			// Por cada una de las variables pendientes me fijo si se pueden mapear
			for (String pendingVariable : pendings) {
				// Primero me fijo si esta entre las variables cnf. En ese caso agrego el mapeo
				boolean pendingVariableExistsInCnfMap = addMappingOfVariableIfExists(cnfVariablesMap, pendingVariable, alreadyMappedVariables);
				if (!pendingVariableExistsInCnfMap) {
					// Luego me fijo entre las variables que ya se completaron de mapear. Si la encuentro ahi agrego el mapeo.
					boolean pendingVariableExistsInCompletedMap = addMappingOfVariableIfExists(completedMap, pendingVariable, alreadyMappedVariables);
					if (!pendingVariableExistsInCompletedMap) {			
						// Si no es una variable que ya tiene el mapeo completo entonces la tengo que reencolar
						hasToEnqueue = true;
						newPendingsVariables.add(pendingVariable);
						// Busco entre las que tienen el mapeo parcial. Si la encuentro ahi agrego el mapeo.
						boolean pendingVariableExistsInPartialMap = addMappingOfVariableIfExists(partialMap, pendingVariable, alreadyMappedVariables);
						if (!pendingVariableExistsInPartialMap) {
							// Si no la encuentro la agrego en el map de parciales y a la cola.
							partialMap.put(pendingVariable, new HashSet<>());
							pendingQueue.add(new Tuple(pendingVariable, convertedVariablesMap.get(pendingVariable)));
						}
					}
				}
			}
			
			if (hasToEnqueue) {
				// Si tengo que reencolar la variable es porque aun tiene pendientes entonces me fijo si ya estaba entre los parciales. 
				// Si la encuentro entonces agrego los mapeos que ya fui realizando anteriormente.
				addMappingOfVariableIfExists(partialMap, variable, alreadyMappedVariables);
				
				// Agrego la variable con el mapeo que ya fui realizando al mapa de parciales.
				partialMap.put(variable, alreadyMappedVariables);		
				// La variable se vuelve a encolar porque tiene penidentes
				pendingQueue.add(new Tuple(variable, newPendingsVariables));
			} else {
				// Agrego los mapeos que fui realizando anteriormente.
				addMappingOfVariableIfExists(partialMap, variable, alreadyMappedVariables);
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

	// Agrega todos los mapeos que estan almacenados en un mapa a un conjunto. 
	private boolean addMappingOfVariableIfExists(Map<String, Set<String>> partialMap, String variable, Set<String> alreadyMappedVariables) {
		boolean exists = false;
		if (partialMap.containsKey(variable)) {
			exists = true;
			alreadyMappedVariables.addAll(partialMap.get(variable));
		}
		return exists;
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
