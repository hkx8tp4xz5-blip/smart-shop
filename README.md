# 智慧商品信息管理系统

> JavaEE 编程课程设计项目

基于 **Spring Boot + MyBatis + Thymeleaf + MySQL + Redis** 实现的智慧商品信息管理系统，采用 Glassmorphism（玻璃拟态）设计风格。

## 技术栈

| 层次 | 技术 |
|------|------|
| 后端框架 | Spring Boot 3.2.0 |
| ORM | MyBatis 3.5.14 |
| 模板引擎 | Thymeleaf 3.1.2 |
| 安全框架 | Spring Security 6.2.0 |
| 数据库 | MySQL 8.x |
| 缓存 | Redis (Lettuce) |
| 分页 | PageHelper 5.3.3 |
| 前端 | HTML5 + CSS3 + JavaScript + Font Awesome |
| 构建工具 | Maven |

## 功能模块

- **用户认证**：登录 / 登出，基于 Spring Security 的角色权限控制（管理员 / 普通用户）
- **商品管理**：商品的增删改查与分页展示
- **分类管理**：商品分类的增删改查
- **供应商管理**：供应商信息的增删改查
- **数据看板**：首页统计信息，数据从数据库动态同步
- **Redis 缓存**：热点数据缓存，提升查询性能

## 数据库表设计

| 表名 | 说明 |
|------|------|
| `t_user` | 用户信息 |
| `t_role` | 角色信息 |
| `t_user_role` | 用户-角色关联 |
| `t_category` | 商品分类 |
| `t_product` | 商品信息 |
| `t_supplier` | 供应商信息 |

数据库初始化脚本见 `smart-shop/init.sql`。

## 项目结构

```
Javaeework2/
├── smart-shop/                  # Spring Boot 主项目
│   ├── src/main/java/com/smart/shop/
│   │   ├── config/              # Redis、Security 配置
│   │   ├── controller/          # 控制器层
│   │   ├── entity/              # 实体类
│   │   ├── mapper/              # MyBatis Mapper 接口
│   │   ├── service/             # 业务逻辑层
│   │   └── SmartShopApplication.java
│   ├── src/main/resources/
│   │   ├── mapper/              # MyBatis XML 映射
│   │   ├── static/css/          # 静态资源
│   │   ├── templates/           # Thymeleaf 模板
│   │   └── application*.yml     # 配置文件
│   ├── init.sql                 # 数据库初始化脚本
│   └── pom.xml
├── glassmorphism-dashboard/     # 仪表盘设计稿
└── JavaEE编程课程设计报告*.docx  # 课程设计报告
```

## 快速开始

1. **导入数据库**：执行 `smart-shop/init.sql`
2. **修改配置**：编辑 `src/main/resources/application-dev.yml` 中的数据库和 Redis 连接信息
3. **启动项目**：运行 `SmartShopApplication.java` 主类
4. **访问系统**：浏览器打开 `http://localhost:8080/`

## 设计风格

前端采用 Glassmorphism（玻璃拟态）设计风格：
- 背景渐变：`#F0F4F8 → #E8EEF2`
- 白色半透明卡片 + 轻柔阴影
- 主色调：`#3B82F6`（蓝色）
- Font Awesome 图标库

## 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+
