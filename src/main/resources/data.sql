DROP TABLE IF EXISTS database_number;

CREATE TABLE database_number (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  random_number DOUBLE NOT NULL
);

INSERT INTO database_number (random_number) VALUES
  (15.0),
  (10.0),
  (1.0);