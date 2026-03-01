# Issue Tracking System API

## 📌 Description
Issue Tracking System adalah REST API berbasis Spring Boot untuk mengelola ticket seperti bug, task, atau request dalam sebuah tim.

Sistem ini mendukung:

- JWT Authentication
- Role-based Authorization (Admin & Regular User)
- Ticket Management
- User Management
- Ticket Assignment
- Filtering, Sorting & Pagination
- Logout dengan Token Blacklist
- soft delete
- simple audit trail

API ini dirancang dengan prinsip:

- Secure
- Scalable
- Role-based access

---

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- JPA / Hibernate
- Maven
- Swagger (OpenAPI)

---

## 🚀 Features

### 🔐 Authentication
- Login → generate JWT token
- Logout → token di blacklist

---

### 👤 User Management
- Create User
- Get User Detail
- Update User
- Delete User

---

### 🎫 Ticket Management
- Create Ticket
- Assign Ticket
- Update Ticket
- Change Status
- Change Priority
- Delete Ticket
- View Ticket Detail
- View Tickets List

---

### 🔍 Filtering & Pagination

Endpoint `/api/tickets` mendukung:

**Filter:**
- status
- priority
- created_by
- assigned_to
- assigned_by

**Sorting:**
- sort_by
- sort_direction

**Pagination:**
- limit
- offset

**Search:**
- keyword

---

## 📡 API Documentation

Detail request & response dapat diakses melalui Swagger setelah aplikasi dijalankan:

http://localhost:8080/swagger-ui/index.html


---

## 📡 API Endpoints & Role Access

### 🔑 Auth

| Method | Endpoint          | Description | Admin | User |
|--------|------------------|-------------|-------|------|
| POST   | /api/auth/login  | Login       | ✅    | ✅   |
| DELETE | /api/auth/logout | Logout      | ✅    | ✅   |

---

### 👤 User

| Method | Endpoint           | Description     | Admin | User |
|--------|--------------------|-----------------|-------|------|
| POST   | /api/user          | Create User     | ✅    | ❌   |
| GET    | /api/user/{idUser} | Get User Detail | ✅    | ✅   |
| PUT    | /api/user/{idUser} | Update User     | ✅    | ❌   |
| DELETE | /api/user/{idUser} | Delete User     | ✅    | ❌   |

---

### 🎫 Ticket

| Method | Endpoint                         | Description                       | Admin | User |
|--------|----------------------------------|-----------------------------------|-------|------|
| POST   | /api/ticket                      | Create Ticket                     | ✅    | ❌   |
| PUT    | /api/ticket/{idTicket}/assign    | Assign Ticket                     | ✅    | ❌   |
| PUT    | /api/ticket/{idTicket}/status    | Change Status                     | ✅    | ✅   |
| PUT    | /api/ticket/{idTicket}/priority  | Change Priority                   | ✅    | ❌   |
| PUT    | /api/ticket/{idTicket}/update    | Update Ticket                     | ✅    | ❌   |
| GET    | /api/tickets                     | Get Tickets (filter & pagination) | ✅    | ✅   |
| GET    | /api/ticket/{idTicket}           | Get Ticket Detail                 | ✅    | ✅   |
| DELETE | /api/ticket/{idTicket}/delete    | Delete Ticket                     | ✅    | ❌   |

---

## 🔑 Authorization

Gunakan JWT Token pada setiap request (kecuali login):

Authorization: Bearer <your_token>

Token didapat dari endpoint login.

---

### 🔐 Dummy Accounts

- **Admin**

username: edo_user

password: password123

- **Regular User**

username: andi_user

password: password123

---

## ▶️ Run Locally
### 1. Clone Repository
git clone https://github.com/username/issue-tracking-system.git

### 2. Setup PostgreSQL Database
Buat database dengan nama:
db_issuetrackingsystem

### 3. Configure `application.propertise`
Tambahkan:
spring.datasource.url=jdbc:postgresql://localhost:5432/db_issuetrackingsystem
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

### 4. Run Project
mvn spring-boot:run


Data dummy akan otomatis ter-generate melalui file `data.sql`.
di \src\main\resources\data.sql

---

## 🧱 Architecture
Controllers -> Handle HTTP Request & Authorization  
Services -> Business Logic  
Repositories -> Database Access  
Entities -> Representasi tabel database  
DTO -> Request & Response Contract  
Exceptions -> Centralized Error Handling  
Helper -> Reusable Logic  
Security -> JWT & Role Authorization  
Utils -> General Helper Tools

---

## 🚧 Future Improvements

- **Deadline Feature**  
  Menambahkan due date pada setiap ticket

- **Group By Ticket**  
  Group ticket berdasarkan status & priority

- **Ownership Validation**  
  Validasi agar user hanya bisa mengakses ticket miliknya

- **Comment Feature**  
  Menambahkan diskusi pada ticket

- **Dashboard Monitoring**  
  Ringkasan ticket (open, in-progress, done)

- **Email Notification**  
  Notifikasi saat ticket di-assign / status berubah

- **Ticket Attachment**  
  Upload file pendukung (screenshot, dll)

- **Audit Trail**  
  Tracking perubahan data (status, assignee, update)

