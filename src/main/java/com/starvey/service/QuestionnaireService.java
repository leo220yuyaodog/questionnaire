package com.starvey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Song
 * @description 针对表【questionnaire】的数据库操作Service
 * @createDate 2022-10-30 13:48:23
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    Page<Questionnaire> getQuestionnairesByUserId(String userId, Integer pageNumber, Integer pageSize);

}
