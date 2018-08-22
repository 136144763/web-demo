package com.example.webDemo;

import cn.binarywang.tools.generator.*;
import com.example.webDemo.domain.Goods;
import com.example.webDemo.repository.GoodsRepository;
import com.example.webDemo.security.connfig.SysCustomerRepository;
import com.example.webDemo.security.connfig.SysUserRepository;
import com.example.webDemo.security.model.SysCustomer;
import com.example.webDemo.security.model.SysUser;
import com.example.webDemo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/23.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcTest {


    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SysCustomerRepository sysCustomerRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    SysUserRepository sysUserRepository;
//    @Test
//    public void testInsertSysUser(){
//        for (int i = 0; i < 3; i++) {
//            SysUser sysUser=new SysUser();
//            sysUser.setUsername(String.valueOf(i));
//            sysUser.setPassword(passwordEncoder.encode(String.valueOf(i)));
//            sysUser.setFullname(ChineseNameGenerator.getInstance().generate());
//            sysUserRepository.save(sysUser);
//        }
//    }

    @Test
    public void testEncoder(){
        String password="123456";
        log.debug("password={}",passwordEncoder.encode(password));
    }

    @Test
    public void testInsertSysCustomer(){
        for (int i = 0; i <20 ; i++) {
            SysCustomer sysCustomer=new SysCustomer();
            String account=RandomStringUtils.randomAlphanumeric(10);
            sysCustomer.setUsername(account);
            sysCustomer.setPassword(passwordEncoder.encode("123456"));
            sysCustomer.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
            sysCustomer.setName(ChineseNameGenerator.getInstance().generate());
            sysCustomer.setSex("0");
            sysCustomer.setAdress(ChineseAddressGenerator.getInstance().generate());
            sysCustomer.setPhoneNum(ChineseMobileNumberGenerator.getInstance().generate());
            sysCustomer.setEmail(EmailAddressGenerator.getInstance().generate());
            sysCustomerRepository.save(sysCustomer);
        }
    }


    @Test
    public void testGenerator() {
        String account = RandomStringUtils.randomAlphanumeric(10);
        log.info("account={}", account);
        String password = "uOJK5X6W7N";
        String salt = UUID.randomUUID().toString().substring(0,5);
        log.info("salt={}", salt);
        log.info("account={}", DigestUtils.md5Hex(password));
    }

    @Test
    public void testJdbc() {
        List<Goods> lists = goodsRepository.findAll();
        for (Goods goods : lists) {
            log.info("goods=={}", goods);
        }
    }

    @Test
    public void test() {
        Page<Goods> goodsList = goodsService.findPageGoods(new PageRequest(1, 15));
        log.debug("goodsList=={}", goodsList.getTotalPages());
        for (Goods goods : goodsList) {
            log.debug("goods=={}", goods);
        }
    }

    @Test
    public void test111() {
        float x = 1;
        float y = 2;
        float z = 3;
        log.info("num={}", y += z-- / ++x);
    }

    @Test
    public void gitTest() {

    }


}
