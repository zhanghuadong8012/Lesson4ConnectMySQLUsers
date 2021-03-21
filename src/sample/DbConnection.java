package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_registration","root","zhd801215ZHD");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
