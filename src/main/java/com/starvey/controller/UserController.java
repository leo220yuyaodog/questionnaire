package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.Questionnaire;
import com.starvey.entity.User;
import com.starvey.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.casbin.casdoor.config.CasdoorConfiguration;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.service.CasdoorUserService;
import org.casbin.casdoor.util.http.CasdoorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("根据租户id查询下属所有用户（分页）")
    @GetMapping("/user")
    public Result getUserOfTenant(@RequestParam String tenantId, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam Integer pageSize) {
        Page<User> page = userService.getUsersByTenantId(tenantId, pageNumber, pageSize);
        return Result.success(page);
    }

    @ApiOperation("获取指定id的用户")
    @GetMapping("/user/{id}")
    public Result getUser(@PathVariable(name = "id") String id) {
        User user = userService.getById(id);
        return user != null ? Result.success(user) : Result. fail("获取用户失败，不存在该id的用户");
    }

    @ApiOperation("添加用户")
    @PostMapping("/user/add")
    public Result addUser(@RequestBody User user) {
        boolean b = userService.save(user);
        return b ? Result.success(user) : Result.fail("添加用户失败");
    }

    @ApiOperation("更新指定id的用户")
    @PostMapping("/user/update")
    public Result updateUser(@RequestBody User user) {
        boolean b = userService.updateById(user);
        return b ? Result.success(user) : Result.fail("更新指定用户失败");
    }

    @ApiOperation("删除指定id的用户")
    @PostMapping("/user/delete")
    public Result deleteUser(@RequestBody String id) {
        boolean b = userService.removeById(id);
        return b ? Result.success("删除指定用户成功") : Result.fail("删除指定用户失败");
    }
}
