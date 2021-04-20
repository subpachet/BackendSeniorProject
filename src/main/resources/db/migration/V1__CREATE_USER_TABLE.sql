CREATE TABLE users(
    id varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    phone varchar(10) NOT NULL,
    username varchar(100) NOT NULL,
    password varchar(12) NOT NULL,
    firstname varchar(200) NOT NULL,
    lastname varchar(200) NOT NULL,
    PRIMARY KEY (id)
);