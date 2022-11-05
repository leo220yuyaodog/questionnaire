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
@TableName("answerer_to_group")
public class AnswererToGroup implements Serializable {
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
	private Integer userId;
	/**
	 * 
	 */
	private String 	answererGroupId;
	/**
	 * 
	 */
	@TableLogic
	private Integer isDeleted;

}
