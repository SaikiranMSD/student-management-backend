# Student Management System - Microservices

A **Spring Boot Microservices** backend for managing students, courses, and grades with service discovery and API gateway.

## 🏗️ Architecture

```
                    ┌─────────────────────┐
                    │    API Gateway      │
                    │    (Zuul - 4444)    │
                    └─────────┬───────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
        ▼                     ▼                     ▼
┌───────────────┐    ┌───────────────┐    ┌───────────────┐
│    Student    │    │    Course     │    │     Grade     │
│    Service    │    │    Service    │    │    Service    │
│   (1111)      │    │   (2222)      │    │   (3333)      │
└───────────────┘    └───────────────┘    └───────────────┘
        │                     │                     │
        └─────────────────────┼─────────────────────┘
                              │
                    ┌─────────▼───────────┐
                    │   Eureka Server     │
                    │      (8761)         │
                    └─────────────────────┘
```

## 📦 Services

| Service | Port | Description |
|---------|------|-------------|
| **Eureka Server** | 8761 | Service Discovery |
| **Student Service** | 1111 | Student CRUD operations |
| **Course Service** | 2222 | Course management |
| **Grade Service** | 3333 | Grades & enrollment management |
| **API Gateway** | 4444 | Zuul routing gateway |

## 🛠️ Tech Stack

- **Java 8** & **Spring Boot 2.7.11**
- **Spring Cloud** (Eureka, Zuul, OpenFeign)
- **Spring Data JPA** & **MySQL**
- **Maven**

## 🚀 Quick Start

```bash
# 1. Create MySQL database
mysql -u root -p -e "CREATE DATABASE studentdb;"

# 2. Start services in order
cd StudentManagementServer && mvn spring-boot:run      # Eureka
cd StudentRecordManagementSystem && mvn spring-boot:run # Student
cd StudentCourseManagementSystem && mvn spring-boot:run # Course
cd StudentGradeManagementSystem && mvn spring-boot:run  # Grade
cd StudentAPIGateway && mvn spring-boot:run             # Gateway
```

## 📡 API Endpoints

**Base URL:** `http://localhost:4444/api`

| Service | Route | Operations |
|---------|-------|------------|
| Student | `/api/service1/**` | GET, POST, PUT, DELETE |
| Course | `/api/service2/**` | GET, POST, PUT, DELETE |
| Grade | `/api/service3/**` | GET, POST, PUT, DELETE |

## ✨ Features

- **Microservices Architecture** - Independent, scalable services
- **Service Discovery** - Dynamic registration with Eureka
- **API Gateway** - Single entry point with Zuul
- **Inter-Service Communication** - OpenFeign clients
- **Data Validation** - Bean Validation API
- **Exception Handling** - Global error handling

## 📁 Project Structure

```
├── StudentManagementServer/         # Eureka Server
├── StudentRecordManagementSystem/   # Student CRUD
├── StudentCourseManagementSystem/   # Course CRUD  
├── StudentGradeManagementSystem/    # Grade Management
└── StudentAPIGateway/               # Zuul Gateway
```

## 👤 Author

**Saikiran MSD** - [@SaikiranMSD](https://github.com/SaikiranMSD)

