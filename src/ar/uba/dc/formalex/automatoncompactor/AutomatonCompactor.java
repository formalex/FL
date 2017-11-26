package ar.uba.dc.formalex.automatoncompactor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

	public AutomatonCompactor(String dir, String automatonName, String automatonExtension, Map<String, String> replacements) {
		this.automatonName = automatonName;
		this.automatonExtension = automatonExtension;
		this.replacements = replacements;
		this.inverseReplacements = inverseReplacements(replacements);
		this.dir = dir;
	}

	public AutomatonCompactor(String dir, String automatonName, String automatonExtension, String replacementsFileName) {
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
		
		this.automatonName = automatonName;
		this.automatonExtension = automatonExtension;
		this.replacements = replacements;
		this.inverseReplacements = inverseReplacements(replacements);
		this.dir = dir;
	}

	private Map<String, String> inverseReplacements(Map<String, String> replacements) {
		Map<String, String> inverseReplacements = new HashMap<String, String>();
		for (String replacement : replacements.keySet()) {
			inverseReplacements.put(replacements.get(replacement), replacement);
		}
		return inverseReplacements;
	}

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
			StringBuffer automatonWithoutCommentsStringBuffer = new StringBuffer();
			
			Pattern p = Pattern.compile("(^|\\s)--.*");
			Matcher m = p.matcher(fromFile(dir + "/"+ automatonName + automatonExtension));
			
			while (m.find()) {
				m.appendReplacement(automatonWithoutCommentsStringBuffer, "");
			}
			m.appendTail(automatonWithoutCommentsStringBuffer);

			StringBuffer automatonWithoutCommentsAndEmptyLinesStringBuffer = new StringBuffer();
			
			Pattern pEmptyLines = Pattern.compile("(?m)^[ \\t]*\\r?\\n");
			Matcher mEmptyLines = pEmptyLines.matcher(automatonWithoutCommentsStringBuffer);
			
			while (mEmptyLines.find()) {
				mEmptyLines.appendReplacement(automatonWithoutCommentsAndEmptyLinesStringBuffer, "");
			}
			mEmptyLines.appendTail(automatonWithoutCommentsAndEmptyLinesStringBuffer);
	
			File automatonWithoutCommentsFile = new File(dir + "/"+automatonName + "WithoutCommentsAndEmptyLines" + automatonExtension);
			FileWriter compactedFileWriter = new FileWriter(automatonWithoutCommentsFile);
			compactedFileWriter.write(automatonWithoutCommentsAndEmptyLinesStringBuffer.toString());
			compactedFileWriter.flush();
			compactedFileWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public File performFileReplacements(String dir, String originalFileName, String fileWithReplacementsName, Map<String, String> replacementsMap) {

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
	
	public String performStringReplacements(CharSequence originalString, Map<String, String> replacementMap) {

			StringBuffer stringWithReplacementsBuffer = new StringBuffer();
			StringBuffer pattern = new StringBuffer();
			
			for (String toBeReplaced : replacementMap.keySet()) {
				pattern.append("|"+toBeReplaced);
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

    public CharSequence fromFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();
     
        // Create a read-only CharBuffer on the file
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
        CharBuffer cbuf = Charset.forName("UTF-8").newDecoder().decode(bbuf);
        input.close();
        return cbuf;
    }

}
