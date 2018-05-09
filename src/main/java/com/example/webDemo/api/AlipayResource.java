package com.example.webDemo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author luofei 2018/5/9.
 */
@Slf4j
@Api(value = "/alipay",tags = "支付宝",description = "授权登录相关")
@Controller
@RestController
public class AlipayResource {


    @ApiOperation(value = "获取支付宝授权URL", notes = "客户端直接获取该URL做跳转")
    @ResponseBody
    @GetMapping("/auth")
    public String auth() throws UnsupportedEncodingException {
        return "";
    }
}
