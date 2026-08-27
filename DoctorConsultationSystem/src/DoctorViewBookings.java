
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 *
 */
public class DoctorViewBookings extends javax.swing.JFrame {

    public static String loggedInDoctorEmail = "";
    public static String loggedInDoctorName = "";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DoctorViewBookings.class.getName());
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form UserHomePage
     */
    public DoctorViewBookings() {
        initComponents();
        if (DoctorLogin.loggedInDoctorName != null) {

            jLabel6.setText(
                    "Welcome, Dr."
                    + DoctorLogin.loggedInDoctorName
            );
        }
        if (DoctorLogin.loggedInDoctorName != null) {

            jLabel3.setText(
                    "Name: Dr." + DoctorLogin.loggedInDoctorName
            );
        }

        if (DoctorLogin.loggedInDoctorEmail != null) {

            jLabel4.setText(
                    "Email: " + DoctorLogin.loggedInDoctorEmail
            );
        }

        setSize(1220, 750);
        setLocationRelativeTo(null);
        loadBookings();

    }

    private void loadBookings() {

        bookingsContainer.removeAll();

        String doctorEmail = DoctorLogin.loggedInDoctorEmail;

        if (doctorEmail == null || doctorEmail.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor email not found. Please login again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            String query
                    = "SELECT booking_id, patient_name, patient_email, gender, age, "
                    + "blood_group, phone, appointment_date, "
                    + "appointment_time, status "
                    + "FROM bookings "
                    + "WHERE doctor_email = '" + doctorEmail + "' "
                    + "ORDER BY appointment_date, appointment_time";
            ResultSet rs = DBLoader.executeQuery(query);

            if (rs == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Could not load bookings. Please check the bookings table.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            boolean found = false;

            while (rs.next()) {

                found = true;
                int bookingId = rs.getInt("booking_id");
                String patientName = rs.getString("patient_name");
                String patientEmail = rs.getString("patient_email");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String bloodGroup = rs.getString("blood_group");
                String phone = rs.getString("phone");
                String appointmentDate = rs.getString("appointment_date");
                String appointmentTime = rs.getString("appointment_time");
                String status = rs.getString("status");

                JPanel bookingCard = createBookingCard(
                        bookingId,
                        patientName,
                        patientEmail,
                        gender,
                        age,
                        bloodGroup,
                        phone,
                        appointmentDate,
                        appointmentTime,
                        status
                );

                bookingCard.setAlignmentX(
                        java.awt.Component.CENTER_ALIGNMENT
                );

                bookingsContainer.add(bookingCard);

                bookingsContainer.add(
                        javax.swing.Box.createVerticalStrut(14)
                );
            }

            rs.close();

            if (!found) {

                JLabel noBookings
                        = new JLabel("No patient bookings found.");

                noBookings.setFont(
                        new Font("Arial", Font.PLAIN, 18)
                );

                noBookings.setForeground(
                        new Color(80, 95, 120)
                );

                noBookings.setAlignmentX(
                        java.awt.Component.CENTER_ALIGNMENT
                );

                bookingsContainer.add(
                        javax.swing.Box.createVerticalStrut(40)
                );

                bookingsContainer.add(noBookings);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading bookings:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }

        bookingsContainer.revalidate();
        bookingsContainer.repaint();
    }

    private JPanel createBookingCard(
            int bookingId,
            String patientName,
            String patientEmail,
            String gender,
            String age,
            String bloodGroup,
            String phone,
            String appointmentDate,
            String appointmentTime,
            String status) {

        // =========================================================
        // COLORS - MEDICARE THEME
        // =========================================================
        Color darkBlue = new Color(23, 63, 122);
        Color textColor = new Color(35, 45, 60);

        Color borderBlue = new Color(210, 225, 245);
        Color lightBlue = new Color(238, 246, 255);
        Color badgeBlue = new Color(229, 240, 255);
        Color badgeTextBlue = new Color(23, 63, 122);

        // =========================================================
        // MAIN CARD
        // =========================================================
        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                borderBlue,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        // =========================================================
        // CARD SIZE
        // =========================================================
        card.setPreferredSize(
                new Dimension(880, 155)
        );

        card.setMinimumSize(
                new Dimension(700, 155)
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        155
                )
        );

        // =========================================================
        // TITLE
        // =========================================================
        JLabel titleLabel = new JLabel("Patient Details");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(darkBlue);

        JPanel titlePanel = new JPanel(
                new BorderLayout()
        );

        titlePanel.setBackground(Color.WHITE);

        titlePanel.setPreferredSize(
                new Dimension(0, 27)
        );

        titlePanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        // =========================================================
        // STATUS BADGE
        // =========================================================
        String statusText;

        if (status == null || status.trim().isEmpty()) {
            statusText = "PENDING";
        } else {
            statusText = status.toUpperCase();
        }

        JLabel statusLabel = new JLabel(
                "  " + statusText + "  "
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                badgeTextBlue
        );

        statusLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        statusLabel.setVerticalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        statusLabel.setOpaque(true);

        statusLabel.setBackground(
                badgeBlue
        );

        statusLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 220, 245),
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                4,
                                7,
                                4,
                                7
                        )
                )
        );

        titlePanel.add(
                statusLabel,
                BorderLayout.EAST
        );

        card.add(
                titlePanel,
                BorderLayout.NORTH
        );
        // =========================================================
