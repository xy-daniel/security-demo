package com.daniel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author daniel
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //标记位置
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Constraint(validatedBy = MyConstraintValidator.class) //执行校验
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
