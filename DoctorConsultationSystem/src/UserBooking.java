
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
public class UserBooking extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserBooking.class.getName());
    private static final long serialVersionUID = 1L;

// ==========================================
// SELECTED DOCTOR / BOOKING DETAILS
// ==========================================
    private String selectedDoctorEmail;
    private String selectedDoctorName;
    private String selectedSpeciality;
    private double selectedConsultationFee;

    private String selectedDate = "";

// ==========================================
// BOOKING VARIABLES
// ==========================================
    private double doctorConsultationFee = 0.0;

    private String selectedTimeSlot = null;

    private JButton selectedTimeButton = null;

    private final Color TIME_NORMAL_COLOR
            = new Color(255, 255, 255);

    private final Color TIME_HOVER_COLOR
            = new Color(233, 242, 253);

    private final Color TIME_SELECTED_COLOR
            = new Color(0, 102, 204);

    private final Color TIME_TEXT_COLOR
            = new Color(40, 55, 75);

    /**
     * Creates new form
     *
     *
     */
    public UserBooking() {

        initComponents();

        setSize(1200, 900);
        setLocationRelativeTo(null);

        // ==========================================
        // LOGGED-IN USER DETAILS
        // ==========================================
        jLabel6.setText(
                "Welcome, " + UserLogin.loggedInUserName
        );

        jLabel3.setText(
                "Name: " + UserLogin.loggedInUserName
        );

        jLabel4.setText(
                "Email: " + UserLogin.loggedInUserEmail
        );

        // ==========================================
        // DEFAULT CONSULTATION FEE
        // ==========================================
        jLabel23.setText("₹0");

        // ==========================================
        // SETUP BOOKING CONTROLS
        // ==========================================
        setupBookingControls();
    }

    public UserBooking(
            String doctorName,
            String doctorEmail,
            String speciality,
            int experience,
            String address,
            double consultationFee,
            String profilePic
    ) {

        initComponents();

        // ==========================================
        // STORE SELECTED DOCTOR DETAILS
        // ==========================================
        this.selectedDoctorName = doctorName;
        this.selectedDoctorEmail = doctorEmail;
        this.selectedSpeciality = speciality;
        this.selectedConsultationFee = consultationFee;

        setSize(1200, 900);
        setLocationRelativeTo(null);

        // ==========================================
        // STORE CONSULTATION FEE
        // ==========================================
        doctorConsultationFee = consultationFee;

        // ==========================================
        // LOGGED-IN USER DETAILS
        // ==========================================
        jLabel6.setText(
                "Welcome, " + UserLogin.loggedInUserName
        );

        jLabel3.setText(
                "Name: " + UserLogin.loggedInUserName
        );

        jLabel4.setText(
                "Email: " + UserLogin.loggedInUserEmail
        );

        // ==========================================
        // SELECTED DOCTOR DETAILS
        // ==========================================
        lblDoctorName.setText(doctorName);

        lblSpeciality.setText(speciality);

        jLabel15.setText(
                "Name                    : " + doctorName
        );

        jLabel13.setText(
                "Speciality              : " + speciality
        );

        jLabel14.setText(
                "Experience            : "
                + experience
                + " years"
        );

        jLabel12.setText(
                "Address                : " + address
        );

        jLabel16.setText(
                "Consultation Fee   : ₹"
                + String.format("%.0f", consultationFee)
        );

        // Dynamic consultation fee in the Note section
        jLabel23.setText(
                "₹" + String.format("%.0f", consultationFee)
        );

        // ==========================================
        // DYNAMIC CONSULTATION FEE IN NOTE
        // ==========================================
        jLabel23.setText(
                "₹" + String.format("%.0f", consultationFee)
        );

        // ==========================================
        // LOAD DOCTOR PROFILE IMAGE
        // ==========================================
        loadDoctorImage(profilePic);

        // ==========================================
        // SETUP BOOKING CONTROLS
        // ==========================================
        setupBookingControls();
    }
    // ==========================================
    //SELECT TIME SLOT
    // ==========================================

    private void loadDoctorImage(String profilePic) {

        try {

            if (profilePic != null
                    && !profilePic.trim().isEmpty()) {

                File imageFile = new File(profilePic);

                // If database contains relative path
                if (!imageFile.isAbsolute()) {

                    imageFile = new File(
                            System.getProperty("user.dir"),
                            profilePic
                    );
                }

                if (imageFile.exists()) {

                    ImageIcon originalIcon
                            = new ImageIcon(
                                    imageFile.getAbsolutePath()
                            );

                    java.awt.Image image
                            = originalIcon.getImage()
                                    .getScaledInstance(
                                            120,
                                            120,
                                            java.awt.Image.SCALE_SMOOTH
                                    );

                    lblDoctorPhoto.setIcon(
                            new ImageIcon(image)
                    );

                    lblDoctorPhoto.setText("");

                } else {

                    lblDoctorPhoto.setIcon(null);

                    lblDoctorPhoto.setText(
                            "No Photo"
                    );
                }

            } else {

                lblDoctorPhoto.setIcon(null);

                lblDoctorPhoto.setText(
                        "No Photo"
                );
            }

        } catch (Exception e) {

            lblDoctorPhoto.setIcon(null);

            lblDoctorPhoto.setText(
                    "No Photo"
            );

            System.out.println(
                    "Error loading doctor image: "
                    + e.getMessage()
            );
        }
    }

    // ==========================================================
