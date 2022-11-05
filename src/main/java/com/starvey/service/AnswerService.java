package com.starvey.service;

import com.starvey.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Song
* @description 针对表【answer】的数据库操作Service
* @createDate 2022-10-30 13:48:23
*/
public interface AnswerService extends IService<Answer> {
    List<Answer> getAnswersByAnswerSheetId(String answerSheetId);

    void removeAnswersByAnswerSheetId(String answerSheetId);
}
