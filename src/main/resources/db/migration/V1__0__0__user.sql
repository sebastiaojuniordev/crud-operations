CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  birth_date DATE NOT NULL,
  created_at DATETIME NOT NULL
);

INSERT INTO user (first_name, last_name, birth_date, created_at) VALUES ('Sebastião', 'Junior', '1995-09-11', CURRENT_TIMESTAMP());
INSERT INTO user (first_name, last_name, birth_date, created_at) VALUES ('Bill', 'Gates', '1955-10-28', CURRENT_TIMESTAMP());