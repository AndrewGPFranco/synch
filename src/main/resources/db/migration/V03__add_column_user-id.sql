ALTER TABLE finance_table
    ADD user_id UUID;

ALTER TABLE finance_table
    ADD CONSTRAINT FK_FINANCE_TABLE_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);