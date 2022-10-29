package com.starvey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {

    // 单选题
    public static final String QUES_TYPE_SINGLE_CHECK = "single_check";
    // 多选题
    public static final String QUES_TYPE_MULTI_CHECK = "multi_check";

    //
    //
    //                         value: 'single_line_text',
    //                        label: '单行文本'
    //                    }, {
    //                        value: 'multi_line_text',
    //                        label: '多行文本'
    //                    }, {
    //                        value: 'number',
    //                        label: '数字'
    //                    }, {
    //                        value: 'grade',
    //                        label: '评分'
    //                    }, {
    //                        value: 'date',
    //                        label: '日期'
    //                    }, {
    //                        value: 'text_description',
    //                        label: '文本描述'

    /**
     * 问题自身的id
     */
    @TableId
    private String id;

    /**
     * 问题所属问卷的id
     */
    private String questionnaireId;

    /**
     * 问题在问卷中的顺序（小的在前，大的在后）
     */
    private Integer order;

    /**
     * 是否是必答题
     */
    private Integer isNecessary;

    /**
     * TODO: 有哪些问题
     * 问题类型
     */
    private String questionType;

    /**
     * 问题描述（也就是“问题”）
     */
    private String questionDescription;

    /**
     * 问题内容。以json字符串存储
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}