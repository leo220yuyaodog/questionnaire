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
@TableName("answer_sheet")
public class AnswerSheet implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    private String questionnareId;
    /**
     *
     */
    private Date fillTime;
    /**
     *
     */
    private String groupId;
    /**
     *
     */
    @TableLogic
    private Integer isDeleted;

}
