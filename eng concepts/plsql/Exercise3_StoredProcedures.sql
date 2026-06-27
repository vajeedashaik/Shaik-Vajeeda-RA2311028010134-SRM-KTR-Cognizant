-- Exercise 3: Stored Procedures

-- Scenario 1: ProcessMonthlyInterest - Apply 1% interest to all savings accounts

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance      = Balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to ' || SQL%ROWCOUNT || ' savings account(s).');
END ProcessMonthlyInterest;
/

-- Scenario 2: UpdateEmployeeBonus - Add bonus percentage to employees in a department

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department     IN VARCHAR2,
    p_bonus_percent  IN NUMBER
) AS
    v_rows NUMBER;
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_department;

    v_rows := SQL%ROWCOUNT;
    COMMIT;

    IF v_rows = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percent || '% applied to ' ||
                             v_rows || ' employee(s) in ' || p_department || '.');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Scenario 3: TransferFunds - Transfer between accounts with balance check

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance NUMBER;
    e_insufficient_funds EXCEPTION;
    e_invalid_amount     EXCEPTION;
BEGIN
    IF p_amount <= 0 THEN
        RAISE e_invalid_amount;
    END IF;

    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts
    SET Balance      = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    UPDATE Accounts
    SET Balance      = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount ||
                         ' from Account ' || p_from_account ||
                         ' to Account ' || p_to_account || '.');

EXCEPTION
    WHEN e_invalid_amount THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Transfer amount must be positive.');
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient balance in Account ' || p_from_account || '.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Source account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END TransferFunds;
/

-- Test calls
BEGIN ProcessMonthlyInterest; END;
/
BEGIN UpdateEmployeeBonus('IT', 15); END;
/
BEGIN TransferFunds(1, 2, 1000); END;
/
BEGIN TransferFunds(3, 1, 9999); END;  -- insufficient funds
/
