package com.daniel.security.core.validateCode.validateCodeProcessor;

import com.daniel.security.core.validateCode.type.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 根据类型查询对应的校验处理器
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        if (type.equals(ValidateCodeType.IMAGE)) {
            return validateCodeProcessors.get("imageCodeProcessor");
        }
        if (type.equals(ValidateCodeType.SMS)) {
            return validateCodeProcessors.get("smsCodeProcessor");
        }
        return null;
    }
}
