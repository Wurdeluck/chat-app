CREATE TABLE IF NOT EXISTS users (
    id                     BIGINT       NOT NULL  PRIMARY KEY,
    name                   VARCHAR      NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    id                     BIGINT       NOT NULL  PRIMARY KEY,
    text                   VARCHAR      NOT NULL,
    user_id                BIGINT       NOT NULL,
    sent_at                BIGINT       NOT NULL
);

ALTER TABLE messages
    ADD FOREIGN KEY (user_id)
        REFERENCES users(id);