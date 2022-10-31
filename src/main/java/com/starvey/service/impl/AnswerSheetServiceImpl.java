package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.AnswerSheet;
import com.starvey.entity.Questionnaire;
import com.starvey.service.AnswerSheetService;
import com.starvey.mapper.AnswerSheetMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Song
* @description 针对表【answer_sheet】的数据库操作Service实现
* @createDate 2022-10-30 13:48:23
*/
@Service
public class AnswerSheetServiceImpl extends ServiceImpl<AnswerSheetMapper, AnswerSheet>
    implements AnswerSheetService{

    @Override
    @Transactional(readOnly = true)
    public Page<AnswerSheet> getAnswerSheetsByQuestionnaireId(String questionnaireId, Integer pageNumber, Integer pageSize) {
        QueryWrapper<AnswerSheet> queryWrapper = new QueryWrapper<AnswerSheet>().eq("questionnaire_id", questionnaireId);
        Page<AnswerSheet> page = this.page(new Page<>(pageNumber, pageSize), queryWrapper);
        return page;
    }
}




