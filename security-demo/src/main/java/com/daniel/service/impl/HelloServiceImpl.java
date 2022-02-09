package com.daniel.service.impl;

import com.daniel.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author daniel
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void greeting(String name) {
        System.out.println("hello " + name);
    }
}
