package com.starvey.controller;

import com.starvey.common.Result;
import com.starvey.entity.Answer;
import com.starvey.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class AnswerController {
    @Autowired
    private AnswerService answerService;


    @ApiOperation("获取指定id的答案")
    @GetMapping("/answer/{id}")
    public Result getAnswer(@PathVariable(name = "id") String id) {
        Answer answer = answerService.getById(id);
        return answer != null ? Result.success(answer) : Result. fail("获取答案失败，不存在该id的答案");
    }

    @ApiOperation("添加答案")
    @PostMapping("/answer/add")
    public Result addAnswer(@RequestBody Answer answer) {
        boolean b = answerService.save(answer);
        return b ? Result.success(answer) : Result.fail("添加答案失败");
    }

    @ApiOperation("更新指定id的答案")
    @PostMapping("/answer/update")
    public Result updateAnswer(@RequestBody Answer answer) {
        boolean b = answerService.updateById(answer);
        return b ? Result.success(answer) : Result.fail("更新指定答案失败");
    }

    @ApiOperation("删除指定id的答案")
    @PostMapping("answer/delete")
    public Result deleteAnswer(@RequestBody String id) {
        boolean b = answerService.removeById(id);
        return b ? Result.success("删除指定答案成功") : Result.fail("删除指定答案失败");
    }
}
