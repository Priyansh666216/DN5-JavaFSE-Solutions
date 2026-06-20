ALTER TABLE loans ADD loan_amount NUMBER(15,2);

-- Set loan amounts for existing records
UPDATE loans SET loan_amount = 50000 WHERE loan_id = 101;
UPDATE loans SET loan_amount = 30000 WHERE loan_id = 102;
UPDATE loans SET loan_amount = 80000 WHERE loan_id = 103;

COMMIT;
DECLARE
    CURSOR c_due_loans IS
        SELECT c.customer_id,
               c.first_name,
               c.last_name,
               c.email,
               l.loan_id,
               l.loan_amount,
               l.due_date,
               l.outstanding_balance,
               (l.due_date - SYSDATE) AS days_remaining
        FROM   customers c
        JOIN   loans l ON c.customer_id = l.customer_id
        WHERE  l.due_date BETWEEN SYSDATE AND (SYSDATE + 30)
        AND    l.status = 'ACTIVE'
        ORDER  BY l.due_date ASC;

    v_reminder_count NUMBER := 0;
    v_urgency_label  VARCHAR2(20);

BEGIN
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('   LOAN DUE DATE REMINDER REPORT');
    DBMS_OUTPUT.PUT_LINE('   Generated: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('========================================');

    FOR rec IN c_due_loans LOOP

        IF rec.days_remaining <= 7 THEN
            v_urgency_label := 'URGENT';
        ELSIF rec.days_remaining <= 15 THEN
            v_urgency_label := 'SOON';
        ELSE
            v_urgency_label := 'UPCOMING';
        END IF;

        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('[' || v_urgency_label || '] Loan #' || rec.loan_id);
        DBMS_OUTPUT.PUT_LINE('Dear ' || rec.first_name || ' ' || rec.last_name || ',');
        DBMS_OUTPUT.PUT_LINE(
            'Your loan of $' || rec.loan_amount ||
            ' is due on '    || TO_CHAR(rec.due_date, 'DD-MON-YYYY') ||
            ' ('             || CEIL(rec.days_remaining) || ' days remaining).'
        );
        DBMS_OUTPUT.PUT_LINE('Outstanding: $' || rec.outstanding_balance);
        DBMS_OUTPUT.PUT_LINE('Email: '        || rec.email);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');

        v_reminder_count := v_reminder_count + 1;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Total reminders: ' || v_reminder_count);

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/