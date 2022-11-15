package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName questionnaire_ip
 */
@TableName(value ="questionnaire_ip")
@Data
public class QuestionnaireIp implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    private Integer questionnaireId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}