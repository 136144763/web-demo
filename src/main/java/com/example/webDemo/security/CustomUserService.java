package com.example.webDemo.security;

import com.example.webDemo.security.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Administrator on 2018/1/11.
 */
@Slf4j
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByUsername(s);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        log.info("s={}", s);
        log.info("username={},password={}", sysUser.getUsername(), sysUser.getPassword());
        return sysUser;
    }
}
