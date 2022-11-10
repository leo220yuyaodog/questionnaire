package com.starvey.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.starvey.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CreateController {

    @Autowired
    private CreateService createService;

    private Gson gson = new Gson();

    @GetMapping("/api/createQuestionnaire")
    public String createQuestionnaire(@RequestParam String username) {
        return createService.createQuestionnaire(username);
    }

    @PostMapping("/api/saveQuestionnaire")
    public String saveQuestionnaire(@RequestBody String body) {
        String questionList = gson.fromJson(body, JsonObject.class).get("questionList").toString();
        String questionnaire = gson.fromJson(body, JsonObject.class).get("questionnaire").toString();
        return createService.saveQuestionnaire(questionnaire, questionList);
    }

    @PostMapping("/api/saveQuestionnaireOutline")
    public String saveQuestionnaireOutline(@RequestBody String questionnaire) {
        return createService.saveQuestionnaireOutline(gson.fromJson(questionnaire, JsonObject.class).get("questionnaire").toString());
    }

    @PostMapping("/api/saveOneQuestion")
    public String saveOneQuestion(@RequestBody String oneQuestion, @RequestParam Integer questionnaireId) {
        return createService.saveOneQuestion(gson.fromJson(oneQuestion, JsonObject.class).get("question").toString(), questionnaireId);
    }

    @GetMapping("/api/deleteQuestionnaire")
    public String deleteQuestionnaire(@RequestParam Integer questionnaireId) {
        return createService.deleteQuestionnaire(questionnaireId);
    }

    @GetMapping("/api/getQuestionList")
    public String getQuestionList(@RequestParam Integer questionnaireId) {
        return createService.getQuestionList(questionnaireId);
    }

    @GetMapping("/api/getQuestionnaireOutline")
    public String getQuestionnaireOutline(@RequestParam Integer questionnaireId) {
        return createService.getQuestionnaireOutline(questionnaireId);
    }

    @PostMapping("/api/releaseQuestionnaire")
    public String releaseQuestionnaire(@RequestParam Integer questionnaireId) {
        return createService.releaseQuestionnaire(questionnaireId);
    }

    @PostMapping("/api/closeQuestionnaire")
    public String closeQuestionnaire(@RequestParam Integer questionnaireId) {
        return createService.closeQuestionnaire(questionnaireId);
    }
}
