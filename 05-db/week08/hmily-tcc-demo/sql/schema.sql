-- 建立 bank1 database 與 account_info table
CREATE DATABASE IF NOT EXISTS `bank1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `bank1`;

CREATE TABLE `account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '户主姓名',
  `account_balance` double DEFAULT NULL COMMENT '帐户余额',
  `frozen_balance` double DEFAULT NULL COMMENT '冻结金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

insert into account_info values (1,'zs',10000,0);

-- 建立 bank2 database 與 account_info table
CREATE DATABASE IF NOT EXISTS `bank2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `bank2`;

CREATE TABLE `account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '户主姓名',
  `account_balance` double DEFAULT NULL COMMENT '帐户余额',
  `frozen_balance` double DEFAULT NULL COMMENT '冻结金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

insert into account_info values (1,'ls',10000,0);