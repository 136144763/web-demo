package com.example.webDemo.service;

import com.example.webDemo.security.connfig.SysCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luofei on 2018/5/18.
 */
@Slf4j
@Service
public class CustomerService {

    @Autowired
    SysCustomerRepository sysCustomerRepository;



}
