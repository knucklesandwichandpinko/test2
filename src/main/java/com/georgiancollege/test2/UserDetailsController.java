package com.georgiancollege.test2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserDetailsController implements Initializable {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, Integer> ageColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, String> birthDateColumn;

    @FXML
    private TableColumn<User, String> universityColumn;

    @FXML
    private Label noOfUsersLabel;

    @FXML
    private ListView<String> addressListView;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Assuming your User class has appropriate getters
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        ageColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        birthDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthDate()));
        universityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUniversity()));

        // Add listener to TableView
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Display address in ListView
                displayAddress(newSelection.getAddress());
                // Display image in ImageView
                displayImage(newSelection.getImage());
            }
        });
    }

    private void displayAddress(User.Address address) {
        // Assuming you have appropriate get methods in the Address class
        addressListView.getItems().clear();
        addressListView.getItems().addAll(
                "Address: " + address.getAddress(),
                "City: " + address.getCity(),
                "Postal Code: " + address.getPostalCode(),
                "State: " + address.getState()
        );
    }

    private void displayImage(String imageUrl) {
        // Display image in ImageView
        Image image = new Image(imageUrl);
        imageView.setImage(image);
    }



    @FXML
    private void usersLessThan30Button_onClick(ActionEvent event) {
        List<User> userList = APIUtility.fetchUserDataFromAPI();

        // Filter users less than 30
        List<User> usersUnder30 = userList.stream()
                .filter(user -> user.getAge() < 30)
                .collect(Collectors.toList());

        // Update TableView
        tableView.setItems(FXCollections.observableArrayList(usersUnder30));

        // Update Label with total number of rows
        noOfUsersLabel.setText("No. of Users: " + usersUnder30.size());
    }

    @FXML
    private void loadAllUsersButton_onClick(ActionEvent event) {
        List<User> userList = APIUtility.fetchUserDataFromAPI();

        // Update TableView
        tableView.setItems(FXCollections.observableArrayList(userList));

        // Update Label with total number of rows
        noOfUsersLabel.setText("No. of Users: " + userList.size());
    }
}
