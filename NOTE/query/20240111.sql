use user;

SELECT * FROM user;


-- 1. memorydb 프로젝트
-- book_store DB 생성(utf8mb4/utf8mb4_bin)
USE book_store;

CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `amount` decimal(14,0) DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE book_store.`user` (
	id BIGINT(32) auto_increment NOT NULL,
	name varchar(50) NOT NULL,
	score INT DEFAULT 0 NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

SELECT * FROM book;
SELECT * FROM USER;

-- memorydb.user.db 패키지의 UserRepository의 findAllScoreGreaterThanEqual 메소드
SELECT * FROM book_store.`user`
WHERE score >= 90
;

-- 아래는 인텔리제이 콘솔에서 복사한 내용
-- findAllByScoreGreaterThanEqual 메소드
select
    ue1_0.id,
    ue1_0.name,
    ue1_0.score 
from
    user ue1_0 
where
    ue1_0.score>=90
;

-- findAllByScoreGreaterThanEqualAndScoreLessThanEqual 메소드
select
    ue1_0.id,
    ue1_0.name,
    ue1_0.score 
from
    user ue1_0 
where
    ue1_0.score>=90
    and ue1_0.score<=100
;


-- 2. simple_board DB 생성
USE simple_board;

-- -----------------------------------------------------
-- Table `simple_board`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`board` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `board_name` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `simple_board`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`post` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `board_id` BIGINT(32) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(4) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NULL,
  `posted_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `simple_board`.`reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_board`.`reply` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT(32) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `replied_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------

SELECT * FROM board;
SELECT * FROM post;
SELECT * FROM reply;



