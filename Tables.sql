CREATE TABLE `patient` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `height` decimal(4,2) DEFAULT NULL,
  `foot_size` int DEFAULT NULL,
  `sex` enum('Male','Female') DEFAULT NULL,
  `age` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `patient_history` (
  `Date` datetime DEFAULT NULL,
  `Notes` text,
  `p_id` int DEFAULT NULL,
  `proc_id` int DEFAULT NULL,
  KEY `fk` (`p_id`),
  KEY `fk_proc_id` (`proc_id`),
  CONSTRAINT `fk` FOREIGN KEY (`p_id`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_proc_id` FOREIGN KEY (`proc_id`) REFERENCES `procedures` (`proc_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `procedures` (
  `Name` text,
  `Duration (minutes)` int DEFAULT NULL,
  `Cause` text,
  `Surgical?` text,
  `proc_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`proc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;