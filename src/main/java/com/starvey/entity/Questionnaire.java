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
 * @TableName questionnaire
 */
@TableName(value ="questionnaire")
@Data
public class Questionnaire implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer questionnaireId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Date endTime;

    /**
     * 
     */
    private Integer fillCount;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String username;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}