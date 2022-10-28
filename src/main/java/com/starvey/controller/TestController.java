package com.starvey.controller;

import com.starvey.common.Result;
import com.starvey.entity.User;
import com.starvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public Result test() {
        System.out.println("/test 访问成功");
        User user = new User();
        user.setId("1");
        userService.save(user);
        for (User user1 : userService.list()) {
            System.out.println(user1);
        }

        userService.removeById("1");

        for (User user1 : userService.list()) {
            System.out.println(user1);
        }
        return  Result.success(user);
    }
}
