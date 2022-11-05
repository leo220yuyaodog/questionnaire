package com.starvey.service;

import com.starvey.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Song
* @description 针对表【question】的数据库操作Service
* @createDate 2022-10-30 13:48:23
*/
public interface QuestionService extends IService<Question> {

    List<Question> getQuestionsByQuestionnaireId(String questionnaireId);

    void removeQuestionsByQuestionnaireId(String questionnaireId);
}
