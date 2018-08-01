USE `presence-control-system`;

DROP TABLE IF EXISTS Signing;
DROP TABLE IF EXISTS Notification;
DROP TABLE IF EXISTS Employee;


CREATE TABLE Employee (
  fingerprintId INT(11) NOT NULL,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  PRIMARY KEY (fingerprintId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Signing (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  fkFingerprintId INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fkFingerprintId) REFERENCES Employee (fingerprintId)
) ENGINE=InnoDB;

CREATE TABLE Notification (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  fkFingerprintId INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  message VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fkFingerprintId) REFERENCES Employee (fingerprintId)
) ENGINE=InnoDB;

