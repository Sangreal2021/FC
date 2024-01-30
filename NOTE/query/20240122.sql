USE delivery;

SELECT * FROM account;

-- delivery의 user 테이블
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `last_login_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SELECT * FROM USER;


-- delivery의 store 테이블
CREATE TABLE IF NOT EXISTS `delivery`.`store` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `status` VARCHAR(50) NULL,
  `category` VARCHAR(50) NULL,
  `star` DOUBLE NULL DEFAULT 0,
  `thumbnail_url` VARCHAR(200) NOT NULL,
  `minimum_amount` DECIMAL(11,4) NOT NULL,
  `minimum_delivery_amount` DECIMAL(11,4) NOT NULL,
  `phone_number` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SELECT * FROM store;


-- delivery의 store_menu 테이블
CREATE TABLE IF NOT EXISTS `delivery`.`store_menu` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `amount` DECIMAL(11,4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `thumbnail_url` VARCHAR(200) NOT NULL,
  `like_count` INT NULL DEFAULT 0,
  `sequence` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

ALTER TABLE `delivery`.`store_menu` 
ADD INDEX `idx_store_id` (`store_id` ASC) VISIBLE;
;


SELECT * FROM store_menu;

















