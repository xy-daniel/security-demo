package com.daniel.validator;

import com.daniel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解处理规则
 *
 * @author daniel
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init MyConstraintValidator");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        helloService.greeting("daniel");
        System.out.println(value);
        return false;
    }
}
