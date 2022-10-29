package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.Question;
import com.starvey.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "问题管理")
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 目前没啥用，或许后面题库管理能用，先写着当个测试
     *
     */
    @ApiOperation("获取指定页大小下的指定页的问题列表")
    @GetMapping("question/list")
    public Result getQuestionList(@RequestParam(defaultValue = "1") Integer pageNumber, Integer pageSize) {
        Page<Question> page = questionService.page(new Page<>(pageNumber, pageSize));
        return Result.success(page);
    }

    @ApiOperation("获取指定id的问题")
    @GetMapping("/question/{id}")
    public Result getQuestion(@PathVariable(name = "id") String id) {
        Question question = questionService.getById(id);
        return question != null ? Result.success(question) : Result. fail("获取问题失败，不存在该id的问题");
    }

    @ApiOperation("添加问题")
    @PostMapping("/question/add")
    public Result addQuestion(@RequestBody Question question) {
        boolean b = questionService.save(question);
        return b ? Result.success(question) : Result.fail("添加问题失败");
    }

    @ApiOperation("更新指定id的问题")
    @PostMapping("/question/update")
    public Result updateQuestion(@RequestBody Question question) {
        boolean b = questionService.updateById(question);
        return b ? Result.success(question) : Result.fail("更新指定问题失败");
    }

    @ApiOperation("删除指定id的问题")
    @PostMapping("question/delete")
    public Result deleteQuestion(@RequestBody String id) {
        boolean b = questionService.removeById(id);
        return b ? Result.success("删除指定问题成功") : Result.fail("删除指定问题失败");
    }
}
