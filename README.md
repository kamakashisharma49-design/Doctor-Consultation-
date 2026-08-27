<div align="center">

# 🩺 MediCare

### Doctor Consultation & Appointment Management System

<p>
  <strong>Connect • Consult • Care</strong>
</p>

<p>
  A modern Java Swing desktop application that connects
  <strong>patients, doctors, appointments, authentication,
  email communication, and MySQL</strong> into one integrated
  healthcare appointment management system.
</p>

<br>

<p>
  <img src="https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk" alt="Java">
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/JDBC-Connectivity-green?style=for-the-badge" alt="JDBC">
  <img src="https://img.shields.io/badge/JavaMail-Email-red?style=for-the-badge" alt="JavaMail">
  <img src="https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white" alt="NetBeans">
</p>

<p>
  <img src="https://img.shields.io/badge/Apache%20Ant-Build%20Tool-orange?style=for-the-badge" alt="Apache Ant">
  <img src="https://img.shields.io/badge/OTP-Authentication-purple?style=for-the-badge" alt="OTP">
  <img src="https://img.shields.io/badge/Platform-Desktop-lightgrey?style=for-the-badge" alt="Desktop">
</p>

<br>

<p>
  👤 <strong>Patients</strong>
  &nbsp; • &nbsp;
  👨‍⚕️ <strong>Doctors</strong>
  &nbsp; • &nbsp;
  📅 <strong>Appointments</strong>
  &nbsp; • &nbsp;
  📧 <strong>Email</strong>
  &nbsp; • &nbsp;
  🔐 <strong>OTP</strong>
  &nbsp; • &nbsp;
  🗄️ <strong>MySQL</strong>
</p>

</div>

---

<h2>📌 About the Project</h2>

<p>
<strong>MediCare</strong> is a desktop-based
<strong>Doctor Consultation and Appointment Management System</strong>
developed using Java Swing and MySQL.
</p>

<p>
The application is designed to simplify the process of discovering
doctors, booking appointments, managing appointment requests,
maintaining user profiles, and communicating appointment updates
through email.
</p>

<p>
MediCare provides separate functionalities for
<strong>patients/users</strong> and <strong>doctors</strong>.
Patients can create accounts, discover doctors according to their
medical speciality, view doctor information, select appointment
dates and times, book appointments, and track their booking status.
</p>

<p>
Doctors can register on the platform, manage their professional
profiles, receive appointment requests, and accept or decline
appointments.
</p>

<p>
The system additionally implements <strong>OTP-based password
recovery</strong>, password management, automated email notifications,
and doctor profile-picture support.
</p>

---

<h2>🎯 Project Objectives</h2>

<ul>
<li>🩺 Provide an easy platform for patients to discover doctors.</li>
<li>📅 Simplify the doctor appointment booking process.</li>
<li>👨‍⚕️ Provide doctors with an organized appointment management system.</li>
<li>💾 Store patient, doctor, and appointment information using MySQL.</li>
<li>📧 Automate appointment-related email communication.</li>
<li>🔐 Provide OTP-based password recovery.</li>
<li>👤 Allow users and doctors to manage their profiles.</li>
<li>🖥️ Build a practical desktop application using Java Swing.</li>
</ul>

---

<h2>✨ Key Features</h2>

<table align="center">
<tr>
<th>👤 Patient</th>
<th>👨‍⚕️ Doctor</th>
</tr>

<tr>
<td>🔐 Registration & Login</td>
<td>📝 Doctor Registration</td>
</tr>

<tr>
<td>🔢 OTP Password Recovery</td>
<td>🔐 Doctor Login</td>
</tr>

<tr>
<td>🔑 Change Password</td>
<td>🔢 OTP Password Recovery</td>
</tr>

<tr>
<td>👤 Profile Management</td>
<td>👤 Profile Management</td>
</tr>

<tr>
<td>🩺 Browse Doctors</td>
<td>📋 View Appointment Requests</td>
</tr>

<tr>
<td>🔎 Search by Speciality</td>
<td>✅ Accept Appointments</td>
</tr>

<tr>
<td>📋 View Doctor Information</td>
<td>❌ Decline Appointments</td>
</tr>

<tr>
<td>📅 Select Date & Time</td>
<td>📧 Email Notifications</td>
</tr>

<tr>
<td>📝 Book Appointments</td>
<td>🖼️ Profile Picture Support</td>
</tr>

<tr>
<td>📊 View Booking Status</td>
<td>🩺 Speciality-Based Profile</td>
</tr>

