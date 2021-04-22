package com.testProject.Parser.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.testProject.Parser.DTO.DataSiteDTO;
import com.testProject.Parser.DTO.WebsiteDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {
    private static BaseFont baseFont = loadBaseFont();

    private static Font fontHeader = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);

    private static Font fontNormal = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);

    private static BaseFont loadBaseFont() {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("asset/times-roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return baseFont;
    }

    public ByteArrayInputStream PDFReport(WebsiteDTO websiteDTO) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.setPageSize(PageSize.A4.rotate());
            document.newPage();

            Paragraph para = new Paragraph("Статистика сайта: " + websiteDTO.getUrl(), fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(2);
            Stream.of("Слово", "Повторений")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, fontNormal));
                        table.addCell(header);
                    });

            for (DataSiteDTO dataSiteDTO : websiteDTO.getDataSiteDTOS()) {
                table.addCell(new Phrase(dataSiteDTO.getWord(), fontNormal));
                table.addCell(new Phrase(dataSiteDTO.getAmount().toString(), fontNormal));
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
