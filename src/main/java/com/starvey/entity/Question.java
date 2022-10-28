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
    private String id;

    /**
     * 
     */
    private String questionnaireId;

    /**
     * 
     */
    private Integer order;

    /**
     * 
     */
    private Integer isNecessary;

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
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}