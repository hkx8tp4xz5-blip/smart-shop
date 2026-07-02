-- =============================================
-- 智慧商品信息管理系统 - 数据库初始化脚本
-- 数据库：2024zzw
-- 服务器：129.204.142.57
-- =============================================

CREATE DATABASE IF NOT EXISTS `2024zzw` DEFAULT CHARSET utf8mb4;
USE `2024zzw`;

-- 1. 用户表
DROP TABLE IF EXISTS t_user_role;
DROP TABLE IF EXISTS t_role;
DROP TABLE IF EXISTS t_product;
DROP TABLE IF EXISTS t_category;
DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(200) NOT NULL COMMENT '登录账号',
    password VARCHAR(200) NOT NULL COMMENT '登录密码(BCrypt加密)',
    active INT(1) DEFAULT 1 COMMENT '1可用，0不可用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. 角色表
CREATE TABLE t_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(200) NOT NULL COMMENT '角色名，如ROLE_admin/ROLE_normal'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. 用户角色关联表
CREATE TABLE t_user_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (role_id) REFERENCES t_role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. 商品分类表
CREATE TABLE t_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT '分类名称',
    descp VARCHAR(500) COMMENT '分类描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. 商品表
CREATE TABLE t_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    photo_url VARCHAR(500) COMMENT '商品图片路径',
    price DOUBLE COMMENT '商品价格',
    descp VARCHAR(500) COMMENT '商品描述',
    release_date DATE COMMENT '发布日期',
    cat_id INT COMMENT '所属分类ID',
    FOREIGN KEY (cat_id) REFERENCES t_category(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- 初始测试数据（密码均为 123456，BCrypt加密）
-- =============================================

-- 用户
INSERT INTO t_user (username, password, active) VALUES
('admin', '$2b$12$SNC//na0fkZKcUZQDpLKd.K90sHPymm1X7p9d700X6btz.COPHX/C', 1),
('bob',   '$2b$12$vS5Qaw483J5WIb18SKMtIuNOIem0UYU3yt8pxcMdS2cdx5QtrMddy', 1);

-- 角色
INSERT INTO t_role (role) VALUES
('ROLE_admin'),
('ROLE_normal');

-- 用户角色关联（admin拥有两个角色，bob仅有普通）
INSERT INTO t_user_role (user_id, role_id) VALUES
(1, 1), -- admin -> ROLE_admin
(1, 2), -- admin -> ROLE_normal
(2, 2); -- bob   -> ROLE_normal

-- 分类
INSERT INTO t_category (name, descp) VALUES
('数码电子', '手机、电脑、耳机等电子产品'),
('图书教材', '各类图书、教材、参考资料'),
('生活居家', '日常家居生活用品');

-- 商品
INSERT INTO t_product (name, price, descp, release_date, cat_id, photo_url) VALUES
('高性能轻薄本', 5999.00, '14英寸2K屏，12代i7处理器，16GB内存', '2026-06-15', 1, '/img/product1.jpg'),
('Spring Boot实战', 79.90, '从入门到精通Spring Boot企业级开发', '2026-05-20', 2, '/img/product2.jpg'),
('MyBatis底层原理', 69.00, '深入剖析MyBatis源码与设计模式', '2026-04-10', 2, '/img/product3.jpg'),
('纯棉亲肤毛巾', 29.90, '100%新疆长绒棉，柔软吸水不掉毛', '2026-06-01', 3, '/img/product4.jpg'),
('无线降噪耳机', 899.00, '40dB主动降噪，30小时超长续航', '2026-06-20', 1, '/img/product5.jpg');
