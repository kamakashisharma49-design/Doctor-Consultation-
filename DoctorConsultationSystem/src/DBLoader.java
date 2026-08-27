
    import java.sql.*;

    public class DBLoader {

        // ==========================================
        // FOR SELECT QUERIES
        // ==========================================
        static ResultSet executeQuery(String sql) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully!!");

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/doctor_consultation",
                        "root",
                        "system123"
                );

                System.out.println("Connection build");

                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                );

                System.out.println("Statement created");

                ResultSet rs = stmt.executeQuery(sql);

                System.out.println("ResultSet created");

                return rs;

            } catch (Exception ex) {

                ex.printStackTrace();

                return null;
            }
        }

        // ==========================================
        // FOR INSERT / UPDATE / DELETE
        // ==========================================
        static int executeUpdate(String sql) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully!!");

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/DoctorConsultationSystem",
                        "root",
                        "system123"
                );

                System.out.println("Connection build");

                Statement stmt = conn.createStatement();

                System.out.println("Statement created");

                int rowsAffected = stmt.executeUpdate(sql);

                System.out.println(
                        "Rows affected: " + rowsAffected
                );

                stmt.close();
                conn.close();

                return rowsAffected;

            } catch (Exception ex) {

                ex.printStackTrace();

                return 0;
            }
        }
    }
