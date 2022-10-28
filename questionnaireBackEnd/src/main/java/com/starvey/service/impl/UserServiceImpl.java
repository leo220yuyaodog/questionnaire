package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.User;
import com.starvey.mapper.UserMapper;
import com.starvey.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-10-28 18:33:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




