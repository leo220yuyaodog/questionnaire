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
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String tenantId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private Integer questionNum;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    /**
     * 
     */
    private Integer countLimit;

    /**
     * 
     */
    private Integer fillCount;

    /**
     * 
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}