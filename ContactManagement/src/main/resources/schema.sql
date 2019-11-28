DROP TABLE IF EXISTS CONTACTS;

CREATE TABLE CONTACTS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  phone_number VARCHAR(100) DEFAULT NULL,
  active CHAR(1) DEFAULT 'T'';
);