
-- store_user 테이블
-- master@gmail.com / 1234

CREATE TABLE IF NOT EXISTS `store_user` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `store_id` BIGINT(32) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `last_login_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_store_id` (`store_id` ASC) VISIBLE)
ENGINE = InnoDB;

SELECT * FROM store_user;
























