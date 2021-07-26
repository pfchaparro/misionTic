-- Generated: 2021-07-25 18:32
-- Model: Fintech_MER
-- Version: 1.0
-- Project: Fintech
-- Author: Pablo Chaparro

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `fintech` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `fintech`.`document_type` (
  `code` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `lastname` VARCHAR(60) NOT NULL,
  `document` VARCHAR(12) NOT NULL,
  `type_document_code` INT(11) NOT NULL,
  `phone_number` VARCHAR(12) NOT NULL,
  `birth_date` DATETIME NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `gender` CHAR(1) NULL DEFAULT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `is_active` TINYINT(4) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `last_update` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_document_type_idx` (`type_document_code` ASC) VISIBLE,
  CONSTRAINT `user_document_type`
    FOREIGN KEY (`type_document_code`)
    REFERENCES `fintech`.`document_type` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`wallet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `wallet_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `wallet_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `fintech`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`pocket` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `amount_pocket` INT(11) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `wallet_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `pocket_user_idx` (`wallet_id` ASC) VISIBLE,
  CONSTRAINT `pocket_user`
    FOREIGN KEY (`wallet_id`)
    REFERENCES `fintech`.`wallet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`wallet_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NOT NULL,
  `transaction_type_id` INT(11) NOT NULL,
  `wallet_id` INT(11) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `wallet_log_wallet_idx` (`wallet_id` ASC) VISIBLE,
  INDEX `wallet_transaction_type_idx` (`transaction_type_id` ASC) VISIBLE,
  CONSTRAINT `wallet_log_wallet`
    FOREIGN KEY (`wallet_id`)
    REFERENCES `fintech`.`wallet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `wallet_transaction_type`
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `fintech`.`transaction_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`pocket_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NOT NULL,
  `transaction_type_id` INT(11) NOT NULL,
  `pocket_id` INT(11) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `pocket_log_pocket_idx` (`pocket_id` ASC) VISIBLE,
  INDEX `pocket_log_transaction_type_idx` (`transaction_type_id` ASC) VISIBLE,
  CONSTRAINT `pocket_log_pocket`
    FOREIGN KEY (`pocket_id`)
    REFERENCES `fintech`.`pocket` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pocket_log_transaction_type`
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `fintech`.`transaction_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fintech`.`transaction_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `is_debit` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
