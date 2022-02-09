package com.daniel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author daniel
 */
@RestController
public class HelloController {

    /**
     * 测试
     * @return 网页返回值
     */
    @GetMapping(("/hello"))
    public String hello() {
        return "hello spring security";
    }
}
