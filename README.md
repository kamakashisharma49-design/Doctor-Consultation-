<div align="center">

# 🩺 MediCare

### Doctor Consultation & Appointment Management System

<p>
  <strong>Connect • Consult • Care</strong>
</p>

<p>
  A Java Swing desktop application for connecting patients and doctors,
  managing appointments, authentication, profiles and email communication.
</p>

<p>
  <img src="https://img.shields.io/badge/Java-26-orange?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Java%20Swing-Desktop%20GUI-1F6FEB?style=for-the-badge">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/JDBC-Connectivity-007396?style=for-the-badge">
  <img src="https://img.shields.io/badge/JavaMail-Email-4285F4?style=for-the-badge">
  <img src="https://img.shields.io/badge/NetBeans-IDE-1B6AC1?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white">
</p>

<p>
  <a href="#-about">About</a> •
  <a href="#-features">Features</a> •
  <a href="#-workflow">Workflow</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-setup">Setup</a>
</p>

<p>
  <img src="https://img.shields.io/github/stars/kamakashisharma49-design/Doctor-Consultation-?style=flat-square">
  <img src="https://img.shields.io/github/forks/kamakashisharma49-design/Doctor-Consultation-?style=flat-square">
  <img src="https://img.shields.io/github/last-commit/kamakashisharma49-design/Doctor-Consultation-?style=flat-square">
</p>

</div>

---

## 🩺 About

**MediCare** is a desktop-based **Doctor Consultation & Appointment Management System** built using **Java Swing, JDBC and MySQL**.

The system provides separate experiences for **patients and doctors**, covering the complete appointment lifecycle — from doctor discovery and booking to appointment status updates and email notifications.

<div align="center">

| 👤 Patient | 👨‍⚕️ Doctor | 🔐 Security | 📧 Communication |
|:---:|:---:|:---:|:---:|
| Discover & book doctors | Manage appointments | OTP recovery | Email notifications |

</div>

---

## ✨ Features

### 👤 Patient Module

| Feature | Description |
|---|---|
| 🔐 Authentication | Registration, login & password management |
| 🔢 OTP Recovery | Email-based password recovery |
| 🩺 Doctor Discovery | Browse doctors by speciality |
| 👨‍⚕️ Doctor Profiles | View professional information |
| 📅 Appointments | Select date/time and book appointments |
| 📊 Booking Status | Track pending, accepted & declined bookings |
| 👤 Profile | Edit personal & contact information |
| 📧 Notifications | Appointment-related email updates |

### 👨‍⚕️ Doctor Module

| Feature | Description |
|---|---|
| 📝 Registration | Professional registration & speciality |
| 👤 Profile | Manage professional information |
| 📋 Requests | View patient appointment requests |
| ✅ Accept | Accept appointment requests |
| ❌ Decline | Decline appointment requests |
| 📅 Bookings | Track appointments |
| 📧 Notifications | Receive booking updates |

---

## 🩺 Specialities

<div align="center">

| ❤️ Cardiologist | 🧠 Neurologist | 🦴 Orthopedic |
|:---:|:---:|:---:|
| Heart & Cardiovascular Care | Nervous System Care | Bones & Musculoskeletal Care |

| 👶 Pediatrician | 🧴 Dermatologist | 🩺 General Physician |
|:---:|:---:|:---:|
| Children's Healthcare | Skin & Dermatological Care | General Healthcare |

</div>

---

## 📧 Email & OTP System

MediCare integrates email communication for authentication and appointment events.

```text
                    📧 EMAIL SERVICE
                          │
          ┌───────────────┼───────────────┐
          │               │               │
          ▼               ▼               ▼
       🔢 OTP        📅 Booking      👨‍⚕️ Doctor
      Recovery       Confirmation       Alert
          │               │               │
          └───────────────┼───────────────┘
                          │
                    Appointment
                      Updates
                          │
                 ┌────────┴────────┐
                 ▼                 ▼
              ✅ Accepted       ❌ Declined
