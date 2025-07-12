CREATE TABLE notification_access_users
(
    notification_id UUID NOT NULL,
    user_id         UUID NOT NULL,
    CONSTRAINT pk_notification_access_users PRIMARY KEY (notification_id, user_id)
);

ALTER TABLE notification_access_users
    ADD CONSTRAINT fk_notaccuse_on_notification_access_table FOREIGN KEY (notification_id) REFERENCES notification_access_table (id);

ALTER TABLE notification_access_users
    ADD CONSTRAINT fk_notaccuse_on_user FOREIGN KEY (user_id) REFERENCES users (id);