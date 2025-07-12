CREATE TABLE finance_table (
                               id UUID PRIMARY KEY,
                               name VARCHAR(255),
                               created_at TIMESTAMP WITHOUT TIME ZONE,
                               updated_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE expense (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255),
                         month VARCHAR(255) NOT NULL,
                         amount DOUBLE PRECISION NOT NULL,
                         finance_table_id UUID,
                         CONSTRAINT fk_expense_finance_table FOREIGN KEY (finance_table_id) REFERENCES finance_table (id)
);

CREATE TABLE finance_table_user (
                                    finance_table_id UUID NOT NULL,
                                    user_id UUID NOT NULL,
                                    CONSTRAINT pk_finance_table_user PRIMARY KEY (finance_table_id, user_id),
                                    CONSTRAINT fk_finance_table_user_finance_table FOREIGN KEY (finance_table_id) REFERENCES finance_table (id),
                                    CONSTRAINT fk_finance_table_user_user FOREIGN KEY (user_id) REFERENCES users (id)
);

ALTER TABLE expense
    ADD CONSTRAINT FK_EXPENSE_ON_FINANCE_TABLE FOREIGN KEY (finance_table_id) REFERENCES finance_table (id);

ALTER TABLE finance_table_user
    ADD CONSTRAINT fk_fintabuse_on_finance_table FOREIGN KEY (finance_table_id) REFERENCES finance_table (id);

ALTER TABLE finance_table_user
    ADD CONSTRAINT fk_fintabuse_on_user FOREIGN KEY (user_id) REFERENCES users (id);