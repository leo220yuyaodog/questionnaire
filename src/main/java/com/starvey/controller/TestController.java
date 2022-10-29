package com.starvey.controller;

import cn.hutool.core.util.IdUtil;
import com.starvey.common.Result;
import com.starvey.entity.User;
import com.starvey.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public Result test() {
        System.out.println("/test 访问成功");
        User user = new User();
        String id = IdUtil.randomUUID();
        user.setId(id);
        userService.save(user);

        for (User user1 : userService.list()) {
            System.out.println(user1);
        }

        userService.removeById(id);

        return  Result.success(user);
    }
}
