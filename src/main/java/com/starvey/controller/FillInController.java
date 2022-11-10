package com.starvey.controller;


import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.starvey.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class FillInController {
    @Autowired
    private CreateService createService;
    @Autowired
    private FillInService fillInService;

    private Gson gson = new Gson();


    @GetMapping("/api/fillin/checkAlreadySubmit")
    public Boolean checkAlreadySubmit(@RequestParam Integer questionnaireId, @Param("ip") String ip) {
        return fillInService.checkAlreadySubmit(questionnaireId, ip);
    }

    @PostMapping("/api/fillin/submitAnswer")
    public String submitAnswer(@RequestParam Integer questionnaireId, @RequestBody String answer) {
        String answerListJson = gson.fromJson(answer, JsonObject.class).get("answerList").toString();
        String ip = gson.fromJson(answer, JsonObject.class).get("ip").getAsString();
        if (!fillInService.checkAlreadySubmit(questionnaireId, ip))
            fillInService.submitAnswer(questionnaireId, answerListJson, ip);
        return "";
    }

    @GetMapping("/api/fillin/getQuestionList")
    public String getQuestionList(@RequestParam Integer questionnaireId) {
        return createService.getQuestionList(questionnaireId);
    }

    @GetMapping("/api/fillin/getQuestionnaireOutline")
    public String getQuestionnaireOutline(@RequestParam Integer questionnaireId) {
        return createService.getQuestionnaireOutline(questionnaireId);
    }
}
