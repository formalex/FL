package ar.uba.dc.formalex.automatoncompactor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutomatonCompactor {

	private String automatonName;
	private String automatonExtension;
	private Map<String, String> replacements;
	private boolean alreadyCompacted;
	private File compactedAutomatonFile;
	private String dir;
	private StringBuffer reducedExpression;
	private StringBuffer pattern;

	public AutomatonCompactor(String dir, String automatonName, String automatonExtension, Map<String, String> replacements) {
		this.automatonName = automatonName;
		this.automatonExtension = automatonExtension;
		this.replacements = replacements;
		this.alreadyCompacted = false;
		this.dir = dir;
	}

	public void compact() {
		try {
			StringBuffer compactedStringBuffer = new StringBuffer();
			pattern = new StringBuffer();
			
			for (String toBeReplaced : replacements.keySet()) {
				pattern.append("|"+toBeReplaced);
			}
			
			pattern.deleteCharAt(0);
			
			Pattern p = Pattern.compile(pattern.toString());
			Matcher m = p.matcher(fromFile(dir + "/"+ automatonName + automatonExtension));
			
			while (m.find()) {
				m.appendReplacement(compactedStringBuffer, replacements.get(m.group()));
			}
			m.appendTail(compactedStringBuffer);
	
			compactedAutomatonFile = new File(dir + "/"+automatonName + "Compacted" + automatonExtension);
			FileWriter compactedFileWriter = new FileWriter(compactedAutomatonFile);
			compactedFileWriter.write(compactedStringBuffer.toString());
			compactedFileWriter.flush();
			compactedFileWriter.close();
			
			alreadyCompacted = true;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void reduceExpression(String ltlExpr) {
		reducedExpression = new StringBuffer();
		Pattern p = Pattern.compile(pattern.toString());
		Matcher m = p.matcher(ltlExpr);
		while (m.find()) {
			m.appendReplacement(reducedExpression, replacements.get(m.group()));
		}
		m.appendTail(reducedExpression);
	}

	
    public CharSequence fromFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();
     
        // Create a read-only CharBuffer on the file
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
        CharBuffer cbuf = Charset.forName("UTF-8").newDecoder().decode(bbuf);
        return cbuf;
    }

	public File getCompactedAutomatonFile() {
		return compactedAutomatonFile;
	}

	public boolean isAlreadyCompacted() {
		return alreadyCompacted;
	}

	public String getReducedExpression() {
		return reducedExpression.toString();
	}

}
