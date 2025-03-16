
CREATE TABLE task (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NULL,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    task_status ENUM ('NOT_STARTED', 'STARTED', 'COMPLETED') DEFAULT 'NOT_STARTED'
);

CREATE TABLE user_data (
 id BIGINT NOT NULL PRIMARY KEY,
 user_name VARCHAR(255),
 hashed_password VARCHAR(255),
 email VARCHAR(255),
 activation_token_id BIGINT UNIQUE,
 full_name VARCHAR(255),
 status VARCHAR(20) CHECK (status IN ('NOT_ACTIVATED', 'ACTIVATED', 'PENDING', 'BLOCKED')),
 role VARCHAR(16) DEFAULT 'USER'
);

CREATE TABLE activation_token (
    token_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255),
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user_data(id) ON DELETE CASCADE
);

CREATE TABLE user_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_data(id)
);