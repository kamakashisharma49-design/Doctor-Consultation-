# 🩺 MediCare — Doctor Consultation System

<p align="center">
  <strong>Connect • Consult • Care</strong><br>
  A desktop-based doctor consultation and appointment management system built with Java Swing and MySQL.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-Desktop%20Application-orange?style=for-the-badge&logo=openjdk" alt="Java">
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white" alt="NetBeans">
</p>

---

## 📌 About the Project

**MediCare** is a desktop-based **Doctor Consultation System** designed to simplify the process of connecting patients with doctors and managing appointments.

The application provides separate experiences for **patients/users** and **doctors**. Users can create accounts, discover doctors by speciality, book appointments, view booking status, and manage their profiles. Doctors can register, maintain their profiles, review appointment requests, and accept or decline bookings.

The system also integrates **email notifications and OTP-based password recovery**, making it a complete and practical healthcare appointment-management solution.

<table align="center">
  <tr>
    <td align="center"><strong>📱 Project Type</strong></td>
    <td align="center"><strong>🏗️ Architecture</strong></td>
    <td align="center"><strong>🗄️ Database</strong></td>
  </tr>
  <tr>
    <td align="center">Java Desktop Application</td>
    <td align="center">GUI + Database Driven</td>
    <td align="center">MySQL</td>
  </tr>
</table>

---

## ✨ Key Features

### 👤 Patient / User Module

<ul>
  <li>🔐 User registration and login</li>
  <li>📧 OTP-based password reset</li>
  <li>🔑 Change and reset password functionality</li>
  <li>👤 Edit and manage user profile</li>
  <li>🩺 Browse doctors by speciality</li>
  <li>📋 View doctor information</li>
  <li>📅 Select appointment date and time</li>
  <li>📝 Book doctor appointments</li>
  <li>📊 View appointment history and status</li>
  <li>📩 Receive email notifications for appointment events</li>
</ul>

### 👨‍⚕️ Doctor Module

<ul>
  <li>📝 Doctor registration</li>
  <li>🔐 Doctor login</li>
  <li>📧 OTP-based password recovery</li>
  <li>👤 Edit doctor profile</li>
  <li>🩺 Doctor speciality-based profiles</li>
  <li>📋 View incoming appointment requests</li>
  <li>✅ Accept appointment requests</li>
  <li>❌ Decline appointment requests</li>
  <li>📩 Receive email notifications for new bookings</li>
  <li>🖼️ Doctor profile-picture support</li>
</ul>

### 📧 Email & OTP System

The application includes an email communication layer using JavaMail functionality.

It supports:

<ul>
  <li>🔢 One-time passwords for password recovery</li>
  <li>📩 Appointment request confirmation to patients</li>
  <li>📨 New booking notifications to doctors</li>
  <li>✅ Appointment acceptance notifications</li>
  <li>❌ Appointment decline notifications</li>
</ul>

---

## 🩺 Available Specialities

<table align="center">
  <tr>
    <td align="center">❤️<br><strong>Cardiologist</strong></td>
    <td align="center">🧠<br><strong>Neurologist</strong></td>
    <td align="center">🦴<br><strong>Orthopedic</strong></td>
  </tr>
  <tr>
    <td align="center">🧴<br><strong>Dermatologist</strong></td>
    <td align="center">👶<br><strong>Pediatrician</strong></td>
    <td align="center">🩺<br><strong>General Physician</strong></td>
  </tr>
</table>

---

## 🔄 Application Workflow

<pre>
                    ┌─────────────────────┐
                    │      🩺 MediCare     │
                    │ Doctor Consultation │
                    └──────────┬──────────┘
                               │
                  ┌────────────┴────────────┐
                  │                         │
             👤 User                     👨‍⚕️ Doctor
                  │                         │
          Register / Login          Register / Login
                  │                         │
          Browse Specialities       Manage Profile
                  │                         │
          Select Doctor             View Requests
                  │                         │
       Select Date & Time          Accept / Decline
                  │
             Book Slot
                  │
                  └──────────┬──────────────┘
                             │
                     📧 Email Updates
                             │
                     📊 Booking Status
</pre>

---

## 🛠️ Technology Stack

<table align="center">
  <tr>
    <td align="center" width="160">
      ☕<br>
      <strong>Java</strong>
    </td>
    <td align="center" width="160">
      🖥️<br>
      <strong>Java Swing</strong>
    </td>
    <td align="center" width="160">
      🗄️<br>
      <strong>MySQL</strong>
    </td>
    <td align="center" width="160">
      🔌<br>
      <strong>JDBC</strong>
    </td>
  </tr>

  <tr>
    <td align="center">Core Application</td>
    <td align="center">Desktop GUI</td>
    <td align="center">Database</td>
    <td align="center">DB Connectivity</td>
  </tr>

  <tr>
    <td align="center" width="160">
      📧<br>
      <strong>JavaMail</strong>
    </td>
    <td align="center" width="160">
      🧰<br>
      <strong>NetBeans</strong>
    </td>
    <td align="center" width="160">
      🏗️<br>
      <strong>Apache Ant</strong>
    </td>
    <td align="center" width="160">
      🔐<br>
      <strong>OTP</strong>
    </td>
  </tr>

  <tr>
    <td align="center">Email & OTP</td>
    <td align="center">Development IDE</td>
    <td align="center">Build System</td>
    <td align="center">Authentication</td>
  </tr>
</table>

---

## 🗃️ Database Design

The project uses a MySQL database named:

```text
doctor_consultation
