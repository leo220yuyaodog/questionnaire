package com.starvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starvey.entity.Answer;
import com.starvey.service.AnswerService;
import com.starvey.mapper.AnswerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Song
* @description 针对表【answer】的数据库操作Service实现
* @createDate 2022-10-30 13:48:22
*/
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer>
    implements AnswerService{

    @Override
    @Transactional(readOnly = true)
    public List<Answer> getAnswersByAnswerSheetId(String answerSheetId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<Answer>().eq("answer_sheet_id", answerSheetId);
        List<Answer> list = this.list(wrapper);
        return list;
    }

    @Override
    @Transactional
    public void removeAnswersByAnswerSheetId(String answerSheetId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<Answer>().eq("answer_sheet_id", answerSheetId);
        List<Answer> list = this.list(wrapper);
        for (Answer answer : list) {
            this.removeById(answer);
        }
    }
}




