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
  `status_checking` int DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`created_by`) REFERENCES `account` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `order_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT 0 comment "status_checking",
  `seller_id` int DEFAULT NULL,
  `buyer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` varchar(255) ,
  `contact` varchar(255) ,
  `hidden_content` varchar(255) ,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`seller_id`) REFERENCES `account` (`id`),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `statusHistory` (
  `id` int not null auto_increment,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `statusChecking` int,
  `createdBy` int,
  `productId` int,
  primary key(id),
  FOREIGN KEY (`createdBy`) references `account` (`id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `myCart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_fee` int DEFAULT NULL,
  `status_checking` int,
  `seller_id` int DEFAULT NULL,
  `buyer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` varchar(255) ,
  `contact` varchar(255) ,
  `hidden_content` varchar(255) ,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`seller_id`) REFERENCES `account` (`id`),
  PRIMARY KEY (`id`)
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


alter table order_history add is_complain bool;
alter table order_history add request_complain text;
alter table order_history add response_complain text;
alter table order_history add is_payback bool;
alter table mycart add order_history_id integer
