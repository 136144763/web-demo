package com.example.webDemo.controller;

import com.example.webDemo.security.validation.PictureValidation;
import com.example.webDemo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/24.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/page/goods")
    @ResponseBody
    public Object findAllGoods(@RequestParam(value = "page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        return goodsService.findPageGoods(request);
    }




    final static String articleUrl="http://blog.csdn.net/Colton_Null/article/details/78744240";
    /**
     * 生成验证码
     */
    //TODO 图片验证
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            PictureValidation randomValidateCode = new PictureValidation();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            log.error("获取验证码失败>>>>   ", e);
        }
    }

}
