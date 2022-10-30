package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.Question;
import com.starvey.entity.Questionnaire;
import com.starvey.service.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "问卷管理")
@RestController
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @ApiOperation("获取指定页大小下的指定页的问卷列表")
    @GetMapping("/questionnaire/list")
    public Result getQuestionnaireList(@RequestParam(defaultValue = "1") Integer pageNumber, Integer pageSize) {
        Page<Questionnaire> page = questionnaireService.page(new Page<>(pageNumber, pageSize));
        return Result.success(page);
    }

    @ApiOperation("获取指定id的问卷")
    @GetMapping("/questionnaire/{id}")
    public Result getQuestionnaire(@PathVariable(name = "id") String id) {
        Questionnaire questionnaire = questionnaireService.getById(id);
        return questionnaire != null ? Result.success(questionnaire) : Result.fail("获取指定问卷失败");
    }

    @ApiOperation("添加问卷")
    @PostMapping("/questionnaire/add")
    public Result addQuestionnaire(@RequestBody Questionnaire questionnaire) {
        boolean b = questionnaireService.save(questionnaire);
        return b ? Result.success(questionnaire) : Result.fail("添加问卷失败");
    }

    @ApiOperation("更新指定id的问卷")
    @PostMapping ("/questionnaire/update")
    public Result updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        boolean b = questionnaireService.updateById(questionnaire);
        return b ? Result.success(questionnaire) : Result.fail("更新指定问卷失败");
    }

    @ApiOperation("删除指定id的问卷")
    @PostMapping("/questionnaire/delete")
    public Result deleteQuestionnaire(@RequestBody String id) {
        boolean b = questionnaireService.removeById(id);
        return b ? Result.success("删除指定问卷成功") : Result.fail("删除指定问卷失败");
    }

    @ApiOperation("根据问卷id查询相应问题")
    @PostMapping("question/listquestion")
    public Result listQuestion(@RequestBody String id) {
        List<Question> list = questionnaireService.listById(id);
        return Result.success(list);
    }
}