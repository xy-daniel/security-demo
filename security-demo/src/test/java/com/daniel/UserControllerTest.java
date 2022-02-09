package com.daniel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * 用户操作相关测试
 *
 * @author daniel
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    /**
     * 伪造web环境
     */
    @Autowired
    private WebApplicationContext wac;

    /**
     * 伪造MVC环境
     */
    private MockMvc mockMvc;

    /**
     * 每次执行测试用例之前执行的方法
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 查询测试
     */
    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                        get("/user")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .param("username", "daniel")
                                .param("age", "10")
                                .param("ageTo", "20")
                                .param("xxx", "yyy")
                        //分页相关参数
//                                .param("size", "15")
//                                .param("page", "3")
//                                .param("sort", "age,desc")
                )
                //判断相应状态码为200
                .andExpect(status().isOk())
                //判断返回值为json且长度为3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(
                        get("/user/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                //判断响应状态码为200
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(
                        get("/user/a")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                //判断响应状态码为4xx
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date birthday = new Date();
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":\"" + birthday.getTime() + "\"}";
        String result = mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(content)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
