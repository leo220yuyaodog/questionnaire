package com.starvey.service;

import com.starvey.entity.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Song
* @description 针对表【tenant】的数据库操作Service
* @createDate 2022-10-30 13:48:23
*/
public interface TenantService extends IService<Tenant> {
    boolean addQuestionnaireTo(String id);
    boolean addAnswerSheetTo(String id);
}
