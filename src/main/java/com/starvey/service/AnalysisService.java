package com.starvey.service;

public interface AnalysisService {
    String getQuestionnairesByUsername(String username);

    String getQuestionnaireById(Integer questionnaireId);

    String getWriteValue(Integer questionId);

    String getQuestionValueList(Integer questionId);
}
