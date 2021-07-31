CREATE TABLE `ec_users` (
  `id` bigint PRIMARY KEY,
  `email` varchar(255) NOT NULL,
  `password_digest` varchar(255) NOT NULL COMMENT '前端用 SHA 256 Hash 處理後再送出',
  `access_token` varchar(255) UNIQUE,
  `user_name` varchar(255),
  `full_name` varchar(255),
  `state` integer,
  `pubsub_token` varchar(255),
  `reset_password_token` varchar(255),
  `reset_password_sent_at` datetime,
  `remember_created_at` datetime,
  `sign_in_count` integer,
  `current_sign_in_at` datetime,
  `last_sign_in_at` datetime,
  `current_sign_in_ip` varchar(255),
  `last_sign_in_ip` varchar(255),
  `confirmation_token` varchar(255),
  `confirmed_at` datetime,
  `confirmation_sent_at` datetime,
  `unconfirmed_email` varchar(255),
  `login_success_count` integer,
  `login_fail_count` integer,
  `create_time` datetime,
  `update_time` datetime
);

CREATE TABLE `ec_products` (
  `id` bigint PRIMARY KEY,
  `name` varchar(255),
  `image_url` varchar(255),
  `price` varchar(255),
  `create_time` datetime,
  `update_time` datetime
);

CREATE TABLE `ec_orders` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `create_time` datetime,
  `update_time` datetime
);

CREATE TABLE `ec_order_items` (
  `id` bigint PRIMARY KEY,
  `order_id` bigint NOT NULL,
  `name` varchar(255),
  `image_url` varchar(255),
  `price` varchar(255),
  `create_time` datetime,
  `update_time` datetime
);
