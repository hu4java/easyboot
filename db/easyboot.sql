/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : easyboot

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 14/09/2020 17:54:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint DEFAULT '0' COMMENT '版本号',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `code` varchar(50) DEFAULT NULL COMMENT '部门编码',
  `pid` bigint NOT NULL COMMENT '上级id',
  `pids` varchar(100) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：０-正常 １-禁用',
  `order_num` smallint DEFAULT '1' COMMENT '排序',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1000000, 1000000, '2020-09-03 17:56:19', 1000000, '2020-09-14 17:03:00', 0, 0, '总公司', '', 0, '0', 0, 1, '', '', '');
INSERT INTO `sys_dept` VALUES (1000001, 1000000, '2020-09-03 18:04:49', NULL, NULL, 0, 0, '上海分公司', 'SH0001', 1000000, '0,1000000', 0, 1, '11212', '', 'rrr');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint DEFAULT NULL COMMENT '版本号',
  `name` varchar(50) DEFAULT NULL COMMENT '字典名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `status` tinyint DEFAULT NULL COMMENT '状态：0-正常 1-禁用',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (10001, 1000000, '2020-09-08 20:47:39', 1000000, '2020-09-14 17:15:11', NULL, NULL, '性别', 'gender_type', 0, '性别字典');
INSERT INTO `sys_dict` VALUES (10002, 1000000, '2020-09-08 20:49:07', 1000000, '2020-09-14 17:16:41', NULL, NULL, '人事状态', 'personnel_status', 0, '人事状态');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint DEFAULT NULL COMMENT '版本号',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `value` varchar(50) DEFAULT NULL COMMENT '值',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：0-正常 1-禁用',
  `order_num` smallint DEFAULT '1' COMMENT '排序',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `dict_type` varchar(50) NOT NULL COMMENT '关联字典类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dicty_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10013 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` VALUES (10000, 1000000, '2020-09-11 19:53:14', 1000000, '2020-09-14 17:52:36', NULL, NULL, '未知', '0', 0, 1, '', 'gender_type');
INSERT INTO `sys_dict_item` VALUES (10001, 1000000, '2020-09-11 19:53:25', NULL, NULL, NULL, NULL, '男', '1', 0, 2, '', 'gender_type');
INSERT INTO `sys_dict_item` VALUES (10002, 1000000, '2020-09-11 19:53:34', NULL, NULL, NULL, NULL, '女', '2', 0, 3, '', 'gender_type');
INSERT INTO `sys_dict_item` VALUES (10003, 1000000, '2020-09-11 19:53:56', NULL, NULL, NULL, NULL, '未知', '0', 0, 1, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10004, 1000000, '2020-09-11 19:54:33', NULL, NULL, NULL, NULL, '面试期', '1', 0, 2, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10005, 1000000, '2020-09-11 19:54:51', NULL, NULL, NULL, NULL, '培训期', '2', 0, 3, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10006, 1000000, '2020-09-11 19:55:04', NULL, NULL, NULL, NULL, '试用期', '3', 0, 4, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10007, 1000000, '2020-09-11 19:55:18', NULL, NULL, NULL, NULL, '兼职员工', '4', 0, 5, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10008, 1000000, '2020-09-11 19:55:31', NULL, NULL, NULL, NULL, '正式员工', '5', 0, 6, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10009, 1000000, '2020-09-11 19:55:43', NULL, NULL, NULL, NULL, '转正失败', '6', 0, 7, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10010, 1000000, '2020-09-11 19:56:01', NULL, NULL, NULL, NULL, '停薪留职', '7', 0, 8, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10011, 1000000, '2020-09-11 19:56:19', 1000000, '2020-09-14 17:53:24', NULL, NULL, '主动离职', '8', 0, 9, '', 'personnel_status');
INSERT INTO `sys_dict_item` VALUES (10012, 1000000, '2020-09-11 19:56:32', NULL, NULL, NULL, NULL, '被解聘', '9', 0, 10, '', 'personnel_status');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint DEFAULT '0' COMMENT '版本号',
  `title` varchar(50) NOT NULL COMMENT '菜单标题',
  `code` varchar(50) DEFAULT NULL COMMENT '权限码',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '上级id',
  `pids` varchar(500) NOT NULL DEFAULT '0' COMMENT '上级id关联字符串',
  `path` varchar(100) DEFAULT NULL COMMENT '菜单路径',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：０-正常 １-禁用',
  `hidden` tinyint(1) DEFAULT '0' COMMENT '是否隐藏',
  `type` tinyint(1) DEFAULT '1' COMMENT '类型：１-目录 ２-菜单 ３-按钮',
  `route_name` varchar(50) DEFAULT NULL COMMENT '路由名',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(100) DEFAULT NULL COMMENT '菜单组件',
  `redirect` varchar(100) DEFAULT NULL COMMENT '重定向',
  `hide_children_in_menu` tinyint(1) DEFAULT '0' COMMENT '强制转换为菜单',
  `order_num` smallint DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000034 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1000000, 0, '2020-09-01 22:39:34', 1000000, '2020-09-10 20:06:41', 0, 0, '系统管理', 'sys', 0, '0', '/sys', 0, 0, 1, 'System', 'system', 'RouteView', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (1000001, 1000000, '2020-09-01 23:15:02', 1000000, '2020-09-09 22:05:08', 0, 0, '菜单管理', 'sys:menu', 1000000, '0,1000000', '/sys/menu', 0, 0, 1, 'Menu', '', 'RouteView', '/sys/menu/list', 1, 4);
