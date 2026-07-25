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

-- Sample quiz data for Docx 3 Hands on 3

INSERT INTO quiz_user (qu_username) VALUES ('jdoe');

INSERT INTO question (qs_text) VALUES ('What is the extension of the hyper text markup language file?');
INSERT INTO question (qs_text) VALUES ('What is the maximum level of heading tag can be used in a HTML page?');
INSERT INTO question (qs_text) VALUES ('The HTML document itself begins with <html> and ends </html>. State True or False');
INSERT INTO question (qs_text) VALUES ('Choose the right option to store text value in a variable');

-- Options for question 1 (qs_id=1)
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (1, '.xhtm', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (1, '.ht', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (1, '.html', 1.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (1, '.htmx', 0.0);

-- Options for question 2 (qs_id=2)
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (2, '5', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (2, '3', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (2, '4', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (2, '6', 1.0);

-- Options for question 3 (qs_id=3)
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (3, 'false', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (3, 'true', 1.0);

-- Options for question 4 (qs_id=4)
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (4, '''John''', 0.5);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (4, 'John', 0.0);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (4, '"John"', 0.5);
INSERT INTO options (op_qs_id, op_text, op_score) VALUES (4, '/John/', 0.0);

-- jdoe's attempt (at_id=1) answering all 4 questions
INSERT INTO attempt (at_qu_id, at_date) VALUES (1, '2024-01-15');

INSERT INTO attempt_question (aq_at_id, aq_qs_id) VALUES (1, 1);
INSERT INTO attempt_question (aq_at_id, aq_qs_id) VALUES (1, 2);
INSERT INTO attempt_question (aq_at_id, aq_qs_id) VALUES (1, 3);
INSERT INTO attempt_question (aq_at_id, aq_qs_id) VALUES (1, 4);

-- Option jdoe selected for each question (op_id follows the insertion order above)
INSERT INTO attempt_option (ao_aq_id, ao_op_id) VALUES (1, 3);  -- Q1: selected '.html'
INSERT INTO attempt_option (ao_aq_id, ao_op_id) VALUES (2, 6);  -- Q2: selected '3' (wrong; correct is '6')
INSERT INTO attempt_option (ao_aq_id, ao_op_id) VALUES (3, 10); -- Q3: selected 'true'
INSERT INTO attempt_option (ao_aq_id, ao_op_id) VALUES (4, 11); -- Q4: selected ''John''
