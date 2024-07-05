CREATE TABLE IF NOT EXISTS user (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS slot (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    coach_id BIGINT NOT NULL,
    workoutype ENUM('WEIGHT', 'CARDIO') NOT NULL,
    event_datetime TIMESTAMP NOT NULL,
    price BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

INSERT INTO user (name, password) VALUES
('person1', 'passpass'),
('person2', 'passpass'),
('coach1', 'wordword'),
('coach2', 'wordword');

INSERT INTO slot (coach_id, workoutype, event_datetime, price) VALUES
(3, 'WEIGHT', '2024-07-10 14:00:00', 20000),
(4, 'CARDIO', '2024-07-10 16:00:00', 20000);
