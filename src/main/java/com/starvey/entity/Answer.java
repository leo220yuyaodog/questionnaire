package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName answer
 */
@TableName(value ="answer")
@Data
public class Answer implements Serializable {
    /**
     * 答案自身的id
     */
    @TableId
    private String id;

    /**
     * 答案对应的问题id
     */
    private String questionId;

    /**
     * 答案所属的答卷id
     */
    private String answerSheetId;

    /**
     * 问题的类型（也就是对应问题的类型，又存了一遍）
     */
    private String questionType;

    /**
     * 问题的描述（也就是对应问题的描述，又记了一遍）
     */
    private String questionDescription;

    /**
     * 答案在答卷中的顺序（小的在前，大的在后）
     */
    private Integer order;

    /**
     * 答案内容。以json字符串存储
     */
    private String answerContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}