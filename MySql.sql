
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
-- TABLE: machine_model_part_totalizers 
--

CREATE TABLE machine_model_part_totalizers(
    machine_model_id        BIGINT    NOT NULL,
    machine_part_id         BIGINT    NOT NULL,
    machine_totalizer_id    BIGINT    NOT NULL,
    PRIMARY KEY (machine_model_id, machine_part_id, machine_totalizer_id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_model_parts 
--

CREATE TABLE machine_model_parts(
    machine_part_id     BIGINT    NOT NULL,
    machine_model_id    BIGINT    NOT NULL,
    PRIMARY KEY (machine_part_id, machine_model_id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_model_totalizers 
--

CREATE TABLE machine_model_totalizers(
    machine_totalizer_id    BIGINT    NOT NULL,
    machine_model_id        BIGINT    NOT NULL,
    PRIMARY KEY (machine_totalizer_id, machine_model_id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_models 
--

CREATE TABLE machine_models(
    id      BIGINT          AUTO_INCREMENT,
    code    VARCHAR(20)     NOT NULL,
    name    VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_parts 
--

CREATE TABLE machine_parts(
    id      BIGINT          AUTO_INCREMENT,
    code    VARCHAR(20)     NOT NULL,
    name    VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: machine_totalizers 
--

CREATE TABLE machine_totalizers(
    id      BIGINT         AUTO_INCREMENT,
    name    VARCHAR(20)    NOT NULL,
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
-- TABLE: service_report_spbu_machine_totalizers 
--

CREATE TABLE service_report_spbu_machine_totalizers(
    service_report_id       CHAR(10)         NOT NULL,
    spbu_id                 BIGINT           NOT NULL,
    machine_identifier      VARCHAR(20)      NOT NULL,
    machine_totalizer_id    BIGINT           NOT NULL,
    counter                 DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (service_report_id, spbu_id, machine_identifier, machine_totalizer_id)
)ENGINE=INNODB
;



-- 
-- TABLE: service_reports 
--

CREATE TABLE service_reports(
    id                          CHAR(10)       NOT NULL,
    date                        CHAR(10),
    spbu_id                     BIGINT         NOT NULL,
    machine_identifier          VARCHAR(20)    NOT NULL,
    failure_mode_handling_id    BIGINT         NOT NULL,
    technician_id               BIGINT         NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: spbu_machine_totalizers 
--

CREATE TABLE spbu_machine_totalizers(
    machine_totalizer_id    BIGINT           NOT NULL,
    spbu_id                 BIGINT           NOT NULL,
    machine_identifier      VARCHAR(20)      NOT NULL,
    counter                 DOUBLE(18, 0)    NOT NULL,
    PRIMARY KEY (machine_totalizer_id, spbu_id, machine_identifier)
)ENGINE=INNODB
;



-- 
-- TABLE: spbu_machines 
--

CREATE TABLE spbu_machines(
    spbu_id               BIGINT         NOT NULL,
    machine_identifier    VARCHAR(20)    NOT NULL,
    machine_model_id      BIGINT,
    PRIMARY KEY (spbu_id, machine_identifier)
)ENGINE=INNODB
;



-- 
-- TABLE: spbus 
--

CREATE TABLE spbus(
    id               BIGINT          AUTO_INCREMENT,
    address          VARCHAR(255)    NOT NULL,
    code             VARCHAR(20)     NOT NULL,
    phone            VARCHAR(20)     NOT NULL,
    supervisor_id    BIGINT          NOT NULL,
    PRIMARY KEY (id)
)ENGINE=INNODB
;



-- 
-- TABLE: users 
--

CREATE TABLE users(
    ID               BIGINT          AUTO_INCREMENT,
    access_role      INT             NOT NULL,
    full_name        VARCHAR(255)    NOT NULL,
    login_id         VARCHAR(255)    NOT NULL,
    password_hash    VARCHAR(255)    NOT NULL,
    PRIMARY KEY (ID)
)ENGINE=INNODB
;



-- 
-- INDEX: FK_failure_mode_handlings_failure_mode_id 
--

CREATE INDEX FK_failure_mode_handlings_failure_mode_id ON failure_mode_handlings(failure_mode_id)
;
-- 
-- INDEX: machine_model_part_totalizers_machine_totalizer_id 
--

CREATE INDEX machine_model_part_totalizers_machine_totalizer_id ON machine_model_part_totalizers(machine_totalizer_id, machine_model_id)
;
-- 
-- INDEX: FK_machine_model_part_totalizers_machine_part_id 
--

CREATE INDEX FK_machine_model_part_totalizers_machine_part_id ON machine_model_part_totalizers(machine_part_id, machine_model_id)
;
-- 
-- INDEX: FK_machine_model_parts_machine_model_id 
--

CREATE INDEX FK_machine_model_parts_machine_model_id ON machine_model_parts(machine_model_id)
;
-- 
-- INDEX: FK_machine_model_parts_machine_part_id 
--

CREATE INDEX FK_machine_model_parts_machine_part_id ON machine_model_parts(machine_part_id)
;
-- 
-- INDEX: FK_machine_model_totalizers_machine_model_id 
--

CREATE INDEX FK_machine_model_totalizers_machine_model_id ON machine_model_totalizers(machine_model_id)
;
-- 
-- INDEX: FK_machine_model_totalizers_machine_totalizer_id 
--

CREATE INDEX FK_machine_model_totalizers_machine_totalizer_id ON machine_model_totalizers(machine_totalizer_id)
;
-- 
-- INDEX: UNQ_machine_models_0 
--

CREATE UNIQUE INDEX UNQ_machine_models_0 ON machine_models(code)
;
-- 
-- INDEX: FK_part_failure_modes_part_id 
--

CREATE INDEX FK_part_failure_modes_part_id ON part_failure_modes(part_id)
;
-- 
-- INDEX: Ref1314 
--

CREATE INDEX Ref1314 ON service_report_spbu_machine_totalizers(service_report_id)
;
-- 
-- INDEX: Ref1116 
--

CREATE INDEX Ref1116 ON service_report_spbu_machine_totalizers(machine_totalizer_id, spbu_id, machine_identifier)
;
-- 
-- INDEX: Ref1017 
--

CREATE INDEX Ref1017 ON service_reports(spbu_id, machine_identifier)
;
-- 
-- INDEX: Ref118 
--

CREATE INDEX Ref118 ON service_reports(failure_mode_handling_id)
;
-- 
-- INDEX: Ref1219 
--

CREATE INDEX Ref1219 ON service_reports(technician_id)
;
-- 
-- INDEX: FK_spbu_machine_totalizers_spbu_id 
--

CREATE INDEX FK_spbu_machine_totalizers_spbu_id ON spbu_machine_totalizers(spbu_id, machine_identifier)
;
-- 
-- INDEX: FK_spbu_machine_totalizers_machine_totalizer_id 
--

CREATE INDEX FK_spbu_machine_totalizers_machine_totalizer_id ON spbu_machine_totalizers(machine_totalizer_id)
;
-- 
-- INDEX: FK_spbu_machines_machine_model_id 
--

CREATE INDEX FK_spbu_machines_machine_model_id ON spbu_machines(machine_model_id)
;
-- 
-- INDEX: FK_spbu_machines_spbu_id 
--

CREATE INDEX FK_spbu_machines_spbu_id ON spbu_machines(spbu_id)
;
-- 
-- INDEX: UNQ_spbus_0 
--

CREATE UNIQUE INDEX UNQ_spbus_0 ON spbus(code)
;
-- 
-- INDEX: FK_spbus_supervisor_id 
--

CREATE INDEX FK_spbus_supervisor_id ON spbus(supervisor_id)
;
-- 
-- INDEX: login_id 
--

CREATE UNIQUE INDEX login_id ON users(login_id)
;
-- 
-- TABLE: failure_mode_handlings 
--

ALTER TABLE failure_mode_handlings ADD CONSTRAINT FK_failure_mode_handlings_failure_mode_id 
    FOREIGN KEY (failure_mode_id)
    REFERENCES part_failure_modes(id)
;


-- 
-- TABLE: machine_model_part_totalizers 
--

ALTER TABLE machine_model_part_totalizers ADD CONSTRAINT FK_machine_model_part_totalizers_machine_part_id 
    FOREIGN KEY (machine_model_id, machine_part_id)
    REFERENCES machine_model_parts(machine_part_id, machine_model_id)
;

ALTER TABLE machine_model_part_totalizers ADD CONSTRAINT machine_model_part_totalizers_machine_totalizer_id 
    FOREIGN KEY (machine_model_id, machine_totalizer_id)
    REFERENCES machine_model_totalizers(machine_totalizer_id, machine_model_id)
;


-- 
-- TABLE: machine_model_parts 
--

ALTER TABLE machine_model_parts ADD CONSTRAINT FK_machine_model_parts_machine_model_id 
    FOREIGN KEY (machine_model_id)
    REFERENCES machine_models(id)
;

ALTER TABLE machine_model_parts ADD CONSTRAINT FK_machine_model_parts_machine_part_id 
    FOREIGN KEY (machine_part_id)
    REFERENCES machine_parts(id)
;


-- 
-- TABLE: machine_model_totalizers 
--

ALTER TABLE machine_model_totalizers ADD CONSTRAINT FK_machine_model_totalizers_machine_model_id 
    FOREIGN KEY (machine_model_id)
    REFERENCES machine_models(id)
;

ALTER TABLE machine_model_totalizers ADD CONSTRAINT FK_machine_model_totalizers_machine_totalizer_id 
    FOREIGN KEY (machine_totalizer_id)
    REFERENCES machine_totalizers(id)
;


-- 
-- TABLE: part_failure_modes 
--

ALTER TABLE part_failure_modes ADD CONSTRAINT FK_part_failure_modes_part_id 
    FOREIGN KEY (part_id)
    REFERENCES machine_parts(id)
;


-- 
-- TABLE: service_report_spbu_machine_totalizers 
--

ALTER TABLE service_report_spbu_machine_totalizers ADD CONSTRAINT Refservice_reports14 
    FOREIGN KEY (service_report_id)
    REFERENCES service_reports(id)
;

ALTER TABLE service_report_spbu_machine_totalizers ADD CONSTRAINT Refspbu_machine_totalizers16 
    FOREIGN KEY (spbu_id, machine_identifier, machine_totalizer_id)
    REFERENCES spbu_machine_totalizers(machine_totalizer_id, spbu_id, machine_identifier)
;


-- 
-- TABLE: service_reports 
--

ALTER TABLE service_reports ADD CONSTRAINT Refspbu_machines17 
    FOREIGN KEY (spbu_id, machine_identifier)
    REFERENCES spbu_machines(spbu_id, machine_identifier)
;

ALTER TABLE service_reports ADD CONSTRAINT Reffailure_mode_handlings18 
    FOREIGN KEY (failure_mode_handling_id)
    REFERENCES failure_mode_handlings(id)
;

ALTER TABLE service_reports ADD CONSTRAINT Refusers19 
    FOREIGN KEY (technician_id)
    REFERENCES users(ID)
;


-- 
-- TABLE: spbu_machine_totalizers 
--

ALTER TABLE spbu_machine_totalizers ADD CONSTRAINT FK_spbu_machine_totalizers_machine_totalizer_id 
    FOREIGN KEY (machine_totalizer_id)
    REFERENCES machine_totalizers(id)
;

ALTER TABLE spbu_machine_totalizers ADD CONSTRAINT FK_spbu_machine_totalizers_spbu_id 
    FOREIGN KEY (spbu_id, machine_identifier)
    REFERENCES spbu_machines(spbu_id, machine_identifier)
;


-- 
-- TABLE: spbu_machines 
--

ALTER TABLE spbu_machines ADD CONSTRAINT FK_spbu_machines_machine_model_id 
    FOREIGN KEY (machine_model_id)
    REFERENCES machine_models(id)
;

ALTER TABLE spbu_machines ADD CONSTRAINT FK_spbu_machines_spbu_id 
    FOREIGN KEY (spbu_id)
    REFERENCES spbus(id)
;


-- 
-- TABLE: spbus 
--

ALTER TABLE spbus ADD CONSTRAINT FK_spbus_supervisor_id 
    FOREIGN KEY (supervisor_id)
    REFERENCES users(ID)
;


