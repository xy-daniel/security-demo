package com.daniel.web.controller;

import com.daniel.dto.User;
import com.daniel.dto.UserQueryCondition;
import com.daniel.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
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
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
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
    public User getInfo(@PathVariable(value = "id") String id) {

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
