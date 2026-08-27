🩺 MediCare — Doctor Consultation System

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

⸻
📌 About the Project

MediCare is a desktop-based Doctor Consultation System designed to simplify the process of connecting patients with doctors and managing appointments.

The application provides separate experiences for patients/users and doctors. Users can create accounts, discover doctors by speciality, book appointments, view booking status, and manage their profiles. Doctors can register, maintain their profiles, review appointment requests, and accept or decline bookings.

The system also integrates email notifications and OTP-based password recovery, making the application more complete and practical as a healthcare appointment-management solution.

Project type: Java Desktop Application

Architecture: GUI + Database-driven application

Primary database: MySQL
⸻
✨ Key Features

👤 Patient / User Module

- 🔐 User registration and login
- 📧 OTP-based password reset
- 🔑 Change/reset password functionality
- 👤 Edit and manage user profile
- 🩺 Browse doctors by speciality
- 📋 View doctor information
- 📅 Select appointment date and time
- 📝 Book doctor appointments
- 📊 View appointment history/status
- 📩 Receive email notifications for appointment events

👨‍⚕️ Doctor Module

- 📝 Doctor registration
- 🔐 Doctor login
- 📧 OTP-based password recovery
- 👤 Edit doctor profile
- 🩺 Doctor speciality-based profiles
- 📋 View incoming appointment requests
- ✅ Accept appointment requests
- ❌ Decline appointment requests
- 📩 Receive email notifications for new bookings
- 🖼️ Doctor profile-picture support

📧 Email & OTP System

The application includes an email communication layer using JavaMail functionality.

It supports:

- One-time passwords for password recovery
- Appointment request confirmation to patients
- New booking notifications to doctors
- Appointment acceptance notifications
- Appointment decline notifications

OTP generation is handled through a dedicated utility class.
⸻
🩺 Available Specialities

The current project includes dedicated doctor browsing screens for:
Speciality	
❤️ Cardiologist	🧠 Neurologist
🦴 Orthopedic	👶 Pediatrician
🧴 Dermatologist	🩺 General Physician
⸻
🔄 Application Workflow

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

⸻
🛠️ Technology Stack
Technology	Purpose
☕ Java	Core application development
🖥️ Java Swing	Desktop graphical user interface
🗄️ MySQL	Persistent data storage
🔌 JDBC	Java–MySQL database connectivity
📧 JavaMail	Email and OTP communication
🧰 Apache NetBeans	Development environment
🏗️ Ant	Project build system
⸻
🗃️ Database Design

The project contains SQL dumps for the doctor_consultation database.

Main tables

doctor

Stores doctor information such as:

- Email
- Full name
- Password
- Speciality
- Experience
- Address
- Consultation fee
- Phone number
- Gender
- Profile picture

user

Stores patient/user information such as:

- User ID
- Full name
- Email
- Password
- Phone
- Gender
- Blood group
- Date of birth
- Age
- Address

bookings

Stores appointment information such as:

- Booking ID
- Doctor email
- Patient email
- Patient name
- Appointment date
- Appointment time
- Booking status
- Gender
- Age
- Blood group
- Phone

The booking table also contains a uniqueness constraint for a doctor's date/time slot to help prevent duplicate bookings for the same slot.
⸻
📂 Project Structure

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

⸻
🚀 Getting Started

1️⃣ Prerequisites

Make sure you have installed:

- JDK 26 or a compatible Java version supported by the project
- Apache NetBeans
- MySQL Server
- MySQL Connector/J
- JavaMail / Jakarta Mail dependencies required by the project

The project was configured in NetBeans with Java source/target level 26.
⸻
2️⃣ Clone the Repository

git clone https://github.com/kamakashisharma49-design/Doctor-Consultation-.git
cd Doctor-Consultation-

⸻
3️⃣ Set Up MySQL

Create/import the database using the SQL files provided in:

Dump20260822/


Import:

doctor_consultation_doctor.sql
doctor_consultation_user.sql
doctor_consultation_bookings.sql


The application expects the database:

doctor_consultation

⸻
4️⃣ Configure Database Credentials

Before running the application, open:

src/DBLoader.java


and configure your local MySQL connection.

Example:

DriverManager.getConnection(
    "jdbc:mysql://127.0.0.1:3306/doctor_consultation",
    "YOUR_USERNAME",
    "YOUR_PASSWORD"
);


⚠️ Security note: Never commit real database passwords or credentials to GitHub. Use environment variables or a local configuration file that is excluded through .gitignore.
⸻
5️⃣ Configure Email / OTP

The email functionality is implemented in:

src/EmailSender.java


Configure your own Gmail SMTP credentials using a Gmail App Password, not your normal Gmail password.

For a public GitHub repository, credentials should be stored outside the source code.

A safer approach is:

Environment Variables
        ↓
EmailSender.java
        ↓
Gmail SMTP
        ↓
User / Doctor

⸻
6️⃣ Open the Project

Open the project in Apache NetBeans:

DoctorConsultationSystem/


Allow NetBeans to load the Ant project and required dependencies.
⸻
7️⃣ Run the Application

Run the configured main class / application entry point from NetBeans.

The application starts with the MediCare welcome page, from where users can proceed to the appropriate user or doctor workflow.
⸻
🔐 Security Considerations

This project is currently designed as an academic/learning desktop application.

Before using it in a production healthcare environment, additional security measures should be implemented, including:

- 🔒 Password hashing instead of storing plain-text passwords
- 🔑 Secure secret management
- 🛡️ Prepared statements for all database operations
- 🔐 Stronger authentication and authorization
- ⏱️ Server-side OTP expiration and rate limiting
- 🧹 Removal of test credentials/data from production builds
- 🔒 Encryption for sensitive patient information
- 📝 Audit logging
- 🏥 Compliance with applicable healthcare data-protection requirements
⸻
🎯 Project Objectives

The project was developed to demonstrate how a complete desktop application can combine:

- Object-oriented programming
- GUI development
- Database connectivity
- CRUD operations
- Authentication
- Appointment management
- Email communication
- OTP verification
- User/doctor workflows
- File-based profile-image handling
⸻
🔮 Future Enhancements

Potential improvements include:

- 📱 Android/iOS mobile application
- 🌐 Web-based version
- 💬 Real-time doctor–patient chat
- 🎥 Video consultation
- 💳 Online payment integration
- 📄 Digital prescription management
- ⭐ Doctor ratings and reviews
- 🔎 Advanced doctor search and filtering
- 📊 Admin dashboard and analytics
- 🔔 In-app notifications
- 🧠 AI-assisted symptom guidance
- ☁️ Cloud database and deployment
- 🔐 Production-grade authentication and encryption
⸻
🌟 Why MediCare?

MediCare brings the core components of a doctor appointment platform into one desktop application:

Find a doctor → Choose a suitable slot → Book an appointment → Track its status → Receive email updates

The project demonstrates practical integration of Java GUI development, MySQL, JDBC, authentication, appointment workflows, and email services in a single application.
⸻
👩‍💻 Author

Kamakshi Sharma

B.Tech — Computer Science & Engineering

Interested in:

- 💻 Software Development
- 🧩 Data Structures & Algorithms
- 🔐 Cybersecurity
- ☁️ Cloud & Network Security
⸻
🤝 Contributing

Contributions, suggestions, and improvements are welcome.

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Commit your changes
5. Push the branch
6. Open a Pull Request
⸻
⭐ Support

If you find this project useful or interesting, consider giving the repository a ⭐ on GitHub.

<p align="center">
  <strong>🩺 MediCare</strong><br>
  <em>Connect • Consult • Care</em>
</p>
