package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Answer;
import com.starvey.service.AnswerService;
import com.starvey.mapper.AnswerMapper;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【answer】的数据库操作Service实现
* @createDate 2022-10-30 13:48:22
*/
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer>
    implements AnswerService{

}