// SETUP BOOKING CONTROLS
// ==========================================================
    private void setupBookingControls() {

        // ==========================================
        // DATE FIELD
        // ==========================================
        jTextField1.setEditable(false);

        jTextField1.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        jTextField1.setToolTipText(
                "Click to select appointment date"
        );

        // Remove old action listener behavior
        jTextField1.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                showCalendar();
            }
        });

        // ==========================================
        // TIME SLOT BUTTONS
        // ==========================================
        JButton[] timeButtons = {
            jButton7,
            jButton8,
            jButton9,
            jButton10,
            jButton11,
            jButton12,
            jButton13,
            jButton14,
            jButton15,
            jButton16,
            jButton17,
            jButton18,
            jButton19,
            jButton20,
            jButton21
        };

        for (JButton button : timeButtons) {

            setupTimeButton(button);
        }
    }
    // ==========================================================
    // TIME SLOT BUTTON SETUP
    // ==========================================================

    private void setupTimeButton(JButton button) {

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBackground(
                TIME_NORMAL_COLOR
        );

        button.setForeground(
                TIME_TEXT_COLOR
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 225, 235)
                )
        );

        // ==========================================
        // HOVER + CLICK
        // ==========================================
        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                // Do not change selected button
                if (button != selectedTimeButton) {

                    button.setBackground(
                            TIME_HOVER_COLOR
                    );

                    button.setForeground(
                            TIME_TEXT_COLOR
                    );
                }
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                // Do not change selected button
                if (button != selectedTimeButton) {

                    button.setBackground(
                            TIME_NORMAL_COLOR
                    );

                    button.setForeground(
                            TIME_TEXT_COLOR
                    );
                }
            }

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                selectTimeSlot(button);
            }
        });
    }
    // ==========================================================
