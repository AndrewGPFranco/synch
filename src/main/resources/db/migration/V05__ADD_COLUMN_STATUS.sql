ALTER TABLE finance_table
    ADD status VARCHAR(15);

UPDATE finance_table
SET status = 'Concluído'
WHERE status is null;

ALTER TABLE finance_table
    ALTER COLUMN status SET NOT NULL;