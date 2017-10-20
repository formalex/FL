package ar.uba.dc.formalex.automatoncompactor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ar.uba.dc.formalex.fl.bgtheory.Action;
import ar.uba.dc.formalex.fl.bgtheory.BackgroundTheory;
import ar.uba.dc.formalex.fl.bgtheory.Counter;
import ar.uba.dc.formalex.fl.bgtheory.Interval;
import ar.uba.dc.formalex.fl.bgtheory.Timer;

public class AutomatonReplacementsGenerator {

	private BackgroundTheory backgroundTheory;
	private String dir;
	private String automatonName;
	private File replacementsFile;

	public AutomatonReplacementsGenerator(BackgroundTheory bt, String dir, String automatonName) {
		this.backgroundTheory = bt;
		this.dir = dir;
		this.automatonName = automatonName;
	}

	public Map<String, String> createVariableAndStatesReplacements() {
		Map<String, String> replacements = null;
		try {
			StringBuffer replacementStringBuffer = new StringBuffer();
			
			replacements = new HashMap<String, String>() {{
			    put("NOT_HAPPENING", "NH");
			    put("HAPPENING", "H");
			    put("JUST_HAPPENED", "JH");
			    put("ACTIVE", "A");
			    put("INACTIVE", "I");
			}};
	
			replacementStringBuffer.append("NOT_HAPPENING="+replacements.get("NOT_HAPPENING")+"\n");
			replacementStringBuffer.append("HAPPENING="+replacements.get("HAPPENING")+"\n");
			replacementStringBuffer.append("JUST_HAPPENED="+replacements.get("JUST_HAPPENED")+"\n");
			replacementStringBuffer.append("ACTIVE="+replacements.get("ACTIVE")+"\n");
			replacementStringBuffer.append("INACTIVE="+replacements.get("INACTIVE")+"\n");
			
			int variableCounter = 0;
			
			for (Action action : backgroundTheory.getActions()) {
				replacements.put(action.getName(), "i"+variableCounter);
				variableCounter++;
				replacementStringBuffer.append(action.getName() + "="+replacements.get(action.getName())+"\n");
			}
			
			for (Interval interval : backgroundTheory.getIntervals()) {
				replacements.put(interval.getName(), "i"+variableCounter);
				variableCounter++;
				replacementStringBuffer.append(interval.getName() + "="+replacements.get(interval.getName())+"\n");
			}
	
			for (Counter counter : backgroundTheory.getCounters()) {
				replacements.put(counter.getName(), "i"+variableCounter);
				variableCounter++;
				replacementStringBuffer.append(counter.getName() + "="+replacements.get(counter.getName())+"\n");
			}
			
			for (Timer timer : backgroundTheory.getTimers()) {
				for (String evento : timer.getEventos()) {
					replacements.put(evento, "i"+variableCounter);
					variableCounter++;
					replacementStringBuffer.append(evento + "="+replacements.get(evento)+"\n");
				}
			}
			
			replacementsFile = new File(dir + "/"+automatonName + "Replacements");
			FileWriter replacementsFileWriter;
			replacementsFileWriter = new FileWriter(replacementsFile);
			replacementsFileWriter.write(replacementStringBuffer.toString());
			replacementsFileWriter.flush();
			replacementsFileWriter.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return replacements;
	}

	public File getReplacementFile() {
		return replacementsFile;
	}

}
