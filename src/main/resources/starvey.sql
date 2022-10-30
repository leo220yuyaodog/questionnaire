/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : questionnaire

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/10/2022 14:04:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '答案自身的id',
  `question_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答案对应的问题id',
  `answer_sheet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答案所属的答卷id',
  `question_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题的类型（也就是对应问题的类型，又存了一遍）',
  `question_description` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '问题的描述（也就是对应问题的描述，又存了一遍）',
  `order` int NULL DEFAULT NULL COMMENT '答案在答卷中的顺序（小的在前，大的在后）',
  `answer_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '答案内容。以json字符串存储',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for answer_sheet
-- ----------------------------
DROP TABLE IF EXISTS `answer_sheet`;
CREATE TABLE `answer_sheet`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '答卷自身id',
  `questionnaire_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答卷对应的问卷id',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '答卷的ip地址',
  `fill_time` datetime NULL DEFAULT NULL COMMENT '答卷完成填写时间（提交的时间）',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列，不用管',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题自身的id',
  `questionnaire_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题所属问卷的id',
  `order` int NULL DEFAULT NULL COMMENT '问题在问卷中的顺序（小的在前，大的在后）',
  `is_necessary` tinyint(1) NULL DEFAULT NULL COMMENT '是否是必答题',
  `question_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题的类型',
  `question_description` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '问题的描述',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '问题内容。以json字符串存储',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问卷自身id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT ' 所属用户的id',
  `tenant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属租户的id（也就是所属用户的租户的id）',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问卷的标题',
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '问卷的描述（显示在问卷的开头）',
  `status` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问卷的状态',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问卷的类型',
  `question_num` int NULL DEFAULT 0 COMMENT '问卷包含的题目数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '问卷创建的时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '问卷开始的时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '问卷结束的时间',
  `count_limit` int NULL DEFAULT NULL COMMENT '对于限制答卷数量的题目，使用该属性进行规定',
  `fill_count` int NULL DEFAULT 0 COMMENT '问卷填写数',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '租户自身id',
  `cost` double(10, 2) NULL DEFAULT 0.00 COMMENT '租户当前计费总额',
  `group_count` int NULL DEFAULT 0 COMMENT '群组总数',
  `questionnaire_count` int NULL DEFAULT 0 COMMENT '问卷总数',
  `answer_sheet_count` int NULL DEFAULT 0 COMMENT '答卷总数',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'User的id',
  `tenant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'User所属租户的id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `phone_num` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `role` int NULL DEFAULT NULL COMMENT '身份',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '头像的链接',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
