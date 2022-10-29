package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.AnswerSheet;
import com.starvey.service.AnswerSheetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class AnswerSheetController {
    @Autowired
    private AnswerSheetService answerSheetService;

    @ApiOperation("获取指定页大小下的指定页的答卷列表")
    @GetMapping("/answer-sheet/list")
    public Result getAnswerSheetList(@RequestParam(defaultValue = "1") Integer pageNumber, Integer pageSize) {
        Page<AnswerSheet> page = answerSheetService.page(new Page<>(pageNumber, pageSize));
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
    public Result addAnswerSheet(@RequestBody AnswerSheet answerSheet) {
        boolean b = answerSheetService.save(answerSheet);
        return b ? Result.success(answerSheet) : Result.fail("添加答卷失败");
    }

    @ApiOperation("更新指定id的答卷")
    @PostMapping ("/answer-sheet/update")
    public Result updateAnswerSheet(@RequestBody AnswerSheet answerSheet) {
        boolean b = answerSheetService.updateById(answerSheet);
        return b ? Result.success(answerSheet) : Result.fail("更新指定答卷失败");
    }

    @ApiOperation("删除指定id的答卷")
    @PostMapping("/answer-sheet/delete")
    public Result deleteAnswerSheet(@RequestBody String id) {
        //TODO: 删除下属答卷
        boolean b = answerSheetService.removeById(id);
        return b ? Result.success("删除指定答卷成功") : Result.fail("删除指定答卷失败");
    }
}
