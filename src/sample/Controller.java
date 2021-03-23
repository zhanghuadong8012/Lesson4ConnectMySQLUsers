package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
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

    @FXML
    private TextField nameTextField,emailTextField,departmentTextField;


    public void addUsers() throws SQLException {
        String query = "insert into users (name, email, department) values (?, ?, ?)";
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String department = departmentTextField.getText();
        DbConnection.insertUser(query,name,email,department);

        User user = new User(name, email, department);

        tableView.getItems().add(user);

        nameTextField.clear();
        emailTextField.clear();
        departmentTextField.clear();
    }



    public void loadUsers() {
        String query = "select * from users";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            list  = DbConnection.getData(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        tableView.setItems(list);
    }

    public void refreshUsers(){
        loadUsers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsers();
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