<tr>
<td>📩 Appointment Emails</td>
<td>📩 New Booking Notifications</td>
</tr>

</table>

---

<h2>👤 Patient Module</h2>

<ul>
<li>🔐 Create a new patient account.</li>
<li>🔑 Login using registered credentials.</li>
<li>📧 Recover forgotten passwords using OTP verification.</li>
<li>🔐 Change account password.</li>
<li>👤 Edit and manage personal profile information.</li>
<li>🩺 Browse doctors based on speciality.</li>
<li>📋 View doctor details.</li>
<li>📅 Select preferred appointment date.</li>
<li>⏰ Select appointment time.</li>
<li>📝 Submit appointment requests.</li>
<li>📊 View appointment history and status.</li>
<li>📩 Receive appointment-related email notifications.</li>
</ul>

---

<h2>👨‍⚕️ Doctor Module</h2>

<ul>
<li>📝 Register as a doctor.</li>
<li>🔐 Login using registered credentials.</li>
<li>📧 Recover forgotten passwords using OTP.</li>
<li>🔑 Change password.</li>
<li>👤 Edit professional profile.</li>
<li>🩺 Maintain speciality information.</li>
<li>🖼️ Upload and display profile picture.</li>
<li>📋 View incoming appointment requests.</li>
<li>✅ Accept patient appointments.</li>
<li>❌ Decline patient appointments.</li>
<li>📩 Receive email notifications for new bookings.</li>
</ul>

---

<h2>🩺 Available Medical Specialities</h2>

<table align="center">
<tr>
<td align="center">
❤️<br>
<strong>Cardiologist</strong>
</td>

<td align="center">
🧠<br>
<strong>Neurologist</strong>
</td>

<td align="center">
🦴<br>
<strong>Orthopedic</strong>
</td>
</tr>

<tr>
<td align="center">
🧴<br>
<strong>Dermatologist</strong>
</td>

<td align="center">
👶<br>
<strong>Pediatrician</strong>
</td>

<td align="center">
🩺<br>
<strong>General Physician</strong>
</td>
</tr>
</table>

---

<h2>🔄 Application Workflow</h2>

<h3>👤 Patient Workflow</h3>

<pre>
                       🩺 MediCare
                           │
                    Register / Login
                           │
                           ▼
                   👤 User Dashboard
                           │
                           ▼
                🩺 Browse Specialities
                           │
                           ▼
                    Select Doctor
                           │
                           ▼
               View Doctor Information
                           │
                           ▼
                  Select Date & Time
                           │
                           ▼
                  📝 Book Appointment
                           │
                           ▼
                  🗄️ Save to MySQL
                           │
                           ▼
                  📧 Email Notification
                           │
                           ▼
                  📊 Track Status
</pre>

<h3>👨‍⚕️ Doctor Workflow</h3>

<pre>
                       🩺 MediCare
                           │
                    Register / Login
                           │
                           ▼
                  👨‍⚕️ Doctor Dashboard
                           │
                           ▼
                View Appointment Requests
                           │
                  ┌────────┴────────┐
                  ▼                 ▼
               ✅ Accept          ❌ Decline
                  │                 │
                  ▼                 ▼
             Update Status      Update Status
                  │                 │
                  └────────┬────────┘
                           ▼
                    📧 Notify Patient
</pre>

---

<h2>🏗️ System Architecture</h2>

<pre>
┌──────────────────────────────────────────────┐
│              PRESENTATION LAYER              │
│                                              │
│                Java Swing GUI                │
│                                              │
│ Login • Registration • Dashboard • Booking  │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│              APPLICATION LAYER               │
│                                              │
│ Authentication • Appointments • Profiles    │
│ OTP • Email • Doctor Management             │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│               DATABASE LAYER                 │
│                                              │
│                    JDBC                      │
└───────────────────────┬──────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────┐
│                  MySQL                       │
│                                              │
│ Users • Doctors • Bookings • Profiles       │
└──────────────────────────────────────────────┘
</pre>

---

<h2>📧 Email Notification System</h2>

<p>
MediCare uses JavaMail functionality to provide automated email
communication between the system, patients, and doctors.
</p>

<table align="center">
<tr>
<th>Event</th>
<th>Notification</th>
</tr>

<tr>
<td>🔢 Password Recovery</td>
<td>OTP sent to registered email</td>
</tr>

<tr>
<td>📝 Appointment Booking</td>
<td>Booking confirmation</td>
</tr>

<tr>
<td>📨 New Appointment</td>
<td>Doctor receives booking notification</td>
</tr>

