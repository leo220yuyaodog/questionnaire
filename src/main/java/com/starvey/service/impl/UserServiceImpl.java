package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Questionnaire;
import com.starvey.entity.User;
import com.starvey.service.UserService;
import com.starvey.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Song
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-10-30 13:48:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    @Transactional(readOnly = true)
    public Page<User> getUsersByTenantId(String tenantId, Integer pageNumber, Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("tenant_id", tenantId);
        Page<User> page = this.page(new Page<>(pageNumber, pageSize), queryWrapper);
        return page;
    }
}




