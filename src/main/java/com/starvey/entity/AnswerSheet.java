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
     * 答卷自身id
     */
    @TableId
    private String id;

    /**
     * 答卷对应的问卷id
     */
    private String questionnaireId;

    /**
     * 答卷的ip地址
     */
    private String ip;

    /**
     * 答卷完成填写时间（提交的时间）
     */
    private Date fillTime;

    /**
     * 逻辑删除列，不用管
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}