<tr>
<td>✅ Appointment Accepted</td>
<td>Patient receives acceptance notification</td>
</tr>

<tr>
<td>❌ Appointment Declined</td>
<td>Patient receives rejection notification</td>
</tr>

</table>

---

<h2>🔐 Authentication & OTP</h2>

<p>
Both patients and doctors can recover forgotten passwords through
OTP verification.
</p>

<pre>
Forgot Password
       │
       ▼
Enter Registered Email
       │
       ▼
Generate OTP
       │
       ▼
Send OTP via Email
       │
       ▼
Enter OTP
       │
       ▼
Verify OTP
       │
       ▼
Set New Password
       │
       ▼
Password Updated
</pre>

---

<h2>🗄️ Database Design</h2>

<p>
MediCare uses <strong>MySQL</strong> as its relational database
management system.
</p>

<h3>Database</h3>

<pre>
doctor_consultation
</pre>

<h3>Database Responsibilities</h3>

<ul>
<li>👤 Store user account information.</li>
<li>👨‍⚕️ Store doctor account information.</li>
<li>🩺 Store doctor speciality information.</li>
<li>📅 Store appointment information.</li>
<li>📊 Maintain appointment status.</li>
<li>👤 Maintain profile information.</li>
<li>🖼️ Store doctor profile-picture references.</li>
</ul>

<h3>CRUD Operations</h3>

<p align="center">
<strong>CREATE</strong>
&nbsp; • &nbsp;
<strong>READ</strong>
&nbsp; • &nbsp;
<strong>UPDATE</strong>
&nbsp; • &nbsp;
<strong>DELETE</strong>
</p>

---

<h2>🔌 JDBC Integration</h2>

<p>
JDBC is used as the bridge between the Java Swing application and
the MySQL database.
</p>

<pre>
Java Swing Interface
        │
        ▼
Application Logic
        │
        ▼
       JDBC
        │
        ▼
      MySQL
        │
        ▼
Database Response
        │
        ▼
Java Swing Interface
</pre>

---

<h2>🖼️ Doctor Profile Picture Support</h2>

<p>
Doctors can upload a profile picture that can be displayed with
their professional profile.
</p>

<pre>
uploads/
│
├── doctor_001.png
├── doctor_002.jpg
├── doctor_003.png
└── ...
</pre>

---

<h2>🛠️ Technology Stack</h2>

<table align="center">
<tr>
<th>Technology</th>
<th>Role</th>
</tr>

<tr>
<td>☕ <strong>Java</strong></td>
<td>Core application development</td>
</tr>

<tr>
<td>🖥️ <strong>Java Swing</strong></td>
<td>Desktop graphical user interface</td>
</tr>

<tr>
<td>🗄️ <strong>MySQL</strong></td>
<td>Database management</td>
</tr>

<tr>
<td>🔌 <strong>JDBC</strong></td>
<td>Database connectivity</td>
</tr>

<tr>
<td>📧 <strong>JavaMail</strong></td>
<td>Email communication</td>
</tr>

<tr>
<td>🧰 <strong>Apache NetBeans</strong></td>
<td>Development environment</td>
</tr>

<tr>
<td>🏗️ <strong>Apache Ant</strong></td>
<td>Build automation</td>
</tr>

<tr>
<td>🔢 <strong>OTP</strong></td>
<td>Password recovery verification</td>
</tr>

</table>

---

<h2>📂 Project Structure</h2>

<pre>
MediCare/
│
├── src/
│   ├── Authentication/
│   ├── User/
│   ├── Doctor/
│   ├── Appointment/
│   ├── Database/
│   ├── Email/
│   └── OTP/
│
├── uploads/
│   └── Doctor Profile Pictures
│
├── nbproject/
│   └── NetBeans Project Configuration
│
├── build.xml
│
└── README.md
</pre>

<p>
<em>
The exact package structure may vary depending on the final
NetBeans project organization.
</em>
</p>

---

<h2>⚙️ Installation & Setup</h2>

<h3>1️⃣ Clone the Repository</h3>

<pre>
git clone &lt;your-repository-url&gt;
</pre>

<h3>2️⃣ Open the Project</h3>

<p>
Open the project in <strong>Apache NetBeans</strong>.
</p>

<h3>3️⃣ Configure MySQL</h3>

<pre>
CREATE DATABASE doctor_consultation;
</pre>

<p>
Import the required database tables and SQL queries into MySQL.
</p>

<h3>4️⃣ Configure JDBC</h3>

<pre>
Host     : localhost
Port     : 3306
Database : doctor_consultation
Username : your_username
Password : your_password
</pre>

