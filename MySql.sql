--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      dodol

CREATE TABLE `failure_mode_handlings`(
    `id`               BIGINT          AUTO_INCREMENT,
    `name`             VARCHAR(255)    NOT NULL,
    `failure_mode_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_model_part_totalizers`(
    `machine_model_id`      BIGINT    NOT NULL,
    `part_id`               BIGINT    NOT NULL,
    `machine_totalizer_id`  BIGINT    NOT NULL,
    PRIMARY KEY (`machine_model_id`, `part_id`, `machine_totalizer_id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_model_parts`(
    `machine_model_id`  BIGINT    NOT NULL,
    `part_id`           BIGINT    NOT NULL,
    PRIMARY KEY (`machine_model_id`, `part_id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_model_totalizers`(
    `machine_model_id`      BIGINT    NOT NULL,
    `machine_totalizer_id`  BIGINT    NOT NULL,
    PRIMARY KEY (`machine_model_id`, `machine_totalizer_id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_models`(
    `id`    BIGINT          NOT NULL,
    `code`  VARCHAR(20)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_parts`(
    `id`    BIGINT          AUTO_INCREMENT,
    `code`  VARCHAR(20)     NOT NULL,
    `name`  VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE TABLE `machine_totalizers`(
    `id`    BIGINT         NOT NULL,
    `name`  VARCHAR(20)    NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE TABLE `part_failure_modes`(
    `id`       BIGINT          AUTO_INCREMENT,
    `name`     VARCHAR(255)    NOT NULL,
    `part_id`  BIGINT          NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE TABLE `spbu_machine_totalizers`(
    `spbu_id`               BIGINT    NOT NULL,
    `machine_model_id`      BIGINT    NOT NULL,
    `machine_totalizer_id`  BIGINT    NOT NULL,
    `counter`               BIGINT    NOT NULL,
    PRIMARY KEY (`spbu_id`, `machine_model_id`, `machine_totalizer_id`)
)ENGINE=INNODB
;



CREATE TABLE `spbu_machines`(
    `spbu_id`           BIGINT          NOT NULL,
    `machine_model_id`  BIGINT          NOT NULL,
    `name`              VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`spbu_id`, `machine_model_id`)
)ENGINE=INNODB
;



CREATE TABLE `spbus`(
    `id`       BIGINT          AUTO_INCREMENT,
    `code`     VARCHAR(20)     NOT NULL,
    `address`  VARCHAR(255)    NOT NULL,
    `phone`    VARCHAR(20)     NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=INNODB
;



CREATE INDEX `Ref77` ON `failure_mode_handlings`(`failure_mode_id`)
;
CREATE INDEX `Ref1318` ON `machine_model_part_totalizers`(`machine_model_id`, `part_id`)
;
CREATE INDEX `Ref1219` ON `machine_model_part_totalizers`(`machine_totalizer_id`, `machine_model_id`)
;
CREATE INDEX `Ref512` ON `machine_model_parts`(`part_id`)
;
CREATE INDEX `Ref1013` ON `machine_model_parts`(`machine_model_id`)
;
CREATE INDEX `Ref1010` ON `machine_model_totalizers`(`machine_model_id`)
;
CREATE INDEX `Ref1924` ON `machine_model_totalizers`(`machine_totalizer_id`)
;
CREATE UNIQUE INDEX `machine_model_code_unique` ON `machine_models`(`code`)
;
CREATE INDEX `Ref54` ON `part_failure_modes`(`part_id`)
;
CREATE INDEX `Ref216` ON `spbu_machine_totalizers`(`machine_model_id`, `spbu_id`)
;
CREATE INDEX `Ref1217` ON `spbu_machine_totalizers`(`machine_totalizer_id`, `machine_model_id`)
;
CREATE INDEX `Ref13` ON `spbu_machines`(`spbu_id`)
;
CREATE INDEX `Ref1015` ON `spbu_machines`(`machine_model_id`)
;
CREATE UNIQUE INDEX `spbu_code_unique` ON `spbus`(`code`)
;
ALTER TABLE `failure_mode_handlings` ADD 
    FOREIGN KEY (`failure_mode_id`)
    REFERENCES `part_failure_modes`(`id`)
;


ALTER TABLE `machine_model_part_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`, `part_id`)
    REFERENCES `machine_model_parts`(`machine_model_id`, `part_id`)
;

ALTER TABLE `machine_model_part_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`, `machine_totalizer_id`)
    REFERENCES `machine_model_totalizers`(`machine_model_id`, `machine_totalizer_id`)
;


ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`part_id`)
    REFERENCES `machine_parts`(`id`)
;

ALTER TABLE `machine_model_parts` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;


ALTER TABLE `machine_model_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;

ALTER TABLE `machine_model_totalizers` ADD 
    FOREIGN KEY (`machine_totalizer_id`)
    REFERENCES `machine_totalizers`(`id`)
;


ALTER TABLE `part_failure_modes` ADD 
    FOREIGN KEY (`part_id`)
    REFERENCES `machine_parts`(`id`)
;


ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`spbu_id`, `machine_model_id`)
    REFERENCES `spbu_machines`(`spbu_id`, `machine_model_id`)
;

ALTER TABLE `spbu_machine_totalizers` ADD 
    FOREIGN KEY (`machine_model_id`, `machine_totalizer_id`)
    REFERENCES `machine_model_totalizers`(`machine_model_id`, `machine_totalizer_id`)
;


ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`spbu_id`)
    REFERENCES `spbus`(`id`)
;

ALTER TABLE `spbu_machines` ADD 
    FOREIGN KEY (`machine_model_id`)
    REFERENCES `machine_models`(`id`)
;


