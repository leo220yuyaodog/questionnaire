package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName answer_sheet
 */
@TableName(value ="answer_sheet")
@Data
public class AnswerSheet implements Serializable {
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
    private String ip;

    /**
     * 
     */
    private Date fillTime;

    /**
     * 
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}