package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.starvey.entity.Question;
import com.starvey.service.QuestionService;
import com.starvey.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Song
* @description 针对表【question】的数据库操作Service实现
* @createDate 2022-11-10 12:47:22
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{


}




