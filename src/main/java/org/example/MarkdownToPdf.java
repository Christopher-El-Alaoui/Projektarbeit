/**
 * Created on 05.02.2025
 */
package org.example;


import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cortility gmbh
 */
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.io.FileOutputStream;
import java.io.IOException;

public class MarkdownToPdf {
    public static void main(String[] args) {
        String markdown = "# Hello World\nThis is a sample markdown document.";
        String pdfPath = "output.pdf";

        try {
            convertMarkdownToPdf(markdown, pdfPath);
            System.out.println("PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertMarkdownToPdf(String markdown, String pdfPath) throws IOException {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);

        try (PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
             PdfDocument pdf = new PdfDocument(writer);
             Document doc = new Document(pdf)) {

            doc.add(new Paragraph(html));
        }
    }
}