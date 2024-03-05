package org.example.v4pa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.v4pa.dao.*;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Parent scene;
    Stage stage;

    @FXML
    private TextField addapptApptIDText;
    @FXML
    private Button addapptCancelButton;
    @FXML
    private ComboBox<Contact> addapptContactComboBox;
    @FXML
    private ComboBox<Customer> addapptCustComboBox;
    @FXML
    private TextField addapptDescriptionText;
    @FXML
    private DatePicker addapptEndDatePicker;
    @FXML
    private TextField addapptEndTimeText;
    @FXML
    private TextField addapptLocationText;
    @FXML
    private Button addapptSaveButton;
    @FXML
    private DatePicker addapptStartDatePicker;
    @FXML
    private TextField addapptStartTimeText;
    @FXML
    private TextField addapptTitleText;
    @FXML
    private ComboBox<String> addapptTypeComboBox;
    @FXML
    private ComboBox<User> addapptUserComboBox;
    private String [] addapptMeetingTypes = {"Announcement", "Brainstorm Session", "Customer Engagement", "Review"};

    ObservableList<Contact> associatedContact = FXCollections.observableArrayList();
    ObservableList<Customer> associatedCustomer = FXCollections.observableArrayList();
    ObservableList<User> associatedUser = FXCollections.observableArrayList();

    @FXML
    void onActionCancelAddApptToApptDetails(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will discard all changes. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSaveAddApptToApptDetails(ActionEvent event) throws IOException, SQLException {

        String title = addapptTitleText.getText();
        /** LOGICAL ERROR: This error is generated if the Title field is blank. */
        if (title.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Title is required");
            alert.showAndWait();
            return;
        }

        String description = addapptDescriptionText.getText();
        /** LOGICAL ERROR: This error is generated if the Description field is blank. */
        if (description.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Description is required");
            alert.showAndWait();
            return;
        }

        String location = addapptLocationText.getText();
        /** LOGICAL ERROR: This error is generated if the Location field is blank. */
        if (title.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Location is required");
            alert.showAndWait();
            return;
        }

        String type = "";
        /** LOGICAL ERROR: This error is generated if the Type combo box field is not selected. */
        try {
            type = addapptTypeComboBox.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Type is required");
            alert.showAndWait();
            return;
        }

        int customerID = 10111;
        /** RUNTIME ERROR: This error is generated if the Customer combo box does not contain a value. */
        try {
            String customer = addapptCustComboBox.getValue().toString();
            customerID = CustomerQuery.findCustomerID(customer).getCustomerID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Customer must be selected");
            alert.showAndWait();
            return;
        }

        int userID = 11111;
        /** RUNTIME ERROR: This error is generated if the User combo box does not contain a value. */
        try {
            String user = addapptUserComboBox.getValue().toString();
            userID = UserQuery.findUserID(user).getUserID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("User must be selected");
            alert.showAndWait();
            return;
        }

        int contactID = 12111;
        /** RUNTIME ERROR: This error is generated if the Contact combo box does not contain a value. */
        try {
            String contact = addapptContactComboBox.getValue().toString();
            contactID = ContactQuery.findContactID(contact).getContactID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Contact must be selected");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save the new appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            associatedCustomer.add(CustomerQuery.findCustomerName(customerID));
            associatedUser.add(UserQuery.findUserName(userID));
            associatedContact.add(ContactQuery.findContactName(contactID));

            AppointmentQuery.addAppointment(title, description, location, type, customerID, userID, contactID);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addapptTypeComboBox.getItems().addAll(addapptMeetingTypes);
        addapptContactComboBox.setItems(ContactQuery.getAllContacts());
        addapptCustComboBox.setItems(CustomerQuery.getAllCustomers());
        addapptUserComboBox.setItems(UserQuery.getAllUsers());
    }
}

