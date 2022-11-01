package com.starvey.controller;

import com.starvey.common.Result;
import com.starvey.entity.Questionnaire;
import com.starvey.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
