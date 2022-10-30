package com.starvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Questionnaire;
import com.starvey.service.QuestionnaireService;
import com.starvey.mapper.QuestionnaireMapper;
import org.springframework.stereotype.Service;

/**
* @author Song
* @description 针对表【questionnaire】的数据库操作Service实现
* @createDate 2022-10-30 13:48:23
*/
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
    implements QuestionnaireService{

}




