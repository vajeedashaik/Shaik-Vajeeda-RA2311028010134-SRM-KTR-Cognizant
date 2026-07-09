-- Exercise 4: Functions

-- Scenario 1: CalculateAge - Returns age in years from date of birth

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER AS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END CalculateAge;
/

-- Scenario 2: CalculateMonthlyInstallment - EMI formula
-- Formula: EMI = P * r * (1+r)^n / ((1+r)^n - 1)
-- P = principal, r = monthly interest rate, n = total months

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount   IN NUMBER,
    p_interest_rate IN NUMBER,   -- annual rate in percentage
    p_duration_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate  NUMBER;
    v_num_months    NUMBER;
    v_emi           NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / (12 * 100);
    v_num_months   := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        v_emi := p_loan_amount / v_num_months;
    ELSE
        v_emi := p_loan_amount
                 * v_monthly_rate
                 * POWER(1 + v_monthly_rate, v_num_months)
                 / (POWER(1 + v_monthly_rate, v_num_months) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);
END CalculateMonthlyInstallment;
/

-- Scenario 3: HasSufficientBalance - Returns TRUE/FALSE for account balance check

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END HasSufficientBalance;
/

-- Test block
DECLARE
    v_age NUMBER;
    v_emi NUMBER;
BEGIN
    -- Test CalculateAge
    v_age := CalculateAge(TO_DATE('1955-05-15', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Age: ' || v_age || ' years');

    -- Test CalculateMonthlyInstallment (10000 loan, 8% annual, 2 years)
    v_emi := CalculateMonthlyInstallment(10000, 8, 2);
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' || v_emi);

    -- Test HasSufficientBalance
    IF HasSufficientBalance(1, 500) THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for 500.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 does NOT have sufficient balance for 500.');
    END IF;
END;
/
