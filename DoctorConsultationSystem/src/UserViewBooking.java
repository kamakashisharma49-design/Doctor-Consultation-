
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author
 */
public class UserViewBooking extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserViewBooking.class.getName());
    private static final long serialVersionUID = 1L;
    private JPanel appointmentsContainer;

    public UserViewBooking() {

        initComponents();
        if (UserLogin.loggedInUserName != null) {

            jLabel6.setText(
                    "Welcome, "
                    + UserLogin.loggedInUserName
            );
        }
        if (UserLogin.loggedInUserName != null) {

            jLabel3.setText(
                    "Name: " + UserLogin.loggedInUserName
            );
        }

        if (UserLogin.loggedInUserEmail != null) {

            jLabel4.setText(
                    "Email: " + UserLogin.loggedInUserEmail
            );
        }

        setSize(1200, 900);
        setLocationRelativeTo(null);
        // ==========================================
        // DYNAMIC APPOINTMENTS CONTAINER
        // ==========================================

        appointmentsContainer = new JPanel();

        appointmentsContainer.setBackground(
                new Color(250, 252, 255)
        );

        appointmentsContainer.setLayout(
                new javax.swing.BoxLayout(
                        appointmentsContainer,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        // ==========================================
        // SCROLL PANE
        // ==========================================
        javax.swing.JScrollPane scrollPane
                = new javax.swing.JScrollPane(
                        appointmentsContainer
                );

        scrollPane.setBorder(null);

        scrollPane.setBackground(
                new Color(250, 252, 255)
        );

        scrollPane.getViewport().setBackground(
                new Color(250, 252, 255)
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        // ==========================================
        // ADD SCROLL PANE
        // ==========================================
        doctorPanel.add(
                scrollPane,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(
                        15,
                        120,
                        910,
                        650
                )
        );

        // ==========================================
        // LOAD APPOINTMENTS
        // ==========================================
        loadAppointments();
    }

    private void loadAppointments() {

        appointmentsContainer.removeAll();

        // ==========================================
        // GET LOGGED-IN USER EMAIL
        // ==========================================
        String patientEmail = UserLogin.loggedInUserEmail;

        if (patientEmail == null
                || patientEmail.trim().isEmpty()) {

            JLabel message = new JLabel(
                    "Please login first."
            );

            message.setFont(
                    new Font("Arial", Font.PLAIN, 18)
            );

            message.setForeground(
                    new Color(80, 95, 120)
            );

            message.setAlignmentX(
                    javax.swing.JComponent.CENTER_ALIGNMENT
            );

            appointmentsContainer.add(
                    javax.swing.Box.createVerticalStrut(30)
            );

            appointmentsContainer.add(message);

            appointmentsContainer.revalidate();
            appointmentsContainer.repaint();

            return;
        }

        // ==========================================
        // SQL QUERY
        // ==========================================
        String query
                = "SELECT "
                + "b.booking_id, "
                + "b.doctor_email, "
                + "b.appointment_date, "
                + "b.appointment_time, "
                + "b.status, "
                + "d.fullname, "
                + "d.speciality, "
                + "d.experience, "
                + "d.address, "
                + "d.consultationFee, "
                + "d.phoneNumber, "
                + "d.profile_picture "
                + "FROM bookings b "
                + "JOIN doctor d "
                + "ON b.doctor_email = d.email "
                + "WHERE b.patient_email = '"
                + patientEmail
                + "' "
                + "ORDER BY "
                + "b.appointment_date ASC, "
                + "b.appointment_time ASC";

        try {

            ResultSet rs
                    = DBLoader.executeQuery(query);

            boolean found = false;

            while (rs != null && rs.next()) {

                found = true;

                // ==========================================
                // BOOKING INFORMATION
                // ==========================================
                int bookingId
                        = rs.getInt("booking_id");

                String doctorEmail
                        = rs.getString("doctor_email");

                String appointmentDate
                        = rs.getString("appointment_date");

                String appointmentTime
                        = rs.getString("appointment_time");

                String status
                        = rs.getString("status");

                // ==========================================
                // DOCTOR INFORMATION
                // ==========================================
                String doctorName
                        = rs.getString("fullname");

                String speciality
                        = rs.getString("speciality");

                String experience
                        = rs.getString("experience");

                String address
                        = rs.getString("address");

                String consultationFee
                        = rs.getString("consultationFee");

                String doctorPhone
                        = rs.getString("phoneNumber");

                String profilePicture
                        = rs.getString("profile_picture");

                // ==========================================
                // CREATE CARD
                // ==========================================
                JPanel card
                        = createAppointmentCard(
                                bookingId,
                                doctorEmail,
                                doctorName,
                                speciality,
                                experience,
                                address,
                                consultationFee,
                                doctorPhone,
                                profilePicture,
                                appointmentDate,
                                appointmentTime,
                                status
                        );

                appointmentsContainer.add(card);

                // Space between cards
                appointmentsContainer.add(
                        javax.swing.Box.createVerticalStrut(15)
                );
            }

            if (rs != null) {
                rs.close();
            }

            // ==========================================
            // NO BOOKINGS
            // ==========================================
            if (!found) {

                JLabel noBookings
                        = new JLabel(
                                "You don't have any appointments yet."
                        );

                noBookings.setFont(
                        new Font("Arial", Font.PLAIN, 18)
                );

                noBookings.setForeground(
                        new Color(80, 95, 120)
                );

                noBookings.setAlignmentX(
                        javax.swing.JComponent.CENTER_ALIGNMENT
                );

                appointmentsContainer.add(
                        javax.swing.Box.createVerticalStrut(40)
                );

                appointmentsContainer.add(noBookings);
            }

            // ==========================================
            // REFRESH
            // ==========================================
            appointmentsContainer.revalidate();
            appointmentsContainer.repaint();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading appointments:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private JPanel createAppointmentCard(
            int bookingId,
            String doctorEmail,
            String doctorName,
            String speciality,
            String experience,
            String address,
            String consultationFee,
            String doctorPhone,
            String profilePicture,
            String appointmentDate,
            String appointmentTime,
            String status) {

        // =====================================================
        // MAIN CARD
        // =====================================================
        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(900, 205)
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        205
                )
        );

        card.setMinimumSize(
                new Dimension(
                        850,
                        205
                )
        );

        card.setLayout(
                new BorderLayout(20, 0)
        );

        // =====================================================
        // LEFT SECTION
        // PHOTO + DOCTOR DETAILS
        // =====================================================
        JPanel doctorSection
                = new JPanel(
                        new BorderLayout(15, 0)
                );

        doctorSection.setBackground(Color.WHITE);

        doctorSection.setPreferredSize(
                new Dimension(365, 170)
        );

        // =====================================================
        // DOCTOR IMAGE
        // =====================================================
        JLabel doctorImage
                = new JLabel();

        doctorImage.setPreferredSize(
                new Dimension(130, 160)
        );

        doctorImage.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        doctorImage.setVerticalAlignment(
                SwingConstants.CENTER
        );

        ImageIcon icon
                = getDoctorImage(profilePicture);

        if (icon != null) {

            doctorImage.setIcon(icon);

        } else {

            doctorImage.setText("Doctor");

            doctorImage.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            doctorImage.setForeground(
                    new Color(80, 95, 120)
            );
        }

        doctorSection.add(
                doctorImage,
                BorderLayout.WEST
        );

        // =====================================================
        // DOCTOR INFORMATION
        // =====================================================
        JPanel doctorInfo
                = new JPanel();

        doctorInfo.setBackground(
                Color.WHITE
        );

        doctorInfo.setLayout(
                new javax.swing.BoxLayout(
                        doctorInfo,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        // Doctor Name
        JLabel nameLabel
                = new JLabel(
                        doctorName
                );

        nameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        nameLabel.setForeground(
                new Color(23, 63, 122)
        );

        // Speciality
        JLabel specialityLabel
                = new JLabel(
                        speciality
                );

        specialityLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        specialityLabel.setForeground(
                new Color(80, 95, 120)
        );

        // Experience
        JLabel experienceLabel
                = new JLabel(
                        "Experience: "
                        + experience
                        + " years"
                );

        experienceLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // Clinic
        JLabel addressLabel
                = new JLabel(
                        "Clinic: "
                        + (address == null
                        || address.trim().isEmpty()
                        ? "Not available"
                        : address)
                );

        addressLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // Phone
        JLabel phoneLabel
                = new JLabel(
                        "Phone: "
                        + (doctorPhone == null
                        || doctorPhone.trim().isEmpty()
                        || doctorPhone.equalsIgnoreCase("null")
                        ? "Not available"
                        : doctorPhone)
                );

        phoneLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // Add doctor information
        doctorInfo.add(
                nameLabel
        );

        doctorInfo.add(
                javax.swing.Box.createVerticalStrut(4)
        );

        doctorInfo.add(
                specialityLabel
        );

        doctorInfo.add(
                javax.swing.Box.createVerticalStrut(10)
        );

        doctorInfo.add(
                experienceLabel
        );

        doctorInfo.add(
                addressLabel
        );

        doctorInfo.add(
                phoneLabel
        );

        doctorSection.add(
                doctorInfo,
                BorderLayout.CENTER
        );

        // =====================================================
        // MIDDLE SECTION
        // APPOINTMENT DETAILS
        // =====================================================
        JPanel appointmentSection
                = new JPanel();

        appointmentSection.setBackground(
                Color.WHITE
        );

        appointmentSection.setLayout(
                new javax.swing.BoxLayout(
                        appointmentSection,
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        appointmentSection.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 10, 3, 5
                )
        );

        // Appointment Heading
        JLabel appointmentHeading
                = new JLabel(
                        "APPOINTMENT DETAILS"
                );

        appointmentHeading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        appointmentHeading.setForeground(
                new Color(23, 63, 122)
        );

        // Date
        JLabel dateLabel
                = new JLabel(
                        "Date: "
                        + appointmentDate
                );

        dateLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // Time
        JLabel timeLabel
                = new JLabel(
                        "Time: "
                        + appointmentTime
                );

        timeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // =====================================================
        // STATUS
        // =====================================================
        String currentStatus;

        if (status == null
                || status.trim().isEmpty()
                || status.equalsIgnoreCase("null")) {

            currentStatus = "Pending";

        } else {

            currentStatus = status;
        }

        JLabel statusLabel
                = new JLabel(
                        "Status: "
                        + currentStatus
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        // =====================================================
        // STATUS COLOR
        // =====================================================
        if (currentStatus.equalsIgnoreCase("Accepted")) {

            statusLabel.setForeground(
                    new Color(0, 153, 76)
            );

        } else if (currentStatus.equalsIgnoreCase("Declined")) {

            statusLabel.setForeground(
                    new Color(220, 53, 69)
            );

        } else if (currentStatus.equalsIgnoreCase("Cancelled")) {

            statusLabel.setForeground(
                    new Color(120, 120, 120)
            );

        } else if (currentStatus.equalsIgnoreCase("Completed")) {

            statusLabel.setForeground(
                    new Color(108, 117, 125)
            );

        } else {

            statusLabel.setForeground(
                    new Color(0, 102, 204)
            );
        }

        // =====================================================
        // CONSULTATION FEE
        // =====================================================
        JLabel feeLabel
                = new JLabel(
                        "Consultation Fee: ₹"
                        + consultationFee
                );

        feeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // =====================================================
        // BOOKING ID
        // =====================================================
        JLabel bookingIdLabel
                = new JLabel(
                        "Booking ID: "
                        + bookingId
                );

        bookingIdLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        bookingIdLabel.setForeground(
                new Color(100, 110, 125)
        );

        // =====================================================
        // ADD APPOINTMENT INFORMATION
        // =====================================================
        appointmentSection.add(
                appointmentHeading
        );

        appointmentSection.add(
                javax.swing.Box.createVerticalStrut(12)
        );

        appointmentSection.add(
                dateLabel
        );

        appointmentSection.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        appointmentSection.add(
                timeLabel
        );

        appointmentSection.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        appointmentSection.add(
                statusLabel
        );

        appointmentSection.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        appointmentSection.add(
                feeLabel
        );

        appointmentSection.add(
                javax.swing.Box.createVerticalStrut(5)
        );

        appointmentSection.add(
                bookingIdLabel
        );

        // =====================================================
        // RIGHT SECTION
        // CANCEL BUTTON
        // =====================================================
        JPanel buttonSection
                = new JPanel(
                        new java.awt.GridBagLayout()
                );

        buttonSection.setBackground(
                Color.WHITE
        );

        buttonSection.setPreferredSize(
                new Dimension(140, 170)
        );

        JButton cancel
                = new JButton(
                        "Cancel"
                );

        cancel.setPreferredSize(
                new Dimension(
                        125,
                        38
                )
        );

        cancel.setBackground(
                new Color(
                        220,
                        53,
                        69
                )
        );

        cancel.setForeground(
                Color.WHITE
        );

        cancel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        cancel.setFocusPainted(
                false
        );

        cancel.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // =====================================================
        // CANCEL BUTTON VISIBILITY
        // =====================================================
        // Do NOT allow cancellation if appointment is:
        // 1. Already Cancelled
        // 2. Already Completed
        //
        // For these statuses the button is disabled/hidden.
        // =====================================================
        if (currentStatus.equalsIgnoreCase("Cancelled")
                || currentStatus.equalsIgnoreCase("Completed")) {

            cancel.setVisible(false);

        } else {

            cancel.setVisible(true);

            // =================================================
            // CANCEL BOOKING ACTION
            // =================================================
            cancel.addActionListener(
                    new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(
                        java.awt.event.ActionEvent e) {

                    // ==========================================
                    // CONFIRM CANCELLATION
                    // ==========================================
                    int choice
                            = JOptionPane.showConfirmDialog(
                                    UserViewBooking.this,
                                    "Are you sure you want to cancel this appointment?",
                                    "Cancel Appointment",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE
                            );

                    if (choice != JOptionPane.YES_OPTION) {

                        return;
                    }

                    // ==========================================
                    // CHECK LOGGED-IN USER
                    // ==========================================
                    String patientEmail
                            = UserLogin.loggedInUserEmail;

                    if (patientEmail == null
                            || patientEmail.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                UserViewBooking.this,
                                "Please login first.",
                                "Login Required",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // ==========================================
                    // SAFELY ESCAPE EMAIL
                    // ==========================================
                    // Escape single quotes so the email cannot
                    // break the SQL query.
                    //
                    // Example:
                    // abc'xyz@gmail.com
                    // becomes
                    // abc''xyz@gmail.com
                    // ==========================================
                    String safePatientEmail
                            = patientEmail.replace(
                                    "'",
                                    "''"
                            );

                    // ==========================================
                    // DELETE ONLY THIS USER'S BOOKING
                    // ==========================================
                    String deleteQuery
                            = "DELETE FROM bookings "
                            + "WHERE booking_id = "
                            + bookingId
                            + " AND patient_email = '"
                            + safePatientEmail
                            + "'";

                    try {

                        int result
                                = DBLoader.executeUpdate(
                                        deleteQuery
                                );

                        // ==========================================
                        // SUCCESS
                        // ==========================================
                        if (result > 0) {

                            JOptionPane.showMessageDialog(
                                    UserViewBooking.this,
                                    "Appointment cancelled successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            // ==========================================
                            // REFRESH BOOKING LIST
                            // ==========================================
                            loadAppointments();

                        } else {

                            JOptionPane.showMessageDialog(
                                    UserViewBooking.this,
                                    "Appointment could not be cancelled.\n"
                                    + "The booking may no longer exist.",
                                    "Cancellation Failed",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                                UserViewBooking.this,
                                "Error cancelling appointment:\n"
                                + ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        ex.printStackTrace();
                    }
                }
            });
        }

        // =====================================================
        // ADD CANCEL BUTTON
        // =====================================================
        java.awt.GridBagConstraints gbc
                = new java.awt.GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor
                = java.awt.GridBagConstraints.CENTER;

        buttonSection.add(
                cancel,
                gbc
        );

        // =====================================================
        // ADD THREE SECTIONS TO CARD
        // =====================================================
        card.add(
                doctorSection,
                BorderLayout.WEST
        );

        card.add(
                appointmentSection,
                BorderLayout.CENTER
        );

        card.add(
                buttonSection,
                BorderLayout.EAST
        );

        return card;
    }

    private ImageIcon getDoctorImage(String imagePath) {

        if (imagePath == null
                || imagePath.trim().isEmpty()) {

            return null;
        }

        File file
                = new File(imagePath);

        if (!file.exists()) {

            return null;
        }

        ImageIcon original
                = new ImageIcon(imagePath);

        java.awt.Image image
                = original.getImage()
                        .getScaledInstance(
                                120,
                                120,
                                java.awt.Image.SCALE_SMOOTH
                        );

        return new ImageIcon(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        userInfoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        doctorPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DoctorList");
        setBackground(new java.awt.Color(234, 244, 255));

        mainPanel.setBackground(new java.awt.Color(234, 244, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        sidePanel.setBackground(new java.awt.Color(233, 242, 253));
        sidePanel.setPreferredSize(new java.awt.Dimension(260, 700));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 63, 122));
        jLabel1.setText("MediCare");

        jLabel2.setBackground(new java.awt.Color(70, 90, 120));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(70, 90, 120));
        jLabel2.setText("Stay Healthy, Stay Happy");

        jButton2.setBackground(new java.awt.Color(0, 102, 204));
        jButton2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Home");
        jButton2.setPreferredSize(new java.awt.Dimension(220, 50));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(0, 102, 204));
        jButton3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("View Bookings");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255), 3));
        jButton3.setPreferredSize(new java.awt.Dimension(220, 50));
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Logout→");
        jButton4.setPreferredSize(new java.awt.Dimension(220, 50));
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setBackground(new java.awt.Color(0, 102, 204));
        jButton5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Change Password");
        jButton5.setPreferredSize(new java.awt.Dimension(220, 50));
        jButton5.addActionListener(this::jButton5ActionPerformed);

        userInfoPanel.setBackground(new java.awt.Color(233, 242, 253));
        userInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(250, 252, 255), 1, true));
        userInfoPanel.setForeground(new java.awt.Color(250, 252, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Email:");

        javax.swing.GroupLayout userInfoPanelLayout = new javax.swing.GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(userInfoPanelLayout);
        userInfoPanelLayout.setHorizontalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        userInfoPanelLayout.setVerticalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        mainPanel.add(sidePanel, java.awt.BorderLayout.WEST);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(940, 900));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(250, 252, 255));
        headerPanel.setPreferredSize(new java.awt.Dimension(920, 110));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 63, 122));
        jLabel6.setText("Welcome, John Doe");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(80, 95, 120));
        jLabel7.setText("View appointments, manage your bookings.");

        jButton1.setBackground(new java.awt.Color(233, 242, 253));
        jButton1.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 204));
        jButton1.setText("=");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 505, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        contentPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, -1));

        doctorPanel.setBackground(new java.awt.Color(250, 252, 255));
        doctorPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 15, 10));
        doctorPanel.setMinimumSize(new java.awt.Dimension(941, 900));
        doctorPanel.setPreferredSize(new java.awt.Dimension(920, 110));
        doctorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(920, 110));
        jPanel1.setLayout(null);

        jButton6.setBackground(new java.awt.Color(233, 242, 253));
        jButton6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton6.setForeground(new java.awt.Color(23, 63, 122));
        jButton6.setText("←   Back");
        jButton6.addActionListener(this::jButton6ActionPerformed);
        jPanel1.add(jButton6);
        jButton6.setBounds(10, 10, 120, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 63, 122));
        jLabel5.setText("View Your Bookings");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(270, 20, 350, 50);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Review doctor details and your bookings");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(300, 70, 290, 19);

        doctorPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 941, -1));

        contentPanel.add(doctorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 940, 790));

        mainPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        UserChangePassword user = new UserChangePassword();
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {

            // Clear logged-in user information
            UserLogin.loggedInUserName = null;
            UserLogin.loggedInUserEmail = null;

            // Open login page
            UserLogin login = new UserLogin();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            // Close current frame
            dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ==========================================
// CREATE POPUP
// ==========================================

        JPopupMenu popup = new JPopupMenu();

        JMenuItem changePassword
                = new JMenuItem("Change Password");

        JMenuItem viewBookings
                = new JMenuItem("View Bookings");

        JMenuItem logout
                = new JMenuItem("Logout");

// ==========================================
// POPUP SIZE
// ==========================================
        popup.setPopupSize(220, 135);

// ==========================================
// COLORS
// ==========================================
        Color popupText
                = new Color(40, 55, 75);

        Color popupHover
                = new Color(242, 247, 252);

        Color popupBorder
                = new Color(218, 226, 235);

        Color darkBlue
                = new Color(23, 63, 122);

// ==========================================
// POPUP BACKGROUND
// ==========================================
        popup.setBackground(Color.WHITE);

// ==========================================
// POPUP BORDER
// ==========================================
        popup.setBorder(
                BorderFactory.createLineBorder(
                        popupBorder,
                        1
                )
        );

// ==========================================
// FONT
// ==========================================
        Font menuFont
                = new Font("Arial", Font.PLAIN, 15);

        changePassword.setFont(menuFont);
        viewBookings.setFont(menuFont);
        logout.setFont(menuFont);

// ==========================================
// TEXT COLOR
// ==========================================
        changePassword.setForeground(popupText);
        viewBookings.setForeground(popupText);
        logout.setForeground(popupText);

// ==========================================
// WHITE BACKGROUND
// ==========================================
        changePassword.setBackground(Color.WHITE);
        viewBookings.setBackground(Color.WHITE);
        logout.setBackground(Color.WHITE);

// ==========================================
// REMOVE DEFAULT BORDERS + PADDING
// ==========================================
        changePassword.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        viewBookings.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        logout.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

// ==========================================
// HOVER EFFECT
// ==========================================
        java.awt.event.MouseAdapter hover
                = new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                JMenuItem item
                        = (JMenuItem) e.getSource();

                // Light blue background
                item.setBackground(
                        new Color(0, 102, 204)
                );

                // WHITE TEXT
                item.setForeground(
                        Color.WHITE
                );
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                JMenuItem item
                        = (JMenuItem) e.getSource();

                // Back to white
                item.setBackground(
                        Color.WHITE
                );

                // Back to normal text
                item.setForeground(
                        popupText
                );
            }
        };

