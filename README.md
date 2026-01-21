# 🌐 Municipal IT Division - Complaint Management System (CMS)

### JakartaEE Project 2025 - IJSE 72 Tharusha Sandaruwan Galle

---

## 📌 Project Overview

The **Complaint Management System (CMS)** is a web-based application developed using **JSP, Jakarta EE (Servlets), MySQL, and Apache Tomcat**. It is part of the **Advanced API Development (IJSE 72)** individual assignment and demonstrates key enterprise web development concepts including:

- **Role-based access**
- **Form-based synchronous interactions**
- **MVC architecture**

### User Roles

- **Employee**: Can submit, view, update, or delete their own complaints (if unresolved).
- **Admin**: Can view all complaints, update complaint statuses with remarks, and delete any complaint.

---

## ✨ Key Features

### 🧑 Employee
- ✅ Register & log in
- ✅ Submit new complaints via **Complaint Form**
- ✅ View list of submitted complaints
- ✅ Edit or delete complaints (if not resolved or in progress)
- ![Employee Complaint](web/view/images/Employee%20Complaint.png)

### 👨‍💼 Admin
- ✅ Log in with admin credentials
- ✅ View **All Complaints** submitted by all users
- ✅ Update complaint status and add remarks
- ✅ **Admin Dashboard**: Quick overview of complaints and statistics

### 🔒 Security & UX
- 🔐 Session management to restrict unauthorized access
- ⚠ SweetAlert2 integration for clean and modern alerts
- ❌ No AJAX used (strict GET/POST via forms only)
- 🌐 MVC-compliant, modular code

---

## 🖼 Screenshots / Demo

| Feature | Screenshot                                                      |
|---------|-----------------------------------------------------------------|
| **Admin Dashboard** | ![Admin Dashboard](web/view/images/Admin%20Dashboard.png)       |
| **All Complaints** | ![All Complaints](web/view/images/All%20Compaints.png)          |
| **Complaint Form** | ![Complaint Form](web/view/images/Complaint%20form.png)         |
| **Employee Complaint** | ![Employee Complaint](web/view/images/Employee%20Complaint.png) |

**YouTube Demo Video**:  
[![Watch Demo](https://img.youtube.com/vi/7zMqkyEsu80/0.jpg)](https://www.youtube.com/watch?v=7zMqkyEsu80)

---

## 🛠 Technologies Used

| Category       | Tools / Technologies               |
|----------------|-----------------------------------|
| Language       | Java 21                           |
| Web            | JSP, Servlets, HTML, CSS          |
| Database       | MySQL, JDBC, Apache Commons DBCP  |
| Architecture   | MVC (Model-View-Controller)       |
| Server         | Apache Tomcat 9+                  |
| Validation     | JavaScript                        |
| Alerts         | SweetAlert2                       |
| Versioning     | Git + GitHub                      |
| Build Tool     | Maven                             |

---

## 🧱 Project Directory Structure

