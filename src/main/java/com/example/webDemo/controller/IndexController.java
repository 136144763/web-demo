package com.example.webDemo.controller;

import com.example.webDemo.service.GoodsService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Administrator on 2017/11/24.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/bootLogin")
    public String bootLogin() {
        return "bootLogin";
    }

    @GetMapping("/testPDF")
    public String testPDF() {
        return "testPDF";
    }

    @GetMapping("/page/goods")
    @ResponseBody
    public Object findAllGoods(@RequestParam(value = "page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        return goodsService.findPageGoods(request);
    }



    @RequestMapping("/down")
    public void down(HttpServletResponse httpServletResponse){
        download("D:\\workspace\\web-demo\\src\\main\\resources\\static\\pdf\\1.pdf",httpServletResponse);
    }

    @RequestMapping("/down/file")
    public void downFile(HttpServletResponse httpServletResponse) throws Exception {
        downloadFile("D:\\workspace\\web-demo\\src\\main\\resources\\static\\pdf","1.pdf",httpServletResponse);
    }

    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            String fileName = file.getName();
            String ext = StringUtils.substringAfter(fileName, ".").toUpperCase();

            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.addHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes()));
            response.addHeader("Content-Length",""+file.length());
            OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            log.warn("", e);
        }

        return response;
    }

    public void downloadFile(String filePath,String fileName,HttpServletResponse response) throws Exception{
        File file = new File(filePath);
        // 清空response
        response.reset();
        // 设置response的Header
        // 转码之后下载的文件不会出现中文乱码
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        response.addHeader("Content-Length", "" + file.length());
        // 以流的形式下载文件

        InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        OutputStream toClient = new BufferedOutputStream( response.getOutputStream());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }


    //在你的html中 <img src="/kaptcha" /> 验证码就出来了
    @RequestMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            log.info("vrifyCode={}", createText);
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


}
