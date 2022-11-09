package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author LiJiaPeng
 * @email 2741956649@qq.com
 * @date 2022-11-05 15:01:06
 */
@Data
@TableName("answer")
public class Answer implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


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
    /**
     *
     */
    private Integer answerSheetId;
    /**
     *
     */
    @TableLogic
    private Integer isDeleted;

}
