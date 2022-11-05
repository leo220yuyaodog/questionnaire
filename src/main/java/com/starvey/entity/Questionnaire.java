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
 *
 * @author LiJiaPeng
 * @email 2741956649@qq.com
 * @date 2022-11-05 15:01:06
 */
@Data
@TableName("questionnaire")
public class Questionnaire implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    private String userId;
    /**
     *
     */
    private String tenantId;
    /**
     *
     */
    private String type;
    /**
     *
     */
    private Integer countLimit;
    /**
     *
     */
    private Integer isDeleted;

}
