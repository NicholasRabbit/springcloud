/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 31/08/2022 21:26:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (3, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', 'spring:  \n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml   \n  type-aliases-package: com.springcloud.learn.entity  \n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug    \n\nconfig: \n  info: from nacos config-payment-client-8001-dev.yml,version=3  ', 'b435eee1d4b0cb4fe2409e047e1cfabe', '2022-08-18 13:05:16', '2022-08-18 13:45:55', 'nacos', '0:0:0:0:0:0:0:1', '', '', '消费者模块配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (11, 'config-payment-client-8001-prod.yml', 'DEFAULT_GROUP', '#这里是Nacos的配置信息，仅作备份，实际并不在项目内使用\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-8001-prod.yml,version=0', '5bb3aaa8608bac636d036fd2b7a6b396', '2022-08-18 13:55:17', '2022-08-18 13:55:17', 'nacos', '0:0:0:0:0:0:0:1', '', '', '支付模块生产环境配置', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (14, 'config-payment-client-dev.yml', 'DEFAULT_GROUP', '\n\nspring:\n  #在Nacos总配置中心这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\n  type-aliases-package: com.springcloud.learn.entity\n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug\n\n#测试用\nconfig:\n  info: config-payment-client-dev.yml,version=1', 'ed6da7c0925eb0817bb1e4cec9d4c5a5', '2022-08-23 12:59:19', '2022-08-23 13:47:17', 'nacos', '0:0:0:0:0:0:0:1', '', '', '支付集群8001/8002配置文件', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (15, 'config-payment-client-prod.yml', 'DEFAULT_GROUP', '\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-prod.yml,version=0', '89be97b6f8201e6dc1bdbaa42ee1d154', '2022-08-23 13:41:09', '2022-08-23 13:41:09', 'nacos', '0:0:0:0:0:0:0:1', '', '', '支付模块集群8001/8002，正式环境配置', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (17, 'config-payment-client-dev.yml', 'DEV_GROUP', '#开发组 DEV_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: DEV_GROUP==>config-payment-client.yml,version=0', '307617b2d7748707e55dd99278b35890', '2022-08-23 13:49:56', '2022-08-23 13:49:56', 'nacos', '0:0:0:0:0:0:0:1', '', '', '开发组', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (18, 'config-payment-client-prod.yml', 'DEV_GROUP', '#DEV_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: DEV_GROUP==>config-payment-client-prod.yml,version=0', 'd7b21951faa9be9d1051fc2b82c9cbce', '2022-08-23 13:51:37', '2022-08-23 13:51:37', 'nacos', '0:0:0:0:0:0:0:1', '', '', '开发组，正式配置文件', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (20, 'config-payment-client-dev.yml', 'DEV_GROUP', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: namespace:develop,group:DEV_GROUP,config-payment.yml,version=0', 'f3a8ac52fef5584bcd136ed736bc7468', '2022-08-23 14:04:01', '2022-08-23 14:04:01', 'nacos', '0:0:0:0:0:0:0:1', '', 'a66d8673-33e5-4887-a7b7-9814fa2490f5', 'develop命名空间', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (22, 'cloud-sentinel-consumer-81-dev.yml', 'DEV_GROUP', 'config: \r\n    info: DEV_GROUP,cloud-sentinel-consumer-81-dev.yml,version=0', 'cc8252d2601383741083b24434606947', '2022-08-30 12:49:35', '2022-08-30 12:49:35', NULL, '0:0:0:0:0:0:0:1', '', 'a66d8673-33e5-4887-a7b7-9814fa2490f5', '81模块', NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'test-dev.yml', 'DEFAULT_GROUP', '', 'config: \r\n    info: test-dev,version=1', '259d22f1883678da1e6a2f5de274373f', '2022-08-18 10:17:09', '2022-08-18 02:17:09', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'payment-dev.yml', 'DEFAULT_GROUP', '', 'config: \r\n    info: payment-dev.yml,version=1', '955e5d1b771735312dc6d8782d327924', '2022-08-18 10:19:24', '2022-08-18 02:19:24', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (2, 3, 'payment-dev.yml', 'DEFAULT_GROUP', '', 'config: \r\n    info: payment-dev.yml,version=1', '955e5d1b771735312dc6d8782d327924', '2022-08-18 21:01:08', '2022-08-18 13:01:09', 'nacos', '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'test-dev.yml', 'DEFAULT_GROUP', '', 'config: \r\n    info: test-dev,version=1', '259d22f1883678da1e6a2f5de274373f', '2022-08-18 21:01:12', '2022-08-18 13:01:12', 'nacos', '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 5, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \r\n  #在这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml   \r\n  type-aliases-package: com.springcloud.learn.entity  \r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug    ', 'fae6316b2f775f70405659a89e28010a', '2022-08-18 21:05:15', '2022-08-18 13:05:16', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (3, 6, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \r\n  #在这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml   \r\n  type-aliases-package: com.springcloud.learn.entity  \r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug    ', 'fae6316b2f775f70405659a89e28010a', '2022-08-18 21:21:31', '2022-08-18 13:21:32', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 7, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml   \n  type-aliases-package: com.springcloud.learn.entity  \n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug    \n\nconfig: \n  info: config-payment-client-8001-dev.yml,version=1     ', '180dd5b549ae05d7f4ef2199f7e53d8a', '2022-08-18 21:33:49', '2022-08-18 13:33:50', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 8, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml   \n  type-aliases-package: com.springcloud.learn.entity  \n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug    \n\nconfig: \n  info: from nacos config-payment-client-8001-dev.yml,version=1    ', '5493f60b7ed325bb6ff3b1eee3554685', '2022-08-18 21:34:48', '2022-08-18 13:34:48', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 9, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml   \n  type-aliases-package: com.springcloud.learn.entity  \n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug    \n\nconfig: \n  info: from nacos config-payment-client-8001-dev.yml,version=2    ', '833de5fa04ea78f361e5f302ab753811', '2022-08-18 21:37:30', '2022-08-18 13:37:31', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 10, 'config-payment-client-8001-dev.yml', 'DEFAULT_GROUP', '', 'spring:  \n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml   \n  type-aliases-package: com.springcloud.learn.entity  \n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug    \n\nconfig: \n  info: from nacos config-payment-client-8001-dev.yml,version=3  ', 'b435eee1d4b0cb4fe2409e047e1cfabe', '2022-08-18 21:45:54', '2022-08-18 13:45:55', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 11, 'config-payment-client-8001-dev-backup.yml', 'DEV_GROUP', '', 'spring:\r\n  #在这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-8001-dev-backup.yml,version=0', '01704f709df7370139145ba9bb89b817', '2022-08-18 21:51:54', '2022-08-18 13:51:54', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (9, 12, 'config-payment-client-8001-dev-backup.yml', 'DEV_GROUP', '', 'spring:\r\n  #在这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-8001-dev-backup.yml,version=0', '01704f709df7370139145ba9bb89b817', '2022-08-18 21:52:48', '2022-08-18 13:52:49', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 13, 'config-payment-client-8001-dev-backup.yml', 'DEV_GROUP', '', 'spring:\n  #在这里统一配置数据库信息\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\n    username: root\n    password: 123456\n\n#MyBatis配置\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.springcloud.learn.entity\n\n\n#设置打印sql日志\nlogging:\n  level:\n    com.springcloud.learn.mapper: debug\n\n#测试用\nconfig:\n  info: config-payment-client-8001-dev.yml,version=0', 'c3b1be9600e20f4492cb2cd921286098', '2022-08-18 21:53:28', '2022-08-18 13:53:28', 'nacos', '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 14, 'config-payment-client-8001-prod.yml', 'DEFAULT_GROUP', '', '#这里是Nacos的配置信息，仅作备份，实际并不在项目内使用\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-8001-prod.yml,version=0', '5bb3aaa8608bac636d036fd2b7a6b396', '2022-08-18 21:55:17', '2022-08-18 13:55:17', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 15, 'config-payment-client', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 20:55:46', '2022-08-23 12:55:46', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 16, 'config-payment-client-dev', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 20:57:43', '2022-08-23 12:57:44', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (12, 17, 'config-payment-client', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 20:57:50', '2022-08-23 12:57:50', 'nacos', '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (13, 18, 'config-payment-client-dev', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 20:58:35', '2022-08-23 12:58:36', 'nacos', '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 19, 'config-payment-client-dev.yml', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 20:59:19', '2022-08-23 12:59:19', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 20, 'config-payment-client-prod.yml', 'DEFAULT_GROUP', '', '\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-prod.yml,version=0', '89be97b6f8201e6dc1bdbaa42ee1d154', '2022-08-23 21:41:09', '2022-08-23 13:41:09', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (14, 21, 'config-payment-client-dev.yml', 'DEFAULT_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: config-payment-client-dev.yml,version=0', 'a3c4ba0ca54972b52d2e63ba03f6784d', '2022-08-23 21:47:17', '2022-08-23 13:47:17', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 22, 'config-payment-client-dev.yml', 'DEV_GROUP', '', '#开发组 DEV_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: DEV_GROUP==>config-payment-client.yml,version=0', '307617b2d7748707e55dd99278b35890', '2022-08-23 21:49:55', '2022-08-23 13:49:56', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 23, 'config-payment-client-prod.yml', 'DEV_GROUP', '', '#DEV_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: DEV_GROUP==>config-payment-client-prod.yml,version=0', 'd7b21951faa9be9d1051fc2b82c9cbce', '2022-08-23 21:51:36', '2022-08-23 13:51:37', 'nacos', '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 24, 'config-payment-client-dev.yml', 'DEVELOP_GROUP', '', '#Namespace: develop  Group:DEVELOP_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: namespace:develop,group:DEVELOP_GROUP,config-payment-client.yml,version=0', '40b6ef3c8138d807486b1f83bd0b059e', '2022-08-23 21:58:57', '2022-08-23 13:58:58', 'nacos', '0:0:0:0:0:0:0:1', 'I', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');
INSERT INTO `his_config_info` VALUES (0, 25, 'config-payment-client-dev.yml', 'DEV_GROUP', '', '\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: namespace:develop,group:DEV_GROUP,config-payment.yml,version=0', 'f3a8ac52fef5584bcd136ed736bc7468', '2022-08-23 22:04:01', '2022-08-23 14:04:01', 'nacos', '0:0:0:0:0:0:0:1', 'I', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');
INSERT INTO `his_config_info` VALUES (19, 26, 'config-payment-client-dev.yml', 'DEVELOP_GROUP', '', '#Namespace: develop  Group:DEVELOP_GROUP\r\n\r\nspring:\r\n  #在Nacos总配置中心这里统一配置数据库信息\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.jdbc.Driver\r\n    url: jdbc:mysql://localhost:3306/spring_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false\r\n    username: root\r\n    password: 123456\r\n\r\n#MyBatis配置\r\nmybatis:\r\n  mapper-locations: classpath*:mapper/**/*.xml   #设置多重路径\r\n  type-aliases-package: com.springcloud.learn.entity\r\n\r\n\r\n#设置打印sql日志\r\nlogging:\r\n  level:\r\n    com.springcloud.learn.mapper: debug\r\n\r\n#测试用\r\nconfig:\r\n  info: namespace:develop,group:DEVELOP_GROUP,config-payment-client.yml,version=0', '40b6ef3c8138d807486b1f83bd0b059e', '2022-08-23 22:04:07', '2022-08-23 14:04:08', 'nacos', '0:0:0:0:0:0:0:1', 'D', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');
INSERT INTO `his_config_info` VALUES (0, 27, 'cloud-sentinel-consumer-81', 'DEV_GROUP', '', 'config: \r\n    info: DEV_GROUP,cloud-sentinel-consumer-81,version=0', '7143431117bc0c88f8b4b6df538c7126', '2022-08-30 20:44:28', '2022-08-30 12:44:29', NULL, '0:0:0:0:0:0:0:1', 'I', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');
INSERT INTO `his_config_info` VALUES (21, 28, 'cloud-sentinel-consumer-81', 'DEV_GROUP', '', 'config: \r\n    info: DEV_GROUP,cloud-sentinel-consumer-81,version=0', '7143431117bc0c88f8b4b6df538c7126', '2022-08-30 20:48:50', '2022-08-30 12:48:50', NULL, '0:0:0:0:0:0:0:1', 'D', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');
INSERT INTO `his_config_info` VALUES (0, 29, 'cloud-sentinel-consumer-81-dev.yml', 'DEV_GROUP', '', 'config: \r\n    info: DEV_GROUP,cloud-sentinel-consumer-81-dev.yml,version=0', 'cc8252d2601383741083b24434606947', '2022-08-30 20:49:35', '2022-08-30 12:49:35', NULL, '0:0:0:0:0:0:0:1', 'I', 'a66d8673-33e5-4887-a7b7-9814fa2490f5');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'a66d8673-33e5-4887-a7b7-9814fa2490f5', 'develop', '开发空间', 'nacos', 1661262765300, 1661262975502);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
