package com.example.webDemo.service;

import com.example.webDemo.repository.CustomerRepository;
import com.example.webDemo.security.model.SysCustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luofei on 2018/5/18.
 */
@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<SysCustomer> findAll(){
        return customerRepository.findAll();
    }
}
