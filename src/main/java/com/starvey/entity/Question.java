package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * 问题自身的id
     */
    @TableId
    private String id;

    /**
     * 问题所属问卷的id
     */
    private String questionnaireId;

    /**
     * 问题在问卷中的顺序（小的在前，大的在后）
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 是否是必答题
     */
    private Integer isNecessary;

    /**
     * 问题的类型
     */
    private String questionType;

    /**
     * 问题的描述
     */
    private String questionDescription;

    /**
     * 问题内容。以json字符串存储
     */
    private String content;

    /**
     * 逻辑删除列
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}