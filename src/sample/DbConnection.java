package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_registration","root","password");
    }

    public static ObservableList<User> getData(String query) throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();

        Connection connection = DbConnection.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()){
            User user = new User(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            list.add(user);
        }
        return list;
    }

    public static void insertUser(String query, String name, String email, String department) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, department);
        preparedStatement.executeUpdate();
    }
}
