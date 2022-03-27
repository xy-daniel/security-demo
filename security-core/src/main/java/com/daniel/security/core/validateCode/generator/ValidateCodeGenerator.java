package com.daniel.security.core.validateCode.generator;

import com.daniel.security.core.validateCode.sessionStrategyData.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
