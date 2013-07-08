
-- 
-- TABLE: `failure_mode_handlings` 
--

CREATE TABLE `failure_mode_handlings`(
    `id`               BIGINT          AUTO_INCREMENT,
    `name`             VARCHAR(255)    NOT NULL,
    `failure_mode_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_part_totalizers` 
--

CREATE TABLE `machine_model_part_totalizers`(
    `machine_model_id`      BIGINT    NOT NULL,
    `machine_part_id`       BIGINT    NOT NULL,
    `machine_totalizer_id`  BIGINT    NOT NULL,
    PRIMARY KEY (`machine_model_id`, `machine_part_id`, `machine_totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_parts` 
--

CREATE TABLE `machine_model_parts`(
    `machine_part_id`   BIGINT           NOT NULL,
    `machine_model_id`  BIGINT           NOT NULL,
    `mttf`              DOUBLE(18, 0)    NOT NULL,
    `mttf_threshold`    DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`machine_part_id`, `machine_model_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_model_totalizers` 
--

CREATE TABLE `machine_model_totalizers`(
    `machine_model_id`      BIGINT    NOT NULL,
    `machine_totalizer_id`  BIGINT    NOT NULL,
    PRIMARY KEY (`machine_model_id`, `machine_totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_models` 
--

CREATE TABLE `machine_models`(
    `id`    BIGINT          AUTO_INCREMENT,
    `code`  VARCHAR(20)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_parts` 
--

CREATE TABLE `machine_parts`(
    `id`    BIGINT          AUTO_INCREMENT,
    `code`  VARCHAR(20)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `machine_totalizers` 
--

CREATE TABLE `machine_totalizers`(
    `id`    BIGINT         AUTO_INCREMENT,
    `name`  VARCHAR(20)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `part_failure_modes` 
--

CREATE TABLE `part_failure_modes`(
    `id`       BIGINT          AUTO_INCREMENT,
    `name`     VARCHAR(255)    NOT NULL,
    `part_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `service_report_spbu_machine_totalizers` 
--

CREATE TABLE `service_report_spbu_machine_totalizers`(
    `spbu_id`               BIGINT           NOT NULL,
    `machine_identifier`    VARCHAR(20)      NOT NULL,
    `machine_totalizer_id`  BIGINT           NOT NULL,
    `service_report_id`     BIGINT           NOT NULL,
    `counter`               DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `machine_identifier`, `machine_totalizer_id`, `service_report_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `service_reports` 
--

CREATE TABLE `service_reports`(
    `id`                        BIGINT         AUTO_INCREMENT,
    `date`                      DATE           NOT NULL,
    `spbu_id`                   BIGINT         NOT NULL,
    `machine_identifier`        VARCHAR(20)    NOT NULL,
    `failure_mode_handling_id`  BIGINT         NOT NULL,
    `technician_id`             BIGINT         NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbu_machine_totalizers` 
--

CREATE TABLE `spbu_machine_totalizers`(
    `spbu_id`               BIGINT           NOT NULL,
    `machine_identifier`    VARCHAR(20)      NOT NULL,
    `machine_totalizer_id`  BIGINT           NOT NULL,
    `alias`                 CHAR(20)         NOT NULL,
    `counter`               DOUBLE(18, 0)    NOT NULL,
    `mttf`                  DOUBLE(18, 0)    NOT NULL,
    `mttf_threshold`        DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `machine_identifier`, `machine_totalizer_id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbu_machines` 
--

CREATE TABLE `spbu_machines`(
    `spbu_id`             BIGINT         NOT NULL,
    `machine_identifier`  VARCHAR(20)    NOT NULL,
    `machine_model_id`    BIGINT         NOT NULL,
    PRIMARY KEY (`spbu_id`, `machine_identifier`)
)ENGINE=INNODB
;



-- 
-- TABLE: `spbus` 
--

CREATE TABLE `spbus`(
    `id`             BIGINT          AUTO_INCREMENT,
    `address`        VARCHAR(255)    NOT NULL,
    `code`           VARCHAR(20)     NOT NULL,
    `phone`          VARCHAR(20)     NOT NULL,
    `supervisor_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- TABLE: `users` 
--

CREATE TABLE `users`(
    `id`             BIGINT          AUTO_INCREMENT,
    `access_role`    INT             NOT NULL,
    `full_name`      VARCHAR(255)    NOT NULL,
    `login_id`       VARCHAR(255)    NOT NULL,
    `password_hash`  VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



-- 
-- INDEX: `FK_failure_mode_handlings_failure_mode_id` 
--

CREATE INDEX `FK_failure_mode_handlings_failure_mode_id` ON `failure_mode_handlings`(`failure_mode_id`)
;
-- 
-- INDEX: `FK_machine_model_part_totalizers_machine_model_id` 
--

CREATE INDEX `FK_machine_model_part_totalizers_machine_model_id` ON `machine_model_part_totalizers`(`machine_part_id`, `machine_model_id`)
;
-- 
-- INDEX: `FK_machine_model_parts_machine_part_id` 
--

CREATE INDEX `FK_machine_model_parts_machine_part_id` ON `machine_model_parts`(`machine_part_id`)
;
-- 
-- INDEX: `FK_machine_model_parts_machine_model_id` 
--

CREATE INDEX `FK_machine_model_parts_machine_model_id` ON `machine_model_parts`(`machine_model_id`)
;
-- 
-- INDEX: `FK_machine_model_totalizers_machine_totalizer_id` 
--

CREATE INDEX `FK_machine_model_totalizers_machine_totalizer_id` ON `machine_model_totalizers`(`machine_totalizer_id`)
;
-- 
-- INDEX: `FK_machine_model_totalizers_machine_model_id` 
--

CREATE INDEX `FK_machine_model_totalizers_machine_model_id` ON `machine_model_totalizers`(`machine_model_id`)
;
-- 
-- INDEX: `UNQ_machine_models_0` 
--

CREATE UNIQUE INDEX `UNQ_machine_models_0` ON `machine_models`(`code`)
;
-- 
-- INDEX: `UNQ_machine_parts_0` 
--

CREATE UNIQUE INDEX `UNQ_machine_parts_0` ON `machine_parts`(`code`)
;
-- 
-- INDEX: `FK_part_failure_modes_part_id` 
--

CREATE INDEX `FK_part_failure_modes_part_id` ON `part_failure_modes`(`part_id`)
;
-- 
-- INDEX: `servicereportspbumachinetotalizersservicereport_id` 
--

CREATE INDEX `servicereportspbumachinetotalizersservicereport_id` ON `service_report_spbu_machine_totalizers`(`service_report_id`)
;
-- 
-- INDEX: `srvicereportspbumachinetotalizersmchinetotalizerid` 
--

CREATE INDEX `srvicereportspbumachinetotalizersmchinetotalizerid` ON `service_report_spbu_machine_totalizers`(`machine_totalizer_id`, `spbu_id`, `machine_identifier`)
;
-- 
-- INDEX: `FK_service_reports_failure_mode_handling_id` 
--

CREATE INDEX `FK_service_reports_failure_mode_handling_id` ON `service_reports`(`failure_mode_handling_id`)
;
-- 
-- INDEX: `FK_service_reports_machine_identifier` 
--

CREATE INDEX `FK_service_reports_machine_identifier` ON `service_reports`(`machine_identifier`, `spbu_id`)
;
-- 
-- INDEX: `FK_service_reports_technician_id` 
--

CREATE INDEX `FK_service_reports_technician_id` ON `service_reports`(`technician_id`)
;
-- 
-- INDEX: `FK_spbu_machine_totalizers_machine_totalizer_id` 
--

CREATE INDEX `FK_spbu_machine_totalizers_machine_totalizer_id` ON `spbu_machine_totalizers`(`machine_totalizer_id`)
;
-- 
-- INDEX: `FK_spbu_machine_totalizers_machine_identifier` 
--

CREATE INDEX `FK_spbu_machine_totalizers_machine_identifier` ON `spbu_machine_totalizers`(`spbu_id`, `machine_identifier`)
;
-- 
-- INDEX: `FK_spbu_machines_machine_model_id` 
--

CREATE INDEX `FK_spbu_machines_machine_model_id` ON `spbu_machines`(`machine_model_id`)
;
-- 
-- INDEX: `FK_spbu_machines_spbu_id` 
--

CREATE INDEX `FK_spbu_machines_spbu_id` ON `spbu_machines`(`spbu_id`)
;
-- 
-- INDEX: `UNQ_spbus_0` 
--

CREATE UNIQUE INDEX `UNQ_spbus_0` ON `spbus`(`code`)
;
-- 
-- INDEX: `FK_spbus_supervisor_id` 
--

CREATE INDEX `FK_spbus_supervisor_id` ON `spbus`(`supervisor_id`)
;
-- 
-- INDEX: `UNQ_users_0` 
--

CREATE UNIQUE INDEX `UNQ_users_0` ON `users`(`login_id`)
;
-- 
-- TABLE: `failure_mode_handlings` 
--

ALTER TABLE `failure_mode_handlings` ADD 
    FOREIGN KEY (`failure_mode_id`)
    REFERENCES `part_failure_modes`(`id`)
;


-- 
-- TABLE: `machine_model_part_totalizers` 
--

ALTER TABLE `machine_model_part_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`, `machine_part_id`)
    REFERENCES `machine_model_parts`(`machine_part_id`, `machine_model_id`)
;


-- 
-- TABLE: `machine_model_parts` 
--

ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;

ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`machine_part_id`)
    REFERENCES `machine_parts`(`id`)
;


-- 
-- TABLE: `machine_model_totalizers` 
--

ALTER TABLE `machine_model_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;

ALTER TABLE `machine_model_totalizers` ADD 
    FOREIGN KEY (`machine_totalizer_id`)
    REFERENCES `machine_totalizers`(`id`)
;


-- 
-- TABLE: `part_failure_modes` 
--

ALTER TABLE `part_failure_modes` ADD 
    FOREIGN KEY (`part_id`)
    REFERENCES `machine_parts`(`id`)
;


-- 
-- TABLE: `service_report_spbu_machine_totalizers` 
--

ALTER TABLE `service_report_spbu_machine_totalizers` ADD 
    FOREIGN KEY (`service_report_id`)
    REFERENCES `service_reports`(`id`)
;

ALTER TABLE `service_report_spbu_machine_totalizers` ADD 
    FOREIGN KEY (`spbu_id`, `machine_identifier`, `machine_totalizer_id`)
    REFERENCES `spbu_machine_totalizers`(`spbu_id`, `machine_identifier`, `machine_totalizer_id`)
;


-- 
-- TABLE: `service_reports` 
--

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`failure_mode_handling_id`)
    REFERENCES `failure_mode_handlings`(`id`)
;

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`spbu_id`, `machine_identifier`)
    REFERENCES `spbu_machines`(`spbu_id`, `machine_identifier`)
;

ALTER TABLE `service_reports` ADD 
    FOREIGN KEY (`technician_id`)
    REFERENCES `users`(`id`)
;


-- 
-- TABLE: `spbu_machine_totalizers` 
--

ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`spbu_id`, `machine_identifier`)
    REFERENCES `spbu_machines`(`spbu_id`, `machine_identifier`)
;

ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`machine_totalizer_id`)
    REFERENCES `machine_totalizers`(`id`)
;


-- 
-- TABLE: `spbu_machines` 
--

ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;

ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`spbu_id`)
    REFERENCES `spbus`(`id`)
;


-- 
-- TABLE: `spbus` 
--

ALTER TABLE `spbus` ADD 
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `users`(`id`)
;


