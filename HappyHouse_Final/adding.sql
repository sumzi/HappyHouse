CREATE TABLE `happyhousepj`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `ndate` DATETIME NOT NULL,
  `userId` VARCHAR(45) NOT NULL,
  `type` SET('notice', 'qna') NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `board_to_userinfo_fk_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `board_to_userinfo_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `happyhousepj`.`userinfo` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `happyhousepj`.`notice` 
DROP COLUMN `userName`,
DROP COLUMN `ndate`,
DROP COLUMN `content`,
DROP COLUMN `title`,
ADD COLUMN `likeCount` INT NULL AFTER `no`,
ADD COLUMN `isReply` CHAR(1) NULL DEFAULT 'F' AFTER `likeCount`,
CHANGE COLUMN `no` `no` INT NOT NULL ;
ALTER TABLE `happyhousepj`.`notice` 
ADD CONSTRAINT `notice_to_board_fk`
  FOREIGN KEY (`no`)
  REFERENCES `happyhousepj`.`board` (`no`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `happyhousepj`.`notice` 
ADD CONSTRAINT `notice_to_board_fk`
  FOREIGN KEY (`no`)
  REFERENCES `happyhousepj`.`board` (`no`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `happyhousepj`.`qna` (
  `no` INT NOT NULL,
  `isReply` CHAR(1) NULL DEFAULT 'F',
  `qUserId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `qna_to_userinfo_fk_idx` (`qUserId` ASC) VISIBLE,
  CONSTRAINT `qna_to_userinfo_fk`
    FOREIGN KEY (`qUserId`)
    REFERENCES `happyhousepj`.`board` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `happyhousepj`.`qna` 
DROP FOREIGN KEY `qna_to_userinfo_fk`;
ALTER TABLE `happyhousepj`.`qna` 
ADD CONSTRAINT `qna_to_userinfo_fk`
  FOREIGN KEY (`qUserId`)
  REFERENCES `happyhousepj`.`board` (`userId`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `happyhousepj`.`notice` 
DROP FOREIGN KEY `notice_to_board_fk`;
ALTER TABLE `happyhousepj`.`notice` 
ADD CONSTRAINT `notice_to_board_fk`
  FOREIGN KEY (`no`)
  REFERENCES `happyhousepj`.`board` (`no`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `happyhousepj`.`qna` 
DROP FOREIGN KEY `qna_to_userinfo_fk`;
ALTER TABLE `happyhousepj`.`qna` 
ADD INDEX `qna_to_userinfo_fk_idx` (`qUserId` ASC) VISIBLE,
DROP INDEX `qna_to_userinfo_fk_idx` ;
;
ALTER TABLE `happyhousepj`.`qna` 
ADD CONSTRAINT `qna_to_userinfo_fk`
  FOREIGN KEY (`qUserId`)
  REFERENCES `happyhousepj`.`userinfo` (`userId`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `qna_to_board_fk`
  FOREIGN KEY (`no`)
  REFERENCES `happyhousepj`.`board` (`no`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `happyhousepj`.`qna` 
DROP FOREIGN KEY `qna_to_userinfo_fk`;
ALTER TABLE `happyhousepj`.`qna` 
DROP COLUMN `qUserId`,
DROP COLUMN `isReply`,
ADD COLUMN `reply` TEXT NULL AFTER `no`,
DROP INDEX `qna_to_userinfo_fk_idx` ;

