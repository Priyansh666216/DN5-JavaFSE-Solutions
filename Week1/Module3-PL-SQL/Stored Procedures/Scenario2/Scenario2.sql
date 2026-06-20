-- Step 1: Create employees table (run first)
CREATE TABLE employees (
    employee_id   NUMBER PRIMARY KEY,
    first_name    VARCHAR2(50),
    last_name     VARCHAR2(50),
    department    VARCHAR2(50),
    salary        NUMBER(10,2),
    last_modified DATE
);

-- Insert test data
INSERT INTO employees VALUES (1, 'Amit',   'Singh',  'LOANS',  50000, SYSDATE);
INSERT INTO employees VALUES (2, 'Neha',   'Verma',  'LOANS',  55000, SYSDATE);
INSERT INTO employees VALUES (3, 'Rahul',  'Gupta',  'IT',     60000, SYSDATE);
INSERT INTO employees VALUES (4, 'Sunita', 'Patel',  'IT',     58000, SYSDATE);
INSERT INTO employees VALUES (5, 'Vikram', 'Sharma', 'HR',     45000, SYSDATE);
COMMIT;
-- Step 2: Create the procedure
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department    IN VARCHAR2,
    p_bonus_percent IN NUMBER
) IS
    CURSOR c_employees IS
        SELECT employee_id, first_name, last_name, salary
        FROM   employees
        WHERE  UPPER(department) = UPPER(p_department);

    v_bonus       NUMBER(10,2);
    v_new_salary  NUMBER(10,2);
    v_count       NUMBER := 0;

BEGIN
    -- Validate bonus percentage
    IF p_bonus_percent <= 0 OR p_bonus_percent > 100 THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Bonus % must be between 1 and 100.');
        RETURN;
    END IF;

    DBMS_OUTPUT.PUT_LINE('Department : ' || UPPER(p_department));
    DBMS_OUTPUT.PUT_LINE('Bonus %    : ' || p_bonus_percent || '%');
    DBMS_OUTPUT.PUT_LINE('----------------------------');

    FOR rec IN c_employees LOOP
        v_bonus      := ROUND(rec.salary * (p_bonus_percent / 100), 2);
        v_new_salary := rec.salary + v_bonus;

        UPDATE employees
        SET    salary        = v_new_salary,
               last_modified = SYSDATE
        WHERE  employee_id   = rec.employee_id;

        v_count := v_count + 1;

        DBMS_OUTPUT.PUT_LINE(
            rec.first_name || ' ' || rec.last_name  ||
            ' | Old Salary: '  || rec.salary        ||
            ' | Bonus: '       || v_bonus           ||
            ' | New Salary: '  || v_new_salary
        );
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('----------------------------');
        DBMS_OUTPUT.PUT_LINE('Employees updated: ' || v_count);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Step 3: Execute it (department name, bonus %)
EXEC UpdateEmployeeBonus('LOANS', 10);
EXEC UpdateEmployeeBonus('IT',    15);