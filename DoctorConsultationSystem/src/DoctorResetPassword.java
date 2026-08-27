
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ARMAAN
 */
public class DoctorResetPassword extends javax.swing.JFrame {

    private String userEmail;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DoctorResetPassword.class.getName());
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form UserHomePage
     */
    public DoctorResetPassword(String email) {
        initComponents();
        setSize(1150, 720);
        setLocationRelativeTo(null);
        this.userEmail = email;

        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel28 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reset Password");
        setBackground(new java.awt.Color(234, 244, 255));

        mainPanel.setBackground(new java.awt.Color(234, 244, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(250, 252, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("New Password ");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 180, 25));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel9.setText("Confirm Password ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 45, 103));
        jLabel13.setText("RESET PASSWORD");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        jLabel14.setForeground(new java.awt.Color(18, 58, 122));
        jLabel14.setText("──────── ♥️ ────────");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 170, 230, -1));

        jButton9.setBackground(new java.awt.Color(45, 127, 249));
        jButton9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("RESET PASSWORD");
        jButton9.addActionListener(this::jButton9ActionPerformed);
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 496, 480, 50));
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 480, 41));
        jPanel3.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 480, 41));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/08_feature_secure_account-2.png"))); // NOI18N
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 53, -1, -1));

        jButton3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(45, 127, 249));
        jButton3.setText("← Back to Login ");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, -1, -1));

        contentPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 820, 700));

        jPanel1.setBackground(new java.awt.Color(237, 245, 255));
        jPanel1.setForeground(new java.awt.Color(93, 106, 127));
        jPanel1.setPreferredSize(new java.awt.Dimension(470, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(18, 58, 113));
        jLabel1.setText("Doctor Consultation");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(18, 58, 113));
        jLabel2.setText("System");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/doc1-2.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 410, 440));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/01_logo_heart_pulse-2.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        contentPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        mainPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        // Get passwords
        String newPassword
                = new String(jPasswordField1.getPassword());

        String confirmPassword
                = new String(jPasswordField3.getPassword());

        // ----------------------------------
        // 1. Check empty fields
        // ----------------------------------
        if (newPassword.isEmpty()
                || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both passwords."
            );

            return;
        }

        // ----------------------------------
        // 2. Check password length
        // ----------------------------------
        if (newPassword.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must be at least 6 characters."
            );

            return;
        }

        // ----------------------------------
        // 3. Check passwords match
        // ----------------------------------
        if (!newPassword.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match."
            );

            return;
        }

        // ----------------------------------
        // 4. Update password in MySQL
        // ----------------------------------
        String url
                = "jdbc:mysql://localhost:3306/DoctorConsultationSystem";

        String username = "root";

        String password = "system123";

        String sql
                = "UPDATE doctor SET pass = ? WHERE email = ?";

        try (
                Connection con
                = DriverManager.getConnection(
                        url,
                        username,
                        password
                ); PreparedStatement pst
                = con.prepareStatement(sql)) {

                    pst.setString(1, newPassword);
                    pst.setString(2, userEmail);

                    int rowsUpdated
                            = pst.executeUpdate();

                    if (rowsUpdated > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Password changed successfully!"
                        );

                        // Open login page
                        DoctorLogin login
                                = new DoctorLogin();

                        login.setLocationRelativeTo(null);
                        login.setVisible(true);

                        this.dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Unable to change password.\n"
                                + "User email was not found."
                        );
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    JOptionPane.showMessageDialog(
                            this,
                            "Database error:\n"
                            + e.getMessage()
                    );
                }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        DoctorLogin login
                = new DoctorLogin();

        login.setLocationRelativeTo(null);
        login.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton3ActionPerformed

        /**
         * @param args the command line arguments
         */
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
