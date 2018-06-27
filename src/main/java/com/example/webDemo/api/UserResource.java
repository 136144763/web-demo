package com.example.webDemo.api;

import com.example.webDemo.repository.CustomerRepository;
import com.example.webDemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luofei on 2018/6/27.
 */
@Slf4j
@Api(value = "/user", tags = "用户", description = "用户信息")
@Controller
@RestController
public class UserResource {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "get user")
    @ResponseBody
    @GetMapping("/user")
    public Object getUser() {
        return customerService.findAll();
    }
}
