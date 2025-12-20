# Student Management System - Microservices Architecture

A comprehensive **Student Management System** built using **Spring Boot Microservices Architecture**. This project demonstrates enterprise-level backend development with service discovery, API gateway, inter-service communication using OpenFeign, and shared database access patterns.

---

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                         API Gateway (Zuul)                          │
│                            Port: 4444                               │
│                      Endpoint: /api/service{n}/**                   │
└─────────────────────────────────────────────────────────────────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
                    ▼               ▼               ▼
┌─────────────────────┐ ┌─────────────────┐ ┌─────────────────────┐
│  Student Record     │ │  Course         │ │  Grade Management   │
│  Management Service │ │  Management     │ │  Service            │
│  Port: 1111         │ │  Port: 2222     │ │  Port: 3333         │
└─────────────────────┘ └─────────────────┘ └─────────────────────┘
          │                                           │
          │              Feign Clients                │
          ├───────────────────────────────────────────┤
          │                                           │
          └───────────────────────────────────────────┘
                                    │
                    ┌───────────────▼───────────────┐
                    │     Eureka Discovery Server    │
                    │          Port: 8761            │
                    └────────────────────────────────┘
                                    │
                    ┌───────────────▼───────────────┐
                    │       MySQL Database           │
                    │        (studentdb)             │
                    └────────────────────────────────┘
```

---

## 📦 Microservices

### 1. Student Management Server (Eureka Server)
**Port:** `8761`

Netflix Eureka Server for service discovery. All microservices register here for dynamic discovery and load balancing.

**Key Annotations:**
- `@EnableEurekaServer` - Enables Eureka Server functionality

---

### 2. Student Record Management Service
**Port:** `1111` | **Service Name:** `StudentRecordManagement`

Handles CRUD operations for student records. Communicates with Grade Management Service via Feign Client for cascade delete/update operations.

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getdata/{id}` | GET | Get student by ID |
| `/getalldata` | GET | Get all students |
| `/postdata` | POST | Create new student |
| `/updatedata` | PUT | Update student (also updates Grade Service) |
| `/deletedata/{id}` | DELETE | Delete student (also deletes from Grade Service) |

**Student Entity Fields:**
| Field | Validation |
|-------|------------|
| `studentId` | Auto-generated (Primary Key) |
| `studentName` | @NotEmpty |
| `studentAge` | @Min(15), @Max(30) |
| `studentEmail` | @Email |
| `studentAddress` | @NotEmpty |
| `studentPhoneNumber` | @Size(max=10) |
| `firstCourseName` | @NotEmpty |
| `secondCourseName` | @NotEmpty |

**Key Features:**
- Input validation using Bean Validation API
- Custom exception handling with `StudentNotFoundException`
- Feign Client integration with Grade Service for cascade operations
- Comprehensive logging with Log4j

---

### 3. Student Course Management Service
**Port:** `2222` | **Service Name:** `StudentCourseManagement`

Manages course catalog information with custom JPA queries.

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/getdata/{id}` | GET | Get course by ID |
| `/getid/{courseName}` | GET | Get course ID by name (Custom Query) |
| `/postdata` | POST | Add new course |
| `/updatedata` | PUT | Update course |
| `/deletedata/{id}` | DELETE | Delete course |

**Course Entity Fields:**
| Field | Validation |
|-------|------------|
| `courseId` | @NotNull (Primary Key) |
| `courseName` | @NotEmpty |

**Custom JPA Query:**
```java
@Query("SELECT courseId FROM Course WHERE courseName=:courseName")
Integer findByCourseName(String courseName);
```

---

### 4. Student Grade Management Service
**Port:** `3333` | **Service Name:** `GradeManagementService`

Manages student grades and enrollments. Uses **OpenFeign** for inter-service communication and has **Maven dependencies** on Student and Course services for shared entity access.

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/postmarksdata/{id}/{marks1}/{marks2}` | POST | Add marks for student |
| `/getmarkssheet/{id}` | GET | Get marks sheet by student ID |
| `/getAllStudentMarks` | GET | Get all students' marks |
| `/update/{id}/{name}` | PUT | Update student name in enrollment |
| `/deletemarkssheet/{id}` | DELETE | Delete marks sheet |

**Enrollment Entity Fields:**
| Field | Validation |
|-------|------------|
| `studentId` | @NotNull (Primary Key) |
| `studentName` | - |
| `marksInSubject1` | @Min(0), @Max(100) |
| `marksInSubject2` | @Min(0), @Max(100) |
| `course1Id`, `course2Id` | @NotNull |
| `courseName1`, `courseName2` | @NotNull |
| `grade` | Calculated (A+, A, B+, B, C, F) |
| `percentage` | Calculated |
| `status` | PASS/FAIL (>=100 marks = PASS) |

**Grade Calculation Logic:**
```
Total Marks >= 180  →  A+
Total Marks >= 160  →  A
Total Marks >= 140  →  B+
Total Marks >= 120  →  B
Total Marks >= 100  →  C (PASS)
Total Marks < 100   →  F (FAIL)
```

**Feign Clients:**
- `StudentServiceClient` → Fetches student info from Student Record Service
- `StudentCourseClient` → Fetches course ID from Course Management Service

---

### 5. Student API Gateway (Zuul)
**Port:** `4444`

Netflix Zuul API Gateway that routes all external requests to appropriate microservices.

**Key Annotations:**
- `@EnableZuulProxy` - Enables Zuul Gateway functionality
- `@EnableDiscoveryClient` - Registers with Eureka

**Route Configuration:**
| Route | Service | Description |
|-------|---------|-------------|
| `/api/service1/**` | StudentRecordManagement | Student CRUD operations |
| `/api/service2/**` | StudentCourseManagement | Course CRUD operations |
| `/api/service3/**` | GradeManagementService | Grade management operations |

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 1.8 | Programming Language |
| **Spring Boot** | 2.7.11 | Application Framework |
| **Spring Cloud** | 2021.0.7 | Microservices Infrastructure |
| **Netflix Eureka** | - | Service Discovery |
| **Netflix Zuul** | 2.2.10 | API Gateway |
| **OpenFeign** | - | Declarative REST Client |
| **Spring Data JPA** | - | Data Persistence |
| **Hibernate** | - | ORM Framework |
| **MySQL** | 8.0 | Relational Database |
| **Lombok** | - | Boilerplate Code Reduction |
| **Log4j** | - | Logging Framework |
| **Maven** | - | Build Tool |

---

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6+
- MySQL 8.0+

### Database Setup

```sql
CREATE DATABASE studentdb;
```

Update database credentials in each service's `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Build Order (Important!)

Since Grade Management Service depends on Student and Course services, build in this order:

```bash
# 1. Build Student Record Management Service
cd StudentRecordManagementSystem
mvn clean install

# 2. Build Course Management Service
cd ../StudentCourseManagementSystem
mvn clean install

# 3. Build Grade Management Service
cd ../StudentGradeManagementSystem
mvn clean install

# 4. Build other services
cd ../StudentManagementServer
mvn clean install

cd ../StudentAPIGateway
mvn clean install
```

### Running the Application

Start services in the following order:

```bash
# 1. Start Eureka Server (must be first)
cd StudentManagementServer
mvn spring-boot:run

# 2. Start Student Record Service
cd StudentRecordManagementSystem
mvn spring-boot:run

# 3. Start Course Management Service
cd StudentCourseManagementSystem
mvn spring-boot:run

# 4. Start Grade Management Service
cd StudentGradeManagementSystem
mvn spring-boot:run

# 5. Start API Gateway (last)
cd StudentAPIGateway
mvn spring-boot:run
```

### Verify Services

- **Eureka Dashboard:** http://localhost:8761
- **API Gateway:** http://localhost:4444/api/

---

## 📡 API Examples

### 1. Create a Course
```bash
POST http://localhost:4444/api/service2/postdata
Content-Type: application/json

{
    "courseId": 1,
    "courseName": "Mathematics"
}
```

### 2. Create a Student
```bash
POST http://localhost:4444/api/service1/postdata
Content-Type: application/json

{
    "studentName": "John Doe",
    "studentAge": 20,
    "studentEmail": "john@example.com",
    "studentAddress": "123 Main St",
    "studentPhoneNumber": "1234567890",
    "firstCourseName": "Mathematics",
    "secondCourseName": "Physics"
}
```

### 3. Post Student Marks
```bash
POST http://localhost:4444/api/service3/postmarksdata/1/85/90
```
Response: `Grade Calculated for the StudentId: 1`

### 4. Get Marks Sheet
```bash
GET http://localhost:4444/api/service3/getmarkssheet/1
```
Response:
```json
{
    "studentId": 1,
    "studentName": "John Doe",
    "marksInSubject1": 85,
    "marksInSubject2": 90,
    "course1Id": 1,
    "course2Id": 2,
    "courseName1": "Mathematics",
    "courseName2": "Physics",
    "grade": "A",
    "percentage": "87.5%",
    "status": "PASS"
}
```

---

## 📁 Project Structure

```
Student-Management-System/
├── StudentManagementServer/              # Eureka Discovery Server
│   └── src/main/java/com/cts/
│       ├── StudentManagementServerApplication.java
│       └── ServletInitializer.java
│
├── StudentRecordManagementSystem/        # Student CRUD Service
│   └── src/main/java/com/cts/
│       ├── controller/RequestHandler.java
│       ├── entity/Student.java
│       ├── dao/IStudentRecordManagement.java
│       ├── service/
│       │   ├── IStudentService.java
│       │   └── StudentServiceImpl.java
│       ├── client/StudentGradeClient.java    # Feign Client
│       ├── exceptionhandler/GlobalExceptionHandler.java
│       ├── studentNotFoundException/StudentNotFoundException.java
│       └── model/ErrorDetails.java
│
├── StudentCourseManagementSystem/        # Course Management Service
│   └── src/main/java/com/cts/
│       ├── controller/RequestHandler.java
│       ├── entity/Course.java
│       ├── dao/IStudentCourseManagement.java  # Custom Query
│       ├── service/
│       │   ├── ICourseService.java
│       │   └── CourseServiceImpl.java
│       ├── exceptionhandler/GlobalExceptionHandler.java
│       └── courseNotFoundException/CourseNotFoundException.java
│
├── StudentGradeManagementSystem/         # Grade Management Service
│   └── src/main/java/com/cts/
│       ├── controller/RequestHandler.java
│       ├── entity/Enrollment.java
│       ├── dao/IStudentGradeDao.java
│       ├── service/
│       │   ├── IStudentGradeService.java
│       │   └── StduentGradeServiceImpl.java
│       ├── client1/StudentServiceClient.java  # Feign Client
│       ├── client2/StudentCourseClient.java   # Feign Client
│       ├── exceptionhandler/GlobalExceptionHandler.java
│       └── IdNotFoundException/IdNotFoundException.java
│
└── StudentAPIGateway/                    # Zuul API Gateway
    └── src/main/java/com/cts/
        ├── StudentApiGatewayApplication.java
        └── ServletInitializer.java
```

---

## ✨ Key Features

- **Microservices Architecture** - Loosely coupled, independently deployable services
- **Service Discovery** - Dynamic service registration using Netflix Eureka
- **API Gateway** - Single entry point with routing using Netflix Zuul
- **Inter-Service Communication** - Declarative REST calls using OpenFeign
- **Cascade Operations** - Delete/Update operations propagate across services
- **Data Validation** - Input validation using Bean Validation API (@Valid, @NotEmpty, @Email, etc.)
- **Custom Exception Handling** - Global exception handlers with meaningful error responses
- **Logging** - Comprehensive logging using Log4j with file appenders
- **Grade Calculation** - Automatic grade and percentage calculation with pass/fail status

---

## 🔗 Service Dependencies

```
StudentGradeManagementSystem
    ├── depends on → StudentRecordManagementSystem (Maven dependency)
    └── depends on → StudentCourseManagementSystem (Maven dependency)

StudentRecordManagementSystem
    └── calls → GradeManagementService (Feign Client - cascade delete/update)

StudentGradeManagementSystem
    ├── calls → StudentRecordManagement (Feign Client - get student info)
    └── calls → StudentCourseManagement (Feign Client - get course ID)
```

---

## 👤 Author

**Saikiran MSD**

- GitHub: [@SaikiranMSD](https://github.com/SaikiranMSD)

---

## 📄 License

This project is for educational purposes.
