-- Exercise 1: Control Structures

-- Scenario 1: Apply 1% discount on loan interest rates for customers above 60

DECLARE
    v_age NUMBER;
BEGIN
    FOR rec IN (SELECT c.CustomerID, c.DOB, l.LoanID, l.InterestRate
                FROM Customers c
                JOIN Loans l ON c.CustomerID = l.CustomerID)
    LOOP
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;

            DBMS_OUTPUT.PUT_LINE('Applied 1% discount to LoanID ' || rec.LoanID ||
                                 ' for CustomerID ' || rec.CustomerID ||
                                 ' (Age: ' || v_age || ')');
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rate discount applied.');
END;
/

-- Scenario 2: Set IsVIP flag for customers with balance over $10,000

DECLARE
BEGIN
    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers)
    LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('CustomerID ' || rec.CustomerID ||
                                 ' (' || rec.Name || ') promoted to VIP.');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status update complete.');
END;
/

-- Scenario 3: Print reminders for loans due within the next 30 days

DECLARE
BEGIN
    FOR rec IN (SELECT l.LoanID, l.EndDate, c.Name, c.CustomerID
                FROM Loans l
                JOIN Customers c ON l.CustomerID = c.CustomerID
                WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30)
    LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Dear ' || rec.Name ||
                             ' (CustomerID: ' || rec.CustomerID || '),' ||
                             ' your LoanID ' || rec.LoanID ||
                             ' is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
