package com.starvey.service;

import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Song
 * @description 针对表【questionnaire】的数据库操作Service
 * @createDate 2022-10-30 13:48:23
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    public List<Questionnaire> getQuestionnairesByUserId(String id);
}
