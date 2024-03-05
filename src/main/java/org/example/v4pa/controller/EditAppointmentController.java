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
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML
    private TextField editapptApptIDText;

    @FXML
    private Button editapptCancelButton;

    @FXML
    private ComboBox<Contact> editapptContactComboBox;

    @FXML
    private ComboBox<Customer> editapptCustComboBox;

    @FXML
    private TextField editapptDescriptionText;

    @FXML
    private DatePicker editapptEndDatePicker;

    @FXML
    private TextField editapptEndTimeText;

    @FXML
    private TextField editapptLocationText;

    @FXML
    private Button editapptSaveButton;

    @FXML
    private DatePicker editapptStartDatePicker;

    @FXML
    private TextField editapptStartTimeText;

    @FXML
    private TextField editapptTitleText;

    @FXML
    private ComboBox<String> editapptTypeComboBox;

    @FXML
    private ComboBox<User> editapptUserComboBox;

    private String [] editapptMeetingTypes = {"Announcement", "Brainstorm Session", "Customer Engagement", "Review"};

    Appointment appointment;
    ObservableList<Contact> associatedContact = FXCollections.observableArrayList();
    ObservableList<Customer> associatedCustomer = FXCollections.observableArrayList();
    ObservableList<User> associatedUser = FXCollections.observableArrayList();

    @FXML
    void onActionCancelEditApptToApptDetails(ActionEvent event) throws IOException {

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
    void onActionSaveEditApptToApptDetails(ActionEvent event) throws IOException, SQLException {

        int id = 0;
        try {
            id = Integer.parseInt(editapptApptIDText.getText());
        } catch (NumberFormatException e) {
        }

        String title = editapptTitleText.getText();
        /** LOGICAL ERROR: This error is generated if the title field is blank or contains any number values. */
        if (title.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for Title");
            alert.showAndWait();
            return;
        }

        String description = editapptDescriptionText.getText();
        /** LOGICAL ERROR: This error is generated if the Description field is blank. */
        if (description.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Description is required");
            alert.showAndWait();
            return;
        }

        String location = editapptLocationText.getText();
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
            type = editapptTypeComboBox.getValue().toString();
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
            String customer = editapptCustComboBox.getValue().toString();
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
            String user = editapptUserComboBox.getValue().toString();
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
            String contact = editapptContactComboBox.getValue().toString();
            contactID = ContactQuery.findContactID(contact).getContactID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Contact must be selected");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save the appointment edits?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            associatedCustomer.add(CustomerQuery.findCustomerName(customerID));
            associatedUser.add(UserQuery.findUserName(userID));
            associatedContact.add(ContactQuery.findContactName(contactID));
//            Appointment appointment = new Appointment(this.appointment.getApptID(), title, description, location, type, customerID, userID, contactID);
//            appointment.addAssociatedCustomer(CustomerQuery.selectCustomer(customerID));
//            appointment.addAssociatedUser(UserQuery.selectUser(userID));
//            appointment.addAssociatedContact(ContactQuery.selectContact(contactID));

            AppointmentQuery.updateAppointment(id, title, description, location, type, customerID, userID, contactID);
            // Make a query that can update an appointment with attached objects

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editapptTypeComboBox.getItems().addAll(editapptMeetingTypes);
        editapptContactComboBox.setItems(ContactQuery.getAllContacts());
        editapptCustComboBox.setItems(CustomerQuery.getAllCustomers());
        editapptUserComboBox.setItems(UserQuery.getAllUsers());
    }

    public void sendAppointment(Appointment appointment) {
        editapptApptIDText.setText(String.valueOf(appointment.getApptID()));
        editapptTitleText.setText(appointment.getApptTitle());
        editapptDescriptionText.setText(appointment.getApptDescription());
        editapptLocationText.setText(appointment.getApptLocation());
        editapptTypeComboBox.setValue(appointment.getApptType());
        int customerID = appointment.getApptCustomerID();
        editapptCustComboBox.setValue(CustomerQuery.findCustomerName(customerID));
        int userID = appointment.getApptUserID();
        editapptUserComboBox.setValue(UserQuery.findUserName(userID));
        int contactID = appointment.getApptContactID();
        editapptContactComboBox.setValue(ContactQuery.findContactName(contactID));
    }
}
