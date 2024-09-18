CREATE TABLE task (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(255),
    description VARCHAR(255),
    taskStatus VARCHAR(255)
);
--please add constraints to your table. Now you can enter null fields but title can't be empty. Same for other fields
-- Also TaskStatus is used as enum so please change type also here