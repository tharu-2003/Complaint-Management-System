CREATE DATABASE IF NOT EXISTS complaint_system;
USE complaint_system;


CREATE TABLE users (
                       u_id VARCHAR(50) PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role ENUM('EMPLOYEE', 'ADMIN') NOT NULL
);


CREATE TABLE complaints (
                            c_id VARCHAR(50) PRIMARY KEY,
                            u_id VARCHAR(50) NOT NULL,
                            title VARCHAR(255) NOT NULL,
                            description TEXT NOT NULL,
                            status ENUM('PENDING', 'IN_PROGRESS', 'RESOLVED') DEFAULT 'PENDING',
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            remarks TEXT,
                            FOREIGN KEY (u_id) REFERENCES users(u_id)
);

INSERT INTO users (u_id, username, password, role) VALUES
                                                       ('U001', 'Tharusha', '123', 'ADMIN'),
                                                       ('U002', 'Sachini', '123', 'EMPLOYEE'),
                                                       ('U003', 'Anjana', '123', 'EMPLOYEE');

