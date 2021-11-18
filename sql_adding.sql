ALTER TABLE `happyhousepj`.`userinfo` 
ADD COLUMN `joinDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `userName`,
ADD COLUMN `role` SET('admin', 'nomal') NOT NULL DEFAULT 'nomal' AFTER `joinDate`;

CREATE TABLE `happyhousepj`.`interestarea` (
  `userId` VARCHAR(45) NOT NULL,
  `dongCode` VARCHAR(45) NOT NULL,
  INDEX `interestarea_to_userinfo_fk_idx` (`userId` ASC) VISIBLE,
  INDEX `interestarea_to_dongcode_fk_idx` (`dongCode` ASC) VISIBLE,
  PRIMARY KEY (`userId`),
  CONSTRAINT `interestarea_to_userinfo_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `happyhousepj`.`userinfo` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `interestarea_to_dongcode_fk`
    FOREIGN KEY (`dongCode`)
    REFERENCES `happyhousepj`.`dongcode` (`dongCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `happyhousepj`.`interesthouse` (
  `userId` VARCHAR(45) NOT NULL,
  `aptCode` INT NOT NULL,
  PRIMARY KEY (`userId`),
  INDEX `interesthouse_to_houseinfo_fk_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `interesthouse_to_houseinfo_fk`
    FOREIGN KEY (`aptCode`)
    REFERENCES `happyhousepj`.`houseinfo` (`aptCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `interesthouse_to_userinfo_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `happyhousepj`.`userinfo` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);