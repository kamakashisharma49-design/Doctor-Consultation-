<div align="center">

# 🩺 MediCare

### Doctor Consultation & Appointment Management System

**Connect • Consult • Care**

A Java Swing desktop application for connecting patients with doctors,
booking appointments, managing profiles, and handling appointment notifications.

<br>

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![Java Swing](https://img.shields.io/badge/Java-Swing-blue?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connectivity-green?style=for-the-badge)
![NetBeans](https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

</div>

---

## 📌 About

**MediCare** is a desktop-based Doctor Consultation System built using
**Java Swing and MySQL**. It provides separate modules for **patients and doctors**
with appointment booking, profile management, authentication, OTP password
recovery, and email notifications.

---

## ✨ Features

| 👤 Patient | 👨‍⚕️ Doctor |
|---|---|
| 🔐 Registration & Login | 📝 Registration & Login |
| 🔑 Password Management | 🔑 Password Management |
| 🔢 OTP Password Recovery | 🔢 OTP Password Recovery |
| 👤 Profile Management | 👤 Profile Management |
| 🩺 Browse Doctors | 🩺 Speciality Profile |
| 📋 View Doctor Details | 📋 View Appointment Requests |
| 📅 Book Appointment | ✅ Accept Appointment |
| ⏰ Select Date & Time | ❌ Decline Appointment |
| 📊 View Booking History | 🖼️ Profile Picture |
| 📩 Email Notifications | 📩 Email Notifications |

---

## 🩺 Available Specialities

| ❤️ Cardiologist | 🧠 Neurologist | 🦴 Orthopedic |
|:---:|:---:|:---:|
| Heart Care | Neurological Care | Bone & Joint Care |

| 🧴 Dermatologist | 👶 Pediatrician | 🩺 General Physician |
|:---:|:---:|:---:|
| Skin Care | Child Care | General Medical Care |

---

## 🔄 Application Workflow

| Step | Patient | Doctor |
|---|---|---|
| 1 | Register / Login | Register / Login |
| 2 | Browse Specialities | Manage Profile |
| 3 | Select Doctor | View Requests |
| 4 | Select Date & Time | Accept / Decline |
| 5 | Book Appointment | Update Status |
| 6 | Receive Notification | Receive Notification |
| 7 | Track Booking Status | — |

---

## 🏗️ System Architecture

| Layer | Technology | Responsibility |
|---|---|---|
| 🖥️ Presentation | Java Swing | User Interface |
| ⚙️ Application | Java | Business Logic |
| 🔌 Connectivity | JDBC | Database Communication |
| 🗄️ Database | MySQL | Data Storage |
| 📧 Communication | JavaMail | Email & OTP |
| 🧰 Development | NetBeans | Project Development |
| 🏗️ Build | Apache Ant | Project Build |

---

## 🗄️ Database

| Property | Details |
|---|---|
| Database | MySQL |
| Database Name | `doctorconsultationsystem` |
| Connectivity | JDBC |
| Operations | CRUD |
| Main Data | Users, Doctors, Bookings, Profiles |
| Image Support | Doctor Profile Pictures |

### Database Flow

```text
Java Swing
    ↓
Java Application Logic
    ↓
JDBC
    ↓
MySQL