<h3>5️⃣ Configure Email</h3>

<p>
Configure the email account and JavaMail/Jakarta Mail dependencies
required by the application.
</p>

<h3>6️⃣ Configure Profile Pictures</h3>

<pre>
uploads/
</pre>

<p>
Create the required upload directory for doctor profile pictures.
</p>

<h3>7️⃣ Build & Run</h3>

<pre>
Clean and Build
       ↓
Run Project
       ↓
🩺 MediCare Welcome Page
</pre>

---

<h2>💻 System Requirements</h2>

<table align="center">
<tr>
<th>Requirement</th>
<th>Recommended</th>
</tr>

<tr>
<td>☕ Java</td>
<td>JDK</td>
</tr>

<tr>
<td>🧰 IDE</td>
<td>Apache NetBeans</td>
</tr>

<tr>
<td>🗄️ Database</td>
<td>MySQL Server</td>
</tr>

<tr>
<td>🛠️ Database Tool</td>
<td>MySQL Workbench</td>
</tr>

<tr>
<td>🔌 Connectivity</td>
<td>MySQL JDBC Driver</td>
</tr>

<tr>
<td>📧 Email</td>
<td>JavaMail / Jakarta Mail</td>
</tr>

<tr>
<td>🧠 RAM</td>
<td>4 GB or higher</td>
</tr>

</table>

---

<h2>📊 Project Highlights</h2>

<table align="center">
<tr>
<td align="center">
<h3>👤</h3>
<strong>2 User Roles</strong><br>
Patients & Doctors
</td>

<td align="center">
<h3>📅</h3>
<strong>Appointment</strong><br>
Booking & Tracking
</td>

<td align="center">
<h3>📧</h3>
<strong>Email</strong><br>
Automated Notifications
</td>

<td align="center">
<h3>🔐</h3>
<strong>OTP</strong><br>
Password Recovery
</td>
</tr>
</table>

---

<h2>🌟 Advantages</h2>

<ul>
<li>⚡ Simplifies the appointment booking process.</li>
<li>🩺 Makes doctor discovery easier.</li>
<li>📅 Provides organized appointment scheduling.</li>
<li>💾 Maintains centralized database records.</li>
<li>📧 Automates appointment notifications.</li>
<li>🔐 Provides OTP-based password recovery.</li>
<li>👤 Allows profile management.</li>
<li>🖥️ Provides a dedicated desktop GUI.</li>
<li>🧩 Combines multiple real-world software concepts in one project.</li>
</ul>

---

<h2>📚 Learning Outcomes</h2>

<p>
Developing MediCare provided practical experience with:
</p>

<table align="center">
<tr>
<td align="center">☕<br><strong>Java</strong></td>
<td align="center">🖥️<br><strong>Java Swing</strong></td>
<td align="center">🗄️<br><strong>MySQL</strong></td>
<td align="center">🔌<br><strong>JDBC</strong></td>
</tr>

<tr>
<td>OOP<br>Exception Handling</td>
<td>GUI<br>Event Handling</td>
<td>SQL<br>CRUD</td>
<td>Connections<br>Queries</td>
</tr>

<tr>
<td align="center">📧<br><strong>JavaMail</strong></td>
<td align="center">🔐<br><strong>Authentication</strong></td>
<td align="center">🧩<br><strong>System Design</strong></td>
<td align="center">🛠️<br><strong>Debugging</strong></td>
</tr>
</table>

---

<h2>🗺️ Roadmap</h2>

<h3>✅ Implemented</h3>

<ul>
<li>✅ User registration and login</li>
<li>✅ Doctor registration and login</li>
<li>✅ User profile management</li>
<li>✅ Doctor profile management</li>
<li>✅ Doctor speciality browsing</li>
<li>✅ Doctor information display</li>
<li>✅ Appointment booking</li>
<li>✅ Appointment history</li>
<li>✅ Appointment status tracking</li>
<li>✅ Doctor appointment requests</li>
<li>✅ Accept / decline appointments</li>
<li>✅ Email notifications</li>
<li>✅ OTP password recovery</li>
<li>✅ Change password functionality</li>
<li>✅ Doctor profile-picture support</li>
</ul>

<h3>🔮 Future Enhancements</h3>

