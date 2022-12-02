package Model;

import java.io.*;
import java.text.*;

import javax.sound.midi.Soundbank;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

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

	public boolean createFilePDF(String path) throws Exception {
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
		return true;
	}

	public boolean inserttextPDF(String text, String filePath) throws Exception {
		Document document = new Document();
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		document.open();
		File fontFile = new File("vuArial.ttf");
		BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 15);
		document.add(new Paragraph(text, font));
		document.close();
		writer.close();
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		DAO dao = new DAO();
		//System.out.println(dao.createFilePDF("bill3.pdf"));
		//System.out.println(dao.inserttextPDF("Bill 0079 \nItem1: đậu nành nóng	 12.000VND\nItem2: hạt lanh nguội	 23.000VND\nTổng: ...", "bill3.pdf"));
		Document document = new Document();
		File file = new File("bill3.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		document.open();
		File fontFile = new File("vuArial.ttf");
		BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 15);
		document.add(new Paragraph("Diệp", font));
		document.close();
		writer.close();
	}
}
