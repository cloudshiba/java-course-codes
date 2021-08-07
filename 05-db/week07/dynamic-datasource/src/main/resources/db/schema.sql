SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `ec_orders`;
CREATE TABLE `ec_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `total_price` decimal(13,2) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `ec_order_items`;
CREATE TABLE `ec_order_items` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `price` decimal(13,2) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `ec_products`;
CREATE TABLE `ec_products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `ec_users`;
CREATE TABLE `ec_users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_digest` varchar(255) NOT NULL COMMENT '前端用 SHA 256 Hash 處理後再送出',
  `access_token` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `pubsub_token` varchar(255) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `reset_password_sent_at` bigint(20) NOT NULL DEFAULT '0',
  `remember_created_at` bigint(20) NOT NULL DEFAULT '0',
  `sign_in_count` int(11) DEFAULT NULL,
  `current_sign_in_at` bigint(20) NOT NULL DEFAULT '0',
  `last_sign_in_at` bigint(20) NOT NULL DEFAULT '0',
  `current_sign_in_ip` varchar(255) DEFAULT NULL,
  `last_sign_in_ip` varchar(255) DEFAULT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `confirmed_at` bigint(20) NOT NULL DEFAULT '0',
  `confirmation_sent_at` bigint(20) NOT NULL DEFAULT '0',
  `unconfirmed_email` varchar(255) DEFAULT NULL,
  `login_success_count` int(11) DEFAULT NULL,
  `login_fail_count` int(11) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `access_token` (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
