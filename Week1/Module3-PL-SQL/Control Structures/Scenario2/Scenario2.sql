DECLARE
    CURSOR c_customers IS
        SELECT customer_id, first_name, last_name,
               account_balance, is_vip
        FROM   customers;

    v_vip_threshold  CONSTANT NUMBER := 10000;
    v_promoted_count NUMBER := 0;
    v_already_vip    NUMBER := 0;

BEGIN
    FOR rec IN c_customers LOOP

        IF rec.account_balance > v_vip_threshold THEN

            IF rec.is_vip = 'FALSE' OR rec.is_vip IS NULL THEN
                -- Not yet VIP → promote
                UPDATE customers
                SET    is_vip         = 'TRUE',
                       vip_since      = SYSDATE,
                       last_modified  = SYSDATE
                WHERE  customer_id    = rec.customer_id;

                v_promoted_count := v_promoted_count + 1;

                DBMS_OUTPUT.PUT_LINE(
                    '★ PROMOTED TO VIP → ' || rec.first_name || ' ' || rec.last_name ||
                    ' | Balance: $' || TO_CHAR(rec.account_balance, '999,999.99')
                );

            ELSE
                -- Already VIP, no action needed
                v_already_vip := v_already_vip + 1;
                DBMS_OUTPUT.PUT_LINE(
                    '✔ Already VIP    → ' || rec.first_name || ' ' || rec.last_name ||
                    ' | Balance: $' || TO_CHAR(rec.account_balance, '999,999.99')
                );
            END IF;

        ELSE
            -- Below threshold → revoke VIP if previously set
            UPDATE customers
            SET    is_vip        = 'FALSE',
                   last_modified = SYSDATE
            WHERE  customer_id   = rec.customer_id
            AND    is_vip        = 'TRUE';

            IF SQL%ROWCOUNT > 0 THEN
                DBMS_OUTPUT.PUT_LINE(
                    '✘ VIP REVOKED    → ' || rec.first_name || ' ' || rec.last_name ||
                    ' | Balance: $' || TO_CHAR(rec.account_balance, '999,999.99')
                );
            END IF;
        END IF;

    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Newly promoted : ' || v_promoted_count);
    DBMS_OUTPUT.PUT_LINE('Already VIP    : ' || v_already_vip);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/