package com.starvey.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starvey.common.Result;
import com.starvey.entity.Questionnaire;
import com.starvey.service.QuestionService;
import com.starvey.service.QuestionnaireService;
import com.starvey.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@Api(tags = "问卷管理")
@RestController
public class QuestionnaireController {
    @Autowired
    private TenantService tenantService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionnaireService questionnaireService;

    @ApiOperation("根据用户id查询下属所有问卷（分页）")
    @GetMapping("/questionnaires")
    public Result getQuestionnaireOfUser(@PathParam("userId") String id, @PathParam("pageNumber") Integer pageNumber, @PathParam("pageSize") Integer pageSize) {
        Page<Questionnaire> page = questionnaireService.getQuestionnairesByUserId(id, pageNumber, pageSize);
        return Result.success(page);
    }

    @ApiOperation("获取指定id的问卷")
    @GetMapping("/questionnaire/{id}")
    public Result getQuestionnaire(@PathVariable(name = "id") String id) {
        Questionnaire questionnaire = questionnaireService.getById(id);
        return Result.success(questionnaire);
    }


    @ApiOperation("新增一个空的问卷")
    @PostMapping("/questionnaire/add")
    @Transactional
    public Result addQuestionnaire(@RequestBody Questionnaire questionnaire) {
        // TODO: 错误处理，全局异常处理
        // 1. 添加问卷
        questionnaireService.save(questionnaire);
        // 2. 更新租户问卷计数和计费
        tenantService.addQuestionnaireTo(questionnaire.getTenantId());
        // 3. 返回结果
        return Result.success(questionnaire);
    }

    @ApiOperation("更新指定id的问卷")
    @PostMapping ("/questionnaire/update")
    public Result updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        boolean b = questionnaireService.updateById(questionnaire);
        return b ? Result.success(questionnaire) : Result.fail("更新指定问卷失败");
    }

    @ApiOperation("删除指定id的问卷和其下属问题")
    @PostMapping("/questionnaire/delete")
    @Transactional
    public Result deleteQuestionnaire(@RequestBody String id) {
        // 1. 删除问卷
        questionnaireService.removeById(id);
        // 2. 删除下属问题
        questionService.removeQuestionsByQuestionnaireId(id);
        // 3. 返回结果
        return Result.success("删除指定问卷成功");
    }

}
