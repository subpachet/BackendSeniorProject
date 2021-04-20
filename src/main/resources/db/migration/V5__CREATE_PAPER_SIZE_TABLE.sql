CREATE TABLE paper_size(
    id int NOT NULL AUTO_INCREMENT,
    paper_code VARCHAR(255),
    paper_description VARCHAR(255),
    cost DECIMAL(5,2),
    PRIMARY KEY (id)
);