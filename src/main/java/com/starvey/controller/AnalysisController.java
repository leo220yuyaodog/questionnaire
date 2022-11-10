package com.starvey.controller;


import com.starvey.service.AnalysisService;
import com.starvey.utils.PrettifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;
    PrettifyUtil prettifyUtil = new PrettifyUtil();

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/api/getQuestionnaires")
    public String getQuestionnaires(@RequestParam String username) {
        return analysisService.getQuestionnairesByUsername(username);
    }

    @GetMapping("/api/getQuestionValueList")
    public String getQuestionValueList(@RequestParam Integer questionId) {
        return analysisService.getQuestionValueList(questionId);
    }

    @GetMapping("/api/getWriteValue")
    public String getWriteValue(@RequestParam Integer questionId) {
        return analysisService.getWriteValue(questionId);
    }


}
