-- Step 1: Create accounts table (run first)
CREATE TABLE accounts (
    account_id      NUMBER PRIMARY KEY,
    customer_id     NUMBER,
    account_type    VARCHAR2(20),  -- 'SAVINGS' or 'CURRENT'
    balance         NUMBER(15,2),
    last_modified   DATE
);


-- Insert test data
INSERT INTO accounts VALUES (1, 1, 'SAVINGS', 10000, SYSDATE);
INSERT INTO accounts VALUES (2, 2, 'SAVINGS', 25000, SYSDATE);
INSERT INTO accounts VALUES (3, 3, 'CURRENT', 15000, SYSDATE);
INSERT INTO accounts VALUES (4, 1, 'SAVINGS', 5000,  SYSDATE);
COMMIT;
-- Step 2: Create the procedure
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    CURSOR c_savings IS
        SELECT account_id, balance
        FROM   accounts
        WHERE  account_type = 'SAVINGS';

    v_interest      NUMBER(15,2);
    v_new_balance   NUMBER(15,2);
    v_count         NUMBER := 0;

BEGIN
    FOR rec IN c_savings LOOP
        v_interest    := ROUND(rec.balance * 0.01, 2);  -- 1% interest
        v_new_balance := rec.balance + v_interest;

        UPDATE accounts
        SET    balance        = v_new_balance,
               last_modified  = SYSDATE
        WHERE  account_id     = rec.account_id;

        v_count := v_count + 1;

        DBMS_OUTPUT.PUT_LINE(
            'Account #'    || rec.account_id      ||
            ' | Old Bal: ' || rec.balance         ||
            ' | Interest: '|| v_interest          ||
            ' | New Bal: ' || v_new_balance
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('----------------------------');
    DBMS_OUTPUT.PUT_LINE('Accounts updated: ' || v_count);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Step 3: Execute it
EXEC ProcessMonthlyInterest;