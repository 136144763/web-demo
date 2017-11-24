package com.example.webDemo.controller;

import com.example.webDemo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("goodsList", goodsService.findGoods());
        return "index";
    }

    @GetMapping("/page/goods")
    @ResponseBody
    public Object findAllGoods(@RequestParam(value = "page") int page, @RequestParam(value = "size", defaultValue = "20") int size) {
        log.debug("page=={}", page);
        PageRequest request = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "id"));
        return goodsService.findPageGoods(request);
    }

}
