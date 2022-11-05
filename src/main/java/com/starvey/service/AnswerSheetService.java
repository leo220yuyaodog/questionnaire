package com.starvey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.entity.AnswerSheet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Song
* @description 针对表【answer_sheet】的数据库操作Service
* @createDate 2022-10-30 13:48:23
*/
public interface AnswerSheetService extends IService<AnswerSheet> {

    Page<AnswerSheet> getAnswerSheetsByQuestionnaireId(String questionnaireId, Integer pageNumber, Integer pageSize);
}
