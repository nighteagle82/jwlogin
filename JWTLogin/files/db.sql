CREATE TABLE `member` (
      `id` bigint(21) NOT NULL AUTO_INCREMENT,
      `mid` varchar(50) NOT NULL,
      `name` varchar(30) NOT NULL,
      `password` varchar(32) DEFAULT NULL,
      `locked` int(2) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 2F54AFE6B69AEB84202A08C6D993C03B     --> 123456
INSERT INTO `member`(`id`, `mid`, `name`, `password`, `locked`) VALUES (1, 'admin', '管理员', '2F54AFE6B69AEB84202A08C6D993C03B', 0);
INSERT INTO `member`(`id`, `mid`, `name`, `password`, `locked`) VALUES (2, 'ne', '普通用户', '2F54AFE6B69AEB84202A08C6D993C03B', 0);
INSERT INTO `member`(`id`, `mid`, `name`, `password`, `locked`) VALUES (3, 'mermaid', '访客', '2F54AFE6B69AEB84202A08C6D993C03B', 1);




CREATE TABLE `action` (
      `id` bigint(21) NOT NULL AUTO_INCREMENT,
      `actid` varchar(50) NOT NULL,
      `title` varchar(200) NOT NULL,
      `rid` varchar(50) NOT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (1, 'member:add', '创建用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (2, 'member:edit', '编缉用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (3, 'member:delete', '删除用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (4, 'member:list', '查看用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (5, 'dept:add', '创建部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (6, 'dept:edit', '编缉部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (7, 'dept:delete', '删除部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (8, 'dept:list', '查看部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (9, 'goods:add', '商品添加', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (10, 'goods:edit', '商品修改', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (11, 'goods:delete', '商品删除', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (12, 'goods:list', '商品列表', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (13, 'goods:item', '商品分类', 'goods');



CREATE TABLE `role` (
    `id` bigint(21) NOT NULL AUTO_INCREMENT,
    `rid` varchar(50) NOT NULL,
    `title` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role`(`id`, `rid`, `title`) VALUES (1, 'member', '用户管理');
INSERT INTO `role`(`id`, `rid`, `title`) VALUES (2, 'dept', '部门管理');
INSERT INTO `role`(`id`, `rid`, `title`) VALUES (3, 'news', '新闻管理');



CREATE TABLE `action` (
      `id` bigint(21) NOT NULL AUTO_INCREMENT,
      `actid` varchar(50) NOT NULL,
      `title` varchar(200) NOT NULL,
      `rid` varchar(50) NOT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (1, 'member:add', '创建用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (2, 'member:edit', '编缉用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (3, 'member:delete', '删除用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (4, 'member:list', '查看用户', 'member');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (5, 'dept:add', '创建部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (6, 'dept:edit', '编缉部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (7, 'dept:delete', '删除部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (8, 'dept:list', '查看部门', 'dept');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (9, 'goods:add', '商品添加', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (10, 'goods:edit', '商品修改', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (11, 'goods:delete', '商品删除', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (12, 'goods:list', '商品列表', 'goods');
INSERT INTO `action`(`id`, `actid`, `title`, `rid`) VALUES (13, 'goods:item', '商品分类', 'goods');



CREATE TABLE `member_role` (
   `id` bigint(21) NOT NULL AUTO_INCREMENT,
   `mid` varchar(50) NOT NULL,
   `rid` varchar(50) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


INSERT INTO `member_role`(`id`, `mid`, `rid`) VALUES (1, 'admin', 'member');
INSERT INTO `member_role`(`id`, `mid`, `rid`) VALUES (2, 'admin', 'dept');
INSERT INTO `member_role`(`id`, `mid`, `rid`) VALUES (3, 'admin', 'goods');
INSERT INTO `member_role`(`id`, `mid`, `rid`) VALUES (4, 'ne', 'goods');
INSERT INTO `member_role`(`id`, `mid`, `rid`) VALUES (5, 'mermaid', 'dept');
