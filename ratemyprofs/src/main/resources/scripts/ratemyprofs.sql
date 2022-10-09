-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema ratemyprofs
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ratemyprofs
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ratemyprofs` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `ID_USER` INT NOT NULL AUTO_INCREMENT,
  `USER_FIRST_NAME` VARCHAR(45) NOT NULL,
  `USER_LAST_NAME` VARCHAR(45) NULL,
  `USER_STATUS` VARCHAR(1) NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_USER`))
ENGINE = InnoDB;

USE `ratemyprofs` ;

-- -----------------------------------------------------
-- Table `ratemyprofs`.`INST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`INST` (
  `ID_INST` INT NOT NULL AUTO_INCREMENT,
  `INST_NAME` VARCHAR(128) NOT NULL,
  `INST_STATUS` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID_INST`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`DEPT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`DEPT` (
  `ID_DEPT` INT NOT NULL AUTO_INCREMENT,
  `DEPT_NAME` VARCHAR(128) NOT NULL,
  `ID_INST` INT NOT NULL,
  `DEPT_STATUS` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID_DEPT`),
  INDEX `ID_INSTITUTION_idx` (`ID_INST` ASC) VISIBLE,
  CONSTRAINT `ID_INSTITUTION`
    FOREIGN KEY (`ID_INST`)
    REFERENCES `ratemyprofs`.`INST` (`ID_INST`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`COURSE` (
  `ID_COURSE` INT NOT NULL AUTO_INCREMENT,
  `ID_DEPT` INT NOT NULL,
  `COURSE_STATUS` VARCHAR(1) NOT NULL,
  `COURSE_NAME` VARCHAR(128) NOT NULL,
  `COURSE_CODE` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ID_COURSE`),
  INDEX `ID_DEPARTMENT_idx` (`ID_DEPT` ASC) VISIBLE,
  CONSTRAINT `ID_DEPARTMENT`
    FOREIGN KEY (`ID_DEPT`)
    REFERENCES `ratemyprofs`.`DEPT` (`ID_DEPT`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`PROF`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`PROF` (
  `ID_PROF` INT NOT NULL AUTO_INCREMENT,
  `PROF_FIRST_NAME` VARCHAR(45) NOT NULL,
  `PROF_LAST_NAME` VARCHAR(45) NOT NULL,
  `PROF_STATUS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_PROF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`PROF_COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`PROF_COURSE` (
  `ID_PROF_COURSE` INT NOT NULL AUTO_INCREMENT,
  `COURSE_ID_COURSE` INT NOT NULL,
  `PROF_ID_PROF` INT NOT NULL,
  PRIMARY KEY (`ID_PROF_COURSE`),
  INDEX `fk_PROFESSOR_COURSE_COURSE1_idx` (`COURSE_ID_COURSE` ASC) VISIBLE,
  INDEX `fk_PROFESSOR_COURSE_PROFESSOR1_idx` (`PROF_ID_PROF` ASC) VISIBLE,
  CONSTRAINT `fk_PROFESSOR_COURSE_COURSE1`
    FOREIGN KEY (`COURSE_ID_COURSE`)
    REFERENCES `ratemyprofs`.`COURSE` (`ID_COURSE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROFESSOR_COURSE_PROFESSOR1`
    FOREIGN KEY (`PROF_ID_PROF`)
    REFERENCES `ratemyprofs`.`PROF` (`ID_PROF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`PROF_DEPT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`PROF_DEPT` (
  `ID_PROF_DEPT` INT NOT NULL AUTO_INCREMENT,
  `DEPT_ID_DEPT` INT NOT NULL,
  `PROF_ID_PROF` INT NOT NULL,
  PRIMARY KEY (`ID_PROF_DEPT`),
  INDEX `fk_PROFESSOR_DEPARTMENT_DEPARTMENT1_idx` (`DEPT_ID_DEPT` ASC) VISIBLE,
  INDEX `fk_PROFESSOR_DEPARTMENT_PROFESSOR1_idx` (`PROF_ID_PROF` ASC) VISIBLE,
  CONSTRAINT `fk_PROFESSOR_DEPARTMENT_DEPARTMENT1`
    FOREIGN KEY (`DEPT_ID_DEPT`)
    REFERENCES `ratemyprofs`.`DEPT` (`ID_DEPT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROFESSOR_DEPARTMENT_PROFESSOR1`
    FOREIGN KEY (`PROF_ID_PROF`)
    REFERENCES `ratemyprofs`.`PROF` (`ID_PROF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ratemyprofs`.`RATING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ratemyprofs`.`RATING` (
  `ID_RATING` INT NOT NULL AUTO_INCREMENT,
  `OVERALL_SCORE` INT NOT NULL,
  `DIFFICULTY_LEVEL` INT NOT NULL,
  `WILL_RETAKE` TINYINT NOT NULL,
  `FOR_CREDIT` TINYINT NULL DEFAULT NULL,
  `REQUIRE_TEXTBOOK` TINYINT NULL DEFAULT NULL,
  `REQUIRE_ATTENDANCE` TINYINT NULL DEFAULT NULL,
  `RECEIVED_GRADE` VARCHAR(2) NULL DEFAULT NULL,
  `REVIEW` VARCHAR(350) NOT NULL,
  `PROF_ID_PROF` INT NOT NULL,
  `COURSE_ID_COURSE` INT NULL,
  `DEPT_ID_DEPT` INT NULL,
  `COURSE_CODE` VARCHAR(10) NULL,
  `CREATED` DATETIME NOT NULL,
  `MODIFIED` DATETIME NOT NULL,
  `USER_ID_USER` INT NULL,
  `RATING_STATUS` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID_RATING`),
  INDEX `fk_RATING_PROFESSOR1_idx` (`PROF_ID_PROF` ASC) VISIBLE,
  INDEX `fk_RATING_COURSE1_idx` (`COURSE_ID_COURSE` ASC) VISIBLE,
  INDEX `fk_RATING_DEPARTMENT1_idx` (`DEPT_ID_DEPT` ASC) VISIBLE,
  INDEX `fk_RATING_USER1_idx` (`USER_ID_USER` ASC) VISIBLE,
  CONSTRAINT `fk_RATING_PROFESSOR1`
    FOREIGN KEY (`PROF_ID_PROF`)
    REFERENCES `ratemyprofs`.`PROF` (`ID_PROF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RATING_COURSE1`
    FOREIGN KEY (`COURSE_ID_COURSE`)
    REFERENCES `ratemyprofs`.`COURSE` (`ID_COURSE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RATING_DEPARTMENT1`
    FOREIGN KEY (`DEPT_ID_DEPT`)
    REFERENCES `ratemyprofs`.`DEPT` (`ID_DEPT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RATING_USER1`
    FOREIGN KEY (`USER_ID_USER`)
    REFERENCES `mydb`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
