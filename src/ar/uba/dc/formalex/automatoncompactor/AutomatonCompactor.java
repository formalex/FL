package ar.uba.dc.formalex.automatoncompactor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutomatonCompactor {

	private String automatonName;
	private String automatonExtension;
	private Map<String, String> replacements;
	private String dir;
	private Map<String, String> inverseReplacements;

	/**
	 * Constructor que genera la instancia a partir de un mapa de reemplazos
	 * 
	 * @param dir
	 * @param automatonName
	 * @param automatonExtension
	 * @param replacements
	 */
	public AutomatonCompactor(String dir, String automatonName, String automatonExtension, Map<String, String> replacements) {
		this.automatonName = automatonName;
		this.automatonExtension = automatonExtension;
		this.replacements = replacements;
		this.inverseReplacements = inverseReplacements(replacements);
		this.dir = dir;
	}

	/**
	 * Constructor que genera la instancia a partir de un archivo de reemplazos
	 * 
	 * @param dir
	 * @param automatonName
	 * @param automatonExtension
	 * @param replacementsFileName
	 */
	public AutomatonCompactor(String dir, String automatonName, String automatonExtension, String replacementsFileName) {
		Map<String, String> replacements = generateReplacementsMapFromFile(dir, replacementsFileName);
		this.automatonName = automatonName;
		this.automatonExtension = automatonExtension;
		this.replacements = replacements;
		this.inverseReplacements = inverseReplacements(replacements);
		this.dir = dir;
	}

	/**
	 * Compacta el archivo del automata mediante los reemplazos generando el archivo compactado 
	 * Tiene la posibilidad de eliminar comentarios del archivo
	 * 
	 * @param removeComments
	 * @param compactedFileName
	 * @return
	 */
	public File compact(boolean removeComments, String compactedFileName) {
		if (removeComments) {
			this.removeCommentsAndEmptyLines();
			automatonName = automatonName + "WithoutCommentsAndEmptyLines";
		}
		
		return performFileReplacements(dir, automatonName + automatonExtension, compactedFileName + automatonExtension, replacements);
	}

	public File decompact(String decompactedFileName) {

		return performFileReplacements(dir, automatonName + automatonExtension, decompactedFileName, inverseReplacements);
		
	}

	public String reduceExpression(String ltlExpr, Map<String, String> replacements) {
		
		return performStringReplacements(ltlExpr, replacements);
		
	}

	public void removeCommentsAndEmptyLines() {
		try {
			StringBuffer automatonWithoutComments = removeCommentLines(dir + "/"+ automatonName + automatonExtension, "--");

			StringBuffer automatonWithoutCommentsAndEmptyLines = replacePattern(automatonWithoutComments, "(?m)^[ \\t]*\\r?\\n", "");
			
			File automatonWithoutCommentsFile = new File(dir + "/"+automatonName + "WithoutCommentsAndEmptyLines" + automatonExtension);
			
			FileWriter compactedFileWriter = new FileWriter(automatonWithoutCommentsFile);
			
			compactedFileWriter.write(automatonWithoutCommentsAndEmptyLines.toString());
			
			compactedFileWriter.flush();
			compactedFileWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private StringBuffer removeCommentLines(String filename, String commentString) {
		
		StringBuffer sb = new StringBuffer("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.contains(commentString)) {
					sb.append(line + "\n");
				} else {
					String trimmedLine = line.trim();
					if (trimmedLine.indexOf(commentString) > 0) {
						sb.append(trimmedLine.substring(0, trimmedLine.indexOf(commentString)) + "\n");
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	private StringBuffer replacePattern(StringBuffer stringBuffer, String pattern, String replacement) {
		StringBuffer replacedStringBuffer = new StringBuffer();
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(stringBuffer);
		
		while (m.find()) {
			m.appendReplacement(replacedStringBuffer, replacement);
		}
		m.appendTail(replacedStringBuffer);
		
		return replacedStringBuffer;
	}

	private File performFileReplacements(String dir, String originalFileName, String fileWithReplacementsName, Map<String, String> replacementsMap) {

		File fileWithReplacement = null;
		
		try {
			String stringWithReplacements = performStringReplacements(fromFile(dir + "/"+ originalFileName), replacementsMap);

			fileWithReplacement = new File(dir + "/" + fileWithReplacementsName);
			
			FileWriter fileWithReplacementsFileWriter = new FileWriter(fileWithReplacement);
			fileWithReplacementsFileWriter.write(stringWithReplacements);
			fileWithReplacementsFileWriter.flush();
			fileWithReplacementsFileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return fileWithReplacement;
	}
	
	private String performStringReplacements(String originalString, Map<String, String> replacementMap) {

		StringBuffer stringWithReplacementsBuffer = new StringBuffer();
		StringBuffer pattern = new StringBuffer();
		
		for (String toBeReplaced : replacementMap.keySet()) {
			pattern.append("|\\b"+toBeReplaced+"\\b");
		}
		
		pattern.deleteCharAt(0);
		
		Pattern p = Pattern.compile(pattern.toString());
		Matcher m = p.matcher(originalString);
		
		while (m.find()) {
			m.appendReplacement(stringWithReplacementsBuffer, replacementMap.get(m.group()));
		}
		m.appendTail(stringWithReplacementsBuffer);

		return stringWithReplacementsBuffer.toString();
		
	}

    private String fromFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();
     
        // Create a read-only CharBuffer on the file
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
        CharBuffer cbuf = Charset.forName("UTF-8").newDecoder().decode(bbuf);
        input.close();
        return cbuf.toString();
    }
    
	private Map<String, String> generateReplacementsMapFromFile(String dir, String replacementsFileName) {
		Map<String, String> replacements = new HashMap<String, String>();
		try {
			File replacementsFile = new File(dir + "/" + replacementsFileName);
			BufferedReader bufferedReader = Files.newBufferedReader(replacementsFile.toPath());
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split("=");
				replacements.put(parts[0], parts[1]);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return replacements;
	}

	private Map<String, String> inverseReplacements(Map<String, String> replacements) {
		Map<String, String> inverseReplacements = new HashMap<String, String>();
		for (String replacement : replacements.keySet()) {
			inverseReplacements.put(replacements.get(replacement), replacement);
		}
		return inverseReplacements;
	}

	public Map<String, String> getReplacements() {
		return replacements;
	}

	public void setReplacements(Map<String, String> replacements) {
		this.replacements = replacements;
	}

	public Map<String, String> getInverseReplacements() {
		return inverseReplacements;
	}

	public void setInverseReplacements(Map<String, String> inverseReplacements) {
		this.inverseReplacements = inverseReplacements;
	}

}
