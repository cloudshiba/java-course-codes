DROP TABLE IF EXISTS `speedkill`;
CREATE TABLE `speedkill` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(120) NOT NULL COMMENT '秒殺商品',
    `number` int NOT NULL COMMENT '庫存數量',
    `start_time` timestamp NOT NULL COMMENT '秒殺開始時間',
    `end_time` timestamp NOT NULL COMMENT '秒殺結束時間',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
    PRIMARY KEY (`id`),
    KEY `idx_start_time` (`start_time`),
    KEY `idx_end_time` (`end_time`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒殺庫存表';

INSERT INTO `speedkill`(`id`, `name`, `number`, `start_time`, `end_time`, `create_time`)
VALUES (1000, 'iphone12 128g 黑色', 1000000, '2021-10-01 16:00:00', '2020-10-21 16:00:00', now());

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
     `no` varchar(120) NOT NULL COMMENT '訂單編號',
     `product_id` bigint NOT NULL COMMENT '秒殺商品 id',
     `status` int(4) NOT NULL COMMENT '秒殺狀態',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
     PRIMARY KEY (`id`),
     UNIQUE KEY `idx_no` (`no`),
     KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='訂單表';
