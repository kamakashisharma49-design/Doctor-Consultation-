
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ImageIcon;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author
 */
public class Pediatrician extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pediatrician.class.getName());
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form
     *
     *
     */
    public Pediatrician() {
        initComponents();
        setSize(1200, 780);
        setLocationRelativeTo(null);
        jLabel6.setText("Welcome, " + UserLogin.loggedInUserName);
        jLabel3.setText("Name: " + UserLogin.loggedInUserName);
        jLabel4.setText("Email: " + UserLogin.loggedInUserEmail);
        /// Load doctors from sql database 
        loadDoctors();

    }

// ==========================================
// LOAD DOCTORS
// ==========================================
    private void loadDoctors() {

        try {

            // ==========================================
            // CLEAR EXISTING DOCTOR CARDS
            // ==========================================
            jPanel2.removeAll();

            // ==========================================
            // VERTICAL LAYOUT
            // ==========================================
            jPanel2.setLayout(
                    new javax.swing.BoxLayout(
                            jPanel2,
                            javax.swing.BoxLayout.Y_AXIS
                    )
            );

            // ==========================================
            // SQL QUERY
            // ==========================================
            String query
                    = "SELECT * FROM doctor "
                    + "WHERE speciality = 'Pediatrician'";

            ResultSet rs
                    = DBLoader.executeQuery(query);

            // ==========================================
            // READ DOCTORS
            // ==========================================
            while (rs.next()) {

                // Doctor name
                String name
                        = rs.getString("fullname");

                // Speciality
                String speciality
                        = rs.getString("speciality");

                // Experience
                int experience
                        = rs.getInt("experience");

                // Address
                String address
                        = rs.getString("address");

                // Consultation fee
                double consultationFee
                        = rs.getDouble("consultationfee");

                String doctorEmail
                        = rs.getString("email");

                // ==========================================
                // PROFILE PICTURE PATH
                // ==========================================
                String profilePic
                        = rs.getString("profile_picture");

                // ==========================================
                // CREATE DOCTOR CARD
                // ==========================================
                JPanel card
                        = createDoctorCard(
                                name,
                                speciality,
                                doctorEmail,
                                experience,
                                address,
                                consultationFee,
                                profilePic
                        );

                // Add card
                jPanel2.add(card);

                // Space between cards
                jPanel2.add(
                        javax.swing.Box.createVerticalStrut(15)
                );
            }

            // ==========================================
            // REFRESH
            // ==========================================
            jPanel2.revalidate();
            jPanel2.repaint();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading doctors:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

// ==========================================
// CREATE DOCTOR CARD
// ==========================================
    private JPanel createDoctorCard(
            String name,
            String speciality,
            String doctorEmail,
            int experience,
            String address,
            double consultationFee,
            String profilePic) {

        // ==========================================
        // CREATE CARD
        // ==========================================
        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(210, 225, 245),
                        2
                )
        );

        // ==========================================
        // CARD SIZE
        // ==========================================
        card.setPreferredSize(
                new Dimension(900, 175)
        );

        card.setMinimumSize(
                new Dimension(900, 175)
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        175
                )
        );

        // ==========================================
        // PROFILE PICTURE
        // ==========================================
        JLabel lblProfile
                = new JLabel();

        lblProfile.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        lblProfile.setVerticalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        lblProfile.setBounds(
                20,
                27,
                120,
                120
        );

        lblProfile.setBackground(
                new Color(233, 242, 253)
        );

        lblProfile.setOpaque(true);

        lblProfile.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 220, 245),
                        1
                )
        );
        // ==========================================
