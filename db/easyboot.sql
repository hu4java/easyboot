-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.11 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 easyboot 的数据库结构
DROP DATABASE IF EXISTS `easyboot`;
CREATE DATABASE IF NOT EXISTS `easyboot` /*!40100 DEFAULT CHARACTER SET utf8mb4  */;
USE `easyboot`;

-- 导出  表 easyboot.sys_dept 结构
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint(6) DEFAULT '0' COMMENT '版本号',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `code` varchar(50) DEFAULT NULL COMMENT '部门编码',
  `pid` bigint(20) NOT NULL COMMENT '上级id',
  `pids` varchar(100) CHARACTER SET utf8mb4  DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：０-正常 １-禁用',
  `order_num` smallint(6) DEFAULT '1' COMMENT '排序',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000003 DEFAULT CHARSET=utf8mb4  COMMENT='部门表';

-- 正在导出表  easyboot.sys_dept 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `name`, `code`, `pid`, `pids`, `status`, `order_num`, `phone`, `email`, `remark`) VALUES
	(1000000, 1000000, '2020-09-03 17:56:19', NULL, NULL, 0, 0, '总公司', '', 0, '', 0, 1, '', '', ''),
	(1000001, 1000000, '2020-09-03 18:04:49', NULL, NULL, 0, 0, '上海分公司', 'SH0001', 1000000, '', 0, 1, '11212', '', 'rrr');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 easyboot.sys_dict 结构
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint(6) DEFAULT NULL COMMENT '版本号',
  `name` varchar(50) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '字典名称',
  `type` varchar(50) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0-正常 1-禁用',
  `remark` varchar(50) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4  COMMENT='字典表';

-- 正在导出表  easyboot.sys_dict 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;

