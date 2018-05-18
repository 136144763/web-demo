package com.example.webDemo;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.graphics.text.PageText;
import org.junit.Test;

import java.io.File;

/**
 * @author luofei on 2018/5/17.
 */
public class PDFSplitTest {

    @Test
    public void testPDF() {
        String filePath = "D:/pdftest/1.pdf";
        Document document = new Document();

        try {
            document.setFile(filePath);
            float scale = 1.1f;
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PageText pageText = document.getPageText(i);

                File file=new File("D:/pdftest/icepdf_a" + i + ".pdf");

            }
        } catch (Exception e) {

        }
    }

    
}