// ACCEPT / DECLINE BUTTONS
// =========================================================

        JPanel actionPanel = new JPanel(
                new java.awt.FlowLayout(
                        java.awt.FlowLayout.RIGHT,
                        8,
                        0
                )
        );

        actionPanel.setBackground(Color.WHITE);

        javax.swing.JButton acceptButton
                = new javax.swing.JButton("Accept");

        javax.swing.JButton declineButton
                = new javax.swing.JButton("Decline");

        acceptButton.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        declineButton.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        acceptButton.setForeground(Color.WHITE);
        declineButton.setForeground(Color.WHITE);

        acceptButton.setBackground(
                new Color(40, 167, 69)
        );

        declineButton.setBackground(
                new Color(220, 53, 69)
        );

        acceptButton.setFocusPainted(false);
        declineButton.setFocusPainted(false);

        acceptButton.setBorder(
                BorderFactory.createEmptyBorder(
                        7, 14, 7, 14
                )
        );

        declineButton.setBorder(
                BorderFactory.createEmptyBorder(
                        7, 14, 7, 14
                )
        );
        actionPanel.add(acceptButton);
        actionPanel.add(declineButton);

        if (statusText.equalsIgnoreCase("PENDING")) {

            acceptButton.setVisible(true);
            declineButton.setVisible(true);

        } else {

            acceptButton.setVisible(false);
            declineButton.setVisible(false);
        }

        acceptButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Accept this appointment?",
                    "Accept Booking",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                updateBookingStatus(
                        bookingId,
                        "Accepted",
                        patientEmail,
                        patientName,
                        appointmentDate,
                        appointmentTime
                );
            }
        });

        declineButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Decline this appointment?",
                    "Decline Booking",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                updateBookingStatus(
                        bookingId,
                        "Declined",
                        patientEmail,
                        patientName,
                        appointmentDate,
                        appointmentTime
                );
            }
        });

        // =========================================================
        // BODY
        // =========================================================
        JPanel bodyPanel = new JPanel(
                new GridLayout(
                        1,
                        3,
                        0,
                        0
                )
        );

        bodyPanel.setBackground(Color.WHITE);

        // =========================================================
        // LEFT COLUMN
        //
        // Name
        // Blood Group
        // Appointment Date
        // =========================================================
        JPanel leftPanel = new JPanel(
                new GridLayout(
                        3,
                        1,
                        0,
                        2
                )
        );

        leftPanel.setBackground(Color.WHITE);

        leftPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        2,
                        0,
                        2,
                        20
                )
        );

        leftPanel.add(
                createDetailLabel(
                        "Name:",
                        patientName,
                        darkBlue,
                        textColor
                )
        );

        leftPanel.add(
                createDetailLabel(
                        "Blood Group:",
                        bloodGroup,
                        darkBlue,
                        textColor
                )
        );

        leftPanel.add(
                createDetailLabel(
                        "Appointment Date:",
                        appointmentDate,
                        darkBlue,
                        textColor
                )
        );

        // =========================================================
        // MIDDLE COLUMN
        //
        // Gender
        // Phone
        // Time Slot
        // =========================================================
        JPanel middlePanel = new JPanel(
                new GridLayout(
                        3,
                        1,
                        0,
                        2
                )
        );

        middlePanel.setBackground(Color.WHITE);

        middlePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        2,
                        15,
                        2,
                        15
                )
        );

        // Gender
        middlePanel.add(
                createDetailLabel(
                        "Gender:",
                        gender,
                        darkBlue,
                        textColor
                )
        );

        // Phone
        middlePanel.add(
                createDetailLabel(
                        "Phone:",
                        phone,
                        darkBlue,
                        textColor
                )
        );

        // TIME SLOT UNDER PHONE
        middlePanel.add(
                createDetailLabel(
                        "Time Slot:",
                        appointmentTime,
                        darkBlue,
                        textColor
                )
        );

        // =========================================================
        // RIGHT COLUMN
        //
        // Age + small status area
        // =========================================================
        JPanel rightPanel = new JPanel(
                new java.awt.GridBagLayout()
        );

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        2,
                        15,
                        2,
                        0
                )
        );

        java.awt.GridBagConstraints gbc
                = new java.awt.GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbc.fill = java.awt.GridBagConstraints.NONE;

        JLabel ageLabel = createDetailLabel(
                "Age:",
                String.valueOf(age),
                darkBlue,
                textColor
        );

        rightPanel.add(
                ageLabel,
                gbc
        );

        // =========================================================
        // ADD ALL COLUMNS
        // =========================================================
        bodyPanel.add(leftPanel);
        bodyPanel.add(middlePanel);
        bodyPanel.add(rightPanel);

        card.add(
                bodyPanel,
                BorderLayout.CENTER
        );

        card.add(
                actionPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    private void updateBookingStatus(
            int bookingId,
            String newStatus,
            String patientEmail,
            String patientName,
            String appointmentDate,
            String appointmentTime) {

        try {

            // Update booking status
            String query
                    = "UPDATE bookings "
                    + "SET status = '" + newStatus + "' "
                    + "WHERE booking_id = " + bookingId;

            DBLoader.executeUpdate(query);

            // Get logged-in doctor's name
            String doctorName = DoctorLogin.loggedInDoctorName;

            // Send email to patient
            if (newStatus.equalsIgnoreCase("Accepted")) {

                EmailSender.sendAppointmentAcceptedEmail(
                        patientEmail,
                        patientName,
                        doctorName,
                        appointmentDate,
                        appointmentTime
                );

            } else if (newStatus.equalsIgnoreCase("Declined")) {

                EmailSender.sendAppointmentDeclinedEmail(
                        patientEmail,
                        patientName,
                        doctorName,
                        appointmentDate,
                        appointmentTime
                );
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Booking "
                    + newStatus.toLowerCase()
                    + " successfully.",
                    "Booking Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Refresh booking list
            loadBookings();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error updating booking status:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private JLabel createDetailLabel(
            String title,
            String value,
            Color titleColor,
            Color valueColor) {

        if (value == null || value.trim().isEmpty()) {
            value = "-";
        }

        JLabel label = new JLabel(
                "<html>"
                + "<span style='color:#173F7A;'>"
                + "<b>" + title + "</b>"
                + "</span>"
                + "&nbsp;&nbsp;"
                + "<span style='color:#232D3C;'>"
                + value
                + "</span>"
                + "</html>"
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(valueColor);

        return label;
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        bookingPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bookingScrollPane = new javax.swing.JScrollPane();
        bookingsContainer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Bookings");
        setBackground(new java.awt.Color(234, 244, 255));
        setPreferredSize(new java.awt.Dimension(1200, 710));

        mainPanel.setBackground(new java.awt.Color(234, 244, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        sidePanel.setBackground(new java.awt.Color(233, 242, 253));
        sidePanel.setPreferredSize(new java.awt.Dimension(260, 775));

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
                .addGap(19, 19, 19)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(sidePanelLayout.createSequentialGroup()
                            .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(55, 55, 55))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        mainPanel.add(sidePanel, java.awt.BorderLayout.WEST);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 63, 122));
        jLabel6.setText("Welcome, John Doe");
        contentPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(80, 95, 120));
        jLabel7.setText("Manage your bookings");
        contentPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 63, -1, -1));

        jButton1.setBackground(new java.awt.Color(240, 247, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("=");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        contentPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 70, 50));

        bookingPanel.setBackground(new java.awt.Color(244, 247, 252));
        bookingPanel.setPreferredSize(new java.awt.Dimension(920, 610));
        bookingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(23, 63, 122));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Patient Bookings");
        bookingPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 280, -1));

        bookingScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 233, 242)));
        bookingScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bookingScrollPane.setPreferredSize(new java.awt.Dimension(900, 560));

        bookingsContainer.setBackground(new java.awt.Color(244, 247, 252));
        bookingsContainer.setLayout(new javax.swing.BoxLayout(bookingsContainer, javax.swing.BoxLayout.Y_AXIS));
        bookingScrollPane.setViewportView(bookingsContainer);

        bookingPanel.add(bookingScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 920, 540));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(23, 63, 122));
        jLabel9.setText("View patient details and scheduled appointments.");
        bookingPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jButton6.setBackground(new java.awt.Color(233, 242, 253));
        jButton6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton6.setForeground(new java.awt.Color(23, 63, 122));
        jButton6.setText("←   Back");
        jButton6.addActionListener(this::jButton6ActionPerformed);
        bookingPanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        contentPanel.add(bookingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, -1, -1));

        mainPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
// VIEW BOOKINGS FUNCTIONALITY
// ==========================================
        viewBookings.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                // Close popup
                popup.setVisible(false);

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

        popup.add(viewBookings);

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {

            DoctorLogin login = new DoctorLogin();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

            this.dispose();
        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DoctorHomePage home = new DoctorHomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ChangePassword pass = new ChangePassword();
        pass.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DoctorHomePage home = new DoctorHomePage();

        home.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton6ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DoctorViewBookings().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JScrollPane bookingScrollPane;
    private javax.swing.JPanel bookingsContainer;
    private javax.swing.JPanel contentPanel;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel userInfoPanel;
    // End of variables declaration//GEN-END:variables
}
