package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.starvey.service.QuestionService;
import com.starvey.service.QuestionnaireService;
import com.starvey.mapper.QuestionnaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Song
 * @description 针对表【questionnaire】的数据库操作Service实现
 * @createDate 2022-10-30 13:48:23
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
        implements QuestionnaireService{
    @Autowired
    QuestionService questionService;

    @Override
    public List<Questionnaire> getQuestionnairesByUserId(String id) {
        QueryWrapper<Questionnaire> queryWrapper = new QueryWrapper<Questionnaire>().eq("user_id", id);
        List<Questionnaire> list = this.list(queryWrapper);
        return list;
    }
    @Transactional
    @Override
    public List<Question> removeall(String id) {
        Questionnaire questionnaire=this.getById(id);
        questionnaire.setIsDeleted(1);
        this.updateById(questionnaire);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>().eq("questionnaire_id", id);
        List<Question> lists = questionService.list(queryWrapper);
        lists.stream().map(list->{
            list.setIsDeleted(1);
            questionService.updateById(list);
            return list;
        }).collect(Collectors.toList());
        return lists;
    }
}




