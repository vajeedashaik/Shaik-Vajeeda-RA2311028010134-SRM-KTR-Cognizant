-- Sample payroll data for Docx 2 Hands on 3+

INSERT INTO department (dp_name) VALUES ('Engineering');
INSERT INTO department (dp_name) VALUES ('Finance');
INSERT INTO department (dp_name) VALUES ('HR');

INSERT INTO skill (sk_name) VALUES ('Java');
INSERT INTO skill (sk_name) VALUES ('Spring Boot');
INSERT INTO skill (sk_name) VALUES ('MySQL');
INSERT INTO skill (sk_name) VALUES ('Python');
INSERT INTO skill (sk_name) VALUES ('AWS');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Alice Johnson', 85000, 1, '1990-03-15', 1);

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Bob Smith', 72000, 1, '1988-07-22', 1);

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Carol White', 68000, 0, '1995-11-10', 2);

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('David Brown', 91000, 1, '1985-01-30', 3);

INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 5);
