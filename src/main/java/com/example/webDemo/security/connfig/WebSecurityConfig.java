package com.example.webDemo.security.connfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Administrator on 2018/1/11.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final static String[] ignore = {
            "/img/**", "/getVerify", "/kaptcha", "/alipay"
            , "/swagger-ui.html", "/webjars/**", "/bootLogin/**"
            , "/assets/**", "/login/**", "/pdf/**", "/testPDF/**", "/pdfjs/**", "/down/**", "/admin"
    };

    @Autowired
    LoginSuccessHandler loginSuccessHandler;


    @Bean
    UserDetailsService customerUserService() {
        return new CustomUserService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 重写该方法，添加自定义用户
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ignore).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/bootLogin")
                .successHandler(loginSuccessHandler)
                .failureUrl("/bootLogin?error")
                .permitAll()
                .and()
                .logout().permitAll();
    }

}
