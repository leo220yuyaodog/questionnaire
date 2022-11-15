package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    @TableId(type = IdType.AUTO)
    private Integer answerId;

    /**
     * 
     */
    private Date fillTime;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    private Integer questionId;

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
    private String writeValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}