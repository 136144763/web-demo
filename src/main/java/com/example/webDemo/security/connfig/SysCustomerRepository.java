package com.example.webDemo.security.connfig;

import com.example.webDemo.security.model.SysCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luofei on 2018/5/18.
 */
public interface SysCustomerRepository extends JpaRepository<SysCustomer,Long>{
    /**
     *
     * @param username 客户用户名
     * @return
     */
    SysCustomer findByUsername(String username);
}
