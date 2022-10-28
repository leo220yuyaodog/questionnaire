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
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String questionId;

    /**
     * 
     */
    private String questionnaireId;

    /**
     * 
     */
    private String questionType;

    /**
     * 
     */
    private String questionDescription;

    /**
     * 
     */
    private Integer order;

    /**
     * 
     */
    private String answerContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}