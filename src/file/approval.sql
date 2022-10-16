/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : approval

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2021-04-28 20:49:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for abc
-- ----------------------------
DROP TABLE IF EXISTS `abc`;
CREATE TABLE `abc` (
  `number` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for abc2
-- ----------------------------
DROP TABLE IF EXISTS `abc2`;
CREATE TABLE `abc2` (
  `number` varchar(12) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for applyforall
-- ----------------------------
DROP TABLE IF EXISTS `applyforall`;
CREATE TABLE `applyforall` (
  `number` varchar(255) DEFAULT NULL,
  `fmzl` int(11) DEFAULT NULL,
  `xxzl` int(11) DEFAULT NULL,
  `sjzl` int(11) DEFAULT NULL,
  `rjzzq` int(11) DEFAULT NULL,
  `btsj` int(11) DEFAULT NULL,
  `qtsj` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for applynum
-- ----------------------------
DROP TABLE IF EXISTS `applynum`;
CREATE TABLE `applynum` (
  `name` varchar(60) DEFAULT NULL,
  `value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cg
-- ----------------------------
DROP TABLE IF EXISTS `cg`;
CREATE TABLE `cg` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `CGName` varchar(255) DEFAULT NULL,
  `CGType` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `CNBM` varchar(255) DEFAULT NULL,
  `CGDZ` varchar(255) DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cgzh
-- ----------------------------
DROP TABLE IF EXISTS `cgzh`;
CREATE TABLE `cgzh` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `cgname` varchar(255) DEFAULT NULL,
  `zrlx` varchar(255) DEFAULT NULL,
  `zrdzjf` varchar(255) DEFAULT NULL,
  `dyzz` varchar(255) DEFAULT NULL,
  `qtzz` varchar(255) DEFAULT NULL,
  `zhdw` varchar(255) DEFAULT NULL,
  `zhtime` date DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `mxhzbs` (`judgestatus`,`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `department_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for filesavepath
-- ----------------------------
DROP TABLE IF EXISTS `filesavepath`;
CREATE TABLE `filesavepath` (
  `tusername` varchar(255) DEFAULT NULL,
  `roletype` varchar(255) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hpjl
-- ----------------------------
DROP TABLE IF EXISTS `hpjl`;
CREATE TABLE `hpjl` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `CGName` varchar(255) DEFAULT NULL,
  `JLName` varchar(255) DEFAULT NULL,
  `DYHJR` varchar(255) DEFAULT NULL,
  `QTHJR` varchar(255) DEFAULT NULL,
  `JIXDBM` varchar(255) DEFAULT NULL,
  `ZSNumber` varchar(255) DEFAULT NULL,
  `HJTime` date DEFAULT NULL,
  `JLRank` varchar(255) DEFAULT NULL,
  `HJRank` varchar(255) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `KYJL` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hpxm
-- ----------------------------
DROP TABLE IF EXISTS `hpxm`;
CREATE TABLE `hpxm` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `xmname` varchar(255) DEFAULT NULL,
  `pronumber` varchar(255) DEFAULT NULL,
  `ptjf` varchar(255) DEFAULT NULL,
  `wbjf` varchar(255) DEFAULT NULL,
  `xdbm` varchar(255) DEFAULT NULL,
  `xmsource` varchar(255) DEFAULT NULL,
  `prorank` varchar(255) DEFAULT NULL,
  `proproperty` varchar(255) DEFAULT NULL,
  `hoster` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `cyry` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `lxtime` date DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=1107 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hpzl
-- ----------------------------
DROP TABLE IF EXISTS `hpzl`;
CREATE TABLE `hpzl` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `xmName` varchar(255) DEFAULT NULL,
  `ZLQR` varchar(255) DEFAULT NULL,
  `FMR` varchar(255) DEFAULT NULL,
  `QTFMR` varchar(255) DEFAULT NULL,
  `BFBM` varchar(255) DEFAULT NULL,
  `ZLNumber` varchar(255) DEFAULT NULL,
  `SQTime` date DEFAULT NULL,
  `SYTime` date DEFAULT NULL,
  `PType` varchar(255) DEFAULT NULL,
  `Pstatus` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hxlx
-- ----------------------------
DROP TABLE IF EXISTS `hxlx`;
CREATE TABLE `hxlx` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `XMName` varchar(255) DEFAULT NULL,
  `Principal` varchar(255) DEFAULT NULL,
  `XmStatus` varchar(255) DEFAULT NULL,
  `ZJE` varchar(255) DEFAULT NULL,
  `YBK` varchar(255) DEFAULT NULL,
  `ContractNumber` varchar(255) DEFAULT NULL,
  `AUnit` varchar(255) DEFAULT NULL,
  `QDTime` date DEFAULT NULL,
  `STime` date DEFAULT NULL,
  `JZTime` date DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hxxm
-- ----------------------------
DROP TABLE IF EXISTS `hxxm`;
CREATE TABLE `hxxm` (
  `hid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `XMName` varchar(255) DEFAULT NULL,
  `Principal` varchar(255) DEFAULT NULL,
  `XmStatus` varchar(255) DEFAULT NULL,
  `ZJE` varchar(255) DEFAULT NULL,
  `YBK` varchar(255) DEFAULT NULL,
  `ContractNumber` varchar(255) DEFAULT NULL,
  `AUnit` varchar(255) DEFAULT NULL,
  `QDTime` date DEFAULT NULL,
  `STime` date DEFAULT NULL,
  `JZTime` date DEFAULT NULL,
  `JXTime` date DEFAULT NULL,
  `JXJY` varchar(255) DEFAULT NULL,
  `JFStatus` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hylw
-- ----------------------------
DROP TABLE IF EXISTS `hylw`;
CREATE TABLE `hylw` (
  `hid` bigint(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `Timu` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `Press` varchar(255) DEFAULT NULL,
  `HYName` varchar(255) DEFAULT NULL,
  `Wheres` varchar(255) DEFAULT NULL,
  `Times` date DEFAULT NULL,
  `Rank` varchar(255) DEFAULT NULL,
  `Types` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `ShouLu` varchar(255) DEFAULT NULL,
  `ZiShu` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jxys
-- ----------------------------
DROP TABLE IF EXISTS `jxys`;
CREATE TABLE `jxys` (
  `jid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `xmname` varchar(255) DEFAULT NULL,
  `pronumber` varchar(255) DEFAULT NULL,
  `xmly` varchar(255) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL,
  `protype` varchar(255) DEFAULT NULL,
  `proproperty` varchar(255) DEFAULT NULL,
  `hoster` varchar(255) DEFAULT NULL,
  `xdbm` varchar(255) DEFAULT NULL,
  `cyry` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `pstatus` varchar(255) DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  PRIMARY KEY (`jid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=868 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for model_original
-- ----------------------------
DROP TABLE IF EXISTS `model_original`;
CREATE TABLE `model_original` (
  `action` text,
  `content` text,
  `no` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `noticeTop` varchar(255) DEFAULT NULL,
  `noticeArticle` varchar(255) DEFAULT NULL,
  `generateDate` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `assnumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for opinion
-- ----------------------------
DROP TABLE IF EXISTS `opinion`;
CREATE TABLE `opinion` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `tusername` varchar(255) DEFAULT NULL,
  `roletype` varchar(255) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `departopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qklw
-- ----------------------------
DROP TABLE IF EXISTS `qklw`;
CREATE TABLE `qklw` (
  `qid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `TiMu` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `QiKanName` varchar(255) DEFAULT NULL,
  `JQNumber` varchar(255) DEFAULT NULL,
  `Page` varchar(255) DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `Rank` varchar(255) DEFAULT NULL,
  `ZiShu` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `FQ` varchar(255) DEFAULT NULL,
  `YXYZ` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`qid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=1249 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for rjzz
-- ----------------------------
DROP TABLE IF EXISTS `rjzz`;
CREATE TABLE `rjzz` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `xmName` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `DJNumber` varchar(255) DEFAULT NULL,
  `SYDW` varchar(255) DEFAULT NULL,
  `FinishTime` date DEFAULT NULL,
  `DJTime` date DEFAULT NULL,
  `SFTime` date DEFAULT NULL,
  `BQType` varchar(255) DEFAULT NULL,
  `SYFS` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for studentgrade
-- ----------------------------
DROP TABLE IF EXISTS `studentgrade`;
CREATE TABLE `studentgrade` (
  `stuId` char(4) NOT NULL DEFAULT '',
  `subId` int(11) NOT NULL DEFAULT '0',
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`stuId`,`subId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systime
-- ----------------------------
DROP TABLE IF EXISTS `systime`;
CREATE TABLE `systime` (
  `starttime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacherdoc
-- ----------------------------
DROP TABLE IF EXISTS `teacherdoc`;
CREATE TABLE `teacherdoc` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT,
  `tusername` varchar(50) NOT NULL,
  `department` varchar(50) DEFAULT NULL,
  `roletype` varchar(20) DEFAULT NULL,
  `judgestatus` varchar(20) DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  `tcategory` varchar(20) DEFAULT NULL,
  `tcategory2` varchar(20) DEFAULT NULL,
  `tapply` varchar(255) DEFAULT NULL,
  `tapplyname` varchar(255) DEFAULT NULL,
  `tproject` varchar(255) DEFAULT NULL,
  `tprojectid` varchar(255) DEFAULT NULL,
  `tprojectname` varchar(255) DEFAULT NULL,
  `tsortname` varchar(255) DEFAULT NULL,
  `tinventionname` varchar(255) DEFAULT NULL,
  `tunitname` varchar(255) DEFAULT NULL,
  `tphone` varchar(255) DEFAULT NULL,
  `temail` varchar(255) DEFAULT NULL,
  `tintroduce` text,
  `tduty` varchar(255) DEFAULT NULL,
  `tinventionname2` varchar(255) DEFAULT NULL,
  `tapplydate` date DEFAULT NULL,
  `dunitopinion` varchar(255) DEFAULT NULL,
  `dunitheadname` varchar(255) DEFAULT NULL,
  `dunitchapter` varchar(255) DEFAULT NULL,
  `ddate` date DEFAULT NULL,
  `suretduty` varchar(255) DEFAULT NULL,
  `sureapply` varchar(255) DEFAULT NULL,
  `surename` varchar(255) DEFAULT NULL,
  `surechapter` varchar(255) DEFAULT NULL,
  `suredate` date DEFAULT NULL,
  `adate` date DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `tusername` (`tusername`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tyhj
-- ----------------------------
DROP TABLE IF EXISTS `tyhj`;
CREATE TABLE `tyhj` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `Awardee` varchar(255) DEFAULT NULL,
  `Entryname` varchar(255) DEFAULT NULL,
  `WinTime` date DEFAULT NULL,
  `CertificateID` varchar(255) DEFAULT NULL,
  `SportsLV` varchar(255) DEFAULT NULL,
  `Ranking` varchar(255) DEFAULT NULL,
  `BJBM` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `number` varchar(11) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `qualifications` varchar(40) DEFAULT NULL,
  `degree` varchar(40) DEFAULT NULL,
  `hdgu` varchar(100) DEFAULT NULL,
  `major` varchar(40) DEFAULT NULL,
  `rdirection` varchar(50) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `assessmentdate` date DEFAULT NULL,
  `roletype` varchar(10) DEFAULT NULL,
  `fmzl` varchar(255) DEFAULT NULL,
  `xxzl` varchar(255) DEFAULT NULL,
  `sjzl` varchar(255) DEFAULT NULL,
  `rjzzq` varchar(255) DEFAULT NULL,
  `btsj` varchar(255) DEFAULT NULL,
  `qtsj` varchar(255) DEFAULT NULL,
  `loginstatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`,`number`),
  KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=1438 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xsch
-- ----------------------------
DROP TABLE IF EXISTS `xsch`;
CREATE TABLE `xsch` (
  `xid` bigint(20) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `xmName` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Rank` varchar(255) DEFAULT NULL,
  `ZSNumber` varchar(255) DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `XFBM` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xsjl
-- ----------------------------
DROP TABLE IF EXISTS `xsjl`;
CREATE TABLE `xsjl` (
  `xid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `xmName` varchar(255) DEFAULT NULL,
  `CYRY` varchar(255) DEFAULT NULL,
  `Wheres` varchar(255) DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `ZBDW` varchar(255) DEFAULT NULL,
  `Rank` varchar(255) DEFAULT NULL,
  `LunWen` varchar(255) DEFAULT NULL,
  `FileName` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `fayan` varchar(255) DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for xszz
-- ----------------------------
DROP TABLE IF EXISTS `xszz`;
CREATE TABLE `xszz` (
  `xid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `TiMu` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `PressType` varchar(255) DEFAULT NULL,
  `Press` varchar(255) DEFAULT NULL,
  `cip` varchar(255) DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `ZiShu` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `WorkType` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `zzlx` varchar(255) DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ymhj
-- ----------------------------
DROP TABLE IF EXISTS `ymhj`;
CREATE TABLE `ymhj` (
  `yid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `TiMu` varchar(255) DEFAULT NULL,
  `AwardName` varchar(255) DEFAULT NULL,
  `DYZZ` varchar(255) DEFAULT NULL,
  `Organizer` varchar(255) DEFAULT NULL,
  `QTZZ` varchar(255) DEFAULT NULL,
  `AwardTime` date DEFAULT NULL,
  `RewardSort` varchar(255) DEFAULT NULL,
  `Reward` varchar(255) DEFAULT NULL,
  `Points` varchar(255) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `FJSC` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `uploadfile` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`yid`),
  KEY `mxhzbs` (`judgestatus`(191),`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for zkjs
-- ----------------------------
DROP TABLE IF EXISTS `zkjs`;
CREATE TABLE `zkjs` (
  `zid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `cgname` varchar(255) DEFAULT NULL,
  `cnjb` varchar(255) DEFAULT NULL,
  `dyzz` varchar(255) DEFAULT NULL,
  `qtzz` varchar(255) DEFAULT NULL,
  `cnbm` varchar(255) DEFAULT NULL,
  `cntime` date DEFAULT NULL,
  `points` varchar(255) DEFAULT NULL,
  `reward` varchar(255) DEFAULT NULL,
  `fjsc` varchar(255) DEFAULT NULL,
  `judgestatus` varchar(255) DEFAULT NULL,
  `departmentopinion` varchar(255) DEFAULT NULL,
  `schoolopinion` varchar(255) DEFAULT NULL,
  `systime` date DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`zid`),
  KEY `mxhzbs` (`judgestatus`,`systime`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
