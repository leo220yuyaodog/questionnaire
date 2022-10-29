package com.starvey.controller;

import cn.hutool.core.util.IdUtil;
import com.starvey.common.Result;
import com.starvey.entity.User;
import com.starvey.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public Result test() {
        System.out.println("/test 访问成功");
        return Result.success("/test 访问成功", new Date());
    }
}
