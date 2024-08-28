package com.shark.example.file.html;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HtmlToFileExample {
    public static void main(String[] args) {
        String url = "https://tw.stock.yahoo.com/quote/1101.TW/revenue";
        String outputPdfPath = "file/html_to_pdf.pdf";
        String outputPngPath = "file/html_to_png.png";

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPdfPath)) {
            Document document = Jsoup.connect(url).get();
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            Elements revenueContentElements = document.getElementsByClass("M(0) P(0) List(n)");
            String html = revenueContentElements.html();
            System.out.println("html: " + html);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(fileOutputStream);
            builder.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (PDDocument document = PDDocument.load(new File(outputPdfPath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);

            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                try (FileOutputStream fos = new FileOutputStream(outputPngPath)) {
                    fos.write(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
