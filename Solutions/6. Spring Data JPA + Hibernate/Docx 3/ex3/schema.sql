-- Run in MySQL client: mysql -u root -p
-- mysql> create schema ormlearn;
-- mysql> use ormlearn;
-- mysql> source path/to/schema.sql

-- ===================== Country Table =====================
CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(100) NOT NULL
);

-- ===================== Stock Table (Docx 2 - Hands on 2) =====================
CREATE TABLE IF NOT EXISTS stock (
    st_id     INT NOT NULL AUTO_INCREMENT,
    st_code   VARCHAR(10),
    st_date   DATE,
    st_open   NUMERIC(10, 2),
    st_close  NUMERIC(10, 2),
    st_volume NUMERIC,
    PRIMARY KEY (st_id)
);

-- ===================== Payroll Tables (Docx 2 - Hands on 3) =====================
CREATE TABLE IF NOT EXISTS department (
    dp_id   INT NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(50),
    PRIMARY KEY (dp_id)
);

CREATE TABLE IF NOT EXISTS employee (
    em_id            INT NOT NULL AUTO_INCREMENT,
    em_name          VARCHAR(100),
    em_salary        DOUBLE,
    em_permanent     TINYINT(1),
    em_date_of_birth DATE,
    em_dp_id         INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department (dp_id)
);

CREATE TABLE IF NOT EXISTS skill (
    sk_id   INT NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(50),
    PRIMARY KEY (sk_id)
);

CREATE TABLE IF NOT EXISTS employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee (em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill (sk_id)
);

-- ===================== Quiz Attempt Tables (Docx 3 - Hands on 3) =====================
-- Table is named quiz_user (not "user") because USER is a reserved word in MySQL
CREATE TABLE IF NOT EXISTS quiz_user (
    qu_id       INT NOT NULL AUTO_INCREMENT,
    qu_username VARCHAR(100),
    PRIMARY KEY (qu_id)
);

CREATE TABLE IF NOT EXISTS question (
    qs_id   INT NOT NULL AUTO_INCREMENT,
    qs_text VARCHAR(500),
    PRIMARY KEY (qs_id)
);

CREATE TABLE IF NOT EXISTS options (
    op_id    INT NOT NULL AUTO_INCREMENT,
    op_qs_id INT NOT NULL,
    op_text  VARCHAR(255),
    op_score NUMERIC(3, 1),
    PRIMARY KEY (op_id),
    FOREIGN KEY (op_qs_id) REFERENCES question (qs_id)
);

CREATE TABLE IF NOT EXISTS attempt (
    at_id    INT NOT NULL AUTO_INCREMENT,
    at_qu_id INT NOT NULL,
    at_date  DATE,
    PRIMARY KEY (at_id),
    FOREIGN KEY (at_qu_id) REFERENCES quiz_user (qu_id)
);

CREATE TABLE IF NOT EXISTS attempt_question (
    aq_id    INT NOT NULL AUTO_INCREMENT,
    aq_at_id INT NOT NULL,
    aq_qs_id INT NOT NULL,
    PRIMARY KEY (aq_id),
    FOREIGN KEY (aq_at_id) REFERENCES attempt (at_id),
    FOREIGN KEY (aq_qs_id) REFERENCES question (qs_id)
);

CREATE TABLE IF NOT EXISTS attempt_option (
    ao_id    INT NOT NULL AUTO_INCREMENT,
    ao_aq_id INT NOT NULL,
    ao_op_id INT NOT NULL,
    PRIMARY KEY (ao_id),
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question (aq_id),
    FOREIGN KEY (ao_op_id) REFERENCES options (op_id)
);
