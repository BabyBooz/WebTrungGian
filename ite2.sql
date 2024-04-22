create database ite2;
use ite2;

CREATE TABLE `Account` (
    id           INT AUTO_INCREMENT,
    `user`         VARCHAR(255),
    email        VARCHAR(255),
    phone        INT,
    describes     VARCHAR(255),
    `role`         int default 0,
    `password`     VARCHAR(255),
    token varchar(255),
    expiration_time timestamp,
    `status` int,
    `wallet` float default 0,
    `created_date` datetime DEFAULT current_timestamp(),
    `updated_date` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`),
	UNIQUE KEY `user` (`user`),
	UNIQUE KEY `email` (`email`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE TABLE `anouncement` (
--   `id` int NOT NULL AUTO_INCREMENT,
--   `start_date` datetime DEFAULT NULL,
--   `end_date` datetime DEFAULT NULL,
--   `description` varchar(255),
--   `status` int DEFAULT NULL,
--   `created_by` int NOT NULL,  
--   PRIMARY KEY (`id`),
--   FOREIGN KEY (`created_by`) REFERENCES `account` (`role`)  -- Khóa ngoại với bảng `account`
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` int ,
  `name` varchar(255) ,
  `image` text ,
  `price` float,
  `status_fee` int,
  `description` varchar(255) ,
  `contact` varchar(255) ,
  `hidden_content` varchar(255),
  `status_product` int, 
  `status_checking` varchar(255),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`created_by`) REFERENCES `account` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `order_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  `buyer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` varchar(255) ,
  `contact` varchar(255) ,
  `hidden_content` varchar(255) ,
  `created_date` datetime ,
  `updated_date` datetime ,
  FOREIGN KEY (`seller_id`) REFERENCES `account` (`id`),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE `recharge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `transid` varchar(255) ,
  `price` float DEFAULT NULL,
  `description` varchar(255) ,
  `status_transaction` int DEFAULT NULL,
  `created_date` datetime DEFAULT current_timestamp(),
  `updated_date` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`userid`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `withdraw` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `bank_name` varchar(255) ,
  `bank_branch` varchar(255) ,
  `account_number` varchar(255),
  `account_name` varchar(255),
  `price` float DEFAULT NULL,
  `status_transaction` int DEFAULT NULL,
  `created_date` datetime DEFAULT current_timestamp(),
  `updated_date` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`userid`) references `account`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `payment_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `withdraw_request_id` int,
  `price` float,
  `bank_name` varchar(255),
  `bank_branch` varchar(255),
  `status_transaction` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`userid`) REFERENCES `account` (`id`),
  foreign key (`withdraw_request_id`) references `withdraw` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


