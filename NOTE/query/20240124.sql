USE delivery;

-- user_order 테이블
CREATE TABLE IF NOT EXISTS `delivery`.`user_order` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(32) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `amount` DECIMAL(11,4) NOT NULL,
  `ordered_at` DATETIME NULL,
  `accepted_at` DATETIME NULL,
  `cooking_started_at` DATETIME NULL,
  `delivery_started_at` DATETIME NULL,
  `received_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id` ASC) VISIBLE
)
ENGINE = InnoDB;

SELECT * FROM user_order;
-- truncate TABLE user_order; 

-- 테스트용 데이터
INSERT INTO `user_order` (`user_id`, `status`, `amount`) VALUES ('1', 'REGISTERED', '8000');



-- user_order_menu 테이블
-- user_order 테이블과 store_menu 테이블 매핑용
CREATE TABLE IF NOT EXISTS `user_order_menu` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `user_order_id` BIGINT(32) NOT NULL,
  `store_menu_id` BIGINT(32) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_order_id` (`user_order_id` ASC) VISIBLE,
  INDEX `idx_store_menu_id` (`store_menu_id` ASC) VISIBLE
)
ENGINE = InnoDB;

SELECT * FROM user_order_menu;


-- 주문내역 확인
SELECT * FROM user_order WHERE user_id=1;
SELECT * FROM user_order_menu WHERE user_order_id = 1;
SELECT * FROM store_menu WHERE id IN (1,2);

SELECT 
	u.name, sm.name, uo.amount
FROM USER AS u
	JOIN user_order AS uo ON u.id = uo.user_id
	JOIN user_order_menu AS uom ON uom.user_order_id = uo.id
	JOIN store_menu AS sm ON sm.id = uom.store_menu_id
WHERE u.id = 1;


SELECT * FROM store_menu;




















