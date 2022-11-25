package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.mysql.cj.exceptions.StatementIsClosedException;

import cart.ShoppingCartItem;
import model.Product;
import servlet.Cart;

public class WritePDF {
	private static final Font FONT_FIELDNAME = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.BLACK);
	private static final Font FONT_TITLE2 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
	private static final Font FONT_TITLE1 = new Font(FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
	private static final Font FONT_HEADER_TABLE = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
	private static final TabSettings TAB_FIELDNAME = new TabSettings(150f);
	private static final String PATH ="C:\\Users\\Asus\\eclipse-workspace\\ATBMHTT\\ATHTTT_Nhom7_DigitalSignature\\Web\\src\\main\\webapp\\invoice.pdf";
	public static void main(String[] args) {
//		makeInvoicePDF("Nguyen Khai Hieu", "khaihieupc2@gmail.com", "0343385406", "Thu Duc-HCM-Viet Nam",
//				"03/Duong 17/TP Thu Duc/TP HCM", "", "", "30/11/2022", null);
		// System.out.println(formatCurrent(10000000.0));
		System.out.println(formatDate(LocalDateTime.now()));
	}

	public static void makeInvoicePDF(String name, String phoneNumber, String email, String address, String desAddress,
			String shipping, String payement, LocalDateTime dateIssue, List<ShoppingCartItem<Product>> list) {
		Document document = new Document();

		try {
			// khởi tạo một PdfWriter truyền vào document và FileOutputStream
			PdfWriter.getInstance(document, new FileOutputStream(
				PATH));

			// mở file để thực hiện viết
			document.open();
			// thêm nội dung sử dụng add function
			Paragraph paragraph1 = new Paragraph("INVOICE", FONT_TITLE1);
			paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph1.setSpacingAfter(15);
			document.add(paragraph1);

			document.add(new Paragraph("Information", FONT_TITLE2));

			Paragraph paragraph2 = new Paragraph();
			paragraph2.add(new Phrase("Name:", FONT_FIELDNAME));
			paragraph2.setTabSettings(TAB_FIELDNAME);
			Phrase phrase1 = new Phrase(removeAccent(name));
			paragraph2.add(Chunk.TABBING);
			paragraph2.add(phrase1);
			paragraph2.setIndentationLeft(25);
			document.add(paragraph2);
			//
			Paragraph paragraph3 = new Paragraph();
			paragraph3.add(new Phrase("Phone number:", FONT_FIELDNAME));
			paragraph3.setTabSettings(TAB_FIELDNAME);
			Phrase phrase2 = new Phrase(removeAccent(phoneNumber));
			paragraph3.add(Chunk.TABBING);
			paragraph3.add(phrase2);
			paragraph3.setIndentationLeft(25);
			document.add(paragraph3);
			//
			Paragraph paragraph4 = new Paragraph();
			paragraph4.add(new Phrase("Email:", FONT_FIELDNAME));
			paragraph4.setTabSettings(TAB_FIELDNAME);
			Phrase phrase3 = new Phrase(removeAccent(email));
			paragraph4.add(Chunk.TABBING);
			paragraph4.add(phrase3);
			paragraph4.setIndentationLeft(25);
			document.add(paragraph4);
			//
			Paragraph paragraph5 = new Paragraph();
			paragraph5.add(new Phrase("Address:", FONT_FIELDNAME));
			paragraph5.setTabSettings(TAB_FIELDNAME);
			Phrase phrase4 = new Phrase(removeAccent(address));
			paragraph5.add(Chunk.TABBING);
			paragraph5.add(phrase4);
			paragraph5.setIndentationLeft(25);
			document.add(paragraph5);
			//
			Paragraph paragraph6 = new Paragraph();
			paragraph6.add(new Phrase("Description address:", FONT_FIELDNAME));
			paragraph6.setTabSettings(TAB_FIELDNAME);
			Phrase phrase6 = new Phrase(removeAccent(desAddress));
			paragraph6.add(Chunk.TABBING);
			paragraph6.add(phrase6);
			paragraph6.setIndentationLeft(25);
			document.add(paragraph6);
			//
			Paragraph paragraph7 = new Paragraph();
			paragraph7.add(new Phrase("Date issue :", FONT_FIELDNAME));
			paragraph7.setTabSettings(TAB_FIELDNAME);
			Phrase phrase7 = new Phrase(formatDate(dateIssue));
			paragraph7.add(Chunk.TABBING);
			paragraph7.add(phrase7);
			paragraph7.setIndentationLeft(25);
			document.add(paragraph7);
			//
			Paragraph paragraphTile2 = new Paragraph("Orders", FONT_TITLE2);
			paragraphTile2.setSpacingAfter(10);
			paragraphTile2.setSpacingBefore(10);
			document.add(paragraphTile2);
			//
			PdfPTable t = new PdfPTable(new float[] { 40,20,20,20  });
			t.setWidthPercentage(90);
			Phrase headerTable1 = new Phrase("Product",FONT_HEADER_TABLE); 
			PdfPCell c1 = new PdfPCell(headerTable1);
			styleCell(c1);
			t.addCell(c1);
			
			Phrase headerTable2 = new Phrase("Quantity",FONT_HEADER_TABLE); 
			PdfPCell c2 = new PdfPCell(headerTable2);
			styleCell(c2);
			t.addCell(c2);
			
			Phrase headerTable3 = new Phrase("Price (VND)",FONT_HEADER_TABLE); 
			PdfPCell c3 = new PdfPCell(headerTable3);
			styleCell(c3);
			t.addCell(c3);
			
			Phrase headerTable4 = new Phrase("Total (VND)",FONT_HEADER_TABLE); 
			PdfPCell c4 = new PdfPCell(headerTable4);
			styleCell(c4);
			t.addCell(c4);
			
			if (list != null) {
				for (ShoppingCartItem<Product> cartItem : list) {
					PdfPCell nameCell = new PdfPCell(new Phrase(removeAccent(cartItem.getItem().getNameProduct())));
					styleCell(nameCell);
					PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(cartItem.getQuantity())));
					styleCell(qtyCell);
					PdfPCell priceCell = new PdfPCell(new Phrase(formatCurrent(cartItem.getItem().getPrice())));
					styleCell(priceCell);
					double total = cartItem.getItem().getPrice() * cartItem.getQuantity();
					PdfPCell totalCell = new PdfPCell(new Phrase(formatCurrent(total)));
					styleCell(totalCell);
					
					t.addCell(nameCell);
					t.addCell(qtyCell);
					t.addCell(priceCell);
					t.addCell(totalCell);
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
	public static void styleCell(PdfPCell c) {
			c.setPaddingLeft(5);
			c.setPaddingBottom(10);
			c.setPaddingTop(5);
			
			
	}
	public static String formatDate(LocalDateTime localDateTime) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
		 String formatDateTime = localDateTime.format(formatter);
		  return formatDateTime;
	}
	public static String removeAccent(String s) {
		s = s.replaceAll("Đ", "D");
		s = s.replaceAll("đ", "d");
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String unAccent = pattern.matcher(temp).replaceAll("");
		return unAccent;
	}
	
	public static String formatCurrent(double price) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(price);
	}
}
