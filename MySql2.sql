--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Samsung Electronics
-- Project :      database2.DM1
-- Author :       MSCI
--
-- Date Created : Monday, July 08, 2013 19:14:51
-- Target DBMS : MySQL 5.x
--

drop database mtmgmt;
create database mtmgmt;
use mtmgmt;

-- 
-- TABLE: `failure_mode_handlings` 
--

CREATE TABLE `failure_mode_handlings`(
    `part_id`                     VARCHAR(40)     NOT NULL,
    `failure_mode_code`           VARCHAR(40)     NOT NULL,
    `failure_mode_handling_code`  VARCHAR(40)     NOT NULL,
    `name`                        VARCHAR(255)    NOT NULL,
    `description`                 TEXT            NOT NULL,
    PRIMARY KEY (`part_id`, `failure_mode_code`, `failure_mode_handling_code`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_part_totalizers` 
--

CREATE TABLE `machine_model_part_totalizers`(
    `model_id`                       VARCHAR(40)    NOT NULL,
    `part_id`                        VARCHAR(40)    NOT NULL,
    `machine_model_part_identifier`  VARCHAR(40)    NOT NULL,
    `totalizer_id`                   VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`model_id`, `part_id`, `machine_model_part_identifier`, `totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_parts` 
--

CREATE TABLE `machine_model_parts`(
    `model_id`                       VARCHAR(40)    NOT NULL,
    `part_id`                        VARCHAR(40)    NOT NULL,
    `machine_model_part_identifier`  VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`model_id`, `part_id`, `machine_model_part_identifier`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_totalizers` 
--

CREATE TABLE `machine_model_totalizers`(
    `model_id`      VARCHAR(40)    NOT NULL,
    `totalizer_id`  VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`model_id`, `totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_models` 
--

CREATE TABLE `machine_models`(
    `model_id`  VARCHAR(40)     NOT NULL,
    `name`      VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`model_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_part_types` 
--

CREATE TABLE `machine_part_types`(
    `part_id`                 VARCHAR(40)      NOT NULL,
    `name`                    VARCHAR(255)     NOT NULL,
    `default_mttf`            DOUBLE(18, 0)    NOT NULL,
    `default_mttf_threshold`  DOUBLE(18, 0)    NOT NULL,
    `mttf_by_totalizer`       BIT(1)           NOT NULL,
    PRIMARY KEY (`part_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `part_failure_modes` 
--

CREATE TABLE `part_failure_modes`(
    `part_id`            VARCHAR(40)     NOT NULL,
    `failure_mode_code`  VARCHAR(40)     NOT NULL,
    `name`               VARCHAR(255)    NOT NULL,
    `description`        TEXT            NOT NULL,
    PRIMARY KEY (`part_id`, `failure_mode_code`)
)ENGINE=INNODB
;



-- 
-- TABLE: `service_report_spbu_machine_totalizers` 
--

CREATE TABLE `service_report_spbu_machine_totalizers`(
    `service_report_id`  BIGINT           NOT NULL,
    `spbu_id`            BIGINT           NOT NULL,
    `model_id`           VARCHAR(40)      NOT NULL,
    `serial_number`      VARCHAR(40)      NOT NULL,
    `totalizer_id`       VARCHAR(40)      NOT NULL,
    `counter`            DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`service_report_id`, `spbu_id`, `model_id`, `serial_number`, `totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `service_reports` 
--

CREATE TABLE `service_reports`(
    `id`                             BIGINT         AUTO_INCREMENT,
    `spbu_id`                        BIGINT         NOT NULL,
    `model_id`                       VARCHAR(40)    NOT NULL,
    `serial_number`                  VARCHAR(40)    NOT NULL,
    `date`                           DATE           NOT NULL,
    `part_id`                        VARCHAR(40)    NOT NULL,
    `machine_model_part_identifier`  VARCHAR(40)    NOT NULL,
    `failure_mode_code`              VARCHAR(40)    NOT NULL,
    `failure_mode_handling_code`     VARCHAR(40)    NOT NULL,
    `technician_id`                  BIGINT         NOT NULL,
    PRIMARY KEY (`id`, `spbu_id`, `model_id`, `serial_number`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbu_machine_part_mttfs` 
--

CREATE TABLE `spbu_machine_part_mttfs`(
    `spbu_id`                        BIGINT           NOT NULL,
    `model_id`                       VARCHAR(40)      NOT NULL,
    `serial_number`                  VARCHAR(40)      NOT NULL,
    `part_id`                        VARCHAR(40)      NOT NULL,
    `machine_model_part_identifier`  VARCHAR(40)      NOT NULL,
    `mttf`                           DOUBLE(18, 0)    NOT NULL,
    `mttf_threshold`                 DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `model_id`, `serial_number`, `part_id`, `machine_model_part_identifier`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbu_machine_totalizers` 
--

CREATE TABLE `spbu_machine_totalizers`(
    `spbu_id`        BIGINT           NOT NULL,
    `model_id`       VARCHAR(40)      NOT NULL,
    `serial_number`  VARCHAR(40)      NOT NULL,
    `totalizer_id`   VARCHAR(40)      NOT NULL,
    `alias`          VARCHAR(40)      NOT NULL,
    `counter`        DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `model_id`, `serial_number`, `totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbu_machines` 
--

CREATE TABLE `spbu_machines`(
    `spbu_id`             BIGINT         NOT NULL,
    `model_id`            VARCHAR(40)    NOT NULL,
    `serial_number`       VARCHAR(40)    NOT NULL,
    `machine_identifier`  VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `model_id`, `serial_number`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbus` 
--

CREATE TABLE `spbus`(
    `id`             BIGINT          AUTO_INCREMENT,
    `code`           VARCHAR(20)     NOT NULL,
    `address`        VARCHAR(255)    NOT NULL,
    `phone`          VARCHAR(40)     NOT NULL,
    `supervisor_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `users` 
--

CREATE TABLE `users`(
    `id`             BIGINT          AUTO_INCREMENT,
    `full_name`      VARCHAR(40)     NOT NULL,
    `access_role`    INT             NOT NULL,
    `login_id`       VARCHAR(255),
    `password_hash`  VARCHAR(255),
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- INDEX: `Ref1530` 
--

CREATE INDEX `Ref1530` ON `failure_mode_handlings`(`failure_mode_code`, `part_id`)
;
-- 
-- INDEX: `Ref67` 
--

CREATE INDEX `Ref67` ON `machine_model_part_totalizers`(`part_id`, `machine_model_part_identifier`, `model_id`)
;
-- 
-- INDEX: `Ref711` 
--

CREATE INDEX `Ref711` ON `machine_model_part_totalizers`(`totalizer_id`, `model_id`)
;
-- 
-- INDEX: `Ref28` 
--

CREATE INDEX `Ref28` ON `machine_model_parts`(`model_id`)
;
-- 
-- INDEX: `Ref39` 
--

CREATE INDEX `Ref39` ON `machine_model_parts`(`part_id`)
;
-- 
-- INDEX: `Ref24` 
--

CREATE INDEX `Ref24` ON `machine_model_totalizers`(`model_id`)
;
-- 
-- INDEX: `Ref329` 
--

CREATE INDEX `Ref329` ON `part_failure_modes`(`part_id`)
;
-- 
-- INDEX: `Ref1327` 
--

CREATE INDEX `Ref1327` ON `service_report_spbu_machine_totalizers`(`model_id`, `spbu_id`, `service_report_id`, `serial_number`)
;
-- 
-- INDEX: `Ref1128` 
--

CREATE INDEX `Ref1128` ON `service_report_spbu_machine_totalizers`(`spbu_id`, `model_id`, `totalizer_id`, `serial_number`)
;
-- 
-- INDEX: `Ref524` 
--

CREATE INDEX `Ref524` ON `service_reports`(`serial_number`, `model_id`, `spbu_id`)
;
-- 
-- INDEX: `Ref625` 
--

CREATE INDEX `Ref625` ON `service_reports`(`machine_model_part_identifier`, `part_id`, `model_id`)
;
-- 
-- INDEX: `Ref126` 
--

CREATE INDEX `Ref126` ON `service_reports`(`technician_id`)
;
-- 
-- INDEX: `Ref1631` 
--

CREATE INDEX `Ref1631` ON `service_reports`(`part_id`, `failure_mode_handling_code`, `failure_mode_code`)
;
-- 
-- INDEX: `Ref516` 
--

CREATE INDEX `Ref516` ON `spbu_machine_part_mttfs`(`serial_number`, `model_id`, `spbu_id`)
;
-- 
-- INDEX: `Ref617` 
--

CREATE INDEX `Ref617` ON `spbu_machine_part_mttfs`(`machine_model_part_identifier`, `part_id`, `model_id`)
;
-- 
-- INDEX: `Ref518` 
--

CREATE INDEX `Ref518` ON `spbu_machine_totalizers`(`model_id`, `spbu_id`, `serial_number`)
;
-- 
-- INDEX: `Ref719` 
--

CREATE INDEX `Ref719` ON `spbu_machine_totalizers`(`totalizer_id`, `model_id`)
;
-- 
-- INDEX: `Ref412` 
--

CREATE INDEX `Ref412` ON `spbu_machines`(`spbu_id`)
;
-- 
-- INDEX: `Ref213` 
--

CREATE INDEX `Ref213` ON `spbu_machines`(`model_id`)
;
-- 
-- INDEX: `unique_spbus_code` 
--

CREATE UNIQUE INDEX `unique_spbus_code` ON `spbus`(`code`)
;
-- 
-- INDEX: `Ref11` 
--

CREATE INDEX `Ref11` ON `spbus`(`supervisor_id`)
;
-- 
-- INDEX: `unique_users_login_id` 
--

CREATE UNIQUE INDEX `unique_users_login_id` ON `users`(`login_id`)
;
-- 
-- TABLE: `failure_mode_handlings` 
--

ALTER TABLE `failure_mode_handlings` ADD 
    FOREIGN KEY (`part_id`, `failure_mode_code`)
    REFERENCES `part_failure_modes`(`part_id`, `failure_mode_code`)
;


-- 
-- TABLE: `machine_model_part_totalizers` 
--

ALTER TABLE `machine_model_part_totalizers` ADD 
    FOREIGN KEY (`model_id`, `part_id`, `machine_model_part_identifier`)
    REFERENCES `machine_model_parts`(`model_id`, `part_id`, `machine_model_part_identifier`)
;

ALTER TABLE `machine_model_part_totalizers` ADD 
    FOREIGN KEY (`model_id`, `totalizer_id`)
    REFERENCES `machine_model_totalizers`(`model_id`, `totalizer_id`)
;


-- 
-- TABLE: `machine_model_parts` 
--

ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`model_id`)
    REFERENCES `machine_models`(`model_id`)
;

ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`part_id`)
    REFERENCES `machine_part_types`(`part_id`)
;


-- 
-- TABLE: `machine_model_totalizers` 
--

ALTER TABLE `machine_model_totalizers` ADD 
    FOREIGN KEY (`model_id`)
    REFERENCES `machine_models`(`model_id`)
;


-- 
-- TABLE: `part_failure_modes` 
--

ALTER TABLE `part_failure_modes` ADD 
    FOREIGN KEY (`part_id`)
    REFERENCES `machine_part_types`(`part_id`)
;


-- 
-- TABLE: `service_report_spbu_machine_totalizers` 
--

ALTER TABLE `service_report_spbu_machine_totalizers` ADD 
    FOREIGN KEY (`service_report_id`, `spbu_id`, `model_id`, `serial_number`)
    REFERENCES `service_reports`(`id`, `spbu_id`, `model_id`, `serial_number`)
;

ALTER TABLE `service_report_spbu_machine_totalizers` ADD 
    FOREIGN KEY (`spbu_id`, `model_id`, `serial_number`, `totalizer_id`)
    REFERENCES `spbu_machine_totalizers`(`spbu_id`, `model_id`, `serial_number`, `totalizer_id`)
;


-- 
-- TABLE: `service_reports` 
--

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`spbu_id`, `model_id`, `serial_number`)
    REFERENCES `spbu_machines`(`spbu_id`, `model_id`, `serial_number`)
;

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`model_id`, `part_id`, `machine_model_part_identifier`)
    REFERENCES `machine_model_parts`(`model_id`, `part_id`, `machine_model_part_identifier`)
;

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`technician_id`)
    REFERENCES `users`(`id`)
;

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`part_id`, `failure_mode_code`, `failure_mode_handling_code`)
    REFERENCES `failure_mode_handlings`(`part_id`, `failure_mode_code`, `failure_mode_handling_code`)
;


-- 
-- TABLE: `spbu_machine_part_mttfs` 
--

ALTER TABLE `spbu_machine_part_mttfs` ADD 
    FOREIGN KEY (`spbu_id`, `model_id`, `serial_number`)
    REFERENCES `spbu_machines`(`spbu_id`, `model_id`, `serial_number`)
;

ALTER TABLE `spbu_machine_part_mttfs` ADD 
    FOREIGN KEY (`model_id`, `part_id`, `machine_model_part_identifier`)
    REFERENCES `machine_model_parts`(`model_id`, `part_id`, `machine_model_part_identifier`)
;


-- 
-- TABLE: `spbu_machine_totalizers` 
--

ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`spbu_id`, `model_id`, `serial_number`)
    REFERENCES `spbu_machines`(`spbu_id`, `model_id`, `serial_number`)
;

ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`model_id`, `totalizer_id`)
    REFERENCES `machine_model_totalizers`(`model_id`, `totalizer_id`)
;


-- 
-- TABLE: `spbu_machines` 
--

ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`spbu_id`)
    REFERENCES `spbus`(`id`)
;

ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`model_id`)
    REFERENCES `machine_models`(`model_id`)
;


-- 
-- TABLE: `spbus` 
--

ALTER TABLE `spbus` ADD 
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `users`(`id`)
;


