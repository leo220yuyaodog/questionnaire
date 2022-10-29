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
     * 租户id
     */
    private String id;

    /**
     * 租户当前计费
     *
     * 其实可以不记的，通过“群组费用1元/个，问卷费用0.5元/份，答卷费用0.1元/份”，那么这张表也就不需要了
     * 要记录的话，最好把群组数量，问卷数量，答卷数量更新的时候也记一下，更方便后面【租户统计】的功能
     *
     */
    private Double cost;

    /**
     * 逻辑删除列，不用管
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}