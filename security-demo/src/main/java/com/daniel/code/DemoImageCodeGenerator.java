package com.daniel.code;

import com.daniel.security.core.validate.code.ImageCode;
import com.daniel.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("imageCodeGenerator1")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("应用级别覆盖默认级别验证码图片生成器......");
        return null;
    }
}
