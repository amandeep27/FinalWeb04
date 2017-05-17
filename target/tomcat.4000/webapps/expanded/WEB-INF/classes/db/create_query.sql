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
  PRIMARY KEY (`l_id`),
  UNIQUE INDEX `l_type_UNIQUE` (`l_type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`ul_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`ul_info` (
  `ul_id` INT UNSIGNED NOT NULL,
  `ul_name` VARCHAR(80) NOT NULL,
  `ul_pwd` BINARY(40) NOT NULL,
  `ul_role` VARCHAR(45) NOT NULL,
  `ul_lang_l_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ul_id`),
  UNIQUE INDEX `ul_id_UNIQUE` (`ul_id` ASC),
  INDEX `fk_ul_info_ul_lang_idx` (`ul_lang_l_id` ASC),
  CONSTRAINT `fk_ul_info_ul_lang`
    FOREIGN KEY (`ul_lang_l_id`)
    REFERENCES `user_login`.`ul_lang` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_type_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_type_en` (
  `bt_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bt_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bt_id`),
  UNIQUE INDEX `bt_name_UNIQUE` (`bt_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_author_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_author_en` (
  `ba_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ba_name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`ba_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_author_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_author_be` (
  `ba_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ba_name` VARCHAR(80) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`ba_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_type_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_type_be` (
  `bt_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bt_name` VARCHAR(80) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`bt_id`),
  UNIQUE INDEX `bt_name_UNIQUE` (`bt_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_en` (
  `bi_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bi_title` VARCHAR(80) NOT NULL,
  `bi_desc` VARCHAR(200) NOT NULL,
  `bi_publish_year` YEAR NOT NULL,
  PRIMARY KEY (`bi_id`),
  UNIQUE INDEX `bi_title_UNIQUE` (`bi_title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_be` (
  `bi_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bi_title` VARCHAR(80) CHARACTER SET 'utf8' NOT NULL,
  `bi_desc` VARCHAR(200) CHARACTER SET 'utf8' NOT NULL,
  `bi_publish_year` YEAR NOT NULL,
  PRIMARY KEY (`bi_id`),
  UNIQUE INDEX `bi_title_UNIQUE` (`bi_title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_type_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_type_en` (
  `bi_id` INT UNSIGNED NOT NULL,
  `bt_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`bi_id`, `bt_id`),
  INDEX `fk_book_info_type_en_book_type_en1_idx` (`bt_id` ASC),
  CONSTRAINT `fk_book_info_type_en_book_info_en1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_en` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_info_type_en_book_type_en1`
    FOREIGN KEY (`bt_id`)
    REFERENCES `user_login`.`book_type_en` (`bt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_lang_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_lang_en` (
  `bi_id` INT UNSIGNED NOT NULL,
  `l_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`bi_id`, `l_id`),
  INDEX `fk_book_info_lang_en_ul_lang1_idx` (`l_id` ASC),
  CONSTRAINT `fk_book_info_lang_en_book_info_en1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_en` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_info_lang_en_ul_lang1`
    FOREIGN KEY (`l_id`)
    REFERENCES `user_login`.`ul_lang` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_author_en`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_author_en` (
  `bi_id` INT UNSIGNED NOT NULL,
  `ba_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`bi_id`, `ba_id`),
  INDEX `fk_book_info_author_en_book_author_en1_idx` (`ba_id` ASC),
  CONSTRAINT `fk_book_info_author_en_book_info_en1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_en` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_info_author_en_book_author_en1`
    FOREIGN KEY (`ba_id`)
    REFERENCES `user_login`.`book_author_en` (`ba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_author_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_author_be` (
  `bi_id` INT UNSIGNED NOT NULL,
  `ba_id` INT UNSIGNED NOT NULL,
  INDEX `fk_table4_book_info_be1_idx` (`bi_id` ASC),
  INDEX `fk_table4_book_author_be1_idx` (`ba_id` ASC),
  PRIMARY KEY (`bi_id`, `ba_id`),
  CONSTRAINT `fk_table4_book_info_be1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_be` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table4_book_author_be1`
    FOREIGN KEY (`ba_id`)
    REFERENCES `user_login`.`book_author_be` (`ba_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_lang_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_lang_be` (
  `l_id` INT UNSIGNED NOT NULL,
  `bi_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`l_id`, `bi_id`),
  INDEX `fk_book_info_lang_be_book_info_be1_idx` (`bi_id` ASC),
  CONSTRAINT `fk_book_info_lang_be_ul_lang1`
    FOREIGN KEY (`l_id`)
    REFERENCES `user_login`.`ul_lang` (`l_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_info_lang_be_book_info_be1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_be` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`book_info_type_be`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`book_info_type_be` (
  `bi_id` INT UNSIGNED NOT NULL,
  `bt_id` INT UNSIGNED NOT NULL,
  INDEX `fk_table6_book_info_be1_idx` (`bi_id` ASC),
  INDEX `fk_table6_book_type_be1_idx` (`bt_id` ASC),
  PRIMARY KEY (`bi_id`, `bt_id`),
  CONSTRAINT `fk_table6_book_info_be1`
    FOREIGN KEY (`bi_id`)
    REFERENCES `user_login`.`book_info_be` (`bi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table6_book_type_be1`
    FOREIGN KEY (`bt_id`)
    REFERENCES `user_login`.`book_type_be` (`bt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_login`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_login`.`table1` (
  `id` INT NOT NULL,
  `dfe` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
