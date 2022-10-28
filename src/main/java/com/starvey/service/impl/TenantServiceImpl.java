package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Tenant;
import com.starvey.mapper.TenantMapper;
import com.starvey.service.TenantService;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【tenant】的数据库操作Service实现
* @createDate 2022-10-28 18:33:29
*/
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant>
    implements TenantService{

}




