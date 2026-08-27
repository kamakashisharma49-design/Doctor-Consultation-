
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String SENDER_EMAIL
            = "demo@gmail.com";

    // IMPORTANT:
    // Use your Gmail App Password here.
    // Do NOT use your normal Gmail password.
    private static final String APP_PASSWORD
            = "abcd";

    // ==========================================
    // CREATE EMAIL SESSION
    // ==========================================
    private static Session createSession() {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(
                props,
                new Authenticator() {

            @Override
            protected PasswordAuthentication
                    getPasswordAuthentication() {

                return new PasswordAuthentication(
                        SENDER_EMAIL,
                        APP_PASSWORD
                );
            }
        }
        );
    }

    // ==========================================
    // COMMON METHOD FOR SENDING EMAIL
    // ==========================================
    private static boolean sendEmail(
            String receiverEmail,
            String subject,
            String emailMessage) {

        try {

            Session session = createSession();

            Message message = new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(SENDER_EMAIL)
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail)
            );

            message.setSubject(subject);

            message.setText(emailMessage);

            Transport.send(message);

            return true;

        } catch (MessagingException e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================
    // SEND OTP
    // ==========================================
    public static boolean sendOTP(
            String receiverEmail,
            String otp) {

        String message
                = "Hello,\n\n"
                + "Your OTP for resetting your password is:\n\n"
                + otp
                + "\n\n"
                + "This OTP is valid for 5 minutes.\n"
                + "If you did not request a password reset, "
                + "please ignore this email.\n\n"
                + "Regards,\n"
                + "Doctor Consultation System";

        return sendEmail(
                receiverEmail,
                "Password Reset OTP",
                message
        );
    }

    // ==========================================
    // EMAIL TO USER AFTER BOOKING
    // ==========================================
    public static void sendBookingRequestToUser(
            String userEmail,
            String userName,
            String doctorName,
            String speciality,
            String appointmentDate,
            String appointmentTime) {

        new Thread(() -> {

            String message
                    = "Dear " + userName + ",\n\n"
                    + "Your appointment request has been received successfully.\n\n"
                    + "APPOINTMENT DETAILS\n"
                    + "----------------------------------\n"
                    + "Doctor: Dr. " + doctorName + "\n"
                    + "Speciality: " + speciality + "\n"
                    + "Date: " + appointmentDate + "\n"
                    + "Time: " + appointmentTime + "\n"
                    + "Status: Pending\n"
                    + "----------------------------------\n\n"
                    + "Your appointment is waiting for confirmation from the doctor.\n\n"
                    + "You will receive another email once the doctor "
                    + "accepts or declines your appointment.\n\n"
                    + "Regards,\n"
                    + "Doctor Consultation System";

            sendEmail(
                    userEmail,
                    "Appointment Request Received",
                    message
            );

        }).start();
    }

    // ==========================================
    // EMAIL TO DOCTOR AFTER NEW BOOKING
    // ==========================================
    public static void sendNewBookingToDoctor(
            String doctorEmail,
            String doctorName,
            String patientName,
            String appointmentDate,
            String appointmentTime) {

        new Thread(() -> {

            String message
                    = "Dear Dr. " + doctorName + ",\n\n"
                    + "You have received a new appointment request.\n\n"
                    + "BOOKING DETAILS\n"
                    + "----------------------------------\n"
                    + "Patient Name: " + patientName + "\n"
                    + "Date: " + appointmentDate + "\n"
                    + "Time: " + appointmentTime + "\n"
                    + "Status: Pending\n"
                    + "----------------------------------\n\n"
                    + "Please login to MediCare to accept or decline "
                    + "this appointment.\n\n"
                    + "Regards,\n"
                    + "MediCare";

            sendEmail(
                    doctorEmail,
                    "New Appointment Request",
                    message
            );

        }).start();
    }

    // ==========================================
    // EMAIL WHEN DOCTOR ACCEPTS
    // ==========================================
    public static void sendAppointmentAcceptedEmail(
            String userEmail,
            String userName,
            String doctorName,
            String appointmentDate,
            String appointmentTime) {

        new Thread(() -> {

            String message
                    = "Dear " + userName + ",\n\n"
                    + "Great news! Your appointment has been confirmed.\n\n"
                    + "APPOINTMENT DETAILS\n"
                    + "----------------------------------\n"
                    + "Doctor: Dr. " + doctorName + "\n"
                    + "Date: " + appointmentDate + "\n"
                    + "Time: " + appointmentTime + "\n"
                    + "Status: CONFIRMED\n"
                    + "----------------------------------\n\n"
                    + "Please be available at the scheduled time.\n\n"
                    + "Thank you for choosing MediCare.\n\n"
                    + "Regards,\n"
                    + "MediCare";

            sendEmail(
                    userEmail,
                    "Appointment Confirmed",
                    message
            );

        }).start();
    }

    // ==========================================
    // EMAIL WHEN DOCTOR DECLINES
    // ==========================================
    public static void sendAppointmentDeclinedEmail(
            String userEmail,
            String userName,
            String doctorName,
            String appointmentDate,
            String appointmentTime) {

        new Thread(() -> {

            String message
                    = "Dear " + userName + ",\n\n"
                    + "Your appointment request has been declined.\n\n"
                    + "APPOINTMENT DETAILS\n"
                    + "----------------------------------\n"
                    + "Doctor: Dr. " + doctorName + "\n"
                    + "Date: " + appointmentDate + "\n"
                    + "Time: " + appointmentTime + "\n"
                    + "Status: DECLINED\n"
                    + "----------------------------------\n\n"
                    + "You may select another doctor or another "
                    + "available appointment slot.\n\n"
                    + "Regards,\n"
                    + "MediCare";

            sendEmail(
                    userEmail,
                    "Appointment Update",
                    message
            );

        }).start();
    }
}
