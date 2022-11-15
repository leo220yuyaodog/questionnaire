package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 
     */
    @TableId
    private Integer questionId;

    /**
     * 
     */
    private String details;

    /**
     * 
     */
    private String questionDescription;

    /**
     * 
     */
    private Boolean questionNullable;

    /**
     * 
     */
    private String questionTitle;

    /**
     * 
     */
    private String questionType;

    /**
     * 
     */
    private Integer questionnaireId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}