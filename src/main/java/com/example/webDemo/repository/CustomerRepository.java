package com.example.webDemo.repository;

import com.example.webDemo.domain.SysCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luofei on 2018/6/27.
 */
public interface CustomerRepository extends JpaRepository<SysCustomer,String>{
}
