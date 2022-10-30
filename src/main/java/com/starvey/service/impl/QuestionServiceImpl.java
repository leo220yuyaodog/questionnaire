package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Question;
import com.starvey.service.QuestionService;
import com.starvey.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【question】的数据库操作Service实现
* @createDate 2022-10-30 13:48:23
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




