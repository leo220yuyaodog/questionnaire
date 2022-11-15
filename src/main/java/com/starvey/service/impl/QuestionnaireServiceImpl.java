package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starvey.entity.Questionnaire;
import com.starvey.service.QuestionService;
import com.starvey.service.QuestionnaireService;
import com.starvey.mapper.QuestionnaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【questionnaire】的数据库操作Service实现
* @createDate 2022-11-10 12:47:22
*/
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
    implements QuestionnaireService{

}




