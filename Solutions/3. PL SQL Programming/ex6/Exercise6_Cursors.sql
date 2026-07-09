-- Exercise 6: Cursors

-- Scenario 1: GenerateMonthlyStatements
-- Print transaction statements for each customer for the current month

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID,
               c.Name,
               t.TransactionID,
               t.TransactionDate,
               t.Amount,
               t.TransactionType
        FROM Customers c
        JOIN Accounts a    ON c.CustomerID   = a.CustomerID
        JOIN Transactions t ON a.AccountID   = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR  FROM t.TransactionDate) = EXTRACT(YEAR  FROM SYSDATE)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_prev_customer NUMBER := -1;
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        IF rec.CustomerID != v_prev_customer THEN
            DBMS_OUTPUT.PUT_LINE('-------------------------------------------');
            DBMS_OUTPUT.PUT_LINE('Statement for: ' || rec.Name ||
                                 ' (ID: ' || rec.CustomerID || ')');
            DBMS_OUTPUT.PUT_LINE('-------------------------------------------');
            v_prev_customer := rec.CustomerID;
        END IF;

        DBMS_OUTPUT.PUT_LINE('  TxnID: ' || rec.TransactionID ||
                             '  Date: '  || TO_CHAR(rec.TransactionDate, 'YYYY-MM-DD') ||
                             '  Type: '  || rec.TransactionType ||
                             '  Amount: ' || rec.Amount);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('-------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Monthly statements generated.');
END;
/

-- Scenario 2: ApplyAnnualFee
-- Deduct annual maintenance fee ($50) from all accounts

DECLARE
    v_annual_fee CONSTANT NUMBER := 50;

    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE OF Balance;
BEGIN
    FOR rec IN ApplyAnnualFee LOOP
        IF rec.Balance >= v_annual_fee THEN
            UPDATE Accounts
            SET Balance      = Balance - v_annual_fee,
                LastModified = SYSDATE
            WHERE CURRENT OF ApplyAnnualFee;

            DBMS_OUTPUT.PUT_LINE('Annual fee deducted from AccountID ' || rec.AccountID ||
                                 '. New balance: ' || (rec.Balance - v_annual_fee));
        ELSE
            DBMS_OUTPUT.PUT_LINE('AccountID ' || rec.AccountID ||
                                 ' has insufficient balance for annual fee. Skipped.');
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee processing complete.');
END;
/

-- Scenario 3: UpdateLoanInterestRates
-- Update loan interest rates based on new policy:
-- LoanAmount > 10000 -> rate + 0.5%  |  LoanAmount <= 10000 -> rate - 0.25%

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount, InterestRate
        FROM Loans
        FOR UPDATE OF InterestRate;

    v_new_rate NUMBER;
BEGIN
    FOR rec IN UpdateLoanInterestRates LOOP
        IF rec.LoanAmount > 10000 THEN
            v_new_rate := rec.InterestRate + 0.5;
        ELSE
            v_new_rate := GREATEST(rec.InterestRate - 0.25, 0);
        END IF;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE CURRENT OF UpdateLoanInterestRates;

        DBMS_OUTPUT.PUT_LINE('LoanID ' || rec.LoanID ||
                             ': Rate changed from ' || rec.InterestRate ||
                             '% to ' || v_new_rate || '%');
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rate update complete.');
END;
/
