package com.starvey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Song
* @description 针对表【user】的数据库操作Service
* @createDate 2022-10-30 13:48:23
*/
public interface UserService extends IService<User> {

    Page<User> getUsersByTenantId(String tenantId, Integer pageNumber, Integer pageSize);

}
