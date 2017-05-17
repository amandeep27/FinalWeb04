-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema user_login
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema user_login
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_login` DEFAULT CHARACTER SET utf8 ;
USE `user_login` ;

-- -----------------------------------------------------
-- Table `user_login`.`ul_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`ul_lang` (
  `l_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `l_type` VARCHAR(45) NOT NULL,
  `l_country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`l_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`ul_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`ul_info` (
  `ul_id` INT UNSIGNED NOT NULL,
  `ul_name` VARCHAR(80) NOT NULL,
  `ul_pwd` BINARY(20) NOT NULL,
  `ul_role` VARCHAR(45) NOT NULL,
  `ul_lang_l_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ul_id`),
  UNIQUE INDEX `ul_id_UNIQUE` (`ul_id` ASC),
  UNIQUE INDEX `ul_role_UNIQUE` (`ul_role` ASC),
  INDEX `fk_ul_info_ul_lang_idx` (`ul_lang_l_id` ASC),
  CONSTRAINT `fk_ul_info_ul_lang`
    FOREIGN KEY (`ul_lang_l_id`)
    REFERENCES `user_login`.`ul_lang` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('1', 'hi', 'IN');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('2', 'en', 'US');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('3', 'ko', 'KR');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('4', 'be', 'TY');

ALTER TABLE `user_login`.`ul_lang` 
ADD UNIQUE INDEX `l_type_UNIQUE` (`l_type` ASC);

ALTER TABLE `user_login`.`ul_info` 
CHANGE COLUMN `ul_pwd` `ul_pwd` BINARY(40) NOT NULL ;
