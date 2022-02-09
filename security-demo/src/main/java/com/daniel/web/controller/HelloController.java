package com.daniel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author daniel
 */
@RestController("/hello")
public class HelloController {

    /**
     * 测试
     * @return 网页返回值
     */
    @GetMapping
    public String hello() {
        return "hello spring security";
    }
}
