CREATE TYPE task_status AS ENUM ('NOT_STARTED', 'STARTED', 'COMPLETED');

CREATE TABLE task (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    taskStatus task_status DEFAULT 'NOT_STARTED'
);
