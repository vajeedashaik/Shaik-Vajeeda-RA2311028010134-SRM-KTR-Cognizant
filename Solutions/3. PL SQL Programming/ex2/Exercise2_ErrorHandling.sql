-- Exercise 2: Error Handling

-- Scenario 1: SafeTransferFunds - Transfer with rollback on error

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
    e_insufficient_funds EXCEPTION;
BEGIN
    -- Lock source row
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount ||
                         ' from Account ' || p_from_account ||
                         ' to Account ' || p_to_account || ' successful.');

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient funds in Account ' || p_from_account ||
                             '. Transfer aborted.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found. Transfer aborted.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error - ' || SQLERRM);
END SafeTransferFunds;
/

-- Scenario 2: UpdateSalary - Increase salary by percentage with error handling

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id      IN NUMBER,
    p_percentage       IN NUMBER
) AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    IF v_count = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for EmployeeID ' || p_employee_id ||
                         ' by ' || p_percentage || '%.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Employee with ID ' || p_employee_id || ' not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END UpdateSalary;
/

-- Scenario 3: AddNewCustomer - Insert customer, handle duplicate ID

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) AS
    v_count NUMBER;
    e_duplicate_customer EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM Customers
    WHERE CustomerID = p_customer_id;

    IF v_count > 0 THEN
        RAISE e_duplicate_customer;
    END IF;

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' added successfully.');

EXCEPTION
    WHEN e_duplicate_customer THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer with ID ' || p_customer_id ||
                             ' already exists. Insertion prevented.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END AddNewCustomer;
/

-- Test calls
BEGIN SafeTransferFunds(1, 2, 500); END;
/
BEGIN SafeTransferFunds(3, 1, 9999); END;   -- insufficient funds
/
BEGIN UpdateSalary(1, 10); END;
/
BEGIN UpdateSalary(99, 10); END;            -- employee not found
/
BEGIN AddNewCustomer(10, 'New Person', TO_DATE('2000-01-01','YYYY-MM-DD'), 2000); END;
/
BEGIN AddNewCustomer(1, 'Duplicate', SYSDATE, 0); END;  -- duplicate
/