INSERT INTO `sys_menu` VALUES (1000002, 1000000, '2020-09-02 14:01:46', 1000000, '2020-09-09 20:53:47', 0, 0, '菜单', 'sys:menu:view', 1000001, '0,1000000,1000001', '/sys/menu/list', 0, 1, 2, 'MenuList', '', '/sys/menu/menu-list', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1000003, 1000000, '2020-09-02 14:05:39', 1000000, '2020-09-09 20:53:57', 0, 0, '新建菜单', 'sys:menu:save', 1000001, '0,1000000,1000001', '/sys/menu/add', 0, 1, 2, 'MenuAdd', '', '/sys/menu/menu-form', NULL, 0, 2);
INSERT INTO `sys_menu` VALUES (1000004, 1000000, '2020-09-02 18:01:08', 1000000, '2020-09-09 20:54:09', 0, 0, '编辑菜单', 'sys:menu:update', 1000001, '0,1000000,1000001', '/sys/menu/edit', 0, 1, 2, 'MenuEdit', '', '/sys/menu/menu-form', NULL, 0, 3);
INSERT INTO `sys_menu` VALUES (1000006, 1000000, '2020-09-02 18:41:54', NULL, NULL, 0, 0, '删除菜单', 'sys:menu:remove', 1000001, '0,1000000,1000001', '', 0, 1, 3, '', '', '', NULL, 0, 4);
INSERT INTO `sys_menu` VALUES (1000007, 1000000, '2020-09-02 19:20:53', 1000000, '2020-09-09 22:04:55', 0, 0, '角色管理', '', 1000000, '0,1000000', '/sys/role', 0, 0, 1, 'Role', '', 'RouteView', '/sys/role/list', 1, 3);
INSERT INTO `sys_menu` VALUES (1000008, 1000000, '2020-09-02 19:23:23', 1000000, '2020-09-09 20:53:27', 0, 0, '角色列表', 'sys:role:view', 1000007, '0,1000000,1000007', '/sys/role/list', 0, 1, 2, 'RoleList', '', '/sys/role/role-list', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1000009, 1000000, '2020-09-02 19:24:44', 1000000, '2020-09-09 20:53:16', 0, 0, '新建角色', 'sys:role:save', 1000007, '0,1000000,1000007', '/sys/role/add', 0, 1, 2, 'RoleAdd', '', '/sys/role/role-form', NULL, 0, 2);
INSERT INTO `sys_menu` VALUES (1000010, 1000000, '2020-09-02 19:25:33', 1000000, '2020-09-09 20:53:35', 0, 0, '编辑角色', 'sys:role:update', 1000007, '0,1000000,1000007', '/sys/role/edit', 0, 1, 2, 'RoleEdit', '', '/sys/role/role-form', NULL, 0, 3);
INSERT INTO `sys_menu` VALUES (1000011, 1000000, '2020-09-02 19:26:02', NULL, NULL, 0, 0, '删除角色', 'sys:role:remove', 1000007, '0,1000000,1000007', '', 0, 1, 3, '', '', '', NULL, 0, 4);
INSERT INTO `sys_menu` VALUES (1000012, 1000000, '2020-09-02 19:34:41', 1000000, '2020-09-09 22:04:31', 0, 0, '部门管理', '', 1000000, '0,1000000', '/sys/dept', 0, 0, 1, 'Dept', '', 'RouteView', '/sys/dept/list', 1, 1);
INSERT INTO `sys_menu` VALUES (1000013, 1000000, '2020-09-02 19:35:19', 1000000, '2020-09-09 20:51:30', 0, 0, '部门列表', 'sys:dept:view', 1000012, '0,1000000,1000012', '/sys/dept/list', 0, 1, 2, 'DeptList', '', '/sys/dept/dept-list', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1000014, 1000000, '2020-09-02 19:35:57', 1000000, '2020-09-09 20:51:46', 0, 0, '新建部门', 'sys:dept:save', 1000012, '0,1000000,1000012', '/sys/dept/add', 0, 1, 2, 'DeptAdd', '', '/sys/dept/dept-form', NULL, 0, 2);
INSERT INTO `sys_menu` VALUES (1000015, 1000000, '2020-09-02 19:36:27', 1000000, '2020-09-09 20:52:01', 0, 0, '编辑部门', 'sys:dept:update', 1000012, '0,1000000,1000012', '/sys/dept/edit', 0, 1, 2, 'DeptEdit', '', '/sys/dept/dept-form', NULL, 0, 3);
INSERT INTO `sys_menu` VALUES (1000016, 1000000, '2020-09-02 19:36:49', NULL, NULL, 0, 0, '删除部门', 'sys:dept:remove', 1000012, '0,1000000,1000012', '', 0, 1, 3, '', '', '', NULL, 0, 4);
INSERT INTO `sys_menu` VALUES (1000017, 1000000, '2020-09-02 19:37:26', 1000000, '2020-09-09 22:04:42', 0, 0, '用户管理', '', 1000000, '0,1000000', '/sys/user', 0, 0, 1, 'User', '', 'RouteView', '/sys/user/list', 1, 2);
INSERT INTO `sys_menu` VALUES (1000018, 1000000, '2020-09-02 19:38:07', 1000000, '2020-09-09 20:52:13', 0, 0, '用户列表', 'sys:user:view', 1000017, '0,1000000,1000017', '/sys/user/list', 0, 1, 2, 'UserList', '', '/sys/user/user-list', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1000019, 1000000, '2020-09-02 19:38:36', 1000000, '2020-09-09 20:52:30', 0, 0, '新建用户', 'sys:user:save', 1000017, '0,1000000,1000017', '/sys/user/add', 0, 1, 2, 'UserAdd', '', '/sys/user/user-form', NULL, 0, 2);
INSERT INTO `sys_menu` VALUES (1000020, 1000000, '2020-09-02 19:39:13', 1000000, '2020-09-09 20:52:43', 0, 0, '编辑用户', 'sys:user:update', 1000017, '0,1000000,1000017', '/sys/user/edit', 0, 1, 2, 'UserEdit', '', '/sys/user/user-form', NULL, 0, 3);
INSERT INTO `sys_menu` VALUES (1000021, 1000000, '2020-09-02 19:39:33', NULL, NULL, 0, 0, '删除用户', 'sys:user:remove', 1000017, '0,1000000,1000017', '', 0, 1, 3, '', '', '', NULL, 0, 4);
INSERT INTO `sys_menu` VALUES (1000023, 1000000, '2020-09-09 22:05:52', 1000000, '2020-09-10 20:35:28', 0, 0, '系统工具', '', 0, '0', '/tools', 0, 0, 1, 'Tools', 'tools-box', 'RouteView', NULL, 0, 5);
INSERT INTO `sys_menu` VALUES (1000024, 1000000, '2020-09-09 22:06:47', 1000000, '2020-09-11 21:34:08', 0, 0, '代码生成', '', 1000023, '0,1000023', '/tools/generate', 0, 0, 1, 'Generate', '', 'RouteView', '/tools/generate/list', 1, 1);
INSERT INTO `sys_menu` VALUES (1000025, 1000000, '2020-09-09 22:07:49', NULL, NULL, 0, 0, '数据库表', 'tools:generate:view', 1000024, '0,1000023,1000024', '/tools/generate/list', 0, 1, 2, 'GenerateList', '', '/tools/generate/list', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1000026, 1000000, '2020-09-09 22:08:33', NULL, NULL, 0, 0, '生成代码', 'tools:generate:code', 1000024, '0,1000023,1000024', '/tools/generate/code', 0, 1, 2, 'GenerateCode', '', '/tools/generate/code', NULL, 0, 2);
INSERT INTO `sys_menu` VALUES (1000027, 1000000, '2020-09-10 19:44:41', 1000000, '2020-09-10 19:49:40', 0, 0, '数据字典', '', 1000000, '0,1000000', '/sys/dict', 0, 0, 1, 'Dict', '', 'RouteView', '/sys/dict/list', 1, 5);
INSERT INTO `sys_menu` VALUES (1000028, 1000000, '2020-09-10 19:47:54', 1000000, '2020-09-10 19:48:31', 0, 0, '字典列表', 'sys:dict:view', 1000027, '0,1000000,1000027', '/sys/dict/list', 0, 1, 2, 'DictList', 'none', '/sys/dict/dict-list', '', 0, 1);
INSERT INTO `sys_menu` VALUES (1000029, 1000000, '2020-09-11 19:43:31', 1000000, '2020-09-11 19:45:43', 0, 0, '字典数据项', 'sys:dict:item', 1000027, '0,1000000,1000027', '/sys/dict/item/list', 0, 1, 2, 'DictItemList', 'none', '/sys/dict/dict-item-list', '', 0, 2);
INSERT INTO `sys_menu` VALUES (1000030, 1000000, '2020-09-13 14:13:16', NULL, NULL, 0, 0, '日志管理', '', 1000000, '0,1000000', '/sys/log', 0, 0, 1, 'SystemLog', '', 'RouteView', '', 0, 6);
INSERT INTO `sys_menu` VALUES (1000031, 1000000, '2020-09-13 14:14:24', 1000000, '2020-09-14 16:16:11', 0, 0, '操作日志', 'sys:log:operate', 1000030, '0,1000000,1000030', '/sys/logs/operate', 0, 0, 1, 'OperateLog', '', 'RouteView', '/sys/logs/operate/list', 1, 1);
INSERT INTO `sys_menu` VALUES (1000032, 1000000, '2020-09-14 16:17:11', NULL, NULL, 0, 0, '日志列表', 'sys:logs:operate', 1000031, '0,1000000,1000030,1000031', '/sys/logs/operate/list', 0, 1, 2, 'OperateLogList', '', 'sys/logs/operate-log-list', '', 0, 1);
INSERT INTO `sys_menu` VALUES (1000033, 1000000, '2020-09-14 16:18:35', NULL, NULL, 0, 0, '操作日志详细', 'sys:logs:operate', 1000031, '0,1000000,1000030,1000031', 'sys/logs/operate/detail', 0, 1, 2, 'OperateLogDetail', '', 'sys/logs/operate-log-detail', '', 0, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `version` smallint NOT NULL DEFAULT '0',
  `type` smallint NOT NULL COMMENT '业务类型',
  `description` varchar(128) NOT NULL COMMENT '描述',
  `ip` varchar(128) DEFAULT NULL COMMENT 'IP',
  `parameter` mediumtext COMMENT '参数',
  `return_body` mediumtext COMMENT '返回数据',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态: 0-成功 1-异常',
  `error_msg` mediumtext COMMENT '错误信息',
  `location` varchar(128) DEFAULT NULL COMMENT '位置',
  `method` varchar(256) DEFAULT NULL COMMENT '方法',
  `request_method` varchar(256) DEFAULT NULL COMMENT '请求方法',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operate_user` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `operate_user_id` bigint DEFAULT NULL COMMENT '操作人ID',
  `time` int DEFAULT NULL COMMENT '耗时',
  `request_url` varchar(255) DEFAULT NULL COMMENT '请求链接',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_operation_log` VALUES (1, 1000000, '2020-09-12 21:54:27', NULL, NULL, 0, 0, 0, '', NULL, '[{\"username\":\"admin\",\"password\":\"admin\"}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"token\":\"290b7caf-68d1-48a5-8794-ce32f9eb15bd\"},\"success\":false}', NULL, NULL, NULL, 'login', NULL, '2020-09-12 21:54:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (2, 1000000, '2020-09-12 21:56:15', NULL, NULL, 0, 0, 0, '', NULL, '[{\"username\":\"admin\",\"password\":\"admin\"}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"token\":\"c0180da4-ac6e-438d-872e-33b73d1df208\"},\"success\":false}', 0, NULL, NULL, 'login', NULL, '2020-09-12 21:56:14', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (3, 1000000, '2020-09-12 22:35:24', NULL, NULL, 0, 0, 0, '', '127.0.0.1', '[{\"username\":\"admin\",\"password\":\"admin\"}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"token\":\"a593cbe6-1262-4aab-91b8-c9beb70dfcac\"},\"success\":false}', 0, NULL, NULL, 'login', NULL, '2020-09-12 22:35:23', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (4, 1000000, '2020-09-13 13:36:42', NULL, NULL, 0, 0, 0, '', '127.0.0.1', '[{\"username\":\"admin\",\"password\":\"admin\"}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"token\":\"0c3431ef-e3c0-421f-a313-14807eea6d68\"},\"success\":false}', 0, NULL, NULL, 'login', NULL, '2020-09-13 13:36:41', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (5, 1000000, '2020-09-13 13:37:06', NULL, NULL, 0, 0, 2, '更新部门', '127.0.0.1', '[{\"id\":1000001,\"name\":\"上海分公司\",\"code\":\"SH0001\",\"pid\":1000000,\"phone\":\"11212\",\"email\":\"\",\"orderNum\":1,\"status\":0,\"remark\":\"rrr\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 13:37:06', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (6, 1000000, '2020-09-13 13:55:23', NULL, NULL, 0, 0, 1, '保存部门', '127.0.0.1', '[{\"name\":\"iT\",\"code\":\"\",\"pid\":1000001,\"phone\":\"\",\"email\":\"\",\"orderNum\":1,\"status\":0,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 13:55:23', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (7, 1000000, '2020-09-13 13:55:30', NULL, NULL, 0, 0, 3, '删除部门', '127.0.0.1', '[{\"id\":1000003}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"name\":\"iT\",\"code\":\"\",\"pid\":1000001,\"pids\":\"0,1000000,1000001\",\"phone\":\"\",\"email\":\"\",\"orderNum\":1,\"status\":0,\"remark\":\"\",\"id\":1000003,\"createBy\":1000000,\"createTime\":{\"date\":{\"year\":2020,\"month\":9,\"day\":13},\"time\":{\"hour\":13,\"minute\":55,\"second\":23,\"nano\":0}},\"isDelete\":false,\"version\":0},\"success\":false}', 0, NULL, NULL, 'remove', NULL, '2020-09-13 13:55:30', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (8, 1000000, '2020-09-13 14:13:16', NULL, NULL, 0, 0, 1, '保存菜单', '127.0.0.1', '[{\"title\":\"日志管理\",\"code\":\"\",\"pid\":1000000,\"path\":\"/sys/log\",\"icon\":\"\",\"type\":1,\"status\":0,\"hidden\":false,\"routeName\":\"SystemLog\",\"redirect\":\"\",\"component\":\"RouteView\",\"hideChildrenInMenu\":false,\"orderNum\":6}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:13:16', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (9, 1000000, '2020-09-13 14:14:24', NULL, NULL, 0, 0, 1, '保存菜单', '127.0.0.1', '[{\"title\":\"操作日志\",\"code\":\"sys:log:operate\",\"pid\":1000030,\"path\":\"/sys/log/operate\",\"icon\":\"none\",\"type\":2,\"status\":0,\"hidden\":false,\"routeName\":\"OperateLog\",\"redirect\":\"\",\"component\":\"sys/log/operate-list\",\"hideChildrenInMenu\":false,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:14:24', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (10, 1000000, '2020-09-13 14:14:43', NULL, NULL, 0, 0, 2, '更新角色', '127.0.0.1', '[{\"id\":1000000,\"name\":\"超级管理员\",\"code\":\"admin\",\"status\":0,\"remark\":\"用户所有权限\",\"menuIds\":[1000000,1000012,1000017,1000007,1000001,1000014,1000013,1000015,1000016,1000018,1000019,1000020,1000021,1000008,1000009,1000010,1000011,1000002,1000003,1000004,1000006,1000023,1000024,1000025,1000026,1000027,1000028,1000029,1000030,1000031]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 14:14:43', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (11, 1000000, '2020-09-13 14:26:03', NULL, NULL, 0, 0, 2, '更新菜单', '127.0.0.1', '[{\"id\":1000031,\"title\":\"操作日志\",\"code\":\"sys:log:operate\",\"pid\":1000030,\"path\":\"/sys/log/operate\",\"icon\":\"none\",\"type\":2,\"status\":0,\"hidden\":false,\"routeName\":\"OperateLog\",\"redirect\":\"\",\"component\":\"sys/log/operate-log-list\",\"hideChildrenInMenu\":false,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 14:26:03', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (12, 1000000, '2020-09-13 14:46:57', NULL, NULL, 0, 0, 1, '保存字典', '127.0.0.1', '[{\"name\":\"日志操作类型\",\"type\":\"log_operate_type\",\"status\":0,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:46:57', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (13, 1000000, '2020-09-13 14:48:57', NULL, NULL, 0, 0, 2, '更新字典', '127.0.0.1', '[{\"id\":10003,\"name\":\"日志操作类型\",\"type\":\"log_operate_type\",\"status\":0,\"remark\":\"日志操作类型\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 14:48:57', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (14, 1000000, '2020-09-13 14:49:55', NULL, NULL, 0, 0, 1, '保存数据', '127.0.0.1', '[{\"title\":\"保存\",\"value\":\"1\",\"dictType\":\"log_operate_type\",\"status\":0,\"orderNum\":1,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:49:55', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (15, 1000000, '2020-09-13 14:50:05', NULL, NULL, 0, 0, 1, '保存数据', '127.0.0.1', '[{\"title\":\"更新\",\"value\":\"2\",\"dictType\":\"log_operate_type\",\"status\":0,\"orderNum\":1,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:50:05', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (16, 1000000, '2020-09-13 14:50:10', NULL, NULL, 0, 0, 2, '更新数据', '127.0.0.1', '[{\"id\":10014,\"title\":\"更新\",\"value\":\"2\",\"dictType\":\"log_operate_type\",\"status\":0,\"orderNum\":2,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 14:50:10', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (17, 1000000, '2020-09-13 14:50:19', NULL, NULL, 0, 0, 1, '保存数据', '127.0.0.1', '[{\"title\":\"删除\",\"value\":\"3\",\"dictType\":\"log_operate_type\",\"status\":0,\"orderNum\":3,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'save', NULL, '2020-09-13 14:50:19', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (18, 0, '2020-09-13 15:26:53', NULL, NULL, 0, 0, 0, '', '101.224.133.70', '[{\"username\":\"admin\",\"password\":\"admin\"}]', '{\"code\":1003,\"message\":\"用户或密码错误\",\"success\":false}', 0, NULL, NULL, 'login', NULL, '2020-09-13 15:26:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (19, 1000000, '2020-09-13 15:27:02', NULL, NULL, 0, 0, 0, '', '101.224.133.70', '[{\"username\":\"admin\",\"password\":\"admin654321\"}]', '{\"code\":0,\"message\":\"成功\",\"data\":{\"token\":\"91540def-d131-4357-8d54-f9928a5d6281\"},\"success\":false}', 0, NULL, NULL, 'login', NULL, '2020-09-13 15:27:01', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (20, 1000000, '2020-09-13 15:28:34', NULL, NULL, 0, 0, 2, '更新角色', '101.224.133.70', '[{\"id\":1000000,\"name\":\"超级管理员\",\"code\":\"admin\",\"status\":0,\"remark\":\"用户所有权限\",\"menuIds\":[1000000,1000012,1000017,1000007,1000001,1000014,1000013,1000015,1000016,1000018,1000019,1000020,1000021,1000008,1000009,1000010,1000011,1000002,1000003,1000004,1000006,1000023,1000024,1000025,1000026,1000027,1000028,1000029,1000030,1000031]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-13 15:28:33', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (21, 1000000, '2020-09-14 15:16:35', NULL, NULL, 0, 0, 2, '更新菜单', '127.0.0.1', '[{\"id\":1000031,\"title\":\"操作日志\",\"code\":\"sys:log:operate\",\"pid\":1000030,\"path\":\"/sys/logs/operate\",\"icon\":\"\",\"type\":2,\"status\":0,\"hidden\":false,\"routeName\":\"OperateLog\",\"redirect\":\"\",\"component\":\"sys/log/operate-log-list\",\"hideChildrenInMenu\":false,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-14 15:16:35', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (22, 1000000, '2020-09-14 15:17:22', NULL, NULL, 0, 0, 2, '更新菜单', '127.0.0.1', '[{\"id\":1000031,\"title\":\"操作日志\",\"code\":\"sys:log:operate\",\"pid\":1000030,\"path\":\"/sys/logs/operate\",\"icon\":\"\",\"type\":2,\"status\":0,\"hidden\":false,\"routeName\":\"OperateLog\",\"redirect\":\"\",\"component\":\"sys/logs/operate-log-list\",\"hideChildrenInMenu\":false,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', NULL, '2020-09-14 15:17:22', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (23, 1000000, '2020-09-14 15:23:27', NULL, NULL, 0, 0, 2, '更新用户', '127.0.0.1', '[{\"id\":1000003,\"name\":\"test2\",\"avatar\":\"\",\"gender\":1,\"mobile\":\"13333333334\",\"email\":\"33@oo.com\",\"state\":5,\"roleIds\":[1000000],\"deptIds\":[1000001]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'update', 'POST', '2020-09-14 15:23:27', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (24, 1000000, '2020-09-14 16:02:30', NULL, NULL, 0, 0, 2, '更新用户', '127.0.0.1', '[{\"id\":1000003,\"name\":\"test2\",\"avatar\":\"\",\"gender\":1,\"mobile\":\"13333333334\",\"email\":\"33@oo.com\",\"state\":5,\"roleIds\":[1000000],\"deptIds\":[1000001]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.UserController.update', 'POST', '2020-09-14 16:02:30', '超级管理员', 1000000, NULL, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (25, 1000000, '2020-09-14 16:12:40', NULL, NULL, 0, 0, 2, '更新字典', '127.0.0.1', '[{\"id\":10001,\"name\":\"性别\",\"type\":\"gender_type\",\"status\":0,\"remark\":\"性别字典\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.DictController.update', 'POST', '2020-09-14 16:12:40', '超级管理员', 1000000, 70, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (26, 1000000, '2020-09-14 16:16:11', NULL, NULL, 0, 0, 2, '更新菜单', '127.0.0.1', '[{\"id\":1000031,\"title\":\"操作日志\",\"code\":\"sys:log:operate\",\"pid\":1000030,\"path\":\"/sys/logs/operate\",\"icon\":\"\",\"type\":1,\"status\":0,\"hidden\":false,\"routeName\":\"OperateLog\",\"redirect\":\"/sys/logs/operate/list\",\"component\":\"RouteView\",\"hideChildrenInMenu\":true,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.MenuController.update', 'POST', '2020-09-14 16:16:11', '超级管理员', 1000000, 67, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (27, 1000000, '2020-09-14 16:17:11', NULL, NULL, 0, 0, 1, '保存菜单', '127.0.0.1', '[{\"title\":\"日志列表\",\"code\":\"sys:logs:operate\",\"pid\":1000031,\"path\":\"/sys/logs/operate/list\",\"icon\":\"\",\"type\":2,\"status\":0,\"hidden\":true,\"routeName\":\"OperateLogList\",\"redirect\":\"\",\"component\":\"sys/logs/operate-log-list\",\"hideChildrenInMenu\":false,\"orderNum\":1}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.MenuController.save', 'POST', '2020-09-14 16:17:11', '超级管理员', 1000000, 74, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (28, 1000000, '2020-09-14 16:17:22', NULL, NULL, 0, 0, 2, '更新角色', '127.0.0.1', '[{\"id\":1000000,\"name\":\"超级管理员\",\"code\":\"admin\",\"status\":0,\"remark\":\"用户所有权限\",\"menuIds\":[1000000,1000012,1000017,1000007,1000001,1000014,1000013,1000015,1000016,1000018,1000019,1000020,1000021,1000008,1000009,1000010,1000011,1000002,1000003,1000004,1000006,1000023,1000024,1000025,1000026,1000027,1000028,1000029,1000030,1000031,1000032]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.RoleController.update', 'POST', '2020-09-14 16:17:22', '超级管理员', 1000000, 76, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (29, 1000000, '2020-09-14 16:18:35', NULL, NULL, 0, 0, 1, '保存菜单', '127.0.0.1', '[{\"title\":\"操作日志详细\",\"code\":\"sys:logs:operate\",\"pid\":1000031,\"path\":\"sys/logs/operate/detail\",\"icon\":\"\",\"type\":2,\"status\":0,\"hidden\":true,\"routeName\":\"OperateLogDetail\",\"redirect\":\"\",\"component\":\"sys/logs/operate-log-detail\",\"hideChildrenInMenu\":false,\"orderNum\":2}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.MenuController.save', 'POST', '2020-09-14 16:18:35', '超级管理员', 1000000, 23, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (30, 1000000, '2020-09-14 16:18:43', NULL, NULL, 0, 0, 2, '更新角色', '127.0.0.1', '[{\"id\":1000000,\"name\":\"超级管理员\",\"code\":\"admin\",\"status\":0,\"remark\":\"用户所有权限\",\"menuIds\":[1000000,1000012,1000017,1000007,1000001,1000014,1000013,1000015,1000016,1000018,1000019,1000020,1000021,1000008,1000009,1000010,1000011,1000002,1000003,1000004,1000006,1000023,1000024,1000025,1000026,1000027,1000028,1000029,1000030,1000031,1000032,1000033]}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.RoleController.update', 'POST', '2020-09-14 16:18:43', '超级管理员', 1000000, 36, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (31, 1000000, '2020-09-14 17:03:00', NULL, NULL, 0, 0, 2, '更新部门', '127.0.0.1', '[{\"id\":1000000,\"name\":\"总公司\",\"code\":\"\",\"pid\":0,\"phone\":\"\",\"email\":\"\",\"orderNum\":1,\"status\":0,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":false}', 0, NULL, NULL, 'com.hu4java.web.system.controller.DeptController.update', 'POST', '2020-09-14 17:03:00', '超级管理员', 1000000, 89, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (32, 1000000, '2020-09-14 17:15:11', NULL, NULL, 0, 0, 2, '更新字典', '127.0.0.1', '[{\"id\":10001,\"name\":\"性别\",\"type\":\"gender_type\",\"status\":0,\"remark\":\"性别字典\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":true}', 0, NULL, NULL, 'com.hu4java.web.system.controller.DictController.update', 'POST', '2020-09-14 17:15:11', '超级管理员', 1000000, 267, NULL, NULL);
INSERT INTO `sys_operation_log` VALUES (33, 1000000, '2020-09-14 17:16:41', NULL, NULL, 0, 0, 2, '更新字典', '127.0.0.1', '[{\"id\":10002,\"name\":\"人事状态\",\"type\":\"personnel_status\",\"status\":0,\"remark\":\"人事状态\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":true}', 0, 'dddd', NULL, 'com.hu4java.web.system.controller.DictController.update', 'POST', '2020-09-14 17:16:41', '超级管理员', 1000000, 219, '/admin/dict/update', NULL);
INSERT INTO `sys_operation_log` VALUES (34, 1000000, '2020-09-14 17:52:36', NULL, NULL, 0, 0, 2, '更新数据', '127.0.0.1', '[{\"id\":10000,\"title\":\"未知\",\"value\":\"0\",\"dictType\":\"gender_type\",\"status\":0,\"orderNum\":1,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":true}', 0, NULL, NULL, 'com.hu4java.web.system.controller.DictItemController.update', 'POST', '2020-09-14 17:52:36', '超级管理员', 1000000, 126, '/admin/dict/item/update', 'Chrome 8 85.0.4183.102');
INSERT INTO `sys_operation_log` VALUES (35, 1000000, '2020-09-14 17:53:24', NULL, NULL, 0, 0, 2, '更新数据', '127.0.0.1', '[{\"id\":10011,\"title\":\"主动离职\",\"value\":\"8\",\"dictType\":\"personnel_status\",\"status\":0,\"orderNum\":9,\"remark\":\"\"}]', '{\"code\":0,\"message\":\"成功\",\"success\":true}', 0, NULL, NULL, 'com.hu4java.web.system.controller.DictItemController.update', 'POST', '2020-09-14 17:53:24', '超级管理员', 1000000, 242, '/admin/dict/item/update', 'Chrome 8/85.0.4183.102');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0-未删除 1-删除',
  `version` smallint DEFAULT NULL COMMENT '版本号',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `code` varchar(50) NOT NULL COMMENT '角色唯一代码',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-正常 1-禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1000004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1000000, 0, '2020-08-23 21:01:15', 1000000, '2020-09-14 16:18:43', 0, 0, '超级管理员', 'admin', 0, '用户所有权限');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint DEFAULT NULL COMMENT '版本号',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (171, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000000);
INSERT INTO `sys_role_menu` VALUES (172, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000012);
INSERT INTO `sys_role_menu` VALUES (173, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000017);
INSERT INTO `sys_role_menu` VALUES (174, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000007);
INSERT INTO `sys_role_menu` VALUES (175, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000001);
INSERT INTO `sys_role_menu` VALUES (176, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000014);
INSERT INTO `sys_role_menu` VALUES (177, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000013);
INSERT INTO `sys_role_menu` VALUES (178, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000015);
INSERT INTO `sys_role_menu` VALUES (179, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000016);
INSERT INTO `sys_role_menu` VALUES (180, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000018);
INSERT INTO `sys_role_menu` VALUES (181, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000019);
INSERT INTO `sys_role_menu` VALUES (182, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000020);
INSERT INTO `sys_role_menu` VALUES (183, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000021);
INSERT INTO `sys_role_menu` VALUES (184, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000008);
INSERT INTO `sys_role_menu` VALUES (185, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000009);
INSERT INTO `sys_role_menu` VALUES (186, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000010);
INSERT INTO `sys_role_menu` VALUES (187, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000011);
INSERT INTO `sys_role_menu` VALUES (188, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000002);
INSERT INTO `sys_role_menu` VALUES (189, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000003);
INSERT INTO `sys_role_menu` VALUES (190, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000004);
INSERT INTO `sys_role_menu` VALUES (191, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000006);
INSERT INTO `sys_role_menu` VALUES (192, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000023);
INSERT INTO `sys_role_menu` VALUES (193, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000024);
INSERT INTO `sys_role_menu` VALUES (194, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000025);
INSERT INTO `sys_role_menu` VALUES (195, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000026);
INSERT INTO `sys_role_menu` VALUES (196, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000027);
INSERT INTO `sys_role_menu` VALUES (197, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000028);
INSERT INTO `sys_role_menu` VALUES (198, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000029);
INSERT INTO `sys_role_menu` VALUES (199, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000030);
INSERT INTO `sys_role_menu` VALUES (200, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000031);
INSERT INTO `sys_role_menu` VALUES (201, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000032);
INSERT INTO `sys_role_menu` VALUES (202, 1000000, '2020-09-14 16:18:43', NULL, NULL, NULL, NULL, 1000000, 1000033);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint DEFAULT '0' COMMENT '版本号',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `salt` varchar(16) NOT NULL COMMENT '加密盐',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `code` varchar(50) DEFAULT NULL COMMENT '员工编号',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别：0-未知 1-男 2-女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-正常 1-禁用',
  `state` tinyint(1) DEFAULT '0' COMMENT '人事状态',
  `contract_expire_date` date DEFAULT NULL COMMENT '合同到期日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1000004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1000000, 0, '2020-08-20 23:47:07', 1000000, '2020-09-12 10:11:00', 0, 0, 'admin', 'BKYDzlmBdNrQFaaGYRmkWNo5ye1PlsvN6aj65uEcIWA=', 'afwLPuiKBK3QB26J', '18888888888', 'admin@admin.com', '超级管理员', NULL, NULL, 1, '1980-01-01', 0, 5, '2599-12-31');
INSERT INTO `sys_user` VALUES (1000001, 1000000, '2020-09-08 20:04:27', NULL, NULL, 0, 0, 'test', 'SM2Q2jxtafJulx4Rv1AS5grG9/R5YKFXcoz+CMW9WGo=', 'rDbkDQDGDBjZoI1C', '13333333333', '33@com.com', '测试', NULL, '', 1, '2020-09-08', 0, 5, NULL);
INSERT INTO `sys_user` VALUES (1000003, 1000000, '2020-09-08 20:16:54', 1000000, '2020-09-14 16:02:30', 0, 0, 'test2', 'T2RrhTN6fhovrouWzDVHmSnKmN94oAzKNSwz4ZU4KYU=', 'Vl1HsqlNiDB5Hslo', '13333333334', '33@oo.com', 'test2', NULL, '', 1, NULL, 0, 5, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint DEFAULT NULL COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户部门关联表';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_dept` VALUES (5, 1000000, '2020-09-05 11:54:55', NULL, NULL, NULL, NULL, 1000000, 1000000);
INSERT INTO `sys_user_dept` VALUES (6, 1000000, '2020-09-08 20:04:27', NULL, NULL, NULL, NULL, 1000001, 1000001);
INSERT INTO `sys_user_dept` VALUES (11, 1000000, '2020-09-14 16:02:30', NULL, NULL, NULL, NULL, 1000003, 1000001);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint DEFAULT '0' COMMENT '版本号',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (6, 1000000, '2020-09-05 11:54:55', NULL, NULL, 0, 0, 1000000, 1000000);
INSERT INTO `sys_user_role` VALUES (7, 1000000, '2020-09-08 20:04:27', NULL, NULL, 0, 0, 1000001, 1000000);
INSERT INTO `sys_user_role` VALUES (12, 1000000, '2020-09-14 16:02:30', NULL, NULL, 0, 0, 1000003, 1000000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
