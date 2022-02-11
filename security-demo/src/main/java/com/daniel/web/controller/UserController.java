package com.daniel.web.controller;

import com.daniel.dto.User;
import com.daniel.dto.UserQueryCondition;
import com.daniel.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户资源
 *
 * @author daniel
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户服务模块")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "资源不存在"),
            @ApiResponse(code = 404, message = "路径不正确")
    })
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 1, size = 20, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(new User());
        userArrayList.add(new User());
        userArrayList.add(new User());
        return userArrayList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "用户详情服务")
    public User getInfo(@ApiParam(value = "用户id") @PathVariable(value = "id") String id) {

//        throw new RuntimeException("user not exist");
//        throw new UserNotExistException(id);

        System.out.println("开始正式调用getInfo资源......");
        System.out.println("id=" + id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user) {
        user.setId("1");
        System.out.println(user);
        return user;
    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User update(@PathVariable(value = "id") String id, @Valid @RequestBody User user, BindingResult errors) {
        System.out.println(id);
        System.out.println(user.toString());
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            user.setId("-1");
        } else {
            user.setId(id);
        }
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
