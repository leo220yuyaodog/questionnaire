package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tenant
 */
@TableName(value ="tenant")
@Data
public class Tenant implements Serializable {
    /**
     * 租户自身id
     */
    @TableId
    private String id;

    /**
     * 租户当前计费总额
     */
    private Double cost;

    /**
     * 群组总数
     */
    private Integer groupCount;

    /**
     * 问卷总数
     */
    private Integer questionnaireCount;

    /**
     * 答卷总数
     */
    private Integer answerSheetCount;

    /**
     * 逻辑删除列
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}