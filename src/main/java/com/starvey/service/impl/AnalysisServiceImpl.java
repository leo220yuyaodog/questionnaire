package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starvey.entity.Answer;
import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.starvey.service.AnalysisService;
import com.starvey.service.AnswerService;
import com.starvey.service.QuestionService;
import com.starvey.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    Gson gson = new Gson();


    @Override
    public String getQuestionnairesByUsername(String username) {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<Questionnaire>().eq("username", username);
        List<Questionnaire> list = questionnaireService.list(wrapper);

        JsonObject res = new JsonObject();
        res.add("questionnaires", gson.fromJson(gson.toJson(list), JsonArray.class));
        return gson.toJson(res);
    }

    @Override
    public String getQuestionnaireById(Integer questionnaireId) {
        return gson.toJson(questionnaireService.getById(questionnaireId));
    }

    @Override
    public String getWriteValue(Integer questionId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<Answer>().eq("question_id", questionId);
        List<Answer> list = answerService.list(wrapper);
        return gson.toJson(list);
    }

    @Transactional
    @Override
    public String getQuestionValueList(Integer questionId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<Answer>().eq("question_id", questionId);
        List<Answer> answerList = answerService.list(wrapper);

        Question question = questionService.getById(questionId);
        String questionType = question.getQuestionType();


        if (questionType.equals("single_check")) {
            Map<String, Integer> resValueMap = new HashMap<>();
            JsonObject temp = gson.fromJson(question.getDetails(), JsonObject.class);
            JsonArray questionOptionsJsonArray = temp.get("questionOptions").getAsJsonArray();
            for (JsonElement questionOptionJson : questionOptionsJsonArray
            ) {
                String questionOption = questionOptionJson.getAsString();
                resValueMap.put(questionOption, 0);
            }
            System.out.println(temp);
            System.out.println(resValueMap);
            for (Answer oneAnswer : answerList
            ) {
                String value = oneAnswer.getWriteValue();
                Integer oldCount = resValueMap.get(value);
                resValueMap.put(value, oldCount + 1);
            }
            return gson.toJson(resValueMap);
        } else if (questionType.equals("multi_check")) {
            Map<String, Integer> resValueMap = new HashMap<>();
            JsonObject temp = gson.fromJson(question.getDetails(), JsonObject.class);
            JsonArray questionOptionsJsonArray = temp.get("questionOptions").getAsJsonArray();
            for (JsonElement questionOptionJson : questionOptionsJsonArray
            ) {
                String questionOption = questionOptionJson.getAsString();
                resValueMap.put(questionOption, 0);
            }
            for (Answer oneAnswer : answerList
            ) {
                JsonArray valueList = gson.fromJson(oneAnswer.getWriteValue(), JsonArray.class);
                for (JsonElement value : valueList
                ) {
                    Integer oldCount = resValueMap.get(value.getAsString());
                    resValueMap.put(value.getAsString(), oldCount + 1);
                }
            }
            return gson.toJson(resValueMap);
        } else if (questionType.equals("number") || questionType.equals("grade")) {
            Map<String, Double> resValueMap = new HashMap<>();
            List<Double> valueList = new ArrayList<>();
            Double sum = 0.0;
            for (Answer oneAnswer : answerList
            ) {
                Double value = gson.fromJson(oneAnswer.getWriteValue(), Double.class);
                valueList.add(value);
                sum += value;
            }
            valueList.sort((a, b) -> (int) (a - b));
            System.out.println(valueList);

            if(valueList.size()==0){
                resValueMap.put("最大值", 0.0);
                resValueMap.put("最小值", 0.0);
                resValueMap.put("平均值", 0.0);
                resValueMap.put("中位数", 0.0);
            }else{
                resValueMap.put("最大值", valueList.get(valueList.size() - 1));
                resValueMap.put("最小值", valueList.get(0));
                resValueMap.put("平均值", sum / valueList.size());
                resValueMap.put("中位数", valueList.get(valueList.size() / 2));
            }

            return gson.toJson(resValueMap);
        }
        return null;
    }

}