<ul>
<li>⬜ 👨‍💼 Admin dashboard</li>
<li>⬜ 📊 Admin analytics</li>
<li>⬜ 💳 Online payment integration</li>
<li>⬜ 📹 Video consultation</li>
<li>⬜ 💬 Doctor-patient chat</li>
<li>⬜ ⭐ Doctor ratings and reviews</li>
<li>⬜ 🔔 Real-time notifications</li>
<li>⬜ 📍 Location-based doctor search</li>
<li>⬜ 📆 Advanced doctor availability calendar</li>
<li>⬜ 🧠 AI-based doctor recommendations</li>
<li>⬜ 📱 Mobile application</li>
<li>⬜ ☁️ Cloud deployment</li>
</ul>

---

<h2>🔒 Security Considerations</h2>

<p>
The system includes authentication and OTP-based password recovery.
For a production-ready version, additional security mechanisms can
be introduced.
</p>

<ul>
<li>🔐 Password hashing</li>
<li>🛡️ Prepared statements</li>
<li>🔑 Secure credential management</li>
<li>⏱️ OTP expiration</li>
<li>🚫 OTP attempt limits</li>
<li>🔒 Session management</li>
<li>🧹 Input validation</li>
<li>📧 Secure email credentials</li>
<li>🗄️ Restricted database access</li>
</ul>

---

<h2>🎓 Academic Project</h2>

<table align="center">
<tr>
<td align="center">
<strong>👩‍💻 Developer</strong><br>
Kamakshi Sharma
</td>

<td align="center">
<strong>🎓 Program</strong><br>
B.Tech CSE
</td>

<td align="center">
<strong>🏫 University</strong><br>
Guru Nanak Dev University
</td>
</tr>
</table>

<p align="center">
<strong>Computer Science & Engineering</strong>
</p>

---

<h2>👩‍💻 About the Developer</h2>

<div align="center">

# Kamakshi Sharma

### B.Tech Computer Science & Engineering Student

<p>
I am a Computer Science and Engineering student passionate about
<strong>software development, problem solving, databases, and
cybersecurity</strong>.
</p>

<p>
MediCare was developed as a practical project to strengthen my
understanding of <strong>Java, Java Swing, SQL, JDBC, database
management, authentication, email integration, and real-world
application development</strong>.
</p>

<br>

<h3>💻 Technical Interests</h3>

<p>
☕ Java &nbsp; • &nbsp;
🧠 Data Structures & Algorithms &nbsp; • &nbsp;
🗄️ SQL & Databases &nbsp; • &nbsp;
🔐 Cybersecurity &nbsp; • &nbsp;
☁️ Network & Cloud Security &nbsp; • &nbsp;
🏗️ Software Engineering
</p>

<br>

<h3>🛠️ Technologies</h3>

<p>
<code>Java</code>
<code>Java Swing</code>
<code>MySQL</code>
<code>SQL</code>
<code>JDBC</code>
<code>JavaMail</code>
<code>NetBeans</code>
<code>Apache Ant</code>
</p>

</div>

---

<h2>🤝 Contribution</h2>

<p>
Contributions, suggestions, and improvements are welcome.
</p>

<pre>
# Fork the repository

# Create a feature branch
git checkout -b feature/new-feature

# Make your changes

# Stage changes
git add .

# Commit changes
git commit -m "Add new feature"

# Push branch
git push origin feature/new-feature
</pre>

<p>
After pushing your changes, create a <strong>Pull Request</strong>.
</p>

---

<h2>📜 License</h2>

<p>
This project is developed primarily for
<strong>educational and learning purposes</strong>.
</p>

<p>
You are welcome to study, modify, and extend the project for
educational purposes while giving appropriate credit to the original
author.
</p>

---

<h2>⭐ Support the Project</h2>

<div align="center">

<p>
If you found <strong>MediCare</strong> useful or interesting,
consider supporting the project.
</p>

<p>
⭐ <strong>Star</strong> the repository
&nbsp;&nbsp; • &nbsp;&nbsp;
🍴 <strong>Fork</strong> the project
&nbsp;&nbsp; • &nbsp;&nbsp;
💡 <strong>Suggest</strong> improvements
&nbsp;&nbsp; • &nbsp;&nbsp;
🐛 <strong>Report</strong> issues
</p>

</div>

---

<div align="center">

# 🩺 MediCare

### Connect • Consult • Care

<p>
<strong>
☕ Java &nbsp; + &nbsp;
🖥️ Swing &nbsp; + &nbsp;
🗄️ MySQL &nbsp; + &nbsp;
🔌 JDBC
</strong>
</p>

<br>

<hr>

<p>
Developed with ❤️ by
<strong>Kamakshi Sharma</strong>
</p>

<p>
<strong>B.Tech CSE • GNDU</strong>
</p>

</div>
