import java.sql.*;
import javafx.scene.control.Label;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/javafx_mariadb";
    private static final String USER = "user";
    private static final String PASSWORD = "pass";


    public static void saveTemperature(double value, double resultValue, String resultName, Label statusLabel) {
        String sql = "INSERT INTO temperature_log (input_value, result_name, result_value) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, value);          // user input
            stmt.setString(2, resultName);     // conversion type
            stmt.setDouble(3, resultValue);    // converted value
            stmt.executeUpdate();

            statusLabel.setText("Saved to database!");

        } catch (SQLException e) {
            statusLabel.setText("DB Error: " + e.getMessage());
        }
    }
}
