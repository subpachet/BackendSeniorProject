CREATE TABLE user_printing_status(
     id INT NOT NULL AUTO_INCREMENT,
     status varchar(10),
     printing_color_status INT,
     printing_type_status INT,
     paper_size varchar(10),
     pages_per_sheet INT,
     additional_paper_type varchar(100),
     binding_type varchar(100),
     cover_paper_color varchar(50),
     additional_cover_paper varchar(100),
     transparent_cover INT,
     file_id int NOT NULL,
     user_id varchar(50),
     PRIMARY KEY (id),
     FOREIGN KEY (file_id) REFERENCES file_storage(id),
     FOREIGN KEY (user_id) REFERENCES users(id)
 );