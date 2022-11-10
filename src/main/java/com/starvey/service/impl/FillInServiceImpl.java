package com.starvey.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starvey.entity.Answer;
import com.starvey.entity.Questionnaire;
import com.starvey.entity.QuestionnaireIp;
import com.starvey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FillInServiceImpl implements FillInService {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionnaireIpService questionnaireIpService;
    @Autowired
    private QuestionnaireService questionnaireService;

    final Gson gson = new Gson();

    @Override
    @Transactional
    public void submitAnswer(Integer questionnaireId, String answerListJson, String ip) {

        // 记录ip防止重复填写
        QuestionnaireIp questionnaireIp = new QuestionnaireIp();
        questionnaireIp.setIp(ip);
        questionnaireIp.setQuestionnaireId(questionnaireId);
        questionnaireIpService.save(questionnaireIp);

        // 如果已经关闭就拒绝
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        if (questionnaire.getStatus().equals("closed")) return ;

        // 记录填写人数加一
        if (questionnaire.getFillCount() == null) questionnaire.setFillCount(0);
        questionnaire.setFillCount(questionnaire.getFillCount() + 1);
        questionnaireService.updateById(questionnaire);

        // 处理answer
        JsonArray answerListArray = gson.fromJson(answerListJson, JsonArray.class);
        for (JsonElement oneAnswer : answerListArray
        ) {
            Answer answer = new Answer();
            answer.setIp(ip);
            answer.setFillTime(new Date());

            JsonObject oneAnswerObject = oneAnswer.getAsJsonObject();
            answer.setQuestionId(oneAnswerObject.get("questionId").getAsInt());
            answer.setQuestionTitle(oneAnswerObject.get("questionTitle").getAsString());
            String questionType = oneAnswerObject.get("questionType").getAsString();
            answer.setQuestionType(questionType);

            if (questionType.equals("single_check")) {
                answer.setWriteValue(oneAnswerObject.get("answerSingleCheck").getAsString());
            } else if (questionType.equals("multi_check")) {
                answer.setWriteValue(oneAnswerObject.get("answerMultiCheck").toString());
            } else if (questionType.equals("single_line_text")) {
                answer.setWriteValue(oneAnswerObject.get("answerText").getAsString());
            } else if (questionType.equals("multi_line_text")) {
                answer.setWriteValue(oneAnswerObject.get("answerText").getAsString());
            } else if (questionType.equals("number")) {
                answer.setWriteValue(oneAnswerObject.get("answerNumber").toString());
            } else if (questionType.equals("grade")) {
                answer.setWriteValue(oneAnswerObject.get("answerGrade").toString());
            } else if (questionType.equals("date")) {
                answer.setWriteValue(oneAnswerObject.get("answerDate").toString());
            }

            answerService.save(answer);
        }


    }


    @Override
    public Boolean checkAlreadySubmit(Integer questionnaireId, String ip) {
        return true;
    }
}
