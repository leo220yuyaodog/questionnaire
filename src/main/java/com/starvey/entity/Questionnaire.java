package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName questionnaire
 */
@TableName(value ="questionnaire")
@Data
public class Questionnaire implements Serializable {
    /**
     * 问卷自身id
     */
    @TableId
    private String id;

    /**
     *  所属用户的id
     */
    private String userId;

    /**
     * 所属租户的id（也就是所属用户的租户的id）
     */
    private String tenantId;

    /**
     * 问卷的标题
     */
    private String title;

    /**
     * 问卷的描述（显示在问卷的开头）
     */
    private String description;

    /**
     * 问卷的状态
     */
    private String status;

    /**
     * 问卷的类型
     */
    private String type;

    /**
     * 问卷包含的题目数量
     */
    private Integer questionNum;

    /**
     * 问卷创建的时间
     */
    private Date createTime;

    /**
     * 问卷开始的时间
     */
    private Date startTime;

    /**
     * 问卷结束的时间
     */
    private Date endTime;

    /**
     * 对于限制答卷数量的题目，使用该属性进行规定
     */
    private Integer countLimit;

    /**
     * 问卷填写数
     */
    private Integer fillCount;

    /**
     * 逻辑删除列
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}