// LOAD PROFILE PICTURE FROM DATABASE PATH
// ==========================================

        try {

            if (profilePic != null && !profilePic.trim().isEmpty()) {

                File imageFile = new File(profilePic);

                // If database contains relative path such as:
                // uploads/doctor_xxx.png
                if (!imageFile.isAbsolute()) {
                    imageFile = new File(
                            System.getProperty("user.dir"),
                            profilePic
                    );
                }

                if (imageFile.exists()) {

                    ImageIcon originalIcon
                            = new ImageIcon(imageFile.getAbsolutePath());

                    java.awt.Image image
                            = originalIcon.getImage().getScaledInstance(
                                    110,
                                    110,
                                    java.awt.Image.SCALE_SMOOTH
                            );

                    lblProfile.setIcon(
                            new ImageIcon(image)
                    );

                    lblProfile.setText("");

                } else {

                    lblProfile.setText("No Photo");

                    System.out.println(
                            "Image not found: "
                            + imageFile.getAbsolutePath()
                    );
                }

            } else {

                lblProfile.setText("No Photo");
            }

        } catch (Exception e) {

            lblProfile.setText("No Photo");

            System.out.println(
                    "Error loading profile picture: "
                    + e.getMessage()
            );
        }

        card.add(lblProfile);

        // ==========================================
        // DOCTOR NAME
        // ==========================================
        JLabel lblName
                = new JLabel(name);

        lblName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        lblName.setForeground(
                new Color(23, 63, 122)
        );

        lblName.setBounds(
                160,
                15,
                450,
                30
        );

        card.add(lblName);

        // ==========================================
        // SPECIALITY
        // ==========================================
        JLabel lblSpeciality
                = new JLabel(
                        "Speciality: "
                        + speciality
                );

        lblSpeciality.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        lblSpeciality.setForeground(
                new Color(0, 102, 204)
        );

        lblSpeciality.setBounds(
                160,
                48,
                400,
                25
        );

        card.add(lblSpeciality);

        // ==========================================
        // EXPERIENCE
        // ==========================================
        JLabel lblExperience
                = new JLabel(
                        "Experience: "
                        + experience
                        + " years"
                );

        lblExperience.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        lblExperience.setForeground(
                new Color(60, 60, 60)
        );

        lblExperience.setBounds(
                160,
                80,
                300,
                25
        );

        card.add(lblExperience);

        // ==========================================
        // ADDRESS
        // ==========================================
        JLabel lblAddress
                = new JLabel(
                        "Address: "
                        + address
                );

        lblAddress.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        lblAddress.setForeground(
                new Color(60, 60, 60)
        );

        lblAddress.setBounds(
                160,
                108,
                480,
                25
        );

        card.add(lblAddress);

        // ==========================================
        // CONSULTATION FEE BADGE
        // ==========================================
        JLabel lblFee
                = new JLabel(
                        "Consultation Fee: ₹"
                        + String.format(
                                "%.0f",
                                consultationFee
                        )
                );

        lblFee.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        lblFee.setForeground(
                new Color(23, 63, 122)
        );

        lblFee.setBackground(
                new Color(233, 242, 253)
        );

        lblFee.setOpaque(true);

        lblFee.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        lblFee.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 220, 245),
                        1
                )
        );

        lblFee.setBounds(
                650,
                45,
                200,
                32
        );

        card.add(lblFee);

        // ==========================================
        // BOOK APPOINTMENT BUTTON
        // ==========================================
        JButton btnBook
                = new JButton(
                        "Book Appointment →"
                );

        btnBook.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        btnBook.setForeground(
                Color.WHITE
        );

        btnBook.setBackground(
                new Color(0, 102, 204)
        );

        btnBook.setFocusPainted(false);

        btnBook.setBounds(
                650,
                85,
                200,
                40
        );

        card.add(btnBook);

        // ==========================================
        // BOOK APPOINTMENT ACTION
        // ==========================================
        btnBook.addActionListener(e -> {

            UserBooking booking = new UserBooking(
                    name,
                    doctorEmail,
                    speciality,
                    experience,
                    address,
                    consultationFee,
                    profilePic
            );

            booking.setLocationRelativeTo(null);

            booking.setVisible(true);

            this.dispose();

        });

        // ==========================================
        // RETURN CARD
        // ==========================================
        return card;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

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
                        .addGap(20, 20, 20)
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
                .addGap(143, 143, 143)
                .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(sidePanel, java.awt.BorderLayout.WEST);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(250, 252, 255));
        headerPanel.setPreferredSize(new java.awt.Dimension(920, 110));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 63, 122));
        jLabel6.setText("Welcome, John Doe");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(80, 95, 120));
        jLabel7.setText("Book appointments, manage your bookings and account.");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 443, Short.MAX_VALUE)
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
        jLabel5.setText("Pediatricians");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(330, 20, 250, 50);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Find the best pediatrician for your care.");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(320, 70, 290, 19);

        doctorPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 941, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(920, 480));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setSize(new java.awt.Dimension(920, 510));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        doctorPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        jScrollPane1.getAccessibleContext().setAccessibleDescription("");

        contentPanel.add(doctorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 940, 620));

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        UserHome home = new UserHome();

        home.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Pediatrician().setVisible(true));
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel userInfoPanel;
    // End of variables declaration//GEN-END:variables
}
