# Student Management System - Microservices Backend

A **Spring Boot Microservices** backend for managing students, courses, grades, and user authentication with service discovery and API gateway.

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
| **Student Service** | 1111 | Student & User CRUD operations |
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

### Student Service (`/api/service1`)
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getalldata` | GET | Get all students |
| `/getdata/{id}` | GET | Get student by ID |
| `/getdatabyname/{name}` | GET | Get student by name |
| `/postdata` | POST | Create student |
| `/updatedata` | PUT | Update student |
| `/deletedata/{id}` | DELETE | Delete student |

### User Management (`/api/service1`)
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getalluser` | GET | Get all users |
| `/getuserdata/{username}` | GET | Get user by username |
| `/postuser` | POST | Register user |
| `/deleteuser/{username}` | DELETE | Delete user |

### Course Service (`/api/service2`)
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getalldata` | GET | Get all courses |
| `/getdata/{id}` | GET | Get course by ID |
| `/postdata` | POST | Create course |
| `/updatedata` | PUT | Update course |
| `/deletedata/{id}` | DELETE | Delete course |

### Grade Service (`/api/service3`)
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getAllStudentMarks` | GET | Get all marks |
| `/getmarkssheet/{id}` | GET | Get marks by student ID |
| `/postmarksdata/{id}/{m1}/{m2}` | POST | Add marks |
| `/deletemarkssheet/{id}` | DELETE | Delete marks |

## ✨ Features

- **Microservices Architecture** - Independent, scalable services
- **Service Discovery** - Dynamic registration with Eureka
- **API Gateway** - Single entry point with Zuul
- **Inter-Service Communication** - OpenFeign clients
- **User Authentication** - Login/Signup functionality
- **Data Validation** - Bean Validation API
- **Exception Handling** - Global error handling

## 📁 Project Structure

```
├── StudentManagementServer/         # Eureka Server
├── StudentRecordManagementSystem/   # Student & User CRUD
├── StudentCourseManagementSystem/   # Course CRUD  
├── StudentGradeManagementSystem/    # Grade Management
└── StudentAPIGateway/               # Zuul Gateway
```

## 👤 Author

**Saikiran MSD** - [@SaikiranMSD](https://github.com/SaikiranMSD)
