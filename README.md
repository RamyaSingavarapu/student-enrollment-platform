# student-enrollment-platform

Backend service for student registration using Spring Boot MVC and REST, featuring full CRUD operations and clean layered architecture.

## Features

- ✅ Register a new student
- ✅ View all students (student list)
- ✅ View a single student by ID
- ✅ Update student details
- ✅ Delete a student
- ✅ REST API (JSON) + MVC views (Thymeleaf)

---

## Tech Stack

- Java 17
- Spring Boot (Web, Spring MVC)
- Thymeleaf (for HTML views)
- Maven
- MongoDB

---

## REST API Endpoints

| Method | Endpoint              | Description              |
|--------|-----------------------|--------------------------|
| GET    | `/student-list`       | Get all students         |
| GET    | `/student/{id}`       | Get student by ID        |
| POST   | `/create-student`     | Create new student       |
| PUT    | `/students/edit/{id}` | Update existing student  |
| DELETE | `/student/{id}`       | Delete student by ID     |

---

