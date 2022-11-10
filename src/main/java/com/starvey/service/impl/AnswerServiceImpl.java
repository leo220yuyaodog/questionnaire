package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starvey.entity.Answer;
import com.starvey.entity.QuestionnaireIp;
import com.starvey.service.AnswerService;
import com.starvey.mapper.AnswerMapper;
import com.starvey.service.QuestionnaireIpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* @author Song
* @description 针对表【answer】的数据库操作Service实现
* @createDate 2022-11-10 12:47:22
*/
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer>
    implements AnswerService{




}




