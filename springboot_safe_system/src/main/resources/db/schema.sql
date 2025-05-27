CREATE DATABASE IF NOT EXISTS chapter07;
USE chapter07;

-- 用户表
DROP TABLE IF EXISTS user_priv;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS priv;
DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    valid INT DEFAULT 1,
    name VARCHAR(50)
);

-- 权限表
CREATE TABLE IF NOT EXISTS priv (
    id INT PRIMARY KEY AUTO_INCREMENT,
    authority VARCHAR(50) NOT NULL UNIQUE
);

-- 用户权限关联表
CREATE TABLE IF NOT EXISTS user_priv (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    priv_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (priv_id) REFERENCES priv(id)
);

-- 图书表
CREATE TABLE IF NOT EXISTS book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    author VARCHAR(50),
    publisher VARCHAR(100),
    price DECIMAL(10,2),
    description TEXT,
    stock INT DEFAULT 0
);

-- 插入初始数据 (使用INSERT INTO代替REPLACE INTO，避免外键问题)
INSERT INTO user (id, username, password, name) VALUES 
(1, 'zhangsan', '$2a$10$ahsaCDzyL01Fn4LpRRoRzOu0JrG6YAY43Rf0dlQY0Yd60US3r485W', '张三'), -- 密码: 1234
(2, 'lisi', '$2a$10$ahsaCDzyL01Fn4LpRRoRzOu0JrG6YAY43Rf0dlQY0Yd60US3r485W', '李四');    -- 密码: 1234

INSERT INTO priv (id, authority) VALUES 
(1, 'ROLE_COMMON'),
(2, 'ROLE_ADMIN');

-- 插入用户权限关联
INSERT INTO user_priv (id, user_id, priv_id) VALUES 
(1, 1, 1), -- zhangsan has ROLE_COMMON
(2, 2, 2); -- lisi has ROLE_ADMIN

-- 插入示例图书数据
INSERT INTO book (id, name, author, publisher, price, description, stock) VALUES
(1, 'Spark大数据分析与实战', '传智播客', '清华大学出版社', 59.90, 'Spark大数据分析入门教程', 100),
(2, 'SpringBoot企业级开发教程', '传智播客', '人民邮电出版社', 69.90, 'Spring Boot框架学习指南', 80),
(3, 'Java核心技术卷I', 'Cay S. Horstmann', '机械工业出版社', 119.00, 'Java编程基础入门', 50),
(4, '深入理解Java虚拟机', '周志明', '机械工业出版社', 89.00, 'JVM原理与实践', 30);