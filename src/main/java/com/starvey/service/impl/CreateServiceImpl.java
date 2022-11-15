package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.starvey.service.CreateService;
import com.starvey.service.QuestionService;
import com.starvey.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CreateServiceImpl implements CreateService {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;

    final Integer QuestionIdDigit = 1000;

    Gson gson = new Gson();


    @Override
    public String createQuestionnaire(String username) {
        Questionnaire newQuestionnaire = new Questionnaire();
        newQuestionnaire.setUsername(username);
        newQuestionnaire.setCreateTime(new Date());
        newQuestionnaire.setStatus("editing");
        newQuestionnaire.setTitle("请输入标题");
        newQuestionnaire.setDescription("请输入描述");
        questionnaireService.save(newQuestionnaire);

        JsonObject res = new JsonObject();
        res.addProperty("id", newQuestionnaire.getQuestionnaireId());

        return gson.toJson(res);
    }

    @Override
    public String deleteQuestionnaire(Integer questionnaireId) {
        questionnaireService.removeById(questionnaireId);
        return "success";
    }

    @Override
    public String getQuestionList(Integer questionnaireId) {

        JsonArray resList = new JsonArray();


        QueryWrapper<Question> wrapper = new QueryWrapper<Question>().eq("questionnaire_id", questionnaireId);
        List<Question> questionList = questionService.list(wrapper);

        for (Question oneQuestion : questionList) {

            JsonObject oneRes = gson.fromJson(gson.toJson(oneQuestion), JsonObject.class);
            oneRes.addProperty("isBoxSelected", false);
            oneRes.addProperty("questionTitle", oneQuestion.getQuestionTitle());
            oneRes.addProperty("questionDescription", oneQuestion.getQuestionDescription());
            oneRes.addProperty("questionIndex", oneQuestion.getQuestionId() % QuestionIdDigit);
            oneRes.addProperty("questionNullable", oneQuestion.getQuestionNullable());
            oneRes.addProperty("questionType", oneQuestion.getQuestionType());

            JsonObject temp = gson.fromJson(oneQuestion.getDetails(), JsonObject.class);

            processDetails(oneRes, temp);

            resList.add(oneRes);
        }
        JsonObject res = new JsonObject();
        res.add("questionList", resList);
        return gson.toJson(res);
    }

    private void processDetails(JsonObject oneRes, JsonObject temp) {
        if (temp != null) {
            oneRes.add("questionOptions", temp.get("questionOptions").getAsJsonArray());
            oneRes.add("frontOptions", temp.get("frontOptions").getAsJsonArray());
            oneRes.addProperty("frontChoose", temp.get("frontChoose").getAsBoolean());
            oneRes.addProperty("numberType", temp.get("numberType").getAsString());
            oneRes.addProperty("defaultNumber", temp.get("defaultNumber").getAsInt());
            oneRes.addProperty("gradeMax", temp.get("gradeMax").getAsInt());
            oneRes.addProperty("date", temp.get("date").getAsString());
            oneRes.addProperty("textDescription", temp.get("textDescription").getAsString());
        }

    }

    @Override
    public String saveQuestionnaireOutline(String questionnaire) {
        JsonObject questionnaireObject = gson.fromJson(questionnaire, JsonObject.class);
        Questionnaire questionnaireEntity = questionnaireService.getById(questionnaireObject.get("questionnaireId").getAsInt());
        questionnaireEntity.setTitle(questionnaireObject.get("questionnaireTitle").getAsString());
        questionnaireEntity.setDescription(questionnaireObject.get("questionnaireDescription").getAsString());

        questionnaireService.updateById(questionnaireEntity);
        return null;
    }

    @Override
    public String saveQuestionnaire(String questionnaire, String questionList) {
        JsonObject questionnaireObject = gson.fromJson(questionnaire, JsonObject.class);
        Integer questionnaireId = questionnaireObject.get("questionnaireId").getAsInt();
        JsonArray questionListArray = gson.fromJson(questionList, JsonArray.class);

        saveQuestionnaireOutline(questionnaire);

        for (JsonElement oneQuestionJson : questionListArray
        ) {
            JsonObject temp = oneQuestionJson.getAsJsonObject();
            saveOneQuestion(questionnaireId, temp);
        }
        return null;
    }

    @Override
    public String getQuestionnaireOutline(Integer questionnaireId) {
        JsonObject res = new JsonObject();
        res.add("questionnaire", gson.fromJson(gson.toJson(questionnaireService.getById(questionnaireId)), JsonObject.class));
        return gson.toJson(res);
    }

    @Override
    public String saveOneQuestion(String question, Integer questionnaireId) {
        JsonObject temp = gson.fromJson(question, JsonObject.class);;
        saveOneQuestion(questionnaireId, temp);
        return null;
    }

    @Override
    public String releaseQuestionnaire(Integer questionnaireId) {
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        questionnaire.setStatus("collecting");
        questionnaire.setFillCount(0);
        questionnaireService.updateById(questionnaire);
        return null;
    }

    @Override
    public String closeQuestionnaire(Integer questionnaireId) {
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        questionnaire.setStatus("closed");
        questionnaire.setFillCount(0);
        questionnaireService.updateById(questionnaire);
        return null;
    }

    private void saveOneQuestion(Integer questionnaireId, JsonObject temp) {
        Question oneQuestion = new Question();
        oneQuestion.setQuestionnaireId(questionnaireId);
        oneQuestion.setQuestionDescription(temp.get("questionDescription").getAsString());
        oneQuestion.setQuestionTitle(temp.get("questionTitle").getAsString());
        oneQuestion.setQuestionId(questionnaireId * QuestionIdDigit + temp.get("questionIndex").getAsInt());
        oneQuestion.setQuestionType(temp.get("questionType").getAsString());
        oneQuestion.setQuestionNullable(temp.get("questionNullable").getAsBoolean());

        JsonObject otherJson = new JsonObject();
        processDetails(otherJson, temp);

        oneQuestion.setDetails(otherJson.toString());

        questionService.saveOrUpdate(oneQuestion);
    }
}
