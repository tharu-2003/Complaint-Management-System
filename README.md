# 🌐 Municipal IT Division - Complaint Management System (CMS)

### JakartaEE Project 2025 - IJSE 72 Tharusha Sandaruwan Galle

---

## 📌 Project Overview

This project is a web-based *Complaint Management System (CMS)* developed using *JSP, **Jakarta EE (Servlets), **MySQL, and **Apache Tomcat. It is part of the Advanced API Development individual assignment and demonstrates key enterprise web development concepts including **role-based access, **form-based synchronous interactions, and the **MVC pattern*.

The system enables two roles:
- *Employee*: Can submit, view, update, or delete their own complaints (if unresolved).
- *Admin*: Can view all complaints, update complaint statuses with remarks, and delete any complaint.

---

## ✨ Key Features

### 🧑 Employee
- ✅ Register & log in
- ✅ Submit new complaints
- ✅ View list of submitted complaints
- ✅ Edit or delete complaints (if not resolved or in progress)

### 👨‍💼 Admin
- ✅ Log in with admin credentials
- ✅ View all complaints from all users
- ✅ Update complaint status and add remarks

### 🔒 Security & UX
- 🔐 Session management to restrict unauthorized access
- ⚠ SweetAlert2 integration for clean and modern alerts
- ❌ No AJAX used (strict GET/POST via forms only)
- 🌐 MVC-compliant, modular code

## 🛠 Technologies Used

| Category       | Tools/Technologies                |
|----------------|-----------------------------------|
| Language       | Java 21                           |
| Web            | JSP, Servlets, HTML, CSS          |
| DB Access      | MySQL, JDBC, Apache Commons DBCP  |
| Architecture   | MVC (Model-View-Controller)       |
| Server         | Apache Tomcat 9+                  |
| Validation     | JavaScript                        |
| Alerts         | SweetAlert2                       |
| Versioning     | Git + GitHub                      |
| Build          | Maven                             |

---

## 🧱 Project Directory Structure

- *Complaint_Management_System/*
  - *src/*
    - *controller/* &nbsp;&nbsp;# Servlets for handling requests
    - *dao/* &nbsp;&nbsp;# DAO classes for DB access
    - *model/* &nbsp;&nbsp;# POJOs / JavaBeans
    - *util/* &nbsp;&nbsp;# DBCP config and utilities
  - *web/*
    - *WEB-INF/* &nbsp;&nbsp;# Web app config files
    - *web.xml* &nbsp;&nbsp;# Deployment descriptor
    - *view/* &nbsp;&nbsp;# JSP files (views)
    - *css/* &nbsp;&nbsp;# Styling
    - *js/* &nbsp;&nbsp;# JS for validation and SweetAlert
  - *db/*
    - *schema.sql* &nbsp;&nbsp;# MySQL schema dump
  - *README.md*
  - *pom.xml*

## ⚙ Setup and Configuration Guide

### 📋 Prerequisites

- Java 11+
- Apache Tomcat 9+
- MySQL 8 or newer
- Git
- IDE (e.g., IntelliJ IDEA / Eclipse)
-  Maven

### 🧪 Installation & Run Instructions

```bash
# 1. Clone the project
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

# 2. Import the database schema into MySQL
mysql -u root -p < db/schema.sql

# 3. Configure DB connection
Edit DBCPDataSource.java with your DB credentials

# 4. Deploy to Apache Tomcat (via WAR or IDE integration)

# 5. Run
Access the system at:
http://localhost:8080/cms-jsp-project/