// SELECT TIME SLOT
// ==========================================================

    private void selectTimeSlot(JButton button) {

        // ==========================================
        // RESET PREVIOUSLY SELECTED BUTTON
        // ==========================================
        if (selectedTimeButton != null) {

            selectedTimeButton.setBackground(
                    TIME_NORMAL_COLOR
            );

            selectedTimeButton.setForeground(
                    TIME_TEXT_COLOR
            );
        }

        // ==========================================
        // SELECT NEW BUTTON
        // ==========================================
        selectedTimeButton = button;

        selectedTimeSlot = button.getText();

        button.setBackground(
                TIME_SELECTED_COLOR
        );

        button.setForeground(
                Color.WHITE
        );

        // ==========================================
        // OPTIONAL: SHOW SELECTION
        // ==========================================
        System.out.println(
                "Selected Time: " + selectedTimeSlot
        );
    }

    private void showCalendar() {

        final JDialog calendarDialog
                = new JDialog(
                        this,
                        "Select Appointment Date",
                        true
                );

        calendarDialog.setSize(380, 350);

        calendarDialog.setLocationRelativeTo(jTextField1);

        calendarDialog.setLayout(
                new BorderLayout(10, 10)
        );

        // ==========================================
        // CALENDAR
        // ==========================================
        Calendar calendar = Calendar.getInstance();

        // ==========================================
        // HEADER
        // ==========================================
        JPanel headerPanel = new JPanel(
                new BorderLayout()
        );

        JButton previousMonth
                = new JButton("←");

        JButton nextMonth
                = new JButton("→");

        JLabel monthLabel
                = new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        monthLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        monthLabel.setForeground(
                new Color(23, 63, 122)
        );

        headerPanel.add(
                previousMonth,
                BorderLayout.WEST
        );

        headerPanel.add(
                monthLabel,
                BorderLayout.CENTER
        );

        headerPanel.add(
                nextMonth,
                BorderLayout.EAST
        );

        calendarDialog.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // DAYS PANEL
        // ==========================================
        JPanel daysPanel = new JPanel(
                new GridLayout(
                        0,
                        7,
                        5,
                        5
                )
        );

        calendarDialog.add(
                daysPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // UPDATE CALENDAR
        // ==========================================
        Runnable updateCalendar = () -> {

            daysPanel.removeAll();

            String[] days = {
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
            };

            // ==========================================
            // DAY HEADINGS
            // ==========================================
            for (String day : days) {

                JLabel dayLabel
                        = new JLabel(
                                day,
                                SwingConstants.CENTER
                        );

                dayLabel.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

                dayLabel.setForeground(
                        new Color(23, 63, 122)
                );

                daysPanel.add(dayLabel);
            }

            // ==========================================
            // FIRST DAY OF MONTH
            // ==========================================
            Calendar temp
                    = (Calendar) calendar.clone();

            temp.set(
                    Calendar.DAY_OF_MONTH,
                    1
            );

            int firstDay
                    = temp.get(Calendar.DAY_OF_WEEK);

            int daysInMonth
                    = temp.getActualMaximum(
                            Calendar.DAY_OF_MONTH
                    );

            // ==========================================
            // EMPTY CELLS BEFORE FIRST DAY
            // ==========================================
            for (int i = 1; i < firstDay; i++) {

                daysPanel.add(
                        new JLabel("")
                );
            }

            // ==========================================
            // TODAY
            // ==========================================
            Calendar today = Calendar.getInstance();

            // Remove time from today's date
            today.set(
                    Calendar.HOUR_OF_DAY,
                    0
            );

            today.set(
                    Calendar.MINUTE,
                    0
            );

            today.set(
                    Calendar.SECOND,
                    0
            );

            today.set(
                    Calendar.MILLISECOND,
                    0
            );

            // ==========================================
            // CREATE DATE BUTTONS
            // ==========================================
            for (int day = 1;
                    day <= daysInMonth;
                    day++) {

                final int selectedDay = day;

                JButton dayButton
                        = new JButton(
                                String.valueOf(day)
                        );

                dayButton.setFocusPainted(false);

                dayButton.setOpaque(true);

                dayButton.setBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        235
                                )
                        )
                );

                // ==========================================
                // CREATE DATE FOR THIS BUTTON
                // ==========================================
                Calendar buttonDate
                        = (Calendar) calendar.clone();

                buttonDate.set(
                        Calendar.DAY_OF_MONTH,
                        selectedDay
                );

                // Remove time from button date
                buttonDate.set(
                        Calendar.HOUR_OF_DAY,
                        0
                );

                buttonDate.set(
                        Calendar.MINUTE,
                        0
                );

                buttonDate.set(
                        Calendar.SECOND,
                        0
                );

                buttonDate.set(
                        Calendar.MILLISECOND,
                        0
                );

                // ==========================================
                // PAST DATE
                // ==========================================
                if (buttonDate.before(today)) {

                    // Disable past date
                    dayButton.setEnabled(false);

                    dayButton.setBackground(
                            new Color(
                                    240,
                                    240,
                                    240
                            )
                    );

                    dayButton.setForeground(
                            Color.GRAY
                    );

                } else {

                    // ==========================================
                    // FUTURE / TODAY DATE
                    // ==========================================
                    dayButton.setBackground(
                            Color.WHITE
                    );

                    dayButton.setForeground(
                            new Color(
                                    40,
                                    55,
                                    75
                            )
                    );

                    dayButton.setCursor(
                            new Cursor(
                                    Cursor.HAND_CURSOR
                            )
                    );

                    // ==========================================
                    // HOVER
                    // ==========================================
                    dayButton.addMouseListener(
                            new java.awt.event.MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                java.awt.event.MouseEvent e) {

                            dayButton.setBackground(
                                    new Color(
                                            233,
                                            242,
                                            253
                                    )
                            );
                        }

                        @Override
                        public void mouseExited(
                                java.awt.event.MouseEvent e) {

                            dayButton.setBackground(
                                    Color.WHITE
                            );
                        }

                        // ==========================================
                        // SELECT DATE
                        // ==========================================
                        @Override
                        public void mouseClicked(
                                java.awt.event.MouseEvent e) {

                            calendar.set(
                                    Calendar.DAY_OF_MONTH,
                                    selectedDay
                            );

                            Date selectedDate
                                    = calendar.getTime();

                            SimpleDateFormat format
                                    = new SimpleDateFormat(
                                            "yyyy-MM-dd"
                                    );

                            jTextField1.setText(
                                    format.format(
                                            selectedDate
                                    )
                            );

                            calendarDialog.dispose();
                        }
                    });
                }

                daysPanel.add(dayButton);
            }

            // ==========================================
            // MONTH LABEL
            // ==========================================
            monthLabel.setText(
                    new SimpleDateFormat(
                            "MMMM yyyy"
                    ).format(
                            calendar.getTime()
                    )
            );

            daysPanel.revalidate();

            daysPanel.repaint();
        };

        // ==========================================
        // PREVIOUS MONTH
        // ==========================================
        previousMonth.addActionListener(e -> {

            Calendar currentMonth
                    = Calendar.getInstance();

            // Current month and year
            int currentYear
                    = currentMonth.get(
                            Calendar.YEAR
                    );

            int currentMonthNumber
                    = currentMonth.get(
                            Calendar.MONTH
                    );

            // Selected calendar month and year
            int selectedYear
                    = calendar.get(
                            Calendar.YEAR
                    );

            int selectedMonth
                    = calendar.get(
                            Calendar.MONTH
                    );

            // ==========================================
            // ALLOW PREVIOUS MONTH ONLY IF IT IS NOT
            // BEFORE THE CURRENT MONTH
            // ==========================================
            if (selectedYear > currentYear
                    || (selectedYear == currentYear
                    && selectedMonth > currentMonthNumber)) {

                calendar.add(
                        Calendar.MONTH,
                        -1
                );

                updateCalendar.run();
            }
        });

        // ==========================================
        // NEXT MONTH
        // ==========================================
        nextMonth.addActionListener(e -> {

            calendar.add(
                    Calendar.MONTH,
                    1
            );

            updateCalendar.run();
        });

        // ==========================================
        // DISPLAY CALENDAR
        // ==========================================
        updateCalendar.run();

        calendarDialog.setVisible(true);
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
        doctorDetailsPanel = new javax.swing.JPanel();
        lblDoctorDetails = new javax.swing.JLabel();
        lblDoctorPhoto = new javax.swing.JLabel();
        lblDoctorName = new javax.swing.JLabel();
        lblSpeciality = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        bookingPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblSelectDate = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();

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
        jLabel5.setText("Next Booking");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(330, 20, 250, 50);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Please review doctor details and select a suitable time slot.");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(240, 70, 420, 19);

        doctorPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 941, -1));

        doctorDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        doctorDetailsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 225, 245)));
        doctorDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDoctorDetails.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblDoctorDetails.setForeground(new java.awt.Color(23, 63, 122));
        lblDoctorDetails.setText("Doctor Details");
        doctorDetailsPanel.add(lblDoctorDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 160, 30));

        lblDoctorPhoto.setBackground(new java.awt.Color(233, 242, 253));
        lblDoctorPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoctorPhoto.setText("jLabel9");
        lblDoctorPhoto.setOpaque(true);
        doctorDetailsPanel.add(lblDoctorPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 55, 120, 120));

        lblDoctorName.setFont(new java.awt.Font("Arial", 1, 21)); // NOI18N
        lblDoctorName.setForeground(new java.awt.Color(23, 63, 122));
        lblDoctorName.setText("Doctor Name");
        lblDoctorName.setToolTipText("");
        doctorDetailsPanel.add(lblDoctorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 65, 190, 35));

        lblSpeciality.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblSpeciality.setForeground(new java.awt.Color(0, 102, 204));
        lblSpeciality.setText("Speciality");
        doctorDetailsPanel.add(lblSpeciality, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 100, 110, 25));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel9.setText("Phone Number     :");
        doctorDetailsPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 390, -1));
        doctorDetailsPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 440, -1));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        doctorDetailsPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, -1));
        doctorDetailsPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 440, 10));

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel11.setText("Gender                  :");
        doctorDetailsPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 390, -1));
        doctorDetailsPanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 440, 10));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel12.setText("Address                :");
        doctorDetailsPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 390, 30));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel13.setText("Speciality              :");
        doctorDetailsPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 390, -1));
        doctorDetailsPanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 440, 20));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel14.setText("Experience            :");
        doctorDetailsPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 390, -1));
        doctorDetailsPanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 440, 10));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel15.setText("Name                    :");
        doctorDetailsPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 390, -1));
        doctorDetailsPanel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 440, 10));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel16.setText("Consultation Fee   :");
        doctorDetailsPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 390, 40));

        doctorPanel.add(doctorDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, 440, 520));

        bookingPanel.setBackground(new java.awt.Color(255, 255, 255));
        bookingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 225, 245)));
        bookingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(23, 63, 122));
        jLabel17.setText("Booking Date & Time");
        bookingPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 210, 30));

        lblSelectDate.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblSelectDate.setText("Select Date");
        bookingPanel.add(lblSelectDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, 25));

        jTextField1.setText("Select a date");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        bookingPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 95, 405, 40));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(23, 63, 122));
        jLabel18.setText("Available Time Slots");
        bookingPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, 25));

        jButton7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(40, 55, 75));
        jButton7.setText("07:30 PM");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 125, 38));

        jButton8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(40, 55, 75));
        jButton8.setText("09:00 AM");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 125, 38));

        jButton9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(40, 55, 75));
        jButton9.setText("09:30 AM");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 125, 38));

        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(40, 55, 75));
        jButton10.setText("10:00 AM");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 125, 38));

        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(40, 55, 75));
        jButton11.setText("10:30 AM");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 125, 38));

        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(40, 55, 75));
        jButton12.setText("11:00 AM");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 125, 38));

        jButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(40, 55, 75));
        jButton13.setText("11:30 AM");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 125, 38));

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(40, 55, 75));
        jButton14.setText("12:00 PM");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 125, 38));

        jButton15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton15.setText("12:30 PM");
        jButton15.setToolTipText("");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 125, 38));

        jButton16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(40, 55, 75));
        jButton16.setText("01:00 PM");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 125, 38));

        jButton17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(40, 55, 75));
        jButton17.setText("05:00 PM");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        jButton17.addActionListener(this::jButton17ActionPerformed);
        bookingPanel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 125, 38));

        jButton18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(40, 55, 75));
        jButton18.setText("05:30 PM");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 125, 38));

        jButton19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(40, 55, 75));
        jButton19.setText("06:00 PM");
        jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 125, 38));

        jButton20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(40, 55, 75));
        jButton20.setText("06:30 PM");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        bookingPanel.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 125, 38));

        jButton21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton21.setForeground(new java.awt.Color(40, 55, 75));
        jButton21.setText("07:00 PM");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 235)));
        jButton21.addActionListener(this::jButton21ActionPerformed);
        bookingPanel.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 125, 38));

        jPanel2.setBackground(new java.awt.Color(233, 242, 253));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(23, 63, 122));
        jLabel19.setText("ⓘ  Please arrive 10 minutes before the selected time.");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(23, 63, 122));
        jLabel20.setText(" Your appointment is important to us.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel20)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        bookingPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 405, 50));
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        doctorPanel.add(bookingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 450, 520));

        jPanel3.setBackground(new java.awt.Color(250, 252, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 230, 238)));
        jPanel3.setPreferredSize(new java.awt.Dimension(900, 200));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(23, 63, 122));
        jLabel21.setText("Note:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 20));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(23, 63, 122));
        jLabel22.setText("• Consultation fees is");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 140, 20));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(23, 63, 122));
        jLabel23.setText("₹1000 ");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 50, 20));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(23, 63, 122));
        jLabel24.setText("(Pay at clinic)");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(23, 63, 122));
        jLabel25.setText("• You can reschedule or cancel your appointment from bookings.");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jButton22.setBackground(new java.awt.Color(0, 102, 204));
        jButton22.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Confirm Booking →");
        jButton22.addActionListener(this::jButton22ActionPerformed);
        jPanel3.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 15, 180, 50));

        doctorPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 910, 80));
        jPanel3.getAccessibleContext().setAccessibleName("");
        jPanel3.getAccessibleContext().setAccessibleDescription("");

        contentPanel.add(doctorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 940, 730));

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
        UserLogin login = new UserLogin();
        login.setVisible(true);
        this.dispose();
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        showCalendar();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

        selectedTimeSlot = jButton17.getText();


    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        selectedTimeSlot = jButton21.getText();


    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        // ==========================================
        // CHECK LOGIN
        // ==========================================
        if (UserLogin.loggedInUserEmail == null
                || UserLogin.loggedInUserEmail.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please login before booking.",
                    "Login Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==========================================
        // CHECK DOCTOR
        // ==========================================
        if (selectedDoctorEmail == null
                || selectedDoctorEmail.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor information is missing.",
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // ==========================================
        // CHECK DATE
        // ==========================================
        String appointmentDate = jTextField1.getText().trim();

        if (appointmentDate.isEmpty()
                || appointmentDate.equalsIgnoreCase("Select a date")
                || appointmentDate.equalsIgnoreCase("Select Date")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment date.",
                    "Date Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

// ==========================================
// PREVENT BOOKING PAST DATES
// ==========================================
        try {

            SimpleDateFormat sdf
                    = new SimpleDateFormat("yyyy-MM-dd");

            sdf.setLenient(false);

            Date selectedDate
                    = sdf.parse(appointmentDate);

            Calendar today
                    = Calendar.getInstance();

            today.set(
                    Calendar.HOUR_OF_DAY,
                    0
            );

            today.set(
                    Calendar.MINUTE,
                    0
            );

            today.set(
                    Calendar.SECOND,
                    0
            );

            today.set(
                    Calendar.MILLISECOND,
                    0
            );

            if (selectedDate.before(today.getTime())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Past dates are not allowed.\n"
                        + "Please select today or a future date.",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid appointment date.",
                    "Date Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

// ==========================================
// CHECK TIME
// ==========================================
        if (selectedTimeSlot == null
                || selectedTimeSlot.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an available time slot.",
                    "Time Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        // ==========================================
        // CHECK TIME
        // ==========================================
        if (selectedTimeSlot == null
                || selectedTimeSlot.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an available time slot.",
                    "Time Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==========================================
        // CONFIRM BOOKING
        // ==========================================
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Confirm your appointment?\n\n"
                + "Doctor: " + selectedDoctorName + "\n"
                + "Date: " + appointmentDate + "\n"
                + "Time: " + selectedTimeSlot + "\n"
                + "Consultation Fee: ₹"
                + String.format("%.0f", selectedConsultationFee),
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            // ==========================================
            // LOGGED-IN USER DETAILS
            // ==========================================
            String patientEmail = UserLogin.loggedInUserEmail;

            String patientName = UserLogin.loggedInUserName;

            // ==========================================
            // CONVERT TIME
            // ==========================================
            String appointmentTime;

            try {

                SimpleDateFormat inputFormat
                        = new SimpleDateFormat("hh:mm a");

                SimpleDateFormat mysqlFormat
                        = new SimpleDateFormat("HH:mm:ss");

                Date time
                        = inputFormat.parse(selectedTimeSlot);

                appointmentTime
                        = mysqlFormat.format(time);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid appointment time.",
                        "Time Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // ==========================================
            // CHECK IF SLOT IS ALREADY BOOKED
            // ==========================================
            String checkQuery
                    = "SELECT * FROM bookings "
                    + "WHERE doctor_email = '" + selectedDoctorEmail + "' "
                    + "AND appointment_date = '" + appointmentDate + "' "
                    + "AND appointment_time = '" + appointmentTime + "' "
                    + "AND status <> 'Cancelled'";

            ResultSet checkRs
                    = DBLoader.executeQuery(checkQuery);

            if (checkRs != null && checkRs.next()) {

                checkRs.close();

                JOptionPane.showMessageDialog(
                        this,
                        "This time slot has already been booked.\n\n"
                        + "Please select another available time slot.",
                        "Slot Already Booked",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (checkRs != null) {
                checkRs.close();
            }

            // ==========================================
            // FETCH PATIENT DETAILS
            // ==========================================
            String patientQuery
                    = "SELECT fullname, gender, age, bloodgroup, phone "
                    + "FROM user "
                    + "WHERE email = '" + patientEmail + "'";

            ResultSet rs
                    = DBLoader.executeQuery(patientQuery);

            if (rs == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not access patient information.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (!rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient details could not be found.\n"
                        + "Please complete your profile first.",
                        "Patient Details Missing",
                        JOptionPane.WARNING_MESSAGE
                );

                rs.close();

                return;
            }

            // ==========================================
            // GET PATIENT DETAILS
            // ==========================================
            patientName
                    = rs.getString("fullname");

            String gender
                    = rs.getString("gender");

            int age
                    = rs.getInt("age");

            String bloodGroup
                    = rs.getString("bloodgroup");

            String phone
                    = rs.getString("phone");

            rs.close();

            // ==========================================
            // VALIDATE PATIENT DETAILS
            // ==========================================
            if (gender == null
                    || gender.trim().isEmpty()
                    || bloodGroup == null
                    || bloodGroup.trim().isEmpty()
                    || phone == null
                    || phone.trim().isEmpty()
                    || age <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please complete your profile details\n"
                        + "(Gender, Age, Blood Group and Phone)\n"
                        + "before booking an appointment.",
                        "Profile Incomplete",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // ==========================================
            // INSERT BOOKING
            // ==========================================
            String query
                    = "INSERT INTO bookings "
                    + "(doctor_email, patient_email, patient_name, "
                    + "appointment_date, appointment_time, status, "
                    + "gender, age, blood_group, phone) "
                    + "VALUES ("
                    + "'" + selectedDoctorEmail + "', "
                    + "'" + patientEmail + "', "
                    + "'" + patientName + "', "
                    + "'" + appointmentDate + "', "
                    + "'" + appointmentTime + "', "
                    + "'Pending', "
                    + "'" + gender + "', "
                    + age + ", "
                    + "'" + bloodGroup + "', "
                    + "'" + phone + "'"
                    + ")";

            DBLoader.executeUpdate(query);
            // ==========================================
          // EMAIL TO USER
         // ==========================================

            EmailSender.sendBookingRequestToUser(
                    patientEmail,
                    patientName,
                    selectedDoctorName,
                    selectedSpeciality,
                    appointmentDate,
                    selectedTimeSlot
            );

             // ==========================================
             // EMAIL TO DOCTOR
             // ==========================================
            EmailSender.sendNewBookingToDoctor(
                    selectedDoctorEmail,
                    selectedDoctorName,
                    patientName,
                    appointmentDate,
                    selectedTimeSlot
            );

            // ==========================================
            // SUCCESS
            // ==========================================
            JOptionPane.showMessageDialog(
                    this,
                    "Appointment booked successfully!",
                    "Booking Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // ==========================================
            // OPEN USER HOME
            // ==========================================
            UserHome home
                    = new UserHome();

            home.setVisible(true);

            this.dispose();

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            // ==========================================
            // DATABASE UNIQUE CONSTRAINT
            // ==========================================
            JOptionPane.showMessageDialog(
                    this,
                    "This time slot has already been booked "
                    + "by another user.\n\n"
                    + "Please select another time slot.",
                    "Slot Already Booked",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while saving booking:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton22ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new UserBooking().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel doctorDetailsPanel;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDoctorDetails;
    private javax.swing.JLabel lblDoctorName;
    private javax.swing.JLabel lblDoctorPhoto;
    private javax.swing.JLabel lblSelectDate;
    private javax.swing.JLabel lblSpeciality;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel userInfoPanel;
    // End of variables declaration//GEN-END:variables
}
