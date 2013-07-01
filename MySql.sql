-- 
-- TABLE: failure_mode_handlings 
--

CREATE TABLE failure_mode_handlings(
    id                 BIGINT          AUTO_INCREMENT,
    name               VARCHAR(255)    NOT NULL,
    failure_mode_id    BIGINT          NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_histories 
--

CREATE TABLE machine_histories(
    id                          BIGINT    AUTO_INCREMENT,
    date                        DATE      NOT NULL,
    machine_id                  BIGINT    NOT NULL,
    failure_mode_handling_id    BIGINT    NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_parts 
--

CREATE TABLE machine_parts(
    id      BIGINT          AUTO_INCREMENT,
    name    VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_totalizers 
--

CREATE TABLE machine_totalizers(
    id            BIGINT          AUTO_INCREMENT,
    name          VARCHAR(255)    NOT NULL,
    total         BIT(1)          NOT NULL,
    machine_id    BIGINT          NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: part_failure_modes 
--

CREATE TABLE part_failure_modes(
    id         BIGINT          AUTO_INCREMENT,
    name       VARCHAR(255)    NOT NULL,
    part_id    BIGINT          NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: spbu_machines 
--

CREATE TABLE spbu_machines(
    id         BIGINT          AUTO_INCREMENT,
    name       VARCHAR(255)    NOT NULL,
    spbu_id    BIGINT          NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: spbus 
--

CREATE TABLE spbus(
    id         BIGINT          AUTO_INCREMENT,
    code       VARCHAR(20)     NOT NULL,
    address    VARCHAR(255)    NOT NULL,
    phone      VARCHAR(20)     NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- INDEX: Ref77 
--

CREATE INDEX Ref77 ON failure_mode_handlings(failure_mode_id)
;
-- 
-- INDEX: Ref25 
--

CREATE INDEX Ref25 ON machine_histories(machine_id)
;
-- 
-- INDEX: Ref88 
--

CREATE INDEX Ref88 ON machine_histories(failure_mode_handling_id)
;
-- 
-- INDEX: Ref29 
--

CREATE INDEX Ref29 ON machine_totalizers(machine_id)
;
-- 
-- INDEX: Ref54 
--

CREATE INDEX Ref54 ON part_failure_modes(part_id)
;
-- 
-- INDEX: Ref13 
--

CREATE INDEX Ref13 ON spbu_machines(spbu_id)
;
-- 
-- INDEX: spbu_code_unique 
--

CREATE UNIQUE INDEX spbu_code_unique ON spbus(code)
;
-- 
-- TABLE: failure_mode_handlings 
--

ALTER TABLE failure_mode_handlings ADD CONSTRAINT Refpart_failure_modes7 
    FOREIGN KEY (failure_mode_id)
    REFERENCES part_failure_modes(id)
;


-- 
-- TABLE: machine_histories 
--

ALTER TABLE machine_histories ADD CONSTRAINT Refspbu_machines5 
    FOREIGN KEY (machine_id)
    REFERENCES spbu_machines(id)
;

ALTER TABLE machine_histories ADD CONSTRAINT Reffailure_mode_handlings8 
    FOREIGN KEY (failure_mode_handling_id)
    REFERENCES failure_mode_handlings(id)
;


-- 
-- TABLE: machine_totalizers 
--

ALTER TABLE machine_totalizers ADD CONSTRAINT Refspbu_machines9 
    FOREIGN KEY (machine_id)
    REFERENCES spbu_machines(id)
;


-- 
-- TABLE: part_failure_modes 
--

ALTER TABLE part_failure_modes ADD CONSTRAINT Refmachine_parts4 
    FOREIGN KEY (part_id)
    REFERENCES machine_parts(id)
;


-- 
-- TABLE: spbu_machines 
--

ALTER TABLE spbu_machines ADD CONSTRAINT Refspbus3 
    FOREIGN KEY (spbu_id)
    REFERENCES spbus(id)
;


