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

The application provides separate experiences for **patients/users** and **doctors**.

Users can:

- Create accounts
- Login securely
- Discover doctors by speciality
- View doctor information
- Book appointments
- Select appointment date and time
- View booking status
- Manage their profiles
- Reset forgotten passwords using OTP

Doctors can:

- Register their accounts
- Login to the system
- Maintain their profiles
- Add speciality and professional information
- View appointment requests
- Accept or decline bookings
- Receive email notifications

The system also integrates **email notifications and OTP-based password recovery**, making the application more complete and practical as a healthcare appointment-management solution.

### 📋 Project Information

<table align="center">
  <tr>
    <th>Category</th>
    <th>Details</th>
  </tr>
  <tr>
    <td>📱 Project Type</td>
    <td>Java Desktop Application</td>
  </tr>
  <tr>
    <td>🏗️ Architecture</td>
    <td>GUI + Database-driven Application</td>
  </tr>
  <tr>
    <td>☕ Programming Language</td>
    <td>Java</td>
  </tr>
  <tr>
    <td>🖥️ GUI Framework</td>
    <td>Java Swing</td>
  </tr>
  <tr>
    <td>🗄️ Primary Database</td>
    <td>MySQL</td>
  </tr>
  <tr>
    <td>🔌 Database Connectivity</td>
    <td>JDBC</td>
  </tr>
</table>

---

## ✨ Key Features

### 👤 Patient / User Module

<ul>
  <li>🔐 User registration and login</li>
  <li>📧 OTP-based password reset</li>
  <li>🔑 Change/reset password functionality</li>
  <li>👤 Edit and manage user profile</li>
  <li>🩺 Browse doctors by speciality</li>
  <li>📋 View doctor information</li>
  <li>📅 Select appointment date and time</li>
  <li>📝 Book doctor appointments</li>
  <li>📊 View appointment history/status</li>
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

The application includes an email communication layer using **JavaMail functionality**.

It supports:

<ul>
  <li>🔢 One-time passwords for password recovery</li>
  <li>📩 Appointment request confirmation to patients</li>
  <li>📨 New booking notifications to doctors</li>
  <li>✅ Appointment acceptance notifications</li>
  <li>❌ Appointment decline notifications</li>
</ul>

OTP generation is handled through a dedicated utility class.

---

## 🩺 Available Specialities

The current project includes dedicated doctor browsing screens for:

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
                    │      MediCare       │
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
                  │                         │
             Book Slot                  │
                  │                         │
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
    <td align="center">
      Core Application
    </td>

    <td align="center">
      Desktop GUI
    </td>

    <td align="center">
      Database
    </td>

    <td align="center">
      DB Connectivity
    </td>
  </tr>

  <tr>
    <td align="center" width="160">
      📧<br>
      <strong>JavaMail</strong>
    </td>

    <td align="center" width="160">
      🧰<br>
      <strong>Apache NetBeans</strong>
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
    <td align="center">
      Email & OTP
    </td>

    <td align="center">
      Development IDE
    </td>

    <td align="center">
      Build System
    </td>

    <td align="center">
      Authentication
    </td>
  </tr>
</table>

---

## 🗃️ Database Design

The project contains SQL dumps for the **doctor_consultation** database.

### 👨‍⚕️ `doctor`

Stores doctor information such as:

<ul>
  <li>Email</li>
  <li>Full name</li>
  <li>Password</li>
  <li>Speciality</li>
  <li>Experience</li>
  <li>Address</li>
  <li>Consultation fee</li>
  <li>Phone number</li>
  <li>Gender</li>
  <li>Profile picture</li>
</ul>

### 👤 `user`

Stores patient/user information such as:

<ul>
  <li>User ID</li>
  <li>Full name</li>
  <li>Email</li>
  <li>Password</li>
  <li>Phone</li>
  <li>Gender</li>
  <li>Blood group</li>
  <li>Date of birth</li>
  <li>Age</li>
  <li>Address</li>
</ul>

### 📅 `bookings`

Stores appointment information such as:

