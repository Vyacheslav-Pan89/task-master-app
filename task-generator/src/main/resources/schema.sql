CREATE TABLE task (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(255),
    description VARCHAR(255),
    taskStatus VARCHAR(255)
);