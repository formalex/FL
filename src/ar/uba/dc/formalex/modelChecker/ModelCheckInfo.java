package ar.uba.dc.formalex.modelChecker;

import java.io.File;

import ar.uba.dc.formalex.automatoncompactor.AutomatonCompactor;
import ar.uba.dc.formalex.util.Fechas;

public class ModelCheckInfo {

	private String tempDir;
	private String ts;
	private String automataName;
	private String automataExtension;
	private String nusmvCommandsName;
	private String nusmvOutputName;
	private File nusmvAutomataFile;
	private File nusmvCommandsFile;
	private File nusmvOutputFile;
	private boolean compactAutomaton;
	private AutomatonCompactor automatonCompactor;

	public ModelCheckInfo(String tempDir, boolean compactAutomaton) {
		
        this.tempDir = tempDir;
		this.compactAutomaton = compactAutomaton;
		this.ts = Fechas.getAAAAMMDD_HHMMSS();
       
		automataName = "automata_" + ts;
		automataExtension = ".nusmv";
		nusmvAutomataFile = new File(tempDir, automataName + automataExtension);
        
		nusmvCommandsName = "nusmvCommands_" + ts;
		nusmvCommandsFile = new File(tempDir, nusmvCommandsName + automataExtension);
		
		nusmvOutputName = "nusmvOut_" + ts;
		nusmvOutputFile = new File(tempDir, nusmvOutputName);
        
        int ind = 1;
        while (nusmvCommandsFile.exists()) {
            nusmvCommandsFile = new File(tempDir, "nusmvCommands_" + ts + "_" + ind++ + automataExtension);
        }
        ind = 1;
        while (nusmvOutputFile.exists()) {
            nusmvOutputFile = new File(tempDir, "nusmvOut_" + ts + "_" + ind++ + automataExtension );
        }
	}

	public String getTempDir() {
		return tempDir;
	}

	public String getAutomataName() {
		return automataName;
	}

	public String getAutomataExtension() {
		return automataExtension;
	}

	public File getNusmvAutomataFile() {
		return nusmvAutomataFile;
	}

	public String getNusmvAutomataPath() {
		return nusmvAutomataFile.getAbsolutePath();
	}
	
	public void setNusmvAutomataFile(File nusmvAutomataFile) {
		this.nusmvAutomataFile = nusmvAutomataFile;
	}

	public File getNusmvCommandsFile() {
		return nusmvCommandsFile;
	}

	public String getNusmvCommandsPath() {
		return nusmvCommandsFile.getAbsolutePath();
	}
	
	public String getNusmvOutputPath() {
		return nusmvOutputFile.getAbsolutePath();
	}

	public String getAutomataCompactedName() {
		return automataName + "Compacted";
	}

	public String getAutomataReplacementName() {
		return automataName + "Replacements";
	}

	public String getOutputPicosatPath() {
		return tempDir + "/outputPicosat_" + ts;
	}

	public String getUnsatVariablesPath() {
		return tempDir + "/unsatVar_" + ts +".var";
	}

	public void setAutomatonCompactor(AutomatonCompactor automatonCompactor) {
		this.automatonCompactor = automatonCompactor;
	}

	public AutomatonCompactor getAutomatonCompactor() {
		return automatonCompactor;
	}

	public boolean isAutomatonCompacted() {
		return compactAutomaton;
	}
	
}
