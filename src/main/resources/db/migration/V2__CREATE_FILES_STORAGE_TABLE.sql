CREATE TABLE file_storage(
    id int NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(255),
    path VARCHAR(255),
    user_id VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);