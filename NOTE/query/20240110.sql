use user;

CREATE TABLE `user`
(
`id` bigint(32) NOT NULL AUTO_INCREMENT comment 'index',
`name` varchar(50) NOT NULL comment '사용자이름',
`age` int NULL DEFAULT '1' comment '사용자나이',
`email` varchar(100) NULL DEFAULT '' comment '이메일주소',
PRIMARY KEY (`id`)
);

select * from user;
select * from book;

-- user 테이블에 삽입.
INSERT INTO `user`
(
	`name`,
	`age`,
	`email`
)
VALUES
(
	'홍길동',
	10,
	'hong@gmail.com'
);

SELECT * FROM `user`;

INSERT INTO `user`
(
	`name`
)
VALUES
(
	'이순신'
);

INSERT INTO `user`
(
	`name`
)
VALUES
(
	'유관순'
);

INSERT INTO `user`
(
	`name`
)
VALUES
(
	'강감찬'
);

SELECT * FROM `user`;


-- Update
UPDATE `user`
SET age = 20
WHERE id > 0 AND name = '유관순';

UPDATE `user`
SET 
	age = 20, email = 'kang@gmail.com'
WHERE id > 0 AND name = '강감찬';

SELECT * FROM `user`;

-- Delete




























