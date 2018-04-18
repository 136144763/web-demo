package com.example.webDemo.security.connfig;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author luofei
 */
@Configuration
public class CaptchaProperties {

    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //是否有边框  默认为true  我们可以自己设置yes，no
        properties.setProperty("kaptcha.border", "yes");
        //边框颜色   默认为Color.BLACK
        properties.setProperty("kaptcha.border.color", "204,204,204");
        //边框粗细度  默认为1
        properties.setProperty("kaptcha.border.thickness", "1");
        //验证码文本字符颜色  默认为Color.BLACK
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //验证码图片宽度  默认为200
        properties.setProperty("kaptcha.image.width", "156");
        // 验证码图片高度  默认为50
        properties.setProperty("kaptcha.image.height", "33");
        //验证码文本字符大小  默认为40
        properties.setProperty("kaptcha.textproducer.font.size","25");
        properties.setProperty("kaptcha.session.key", "code");
        //验证码文本字符长度  默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        //验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty("kaptcha.textproducer.font.names", "微软雅黑");
        //验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
        properties.setProperty("kaptcha.textproducer.char.string", "abcdefghijklmnopqrstuvwxyz0123456789");
        //验证码文本字符间距  默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "6");
        //验证码背景颜色渐进 开始   默认为Color.LIGHT_GRAY
        properties.setProperty("kaptcha.background.clear.from", "white");
        //验证码背景颜色渐进 结束   默认为Color.
        properties.setProperty("kaptcha.background.clear.to", "white");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
