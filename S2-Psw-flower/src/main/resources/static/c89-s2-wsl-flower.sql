/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : c89-s2-wsl-flower

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-12-24 20:12:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `ciid` int(11) NOT NULL auto_increment,
  `uid` int(11) default NULL,
  `fid` int(11) default NULL,
  `count` int(11) default NULL,
  PRIMARY KEY  (`ciid`),
  KEY `uid` (`uid`),
  KEY `fid` (`fid`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `flower` (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('1', '1', '1', '2');
INSERT INTO `cart` VALUES ('2', '1', '4', '5');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL auto_increment,
  `cname` varchar(255) default NULL,
  `intro` varchar(255) default NULL COMMENT '介绍',
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '玫瑰', null);
INSERT INTO `category` VALUES ('2', '月季花', null);
INSERT INTO `category` VALUES ('3', '牡丹', null);
INSERT INTO `category` VALUES ('4', '茉莉花', null);
INSERT INTO `category` VALUES ('5', '菊花', null);
INSERT INTO `category` VALUES ('6', '康乃馨', null);
INSERT INTO `category` VALUES ('7', '满天星', null);
INSERT INTO `category` VALUES ('8', '睡莲', null);
INSERT INTO `category` VALUES ('9', '丁香', null);
INSERT INTO `category` VALUES ('10', '兰花', null);

-- ----------------------------
-- Table structure for flower
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `fid` int(11) NOT NULL auto_increment,
  `fname` varchar(255) default NULL COMMENT '鲜花名字',
  `market_price` decimal(10,2) default NULL COMMENT '商场价',
  `discount` decimal(10,2) default NULL COMMENT '折扣',
  `shop_price` decimal(10,2) default NULL COMMENT '销售价',
  `image` varchar(255) default NULL COMMENT '图片',
  `cid` int(11) default NULL COMMENT '鲜花类型',
  `is_hot` int(11) default NULL COMMENT '是否热卖',
  `fdate` datetime default NULL COMMENT '上线时间',
  `fcount` int(11) default NULL COMMENT '数量',
  `advice` varchar(255) default NULL COMMENT '鲜花描述',
  PRIMARY KEY  (`fid`),
  KEY `cid` (`cid`),
  CONSTRAINT `flower_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO `flower` VALUES ('1', '玫瑰花', '24.00', '8.80', '15.00', 'images/product/01.jpg', '1', '1', '2020-12-21 16:56:14', '3', '养玫瑰花注意事项有三点。第一点：玫瑰花在浇水的时候，要掌握干了就浇水，湿度过大就排水的原则。第二点：在玫瑰花发芽的时候，要施一些氮肥，到了玫瑰花开花以后需要施入一定的磷钾肥。第三点：在每年的春季四、五月份的时候，需要给它进行修剪。');
INSERT INTO `flower` VALUES ('2', '菊花', '240.00', '7.00', null, 'images/product/02.jpg', null, '0', '2020-12-22 19:01:10', '10', null);
INSERT INTO `flower` VALUES ('3', '五彩玫瑰', '160.00', '5.00', null, 'images/product/03.jpg', null, '0', '2020-12-22 19:01:16', '20', null);
INSERT INTO `flower` VALUES ('4', '香槟玫瑰', '140.00', '8.80', null, 'images/product/04.jpg', null, '1', '2020-12-22 22:02:18', '30', null);
INSERT INTO `flower` VALUES ('5', '粉蝶衣', '320.00', '9.00', null, 'images/product/05.jpg', null, '1', '2020-12-22 19:01:22', '11', null);
INSERT INTO `flower` VALUES ('6', '菊花', '150.00', '7.00', null, 'images/product/06.jpg', null, '0', '2020-12-22 19:02:10', '7', null);
INSERT INTO `flower` VALUES ('7', '百合', '110.00', '9.50', null, 'images/product/07.jpg', null, '1', '2020-12-22 19:02:13', '6', null);
INSERT INTO `flower` VALUES ('8', '橙色百合', '130.00', '6.00', null, 'images/product/08.jpg', null, '0', '2020-12-23 19:02:27', '8', null);

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `mid` int(11) NOT NULL auto_increment,
  `content` varchar(1000) default NULL,
  `publishtime` datetime default NULL,
  `modifytime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `uid` int(11) default NULL,
  `tid` int(11) default NULL,
  PRIMARY KEY  (`mid`),
  KEY `FK_reply_uid` (`uid`),
  KEY `FK_reply_topicid` (`tid`),
  CONSTRAINT `FK_topic_tid` FOREIGN KEY (`tid`) REFERENCES `topic` (`tid`),
  CONSTRAINT `FK_user_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('1', '222233333', '2018-06-25 15:57:40', '2018-06-25 15:57:40', '1', '1');
INSERT INTO `msg` VALUES ('2', '我的饿作为123', '2018-06-25 16:42:54', '2020-12-24 19:54:31', '1', '1');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` int(11) NOT NULL auto_increment,
  `uid` int(11) default NULL COMMENT '下单的用户id',
  `name` varchar(255) default NULL COMMENT '下单用户名',
  `total` varchar(255) default NULL COMMENT '订单总金额',
  `otime` datetime default NULL COMMENT '下单时间',
  `state` int(11) default NULL COMMENT '订单状态',
  `addr` varchar(255) default NULL COMMENT '收货地址',
  `phone` varchar(255) default NULL COMMENT '电话',
  PRIMARY KEY  (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `iid` int(11) NOT NULL auto_increment,
  `count` int(11) default NULL COMMENT '下单数量',
  `subtotal` varchar(255) default NULL COMMENT '单价',
  `fid` int(11) default NULL COMMENT '鲜花id',
  `oid` int(11) default NULL,
  PRIMARY KEY  (`iid`),
  KEY `fid` (`fid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `flower` (`fid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `tid` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `content` varchar(4000) default NULL,
  `publishtime` datetime default NULL,
  `modifytime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `uid` int(11) default NULL,
  PRIMARY KEY  (`tid`),
  KEY `FK_topic_uid` (`uid`),
  CONSTRAINT `FK_topic_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '1111', '2222222', '2018-06-25 15:56:49', '2018-06-25 15:56:49', '1');
INSERT INTO `topic` VALUES ('2', '11111', '11111', null, null, '1');
INSERT INTO `topic` VALUES ('3', '累了', '再试一次', '2018-06-25 21:10:44', '2018-06-25 21:10:44', '1');
INSERT INTO `topic` VALUES ('4', 'aaa', 'aaaa', null, null, null);
INSERT INTO `topic` VALUES ('5', '123', '1234', null, null, '1');
INSERT INTO `topic` VALUES ('6', '123', '1234', null, null, '1');
INSERT INTO `topic` VALUES ('7', '123', '1234', null, null, '1');
INSERT INTO `topic` VALUES ('8', '123', '1234', null, null, '1');
INSERT INTO `topic` VALUES ('9', '123', '1234', null, null, '1');
INSERT INTO `topic` VALUES ('10', 'dfdfd', 'fdfdfd', null, null, '1');
INSERT INTO `topic` VALUES ('11', 'avasdfa', 'asdfasdfa', null, null, '1');
INSERT INTO `topic` VALUES ('12', 'vvvvv', 'asdfasf', '2018-07-02 10:31:57', '2018-07-02 10:31:57', '1');
INSERT INTO `topic` VALUES ('13', 'ddddd', '123asd\r\n1', '2018-07-03 00:29:29', '2018-07-03 00:29:29', '1');
INSERT INTO `topic` VALUES ('14', 'dfdfdf', 'dfdfdfdf', '2018-07-05 23:20:28', '2018-07-05 23:20:28', '1');
INSERT INTO `topic` VALUES ('15', '的点点', '滴滴11111', '2018-07-06 16:54:09', '2020-12-24 19:50:18', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL auto_increment COMMENT '用户id(自增)',
  `name` varchar(255) NOT NULL COMMENT '用户名',
  `pwd` varchar(255) default NULL COMMENT '密码',
  `sex` varchar(255) default NULL,
  `code` varchar(20) default NULL COMMENT '验证码',
  `phone` varchar(50) default NULL COMMENT '电话号码',
  `addr` varchar(255) default NULL COMMENT '地址',
  `email` varchar(255) default NULL COMMENT '邮箱',
  `utime` datetime default NULL COMMENT '注册时间',
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小小', '123', '女', null, '123', '12', '212', '2020-12-23 10:48:15');
INSERT INTO `user` VALUES ('2', '李四', 'aaa', '男', null, '112233', '湖南省长沙市', '1111@qq.com', '2020-12-24 14:14:35');
