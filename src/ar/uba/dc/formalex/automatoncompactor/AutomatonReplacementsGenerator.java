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
import ar.uba.dc.formalex.fl.regulation.formula.terminals.LtlActionValue;

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
			
			String notHappening = LtlActionValue.NOT_HAPPENING.getValue();
			String happening = LtlActionValue.HAPPENING.getValue();
			String justHappened = LtlActionValue.JUST_HAPPENED.getValue();
			String active = "ACTIVE";
			String inactive = "INACTIVE";

			replacements = new HashMap<String, String>() {{
				put(notHappening, "NH");
				put(happening, "HA");
				put(justHappened, "JH");
				put(active, "AC");
				put(inactive, "IA");
			}};
	
			replacementStringBuffer.append(notHappening + "=" + replacements.get(notHappening)+"\n");
			replacementStringBuffer.append(happening + "=" + replacements.get(happening)+"\n");
			replacementStringBuffer.append(justHappened + "=" + replacements.get(justHappened)+"\n");
			replacementStringBuffer.append(active + "=" + replacements.get(active)+"\n");
			replacementStringBuffer.append(inactive + "=" + replacements.get(inactive)+"\n");
			
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
