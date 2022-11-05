package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.Tenant;
import com.starvey.service.TenantService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class TenantController {
    @Autowired
    private TenantService tenantService;


    @ApiOperation("获取租户列表（分页）")
    @GetMapping("/tenant")
    public Result getTenantList(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Tenant> page = tenantService.page(new Page<>(pageNumber, pageSize));
        return Result.success(page);
    }

    @ApiOperation("获取指定id的租户")
    @GetMapping("/tenant/{id}")
    public Result getTenant(@PathVariable(name = "id") String id) {
        Tenant tenant = tenantService.getById(id);
        return tenant != null ? Result.success(tenant) : Result. fail("获取租户失败，不存在该id的租户");
    }

    @ApiOperation("添加租户")
    @PostMapping("/tenant/add")
    public Result addTenant(@RequestBody Tenant tenant) {
        boolean b = tenantService.save(tenant);
        return b ? Result.success(tenant) : Result.fail("添加租户失败");
    }

    @ApiOperation("更新指定id的租户")
    @PostMapping("/tenant/update")
    public Result updateTenant(@RequestBody Tenant tenant) {
        boolean b = tenantService.updateById(tenant);
        return b ? Result.success(tenant) : Result.fail("更新指定租户失败");
    }

    @ApiOperation("删除指定id的租户")
    @PostMapping("/tenant/delete")
    public Result deleteTenant(@RequestBody String id) {
        boolean b = tenantService.removeById(id);
        return b ? Result.success("删除指定租户成功") : Result.fail("删除指定租户失败");
    }
}
