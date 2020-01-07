CREATE DATABASE `jd_homework3-4`;
USE `jd_homework3-4`;
CREATE TABLE car
  (
      id        INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
      name      VARCHAR(60) NOT NULL,
      car_model VARCHAR(60) NOT NULL,
      engine_capacity   INT(10) NOT NULL DEFAULT TRUE
  );