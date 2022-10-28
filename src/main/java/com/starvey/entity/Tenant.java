package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tenant
 */
@TableName(value ="tenant")
@Data
public class Tenant implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private Double cost;

    /**
     * 
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}