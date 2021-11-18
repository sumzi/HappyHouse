-- 추가한 sql --

CREATE TABLE `userinfo` (
  `userId` varchar(45) NOT NULL,
  `userPw` varchar(45) DEFAULT NULL,
  `userEmail` varchar(45) DEFAULT NULL,
  `userPhone` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `notice` (
  `no` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `ndate` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into notice values ("1","과제화이팅","삽질은 나를 더 강하게 만든다.",now(),"관리자");
INSERT INTO `userinfo` VALUES ("admin","admin","admin@ssafy.com","010-","관리자");