-- 导出  表 easyboot.sys_dict_item 结构
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE IF NOT EXISTS `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint(6) DEFAULT NULL COMMENT '版本号',
  `title` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '标题',
  `value` varchar(50) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '值',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：0-正常 1-禁用',
  `order_num` smallint(6) DEFAULT '1' COMMENT '排序',
  `remark` varchar(50) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '备注',
  `dict_type` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '关联字典类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dicty_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4  COMMENT='字典数据表';

-- 正在导出表  easyboot.sys_dict_item 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;

-- 导出  表 easyboot.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint(6) DEFAULT '0' COMMENT '版本号',
  `title` varchar(50) NOT NULL COMMENT '菜单标题',
  `code` varchar(50) DEFAULT NULL COMMENT '权限码',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级id',
  `pids` varchar(500) NOT NULL DEFAULT '0' COMMENT '上级id关联字符串',
  `path` varchar(100) DEFAULT NULL COMMENT '菜单路径',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：０-正常 １-禁用',
  `hidden` tinyint(1) DEFAULT '0' COMMENT '是否隐藏',
  `type` tinyint(1) DEFAULT '1' COMMENT '类型：１-目录 ２-菜单 ３-按钮',
  `router_name` varchar(50) DEFAULT NULL COMMENT '路由名',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(100) DEFAULT NULL COMMENT '菜单组件',
  `redirect` varchar(100) DEFAULT NULL COMMENT '重定向',
  `hide_children_in_menu` tinyint(1) DEFAULT '0' COMMENT '强制转换为菜单',
  `order_num` smallint(6) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000023 DEFAULT CHARSET=utf8mb4  COMMENT='菜单表';

-- 正在导出表  easyboot.sys_menu 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `title`, `code`, `pid`, `pids`, `path`, `status`, `hidden`, `type`, `router_name`, `icon`, `component`, `redirect`, `hide_children_in_menu`, `order_num`) VALUES
	(1000000, 0, '2020-09-01 22:39:34', NULL, NULL, 0, 0, '系统管理', 'sys', 0, '0', '/sys', 0, 0, 1, 'System', NULL, 'RouteView', NULL, 0, 0),
	(1000001, 1000000, '2020-09-01 23:15:02', 1000000, '2020-09-02 19:40:05', 0, 0, '菜单管理', 'sys:menu', 1000000, '0,1000000', '/sys/menu', 0, 0, 1, 'Menu', 'user', 'RouteView', NULL, 1, 4),
	(1000002, 1000000, '2020-09-02 14:01:46', 1000000, '2020-09-02 17:48:04', 0, 0, '菜单', 'sys:menu:view', 1000001, '0,1000000,1000001', '/sys/menu/list', 0, 1, 2, 'MenuList', '', 'MenuList', NULL, 0, 1),
	(1000003, 1000000, '2020-09-02 14:05:39', 1000000, '2020-09-02 17:49:41', 0, 0, '新建菜单', 'sys:menu:save', 1000001, '0,1000000,1000001', '/sys/menu/add', 0, 1, 2, 'MenuAdd', '', 'MenuForm', NULL, 0, 2),
	(1000004, 1000000, '2020-09-02 18:01:08', NULL, NULL, 0, 0, '编辑菜单', 'sys:menu:update', 1000001, '0,1000000,1000001', '/sys/menu/edit', 0, 1, 2, 'MenuEdit', '', 'MenuForm', NULL, 0, 3),
	(1000006, 1000000, '2020-09-02 18:41:54', NULL, NULL, 0, 0, '删除菜单', 'sys:menu:remove', 1000001, '0,1000000,1000001', '', 0, 1, 3, '', '', '', NULL, 0, 4),
	(1000007, 1000000, '2020-09-02 19:20:53', 1000000, '2020-09-02 19:39:57', 0, 0, '角色管理', '', 1000000, '0,1000000', '/sys/role', 0, 0, 1, 'Role', '', 'RoleList', NULL, 1, 3),
	(1000008, 1000000, '2020-09-02 19:23:23', 1000000, '2020-09-02 19:29:27', 0, 0, '角色列表', 'sys:role:view', 1000007, '0,1000000,1000007', '/sys/role/list', 0, 1, 2, 'RoleList', '', 'RoleList', NULL, 0, 1),
	(1000009, 1000000, '2020-09-02 19:24:44', 1000000, '2020-09-02 19:28:47', 0, 0, '新建角色', 'sys:role:save', 1000007, '0,1000000,1000007', '/sys/role/add', 0, 1, 2, 'RoleAdd', '', 'RoleForm', NULL, 0, 2),
	(1000010, 1000000, '2020-09-02 19:25:33', 1000000, '2020-09-02 19:28:59', 0, 0, '编辑角色', 'sys:role:update', 1000007, '0,1000000,1000007', '/sys/role/edit', 0, 1, 2, 'RoleEdit', '', 'RoleForm', NULL, 0, 3),
	(1000011, 1000000, '2020-09-02 19:26:02', NULL, NULL, 0, 0, '删除角色', 'sys:role:remove', 1000007, '0,1000000,1000007', '', 0, 1, 3, '', '', '', NULL, 0, 4),
	(1000012, 1000000, '2020-09-02 19:34:41', NULL, NULL, 0, 0, '部门管理', '', 1000000, '0,1000000', '/sys/dept', 0, 0, 1, 'Dept', '', 'RouteView', NULL, 1, 1),
	(1000013, 1000000, '2020-09-02 19:35:19', NULL, NULL, 0, 0, '部门列表', 'sys:dept:view', 1000012, '0,1000000,1000012', '/sys/dept/list', 0, 1, 2, 'DeptList', '', 'DeptList', NULL, 0, 1),
	(1000014, 1000000, '2020-09-02 19:35:57', NULL, NULL, 0, 0, '新建部门', 'sys:dept:save', 1000012, '0,1000000,1000012', '/sys/dept/add', 0, 1, 2, 'DeptAdd', '', 'DeptForm', NULL, 0, 2),
	(1000015, 1000000, '2020-09-02 19:36:27', NULL, NULL, 0, 0, '编辑部门', 'sys:dept:update', 1000012, '0,1000000,1000012', '/sys/dept/edit', 0, 1, 2, 'DeptEdit', '', 'DeptForm', NULL, 0, 3),
	(1000016, 1000000, '2020-09-02 19:36:49', NULL, NULL, 0, 0, '删除部门', 'sys:dept:remove', 1000012, '0,1000000,1000012', '', 0, 1, 3, '', '', '', NULL, 0, 4),
	(1000017, 1000000, '2020-09-02 19:37:26', NULL, NULL, 0, 0, '用户管理', '', 1000000, '0,1000000', '/sys/user', 0, 0, 1, 'User', '', 'RouteView', NULL, 1, 2),
	(1000018, 1000000, '2020-09-02 19:38:07', NULL, NULL, 0, 0, '用户列表', 'sys:user:view', 1000017, '0,1000000,1000017', '/sys/user/list', 0, 1, 2, 'UserList', '', 'UserList', NULL, 0, 1),
	(1000019, 1000000, '2020-09-02 19:38:36', NULL, NULL, 0, 0, '新建用户', 'sys:user:save', 1000017, '0,1000000,1000017', '/sys/user/add', 0, 1, 2, 'UserAdd', '', 'UserForm', NULL, 0, 2),
	(1000020, 1000000, '2020-09-02 19:39:13', NULL, NULL, 0, 0, '编辑用户', 'sys:user:update', 1000017, '0,1000000,1000017', '/sys/user/edit', 0, 1, 2, 'UserEdit', '', 'UserForm', NULL, 0, 3),
	(1000021, 1000000, '2020-09-02 19:39:33', NULL, NULL, 0, 0, '删除用户', 'sys:user:remove', 1000017, '0,1000000,1000017', '', 0, 1, 3, '', '', '', NULL, 0, 4);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 easyboot.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0-未删除 1-删除',
  `version` smallint(6) DEFAULT NULL COMMENT '版本号',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `code` varchar(50) NOT NULL COMMENT '角色唯一代码',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-正常 1-禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1000004 DEFAULT CHARSET=utf8mb4  COMMENT='角色表';

-- 正在导出表  easyboot.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `name`, `code`, `status`, `remark`) VALUES
	(1000000, 0, '2020-08-23 21:01:15', 1000000, '2020-09-03 11:16:32', 0, 0, '超级管理员', 'admin', 0, '用户所有权限');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 easyboot.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint(6) DEFAULT NULL COMMENT '版本号',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4  COMMENT='角色菜单关联表';

-- 正在导出表  easyboot.sys_role_menu 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `role_id`, `menu_id`) VALUES
	(1, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000000),
	(2, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000012),
	(3, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000017),
	(4, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000007),
	(5, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000001),
	(6, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000014),
	(7, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000013),
	(8, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000015),
	(9, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000016),
	(10, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000018),
	(11, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000019),
	(12, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000020),
	(13, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000021),
	(14, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000008),
	(15, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000009),
	(16, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000010),
	(17, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000011),
	(18, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000002),
	(19, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000003),
	(20, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000004),
	(21, 1000000, '2020-09-03 11:16:36', NULL, NULL, NULL, NULL, 1000000, 1000006);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 easyboot.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint(6) DEFAULT '0' COMMENT '版本号',
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
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4  COMMENT='用户表';

-- 正在导出表  easyboot.sys_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `username`, `password`, `salt`, `mobile`, `email`, `name`, `code`, `avatar`, `gender`, `birthday`, `status`, `state`, `contract_expire_date`) VALUES
	(1000000, 0, '2020-08-20 23:47:07', 1000000, '2020-09-05 11:54:55', 0, 0, 'admin', 'q/2Uux49q9eXeebBAxyHqJhrZit6nVCb6hSEYYuqELA=', 'ZzxoGqVXK82Z0fIS', '18888888888', 'admin@admin.com', '超级管理员', NULL, NULL, 1, '1980-01-01', 0, 5, '2599-12-31');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 easyboot.sys_user_dept 结构
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE IF NOT EXISTS `sys_user_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除：０-未删除 １-已删除',
  `version` smallint(6) DEFAULT NULL COMMENT '版本号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4  COMMENT='用户部门关联表';

-- 正在导出表  easyboot.sys_user_dept 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_dept` DISABLE KEYS */;
INSERT INTO `sys_user_dept` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `user_id`, `dept_id`) VALUES
	(5, 1000000, '2020-09-05 11:54:55', NULL, NULL, NULL, NULL, 1000000, 1000000);
/*!40000 ALTER TABLE `sys_user_dept` ENABLE KEYS */;

-- 导出  表 easyboot.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除：0-未删除 1-已删除',
  `version` smallint(6) DEFAULT '0' COMMENT '版本号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4  COMMENT='用户角色关联表';

-- 正在导出表  easyboot.sys_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_delete`, `version`, `user_id`, `role_id`) VALUES
	(6, 1000000, '2020-09-05 11:54:55', NULL, NULL, 0, 0, 1000000, 1000000);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
