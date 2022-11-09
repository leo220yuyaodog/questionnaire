package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.AnswerSheet;
import com.starvey.entity.Questionnaire;
import com.starvey.service.AnswerService;
import com.starvey.service.AnswerSheetService;
import com.starvey.service.QuestionnaireService;
import com.starvey.service.TenantService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

public class AnswerSheetController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private AnswerSheetService answerSheetService;


    @ApiOperation("获取指定问卷id下的所有答卷（分页）")
    @GetMapping("/answer-sheet")
    public Result getAnswerSheetList(@RequestParam String questionnaireId, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam Integer pageSize) {
        Page<AnswerSheet> page = answerSheetService.getAnswerSheetsByQuestionnaireId(questionnaireId, pageNumber, pageSize);
        return Result.success(page);
    }

    @ApiOperation("获取指定id的答卷")
    @GetMapping("/answer-sheet/{id}")
    public Result getAnswerSheetById(@PathVariable(name = "id") String id) {
        AnswerSheet answerSheet = answerSheetService.getById(id);
        return answerSheet != null ? Result.success(answerSheet) : Result.fail("获取指定答卷失败，不存在该id的答卷");
    }

    @ApiOperation("添加答卷")
    @PostMapping("/answerSheet/add")
    @Transactional
    public Result addAnswerSheet(@RequestBody AnswerSheet answerSheet) {
        // 1. 添加答卷
        answerSheetService.save(answerSheet);
        // 2. 更新租户答卷计数和计费
        Questionnaire questionnaire = questionnaireService.getById(answerSheet.getQuestionnareId());
        tenantService.addAnswerSheetTo(questionnaire.getTenantId());
        // 3. 返回结果
        return Result.success(answerSheet);
    }

    // 这个感觉好像不会用到
    @ApiOperation("更新指定id的答卷")
    @PostMapping ("/answer-sheet/update")
    public Result updateAnswerSheet(@RequestBody AnswerSheet answerSheet) {
        boolean b = answerSheetService.updateById(answerSheet);
        return b ? Result.success(answerSheet) : Result.fail("更新指定答卷失败");
    }

    @ApiOperation("删除指定id的答卷和其下属问题")
    @PostMapping("/answer-sheet/delete")
    @Transactional
    public Result deleteAnswerSheet(@RequestBody String id) {
        // 1. 删除答卷
        answerSheetService.removeById(id);
        // 2. 删除下属答案
        answerService.removeAnswersByAnswerSheetId(id);
        // 3. 返回结果
        return Result.success("删除指定答卷成功");
    }
}
