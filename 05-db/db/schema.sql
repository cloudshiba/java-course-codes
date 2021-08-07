CREATE TABLE `ec_users` (
  `id` bigint PRIMARY KEY,
  `email` VARCHAR(255) NOT NULL,
  `password_digest` VARCHAR(255) NOT NULL COMMENT '前端用 SHA 256 Hash 處理後再送出',
  `access_token` VARCHAR(255) UNIQUE,
  `user_name` VARCHAR(255),
  `full_name` VARCHAR(255),
  `state` TINYINT,
  `pubsub_token` VARCHAR(255),
  `reset_password_token` VARCHAR(255),
  `reset_password_sent_at` BIGINT(20) NOT NULL DEFAULT 0,
  `remember_created_at` BIGINT(20) NOT NULL DEFAULT 0,
  `sign_in_count` INT,
  `current_sign_in_at` BIGINT(20) NOT NULL DEFAULT 0,
  `last_sign_in_at` BIGINT(20) NOT NULL DEFAULT 0,
  `current_sign_in_ip` VARCHAR(255),
  `last_sign_in_ip` VARCHAR(255),
  `confirmation_token` VARCHAR(255),
  `confirmed_at` BIGINT(20) NOT NULL DEFAULT 0,
  `confirmation_sent_at` BIGINT(20) NOT NULL DEFAULT 0,
  `unconfirmed_email` VARCHAR(255),
  `login_success_count` INT,
  `login_fail_count` INT,
  `create_time` BIGINT(20) NOT NULL DEFAULT 0,
  `update_time` BIGINT(20) NOT NULL DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ec_products` (
  `id` BIGINT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL,
  `create_time` BIGINT(20) NOT NULL DEFAULT 0,
  `update_time` BIGINT(20) NOT NULL DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ec_orders` (
  `id` BIGINT PRIMARY KEY,
  `user_id` BIGINT NOT NULL,
  `total_price` DECIMAL(13,2) NOT NULL,
  `create_time` BIGINT(20) NOT NULL DEFAULT 0,
  `update_time` BIGINT(20) NOT NULL DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ec_order_items` (
  `id` BIGINT PRIMARY KEY,
  `order_id` BIGINT NOT NULL,
  `name` VARCHAR(255),
  `image_url` VARCHAR(255),
  `price` DECIMAL(13,2) NOT NULL,
  `create_time` BIGINT(20) NOT NULL DEFAULT 0,
  `update_time` BIGINT(20) NOT NULL DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
