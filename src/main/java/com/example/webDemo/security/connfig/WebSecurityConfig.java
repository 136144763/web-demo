package com.example.webDemo.security.connfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Administrator on 2018/1/11.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final static String[] ignore={
            "/img/**","/getVerify","/kaptcha","/alipay","/swagger-ui.html"
    };

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Bean
    UserDetailsService customerUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ignore).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(loginSuccessHandler)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().permitAll();
    }

}
