package com.example.webDemo.security.connfig;

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

import static com.example.webDemo.security.validation.PictureValidation.RANDOMCODEKEY;

/**
 * Created by Administrator on 2018/1/11.
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("LoginSuccessHandle\t {},{},{}", authentication.getPrincipal(), request, response);
        log.info("url={}", request.getRequestURI());
        String validation= (String) request.getSession().getAttribute(RANDOMCODEKEY);
        log.info("validation={}",validation);

        SysUser sysUser = sysUserRepository.findByUsername(authentication.getName());
        log.info("verificationCode={}",sysUser.getVerificationCode());
        request.getSession().setAttribute("username", sysUser.getUsername());
        String url=request.getRequestURI();
        if(!url.isEmpty()){
            response.sendRedirect("/index");
        }
    }
}
