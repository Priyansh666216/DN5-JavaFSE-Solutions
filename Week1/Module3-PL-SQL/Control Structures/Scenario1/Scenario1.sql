ALTER TABLE customers ADD is_vip VARCHAR2(5) DEFAULT 'FALSE';
ALTER TABLE customers ADD vip_since DATE;
ALTER TABLE customers ADD account_balance NUMBER(15,2);

-- Add some balance data to test
UPDATE customers SET account_balance = 15000 WHERE customer_id = 1;
UPDATE customers SET account_balance = 8000  WHERE customer_id = 2;
UPDATE customers SET account_balance = 12000 WHERE customer_id = 3;

COMMIT;
DECLARE
    CURSOR c_customers IS
        SELECT customer_id, first_name, last_name,
               account_balance, is_vip
        FROM customers;

    v_vip_threshold  CONSTANT NUMBER := 10000;
    v_promoted_count NUMBER := 0;
    v_already_vip    NUMBER := 0;

BEGIN
    FOR rec IN c_customers LOOP

        IF rec.account_balance > v_vip_threshold THEN

            IF rec.is_vip = 'FALSE' OR rec.is_vip IS NULL THEN
                UPDATE customers
                SET    is_vip        = 'TRUE',
                       vip_since     = SYSDATE,
                       last_modified = SYSDATE
                WHERE  customer_id   = rec.customer_id;

                v_promoted_count := v_promoted_count + 1;

                DBMS_OUTPUT.PUT_LINE(
                    'PROMOTED TO VIP → ' || rec.first_name || ' ' || rec.last_name ||
                    ' | Balance: $' || rec.account_balance
                );
            ELSE
                v_already_vip := v_already_vip + 1;
                DBMS_OUTPUT.PUT_LINE(
                    'Already VIP → ' || rec.first_name || ' ' || rec.last_name
                );
            END IF;

        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Not eligible → ' || rec.first_name || ' ' || rec.last_name ||
                ' | Balance: $' || rec.account_balance
            );
        END IF;

    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('----------------------------');
    DBMS_OUTPUT.PUT_LINE('Newly promoted : ' || v_promoted_count);
    DBMS_OUTPUT.PUT_LINE('Already VIP    : ' || v_already_vip);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/