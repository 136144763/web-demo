package com.example.webDemo.repository;

import com.example.webDemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luofei on 2018/5/14.
 */
public interface CustomerRepository extends JpaRepository<Customer,String>{
}
