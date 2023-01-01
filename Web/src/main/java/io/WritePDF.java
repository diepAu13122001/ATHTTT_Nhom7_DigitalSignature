package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
import model.OrderDetail;
import model.Product;
import servlet.Cart;

public class WritePDF {
	private static final Font FONT_BRAND = new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.GRAY);
	private static final Font FONT_FIELDNAME = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.BLACK);
	private static final Font FONT_TITLE2 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
	private static final Font FONT_TITLE1 = new Font(FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
	private static final Font FONT_HEADER_TABLE = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
	private static final TabSettings TAB_FIELDNAME = new TabSettings(150f);
	private static final TabSettings TAB_SUBTOTAL = new TabSettings(100f);
	public static final String PATH ="C:\\Users\\Asus\\eclipse-workspace\\ATBMHTT\\ATHTTT_Nhom7_DigitalSignature\\Web\\";
	public static void main(String[] args) {
//		makeInvoicePDF("Nguyen Khai Hieu", "khaihieupc2@gmail.com", "0343385406", "Thu Duc-HCM-Viet Nam",
//				"03/Duong 17/TP Thu Duc/TP HCM", "", "", "30/11/2022", null);
		// System.out.println(formatCurrent(10000000.0));
		System.out.println(formatDate(LocalDateTime.now()));
	}

	public static void makeInvoicePDF(String name, String phoneNumber, String email, String address, String desAddress,
			String shipping, String payement, LocalDateTime dateIssue, List<ShoppingCartItem<Product>> list,double subTotal,double discount
			,double ship, double grandTotal, String fileName) {
		Document document = new Document();

		try {
			// khởi tạo một PdfWriter truyền vào document và FileOutputStream
			File file = new File(PATH+fileName);
			PdfWriter.getInstance(document, new FileOutputStream(
					file));

			// mở file để thực hiện viết
			document.open();
			Paragraph brand = new Paragraph("NHOM-7_ATBMHTTT", FONT_BRAND);
			brand.setAlignment(Paragraph.ALIGN_RIGHT);
			brand.setSpacingAfter(30);
			document.add(brand);
			// thêm nội dung sử dụng add function
			Paragraph paragraph1 = new Paragraph("INVOICE", FONT_TITLE1);
			paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph1);
			Paragraph paragraphDate = new Paragraph("("+formatDate(dateIssue)+")", new Font(FontFamily.HELVETICA,10, Font.ITALIC, BaseColor.BLACK));
			paragraphDate.setAlignment(Paragraph.ALIGN_CENTER);
			paragraphDate.setSpacingAfter(15);
			
			document.add(paragraphDate);

			document.add(new Paragraph("Information", FONT_TITLE2));

			document.add(addParagraph3("Name", name));
			//
			document.add(addParagraph3("Phone number", phoneNumber));
			//
			document.add(addParagraph3("Email", email));
			document.add(addParagraph3("Address", address));
			document.add(addParagraph3("Desciption address", desAddress));
			
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
			//								
			document.add(addParagraph2(subTotal,"SUB TOTAL"));
			document.add(addParagraph2(discount,"DISCOUNT"));
			document.add(addParagraph2(ship,"SHIP"));
			document.add(addParagraph2(grandTotal,"GRAND TOTAL"));
			//
							
			// đóng filelo World PDF document.
			document.close();
			System.out.println("OK");

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void updateInvoicePDF(String name, String phoneNumber, String email, String address, String desAddress,
			String shipping, String payement, String dateIssue, List<OrderDetail> list,double subTotal,double discount
			,double ship, double grandTotal, String fileName) {
		Document document = new Document();

		try {
			// khởi tạo một PdfWriter truyền vào document và FileOutputStream
			File file = new File(PATH+fileName);
			PdfWriter.getInstance(document, new FileOutputStream(
					file));

			// mở file để thực hiện viết
			document.open();
			Paragraph brand = new Paragraph("NHOM-6_ATBMHTTT", FONT_BRAND);
			brand.setAlignment(Paragraph.ALIGN_RIGHT);
			brand.setSpacingAfter(30);
			document.add(brand);
			// thêm nội dung sử dụng add function
			Paragraph paragraph1 = new Paragraph("INVOICE", FONT_TITLE1);
			paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph1);
			Paragraph paragraphDate = new Paragraph("("+dateIssue+")", new Font(FontFamily.HELVETICA,10, Font.ITALIC, BaseColor.BLACK));
			paragraphDate.setAlignment(Paragraph.ALIGN_CENTER);
			paragraphDate.setSpacingAfter(15);
			
			document.add(paragraphDate);

			document.add(new Paragraph("Information", FONT_TITLE2));

			document.add(addParagraph3("Name", name));
			//
			document.add(addParagraph3("Phone number", phoneNumber));
			//
			document.add(addParagraph3("Email", email));
			document.add(addParagraph3("Address", address));
			document.add(addParagraph3("Desciption address", desAddress));
			
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
				for (OrderDetail cartItem : list) {
					PdfPCell nameCell = new PdfPCell(new Phrase(removeAccent(cartItem.getProduct().getNameProduct())));
					styleCell(nameCell);
					PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(cartItem.getQuantity())));
					styleCell(qtyCell);
					PdfPCell priceCell = new PdfPCell(new Phrase(formatCurrent(cartItem.getProduct().getPrice())));
					styleCell(priceCell);
					double total = cartItem.getProduct().getPrice() * cartItem.getQuantity();
					PdfPCell totalCell = new PdfPCell(new Phrase(formatCurrent(total)));
					styleCell(totalCell);
					
					t.addCell(nameCell);
					t.addCell(qtyCell);
					t.addCell(priceCell);
					t.addCell(totalCell);
				}
			}
			document.add(t);
			//								
			document.add(addParagraph2(subTotal,"SUB TOTAL"));
			document.add(addParagraph2(discount,"DISCOUNT"));
			document.add(addParagraph2(ship,"SHIP"));
			document.add(addParagraph2(grandTotal,"GRAND TOTAL"));
			//
							
			// đóng filelo World PDF document.
			document.close();
			System.out.println("OK");

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static Paragraph addParagraph2(double value, String nameField) {
		Paragraph paragraph = new Paragraph();
		paragraph.setIndentationLeft(310);
		paragraph.add(new Phrase(nameField +" :", FONT_FIELDNAME));
		paragraph.setTabSettings(TAB_SUBTOTAL);
		Phrase phrase = new Phrase(formatCurrent(value)+" VND");
		paragraph.add(Chunk.TABBING);
		paragraph.add(phrase);								
		return paragraph;
	}
	private static Paragraph addParagraph3(String nameField,String value) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Phrase(nameField+":", FONT_FIELDNAME));
		paragraph.setTabSettings(TAB_FIELDNAME);
		Phrase phrase1 = new Phrase(removeAccent(value));
		paragraph.add(Chunk.TABBING);
		paragraph.add(phrase1);
		paragraph.setIndentationLeft(25);							
		return paragraph;
	}
	private static void styleCell(PdfPCell c) {
			c.setPaddingLeft(5);
			c.setPaddingBottom(10);
			c.setPaddingTop(5);
			
			
	}
	public static String formatDate(LocalDateTime localDateTime) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String formatDateTime = localDateTime.format(formatter);
		  return formatDateTime;
	}
	private static String removeAccent(String s) {
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
	public static void writeByte(final String desFile, final byte[] inputBytes) throws IOException {
		String fullPath = PATH+desFile;
        final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fullPath));
        bos.write(inputBytes);
        bos.close();
    }
    public static byte[] readBytes(String fileName) throws IOException {
    	String fullPath = PATH+fileName;
    	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fullPath));
    	byte[] bytes = bis.readAllBytes();
    	bis.close();
    	return bytes;
    }
}
