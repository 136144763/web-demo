package com.example.webDemo;

import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import com.example.webDemo.domain.Customer;
import com.example.webDemo.domain.Goods;
import com.example.webDemo.repository.CustomerRepository;
import com.example.webDemo.repository.GoodsRepository;
import com.example.webDemo.service.GoodsService;
import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.util.List;

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
    CustomerRepository customerRepository;

    @Test
    public void testInsert(){
        Customer customer=new Customer();
        customer.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());

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
    public void gitTest(){

    }


}
