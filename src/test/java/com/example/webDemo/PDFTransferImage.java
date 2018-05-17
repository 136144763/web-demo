package com.example.webDemo;

import org.icepdf.core.pobjects.Page;
import org.junit.Test;

import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luofei on 2018/5/16.
 */
public class PDFTransferImage {

    @Test
    public void testPDF2IMGTest() {
        String filePath = "D:/pdftest/1.pdf";
        Document document = new Document();

        try {
            document.setFile(filePath);
            float scale = 1.1f;// 缩放比例（大图）
            // float scale = 0.2f;// 缩放比例（小图）
            float rotation = 0f;// 旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                try {
                    File file = new File("D:/pdftest/icepdf_a" + i + ".jpg");
                    // 这里png作用是：格式是jpg但有png清晰度
                    ImageIO.write(rendImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (PDFException e1) {
            e1.printStackTrace();
        } catch (PDFSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======================完成============================");
    }


    @Test
    public void testPDFTransferOneIMG() {
        String pdfFile = "D:\\pdftest\\1.pdf";
        String outpath = "D:\\pdftest\\1.jpg";
        pdf2multiImage(pdfFile, outpath, 1000);
    }

    private static void pdf2multiImage(String pdfFile, String outpath, int maxPage) {
        try {
            InputStream is = new FileInputStream(pdfFile);
            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
            Document document = new Document();
            try {
                document.setFile(pdfFile);
            } catch (Exception ex) {
            }
            float scale = 3f;  //缩放比例
            float rotation = 0f;  //旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, scale);
                piclist.add(image);
            }
            document.dispose();
            yPic(piclist, outpath);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            File fileImg = null; // 保存读取出的图片
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0) _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ImageIO.write(imageResult, "jpg", outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
