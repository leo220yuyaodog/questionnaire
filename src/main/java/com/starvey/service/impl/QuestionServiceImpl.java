package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Question;
import com.starvey.service.QuestionService;
import com.starvey.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Song
* @description 针对表【question】的数据库操作Service实现
* @createDate 2022-10-30 13:48:23
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Override
    public List<Question> getQuestionsByQuestionnaireId(String id) {
        QueryWrapper<Question> wrapper = new QueryWrapper<Question>().eq("questionnaire_id", id);
        List<Question> list = this.list(wrapper);
        return list;
    }
}




