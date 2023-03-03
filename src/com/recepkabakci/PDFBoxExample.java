package com.recepkabakci;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import lombok.Cleanup;

public class PDFBoxExample {
	public static void main(String[] args) {
	PDFBoxExample pdfbox = new PDFBoxExample();
	
	try {
		pdfbox.createPDF ();
		pdfbox.readPDF();
		pdfbox.createPDFWithImage();
		
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
	
}

	private void readPDF() throws Exception {
		
		String documentPath = "/Users/gozde/Desktop/Coding/03-NeedfulThings/Notes/Clean Code'dan Notlar.pdf";
		PDDocument doc = PDDocument.load(new File(documentPath));
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(doc);
		System.err.println(text);
		
	}

	private void createPDFWithImage() throws Exception {
		
		String imagePath = "C:\\Users\\RECEP KABAKCI\\Desktop\\pdfexample";
		
		@Cleanup 
		PDDocument pdf = new PDDocument();   //pdf belgesi yaratıldı	
		PDPage page = new PDPage();          //sayfa yaratıldı
		pdf.addPage(page);				     //yaratılan sayfa belgeye ekleniyor
		PDImageXObject image = PDImageXObject.createFromFile(imagePath, pdf);
		
		@Cleanup 
		PDPageContentStream content = new PDPageContentStream(pdf, page);
		content.drawImage(image, 20f, 20f);
		
		content.beginText();				//begin ve end metotları arasında yaptıracağımız işlemleri yazıyoruz
		
		content.setFont(PDType1Font.TIMES_ROMAN, 12);
		content.setLeading(14.5f);
		content.newLineAtOffset(20, 750);
		content.showText("Hello everyone, what a wonderful day, right?");
		

		
		content.endText();
		content.close();
		pdf.save(new File("C:\\Users\\RECEP KABAKCI\\Desktop\\pdfexample\\imageExample.pdf"));
		
	}
	public void createPDF() throws Exception{
		
	@Cleanup 
	PDDocument pdf = new PDDocument();  //create a pdf document
	PDPage page = new PDPage();					// create a page
	pdf.addPage(page);				// add the page to document
	
	@Cleanup
	PDPageContentStream content = new PDPageContentStream(pdf, page);
	content.beginText();
	content.setFont(PDType1Font.TIMES_ROMAN, 15);
	content.setLeading(14.5f);
	content.newLineAtOffset(20,750);
	content.showText("Hello Guys");
	content.newLine();
	content.showText("How are you");
	content.newLine();
	content.setFont(PDType1Font.TIMES_ITALIC, 30);
	content.showText("whats up");
	
	
	content.endText();
	content.close();
	pdf.save(new File("C:\\Users\\RECEP KABAKCI\\Desktop\\pdfexample\\pdfexample.pdf"));
	}
}