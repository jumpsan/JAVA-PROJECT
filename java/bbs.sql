/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 80017
Source Host           : 127.0.0.1:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-10-13 00:16:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(255) NOT NULL COMMENT '管理员名称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'lily', '2', null);

-- ----------------------------
-- Table structure for t_approve
-- ----------------------------
DROP TABLE IF EXISTS `t_approve`;
CREATE TABLE `t_approve` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  `post_id` int(11) NOT NULL COMMENT '帖子编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_approve
-- ----------------------------
INSERT INTO `t_approve` VALUES ('1', '3', '2019-10-10 14:59:35', '4');
INSERT INTO `t_approve` VALUES ('2', '102', '2019-10-11 17:19:30', '6');

-- ----------------------------
-- Table structure for t_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_blacklist`;
CREATE TABLE `t_blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `permission` int(11) NOT NULL COMMENT '权限编号；0禁评论，1禁发帖',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blacklist
-- ----------------------------

-- ----------------------------
-- Table structure for t_collect
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect` (
  `user_id` int(11) NOT NULL COMMENT '收藏人用户编号',
  `post_id` int(11) NOT NULL COMMENT '收藏的帖子编号',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_collect
-- ----------------------------

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注编号',
  `follow_id` int(11) NOT NULL COMMENT '关注人用户编号',
  `followed_id` int(11) NOT NULL COMMENT '被关注人用户编号',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follow
-- ----------------------------
INSERT INTO `t_follow` VALUES ('2', '1', '3', '2019-10-10 14:38:02');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息编号',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '信息发送时间',
  `receive_id` int(11) NOT NULL COMMENT '接收人用户编号',
  `send_id` int(11) NOT NULL COMMENT '发起人用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('2', 'hello', '2019-10-10 11:56:43', '1', '3');
INSERT INTO `t_message` VALUES ('3', 'hello', '2019-10-11 19:01:19', '103', '102');

-- ----------------------------
-- Table structure for t_plate
-- ----------------------------
DROP TABLE IF EXISTS `t_plate`;
CREATE TABLE `t_plate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '板块编号',
  `name` varchar(255) NOT NULL COMMENT '板块名',
  `user_id` int(11) NOT NULL COMMENT '创建人编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `describes` varchar(255) DEFAULT NULL COMMENT '相关信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0禁用1启动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_plate
-- ----------------------------
INSERT INTO `t_plate` VALUES ('6', 'teach', '1', '2019-09-25 23:37:47', null, '1');
INSERT INTO `t_plate` VALUES ('7', 'user', '1', '2019-09-26 14:41:48', null, '1');
INSERT INTO `t_plate` VALUES ('8', 'think', '1', '2019-09-27 12:15:21', null, '1');
INSERT INTO `t_plate` VALUES ('9', 'game', '1', '2019-09-27 12:22:08', null, '1');
INSERT INTO `t_plate` VALUES ('10', 'comversation', '1', '2019-10-10 16:48:41', null, '1');

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖名',
  `user_id` int(11) NOT NULL COMMENT '发帖人编号',
  `section_id` int(11) NOT NULL COMMENT '分区编号',
  `reply_num` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '查看数',
  `approve_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `collect_num` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `post_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `video` varchar(255) DEFAULT NULL COMMENT '视频',
  `type` int(11) NOT NULL COMMENT '帖子类型0文字图片，1视频',
  `status` int(11) NOT NULL DEFAULT '3' COMMENT '0普通1置顶2公告3审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post
-- ----------------------------
INSERT INTO `t_post` VALUES ('6', '无', '1', '6', '0', '0', '1', '0', 'AAAA', '2019-10-04 15:30:51', '2019-10-11 19:57:07', 'e3a66a75-9bf0-4325-ac5a-753e85feb616.png', null, '0', '0');
INSERT INTO `t_post` VALUES ('7', 'imagefile', '102', '6', '0', '0', '1', '0', 'AAAA', '2019-10-10 22:53:27', '2019-10-11 17:32:01', '87a6b1ee-574b-4cc6-b00b-87978f3bd010.png', null, '0', '3');
INSERT INTO `t_post` VALUES ('8', 'imagefile2', '102', '6', '0', '0', '0', '0', 'AAAA', '2019-10-11 13:14:09', '2019-10-11 13:14:09', '62fa04ab-94eb-41d2-b8d7-d304928da30b.png', null, '0', '3');
INSERT INTO `t_post` VALUES ('9', '五五', '102', '6', '0', '0', '0', '0', 'VVV', '2019-10-11 13:26:48', '2019-10-11 16:03:15', null, null, '0', '3');

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `reply_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复编号',
  `user_id` int(11) NOT NULL COMMENT '回复人编号',
  `post_id` int(11) NOT NULL COMMENT '回复的帖子编号',
  `content` varchar(255) NOT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reply
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `plate_id` int(11) NOT NULL COMMENT '板块编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('2', '6', '102');

-- ----------------------------
-- Table structure for t_section
-- ----------------------------
DROP TABLE IF EXISTS `t_section`;
CREATE TABLE `t_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分区编号',
  `name` varchar(255) NOT NULL COMMENT '分区名',
  `plate_id` int(11) NOT NULL COMMENT '板块编号',
  `post_num` int(11) DEFAULT '0' COMMENT '帖子数',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT '1' COMMENT '0禁用1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_section
-- ----------------------------
INSERT INTO `t_section` VALUES ('6', 'peace', '9', '4', '1', '2019-10-10 20:49:56', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账号',
  `username` varchar(255) NOT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `introduce` varchar(255) DEFAULT NULL COMMENT '介绍',
  `image` varchar(255) DEFAULT NULL COMMENT '头像',
  `post_num` int(11) DEFAULT '0' COMMENT '帖子数',
  `reply_num` int(11) DEFAULT '0' COMMENT '评论数',
  `collect_num` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('102', 'tempo', '5555', '2019-10-10 15:56:32', null, null, '4', '0', '0');
INSERT INTO `t_user` VALUES ('103', 'tango', '12345', '2019-10-11 19:00:17', null, null, '0', '0', '0');
