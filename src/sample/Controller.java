package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> departmentColumn;

    private Connection conn;

    User user = new User();



    public void loadUsers() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try (ResultSet resultSet = conn.createStatement().executeQuery("select * from users")) {
            while (resultSet.next()){
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setDepartment(resultSet.getString(4));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        tableView.setItems(list);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = DbConnection.getConnection();
    }
}
