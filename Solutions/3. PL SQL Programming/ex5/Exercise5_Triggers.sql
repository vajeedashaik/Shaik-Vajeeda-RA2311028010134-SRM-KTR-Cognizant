-- Exercise 5: Triggers

-- Scenario 1: UpdateCustomerLastModified
-- Auto-update LastModified on Customers row update

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
    BEFORE UPDATE ON Customers
    FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

-- Scenario 2: LogTransaction
-- Insert audit record whenever a Transaction is inserted

CREATE OR REPLACE TRIGGER LogTransaction
    AFTER INSERT ON Transactions
    FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        LogID,
        TransactionID,
        AccountID,
        TransactionDate,
        Amount,
        TransactionType,
        LogDate
    ) VALUES (
        auditlog_seq.NEXTVAL,
        :NEW.TransactionID,
        :NEW.AccountID,
        :NEW.TransactionDate,
        :NEW.Amount,
        :NEW.TransactionType,
        SYSDATE
    );
END LogTransaction;
/

-- Scenario 3: CheckTransactionRules
-- Enforce: withdrawals <= balance, deposits must be positive

CREATE OR REPLACE TRIGGER CheckTransactionRules
    BEFORE INSERT ON Transactions
    FOR EACH ROW
DECLARE
    v_balance NUMBER;
    e_negative_deposit    EXCEPTION;
    e_insufficient_funds  EXCEPTION;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE e_negative_deposit;
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE e_insufficient_funds;
        END IF;
    END IF;

EXCEPTION
    WHEN e_negative_deposit THEN
        RAISE_APPLICATION_ERROR(-20001, 'Deposit amount must be positive.');
    WHEN e_insufficient_funds THEN
        RAISE_APPLICATION_ERROR(-20002,
            'Withdrawal amount exceeds account balance.');
END CheckTransactionRules;
/

-- Test trigger behaviour
-- Triggers fire automatically when DML executes:

-- Test UpdateCustomerLastModified
UPDATE Customers SET Name = 'John Doe Updated' WHERE CustomerID = 1;

-- Test LogTransaction (also fires CheckTransactionRules)
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (3, 1, SYSDATE, 500, 'Deposit');

-- Test CheckTransactionRules - should fail (exceeds balance)
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (4, 3, SYSDATE, 99999, 'Withdrawal');

SELECT * FROM AuditLog;

COMMIT;
