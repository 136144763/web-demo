package com.example.webDemo.security;

import com.example.webDemo.security.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/1/11.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
