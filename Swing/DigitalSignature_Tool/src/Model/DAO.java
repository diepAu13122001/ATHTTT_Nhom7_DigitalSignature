package Model;

import java.io.*;
import java.text.*;

public class DAO {
	public String editText(String input) {
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		accentRemoved = accentRemoved.trim();
		accentRemoved = accentRemoved.toUpperCase();
		return accentRemoved;
	}

	public String readFileTXT(String pathIn) throws FileNotFoundException {
		String result = "";
		File file = new File(pathIn);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		Object[] lines = br.lines().toArray();
		for (int i = 0; i < lines.length; i++) {
			result += lines[i].toString();
			result += "\n";
		}
		return result;
	}

	public boolean writeFileTXT(String text, File pathOut) throws IOException {
		FileWriter myWriter = new FileWriter(pathOut.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(myWriter);
		bw.write(text);
		bw.close();
		myWriter.close();
		return true;
	}
	
	public boolean insertTextTXT(String text, String path) throws Exception {
		FileWriter fw = new FileWriter(path,true); //the true will append the new data
	    fw.write(text);//appends the string to the file
	    fw.close();
		return true;
	}
}