// ==========================================
// ADD HOVER TO ALL ITEMS
// ==========================================
        changePassword.addMouseListener(hover);

        viewBookings.addMouseListener(hover);

        logout.addMouseListener(hover);
        // ==========================================
        // CHANGE PASSWORD FUNCTIONALITY
        // ==========================================
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                // Close popup
                popup.setVisible(false);

                // Open Change Password page
                UserChangePassword cp = new UserChangePassword();

                cp.setLocationRelativeTo(null);
                cp.setVisible(true);

                // Close current window
                dispose();
            }
        });

// ==========================================
// LOGOUT FUNCTIONALITY
// ==========================================
        logout.addActionListener(e -> {

            popup.setVisible(false);

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {

                // Clear logged-in user information
                UserLogin.loggedInUserName = null;
                UserLogin.loggedInUserEmail = null;

                // Open login page
                UserLogin login = new UserLogin();
                login.setLocationRelativeTo(null);
                login.setVisible(true);

                // Close current frame
                dispose();
            }
        });
// ==========================================
// ADD ITEMS TO POPUP
// ==========================================
        popup.add(changePassword);

        popup.add(logout);

// ==========================================
// SHOW POPUP
// ==========================================
        int popupWidth = 220;

        int x
                = jButton1.getWidth()
                - popupWidth;

        int y
                = jButton1.getHeight()
                + 5;

        popup.show(
                jButton1,
                x,
                y
        );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        UserHome home = new UserHome();

        home.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        UserViewBooking bookings
                = new UserViewBooking();

        bookings.setLocationRelativeTo(null);
        bookings.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UserHome home = new UserHome();

        home.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserViewBooking().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel userInfoPanel;
    // End of variables declaration//GEN-END:variables
}
