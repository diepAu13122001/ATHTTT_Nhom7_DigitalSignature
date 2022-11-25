package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cart.ShoppingCartItem;
import model.Product;
import servlet.Cart;

public class WritePDF {
	private static final Font FONT_FIELDNAME = new Font(FontFamily.HELVETICA, 12,Font.ITALIC, BaseColor.BLACK);
	private static final Font FONT_TITLE2 = new Font(FontFamily.HELVETICA, 14,Font.BOLD, BaseColor.BLACK);
	private static final Font FONT_TITLE1 = new Font(FontFamily.HELVETICA, 18,Font.BOLD, BaseColor.BLACK);
	private static final TabSettings TAB_FIELDNAME = new TabSettings(150f);
	public static void main(String[] args) {
//		makeInvoicePDF("invoice.pdf", "Nguyen Khai Hieu","khaihieupc2@gmail.com","0343385406","Thu Duc-HCM-Viet Nam",
//				"03/Duong 17/TP Thu Duc/TP HCM","","","30/11/2022",null);
		//System.out.println(formatCurrent(10000000.0));
	}

	public static void makeInvoicePDF(String name, String phoneNumber,String email,String address, String desAddress,
			String shipping,String payement, String dateIssue, List<ShoppingCartItem<Product>> list) {
		 Document document = new Document();

	        try {
	        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
	            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Asus\\eclipse-workspace\\ATBMHTT\\ATHTTT_Nhom7_DigitalSignature\\Web\\invoice.pdf"));

	            // mở file để thực hiện viết
	            document.open();
	            // thêm nội dung sử dụng add function
	            Paragraph paragraph1 = new Paragraph("INVOICE",FONT_TITLE1);
	            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	            paragraph1.setSpacingAfter(15);
	            document.add(paragraph1);
	            
	            document.add(new Paragraph("Information",FONT_TITLE2));
	      
	            Paragraph paragraph2 = new Paragraph();
	            paragraph2.add(new Phrase("Name:",FONT_FIELDNAME));
	            paragraph2.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase1 = new Phrase(removeAccent(name));           
	            paragraph2.add(Chunk.TABBING);
	            paragraph2.add(phrase1);
	            paragraph2.setIndentationLeft(25);
	            document.add(paragraph2);
	            //
	            Paragraph paragraph3 = new Paragraph();
	            paragraph3.add(new Phrase("Phone number:",FONT_FIELDNAME));
	            paragraph3.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase2 = new Phrase(removeAccent(phoneNumber));           
	            paragraph3.add(Chunk.TABBING);
	            paragraph3.add(phrase2);
	            paragraph3.setIndentationLeft(25);
	            document.add(paragraph3);
	            //
	            Paragraph paragraph4 = new Paragraph();
	            paragraph4.add(new Phrase("Email:",FONT_FIELDNAME));
	            paragraph4.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase3 = new Phrase(removeAccent(email));           
	            paragraph4.add(Chunk.TABBING);
	            paragraph4.add(phrase3);
	            paragraph4.setIndentationLeft(25);
	            document.add(paragraph4);
	            //
	            Paragraph paragraph5 = new Paragraph();
	            paragraph5.add(new Phrase("Address:",FONT_FIELDNAME));
	            paragraph5.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase4 = new Phrase(removeAccent(address));           
	            paragraph5.add(Chunk.TABBING);
	            paragraph5.add(phrase4);
	            paragraph5.setIndentationLeft(25);
	            document.add(paragraph5);
	            //
	            Paragraph paragraph6 = new Paragraph();
	            paragraph6.add(new Phrase("Description address:",FONT_FIELDNAME));
	            paragraph6.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase6 = new Phrase(removeAccent(desAddress));           
	            paragraph6.add(Chunk.TABBING);
	            paragraph6.add(phrase6);
	            paragraph6.setIndentationLeft(25);
	            document.add(paragraph6);
	            //
	            Paragraph paragraph7 = new Paragraph();
	            paragraph7.add(new Phrase("Date issue :",FONT_FIELDNAME));
	            paragraph7.setTabSettings(TAB_FIELDNAME);
	            Phrase phrase7 = new Phrase(dateIssue);           
	            paragraph7.add(Chunk.TABBING);
	            paragraph7.add(phrase7);
	            paragraph7.setIndentationLeft(25);
	            document.add(paragraph7);
	            //
	            Paragraph paragraphTile2 =  new Paragraph("Orders",FONT_TITLE2);
	            paragraphTile2.setSpacingAfter(10);
	            paragraphTile2.setSpacingBefore(10);
	            document.add(paragraphTile2);
	            //
	            PdfPTable t = new PdfPTable(4);

	            PdfPCell c1 = new PdfPCell(new Phrase("Name product"));
	            t.addCell(c1);
	            PdfPCell c2 = new PdfPCell(new Phrase("Quantity"));
	            t.addCell(c2);
	            PdfPCell c3 = new PdfPCell(new Phrase("Price"));
	            t.addCell(c3);
	            PdfPCell c4 = new PdfPCell(new Phrase("Total"));
	            t.addCell(c4);
	            if(list!=null) {
	            for(ShoppingCartItem<Product> cartItem : list) {
	            	t.addCell(removeAccent(cartItem.getItem().getNameProduct()) );
	 	            t.addCell(String.valueOf(cartItem.getQuantity()));
	 	            t.addCell(formatCurrent(cartItem.getItem().getPrice()));
	 	            double total = cartItem.getItem().getPrice()*cartItem.getQuantity();
	 	            t.addCell(formatCurrent(total));
	            }
	            }
	            document.add(t);
	            
	            
	            // đóng filelo World PDF document.
	            document.close();
	            System.out.println("OK");

	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	public static String removeAccent(String s) {		
		s=s.replaceAll("Đ", "D");
		s=s.replaceAll("đ", "d");
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String unAccent = pattern.matcher(temp).replaceAll("");		
		return unAccent;
	}
	public static String formatCurrent(double price) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(price)+" VNĐ";
	}
}