<ul>
  <li>Booking ID</li>
  <li>Doctor email</li>
  <li>Patient email</li>
  <li>Patient name</li>
  <li>Appointment date</li>
  <li>Appointment time</li>
  <li>Booking status</li>
  <li>Gender</li>
  <li>Age</li>
  <li>Blood group</li>
  <li>Phone</li>
</ul>

> 💡 The booking table also contains a **uniqueness constraint for a doctor's date/time slot** to help prevent duplicate bookings for the same slot.

---

## 📂 Project Structure

The project is organized into separate screens for users, doctors, authentication, speciality-based doctor browsing, database connectivity, and email/OTP services.

<details>
<summary><strong>📁 Click to expand project structure</strong></summary>

<br>

<pre>
DoctorConsultationSystem/
│
├── 📁 src/
│   │
│   ├── 🏠 WelcomePage.java
│   │
│   ├── 👤 User Module
│   │   ├── UserLogin.java
│   │   ├── UserRegisteration.java
│   │   ├── UserHome.java
│   │   ├── UserBooking.java
│   │   ├── UserViewBooking.java
│   │   ├── UserEditProfile.java
│   │   ├── UserForgotPassword.java
│   │   ├── UserOTPVerification.java
│   │   ├── UserResetPassword.java
│   │   ├── UserNewPassword.java
│   │   └── UserChangePassword.java
│   │
│   ├── 👨‍⚕️ Doctor Module
│   │   ├── DoctorLogin.java
│   │   ├── DoctorRegisteration.java
│   │   ├── DoctorHomePage.java
│   │   ├── DoctorViewBookings.java
│   │   ├── DoctorEditProfile.java
│   │   ├── DoctorForgotPassword.java
│   │   ├── DoctorOTPVerification.java
│   │   └── DoctorResetPassword.java
│   │
│   ├── 🩺 Speciality Screens
│   │   ├── Cardiologist.java
│   │   ├── Dermatologist.java
│   │   ├── GeneralPhysician.java
│   │   ├── Neurologist.java
│   │   ├── Orthopedic.java
│   │   └── Pediatrician.java
│   │
│   └── ⚙️ Utility & Services
│       ├── DBLoader.java
│       ├── EmailSender.java
│       └── OTPUtil.java
│
├── 📁 uploads/
│   └── 🖼️ doctor profile images
│
├── 📁 nbproject/
├── 📄 build.xml
└── 📄 manifest.mf
│
└── 📁 Dump20260822/
    ├── doctor_consultation_doctor.sql
    ├── doctor_consultation_user.sql
    └── doctor_consultation_bookings.sql
</pre>

</details>

---

# 🚀 Getting Started

Follow the steps below to set up and run the **Doctor Consultation System** on your local machine.

---

<details>
<summary><strong>1️⃣ Prerequisites</strong></summary>

<br>

Make sure the following software and dependencies are installed:

<table align="center">
  <tr>
    <th>Requirement</th>
    <th>Purpose</th>
  </tr>

  <tr>
    <td>☕ <strong>JDK 26</strong></td>
    <td>Run and compile the Java application</td>
  </tr>

  <tr>
    <td>🧰 <strong>Apache NetBeans</strong></td>
    <td>Open and develop the project</td>
  </tr>

  <tr>
    <td>🗄️ <strong>MySQL Server</strong></td>
    <td>Store application data</td>
  </tr>

  <tr>
    <td>🔌 <strong>MySQL Connector/J</strong></td>
    <td>Connect Java with MySQL</td>
  </tr>

  <tr>
    <td>📧 <strong>JavaMail / Jakarta Mail</strong></td>
    <td>Email notifications and OTP functionality</td>
  </tr>
</table>

<br>

> 💡 **Note:** The project was configured in NetBeans with **Java source/target level 26**.

</details>

---

<details>
<summary><strong>2️⃣ Clone the Repository</strong></summary>

<br>

Open your terminal and run:

```bash
git clone https://github.com/kamakashisharma49-design/Doctor-Consultation-.git
cd Doctor-Consultation-
