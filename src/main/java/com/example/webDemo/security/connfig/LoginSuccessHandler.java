package com.example.webDemo.security.connfig;

import com.example.webDemo.security.model.SysCustomer;
import com.example.webDemo.security.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/11.
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysCustomerRepository sysCustomerRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("url={}", request.getRequestURI());
        String imageCode = request.getParameter("imageCode");
        String validation = (String) request.getSession().getAttribute("vrifyCode");
        log.info("imageCode={}", request.getParameter("imageCode"));
        log.info("validation={}", validation);

        SysUser sysUser = sysUserRepository.findByUsername(authentication.getName());
        SysCustomer sysCustomer=sysCustomerRepository.findByUsername(authentication.getName());
        if (sysUser != null) {
            request.getSession().setAttribute("username", sysUser.getFullname());
            String url = request.getRequestURI();
            if (imageCode.equalsIgnoreCase(validation) && !url.isEmpty()) {
                response.sendRedirect("/index");
            } else {
                response.sendRedirect("/bootLogin?error");
            }
        }
        if(sysCustomer!=null){
            request.getSession().setAttribute("username", sysCustomer.getName());
            String url = request.getRequestURI();
            if (imageCode.equalsIgnoreCase(validation) && !url.isEmpty()) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/bootLogin?error");
            }
        }

    }
}
