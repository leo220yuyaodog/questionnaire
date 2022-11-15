package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;


import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable{
    /**
     * User的id
     */
    @TableId
    private String id;

    /**
     * User所属租户的id
     */
    private String tenantId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 身份
     */
    private Integer role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像的链接
     */
    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
