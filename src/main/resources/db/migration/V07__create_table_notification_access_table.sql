CREATE TABLE notification_access_table
(
    id                   UUID         NOT NULL,
    user_owner_id        UUID         NOT NULL,
    content_message      VARCHAR(255) NOT NULL,
    was_read_destination BOOLEAN,
    created_at           TIMESTAMP WITHOUT TIME ZONE,
    was_expired          BOOLEAN,
    CONSTRAINT pk_notification_access_table PRIMARY KEY (id)
);

ALTER TABLE notification_access_table
    ADD CONSTRAINT FK_NOTIFICATION_ACCESS_TABLE_ON_USER_OWNER FOREIGN KEY (user_owner_id) REFERENCES users (id);