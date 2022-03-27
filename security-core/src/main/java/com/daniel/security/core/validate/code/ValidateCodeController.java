package com.daniel.security.core.validate.code;

import com.daniel.security.core.validate.code.chonggouValidateCode.ValidateCodeProcessor;
import com.daniel.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class ValidateCodeController {

//    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_KEY";
//
//    private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//    @Autowired
//    private ValidateCodeGenerator imageCodeGenerator;
//
//    @Autowired
//    private ValidateCodeGenerator smsCodeGenerator;
//
//    @Autowired
//    private SmsCodeSender smsCodeSender;
//
//    /**
//     * 获取图形验证码接口
//     */
//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //生成随机数图片
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
//        //将随机数存储到session中
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//        //将生成的图片写到接口的响应中
//        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//    }
//
//    /**
//     * 获取短信验证码接口
//     */
//    @GetMapping("/code/sms")
//    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//        //生成随机数图片
//        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
//        //将随机数存储到session中
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//        //将生成的验证码通过短信的方式发送出去
//        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//        smsCodeSender.send(mobile, smsCode.getCode());
//    }

    //上面的两个控制器代码逻辑是相似的，因此可以使用模板模式来重构
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的{@link ValidateCodeProcessor}接口实现
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
    }
}
