ALTER TABLE notification_access_users
    DROP CONSTRAINT pk_notification_access_users;

ALTER TABLE notification_access_users
    ADD id UUID;

ALTER TABLE notification_access_users
    ADD was_read BOOLEAN;

ALTER TABLE notification_access_table
    DROP COLUMN was_read_destination;

ALTER TABLE notification_access_users
    ADD CONSTRAINT pk_notification_access_users PRIMARY KEY (id);