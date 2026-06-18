-- Uses the accounts table created in Scenario 1
-- Step 1: Check current balances
SELECT account_id, customer_id, account_type, balance FROM accounts;
-- Step 2: Create the procedure
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account  IN NUMBER,
    p_to_account    IN NUMBER,
    p_amount        IN NUMBER
) IS
    v_from_balance  NUMBER(15,2);
    v_to_balance    NUMBER(15,2);

BEGIN
    -- Validate amount
    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Transfer amount must be greater than 0.');
        RETURN;
    END IF;

    -- Check source account exists and get balance
    BEGIN
        SELECT balance INTO v_from_balance
        FROM   accounts
        WHERE  account_id = p_from_account
        FOR UPDATE;  -- Lock row during transaction
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Source account #' || p_from_account || ' not found.');
            RETURN;
    END;

    -- Check destination account exists
    BEGIN
        SELECT balance INTO v_to_balance
        FROM   accounts
        WHERE  account_id = p_to_account
        FOR UPDATE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Destination account #' || p_to_account || ' not found.');
            RETURN;
    END;

    -- Check same account
    IF p_from_account = p_to_account THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Cannot transfer to the same account.');
        RETURN;
    END IF;

    -- Check sufficient balance
    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE(
            'ERROR: Insufficient balance.' ||
            ' Available: $' || v_from_balance ||
            ' | Requested: $' || p_amount
        );
        RETURN;
    END IF;

    -- Perform transfer
    UPDATE accounts
    SET    balance       = balance - p_amount,
           last_modified = SYSDATE
    WHERE  account_id    = p_from_account;

    UPDATE accounts
    SET    balance       = balance + p_amount,
           last_modified = SYSDATE
    WHERE  account_id    = p_to_account;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer Successful!');
    DBMS_OUTPUT.PUT_LINE('----------------------------');
    DBMS_OUTPUT.PUT_LINE('From Account #' || p_from_account ||
                         ' | Old Bal: $'  || v_from_balance ||
                         ' | New Bal: $'  || (v_from_balance - p_amount));
    DBMS_OUTPUT.PUT_LINE('To   Account #' || p_to_account   ||
                         ' | Old Bal: $'  || v_to_balance   ||
                         ' | New Bal: $'  || (v_to_balance + p_amount));
    DBMS_OUTPUT.PUT_LINE('Amount Transferred: $' || p_amount);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END TransferFunds;
/

-- Step 3: Execute it (from_account, to_account, amount)
EXEC TransferFunds(1, 2, 3000);   -- Valid transfer
EXEC TransferFunds(4, 2, 99999);  -- Should fail: insufficient balance
EXEC TransferFunds(1, 1, 500);    -- Should fail: same account