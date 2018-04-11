package com.example.webDemo.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */
@Entity
public class SysUser implements UserDetails {

    @Id
    @GeneratedValue
    private long Id;

    private String username;

    private String password;

    private String verificationCode;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SysUser> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysUser> roles = this.getRoles();
        for (SysUser sysUser : roles) {
            auths.add(new SimpleGrantedAuthority(sysUser.getUsername()));
        }
        return auths;
    }

    public List<SysUser> getRoles() {
        return roles;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
