package com.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout.Alignment;

import com.demo.dto.InvoiceData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {
	public static ByteArrayInputStream citiesReport(List<InvoiceData> invoices) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 5, 3, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("ProductName", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Price", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Quanity", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			int totalAmount = 0;
			for (InvoiceData invoice : invoices) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(invoice.getProdName()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(Double.toString(invoice.getPrice())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(Integer.toString(invoice.getQuanity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(Double.toString(invoice.getAmount())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				totalAmount += invoice.getAmount();
			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(new Paragraph("Order Invoice"));

			Paragraph para = new Paragraph("Total Amount : " + totalAmount);
			para.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(para);

			document.add(table);
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
