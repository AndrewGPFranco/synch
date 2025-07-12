ALTER TABLE expense ADD due_date date;
UPDATE expense SET due_date = '2025-07-12' WHERE due_date IS NULL;
ALTER TABLE expense ALTER COLUMN due_date SET NOT NULL;

ALTER TABLE expense ADD payment_category VARCHAR(10);
UPDATE expense SET payment_category = 'FIXED' WHERE payment_category IS NULL;
ALTER TABLE expense ALTER COLUMN payment_category SET NOT NULL;

ALTER TABLE expense ADD payment_date date;

ALTER TABLE finance_table_user DROP CONSTRAINT pk_finance_table_user;

ALTER TABLE expense ALTER COLUMN name SET NOT NULL;

ALTER TABLE finance_table ALTER COLUMN status DROP NOT NULL;