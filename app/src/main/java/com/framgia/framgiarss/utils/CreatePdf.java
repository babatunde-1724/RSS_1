package com.framgia.framgiarss.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdf {

    public Boolean write(String title, String publishedDate, String description, String Author) {
        try {
            String fpath = Constants.ConstantKeys.SD_PATH + title + Constants.ConstantKeys.PDF_EXTENSION;
            File file = new File(fpath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Document document = new Document();
            PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            Paragraph title_paragraph = new Paragraph();
            title_paragraph.add(title);
            title_paragraph.setAlignment(Element.ALIGN_CENTER);

            Paragraph date_paragraph = new Paragraph();
            date_paragraph.add(publishedDate);
            date_paragraph.setAlignment(Element.ALIGN_CENTER);

            Paragraph description_paragraph = new Paragraph();
            description_paragraph.add(description);
            description_paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

            Paragraph author_paragraph = new Paragraph();
            author_paragraph.add(Author);
            author_paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(new Paragraph(title_paragraph));
            document.add(new Paragraph(date_paragraph));
            document.add(new Paragraph(description_paragraph));
            document.add(new Paragraph(author_paragraph));
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return true;
    }

}