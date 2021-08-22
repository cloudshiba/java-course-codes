-- account_db
-- 採用 ShardingSphere 分庫的方式
CREATE TABLE `t_cny_account` (
  `id` bigint(20) NOT NULL COMMENT '人民幣帳戶 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '考慮到匯率計算，金额（元）放到 10000 倍',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '創建時間',
  `updated` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

CREATE TABLE `t_cny_freeze` (
  `id` bigint(20) NOT NULL COMMENT '人民幣凍結紀錄 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `account_id` bigint(20) NOT NULL COMMENT '人民幣帳戶 ID',
  `amount` bigint(20) NOT NULL COMMENT '凍結金额，數值为金额（元）放大10000倍',
  `exchange_id` bigint(20) NOT NULL COMMENT '關聯的匯兌交易 ID',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '創建時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci


CREATE TABLE `t_usd_account` (
  `id` bigint(20) NOT NULL COMMENT '美元帳戶 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用戶 ID',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '考慮到匯率計算，金额（元）放到 10000 倍',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '創建時間',
  `updated` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

CREATE TABLE `t_usd_freeze` (
  `id` bigint(20) NOT NULL COMMENT '美元凍結紀錄 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `account_id` bigint(20) NOT NULL COMMENT '美元帳戶 ID',
  `amount` bigint(20) NOT NULL COMMENT '凍結金额，數值为金额（元）放大10000倍',
  `exchange_id` bigint(20) NOT NULL COMMENT '關聯的匯兌交易 ID',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '創建時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

-- exchange_db
-- 僅用一個集中的 Database，不分庫
CREATE TABLE `t_exchange` (
  `id` bigint(20) NOT NULL COMMENT '交易紀錄 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `out_account_id` bigint(20) NOT NULL COMMENT '匯兌轉出的帳戶 ID',
  `out_currency_type` char(4) NOT NULL COMMENT '匯兌轉出的貨幣代號',
  `out_amount` decimal(10,0) NOT NULL COMMENT '匯兌轉出的交易金额',
  `in_account_id` bigint(20) NOT NULL COMMENT '匯兌轉入的帳號 ID',
  `in_currency_type` char(4) NOT NULL COMMENT '匯兌轉入的貨幣代號',
  `in_amount` decimal(10,0) NOT NULL COMMENT '匯兌轉入的交易金額',
  `status` int(11) NOT NULL COMMENT '狀態: 0-創建，1-準備，2-匯兌成功，3-匯兌失敗',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '創建時間',
  `updated` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新時間',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
