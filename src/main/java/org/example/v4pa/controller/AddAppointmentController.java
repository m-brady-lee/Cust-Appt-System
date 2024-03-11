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
import org.example.v4pa.helper.AppointmentFinder;
import org.example.v4pa.helper.CustomerFinder;
import org.example.v4pa.helper.GeneralInterface;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

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

    LocalTime currentTime = LocalTime.now();
    LocalDate currentDate = LocalDate.now();
    LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);



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
        if (location.isBlank()) {
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

        LocalDate startDate = addapptStartDatePicker.getValue();
        /** LOGICAL ERROR: This error is generated if the Start Date is before the current date. */
        if(startDate.isBefore(currentDate)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Start Date cannot be before today");
            alert.showAndWait();
            return;
        }

        LocalTime startTime = currentTime;
        /** RUNTIME ERROR: This error is generated if the Start Time is less than 00:00 or greater than 24:00. */
        try {
            startTime = LocalTime.parse(addapptStartTimeText.getText());
        } catch (DateTimeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Start Time must be between 00:00 and 24:00 ");
            alert.showAndWait();
            return;
        }

        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        /** LOGICAL ERROR: This error is generated if the 'Start Date' date picker and Start Time fields do not contain values. */
        if (startDateTime == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Start Date and Time are required");
            alert.showAndWait();
            return;
        }

        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime localStartZDT = startDateTime.atZone(localZoneID);
        ZonedDateTime easternStartZDT = localStartZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime easternStartLDT = easternStartZDT.toLocalDateTime();
        LocalDate cutoffStartDate = easternStartZDT.toLocalDate();
        LocalDateTime cutoffmorningApptStart = cutoffStartDate.atTime(8, 00);
        LocalDateTime cutoffeveningApptStart = cutoffStartDate.atTime(22, 0);

        /** LOGICAL ERROR: This error is generated if the user tries to schedule an appointment outside of business hours. */
        if(easternStartLDT.isBefore(cutoffmorningApptStart) || easternStartLDT.isAfter(cutoffeveningApptStart)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments must be scheduled within business hours:\n" + "\t\t\t8:00 AM - 10:00 PM ET");
            alert.showAndWait();
            return;
        }

        LocalDate endDate = addapptEndDatePicker.getValue();
        LocalTime endTime = currentTime;
        /** RUNTIME ERROR: This error is generated if the End Time is less than 00:00 or greater than 24:00. */
        try {
            endTime = LocalTime.parse(addapptEndTimeText.getText());
        } catch (DateTimeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("End Time must be between 00:00 and 24:00 ");
            alert.showAndWait();
            return;
        }

        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        /** LOGICAL ERROR: This error is generated if the 'End Date' date picker and End Time fields do not contain values. */
        if (endDateTime == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("End Date and Time are required");
            alert.showAndWait();
            return;
        }

        /** LOGICAL ERROR: This error is generated if the End Date and Time are before the Start Date and Time. */
        if(endDateTime.isBefore(startDateTime) || endDateTime.isEqual(startDateTime)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("End Date and Time must be after Start Date and Time");
            alert.showAndWait();
            return;
        }

        ZonedDateTime localEndZDT = endDateTime.atZone(localZoneID);
        ZonedDateTime easternEndZDT = localEndZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime easternEndLDT = easternEndZDT.toLocalDateTime();
        LocalDate cutoffEndDate = easternEndZDT.toLocalDate();
        LocalDateTime cutoffmorningApptEnd = cutoffEndDate.atTime(8, 00);
        LocalDateTime cutoffeveningApptEnd = cutoffEndDate.atTime(22, 0);

        /** LOGICAL ERROR: These errors are generated if the user tries to schedule an appointment outside of business hours. */
        if(easternEndLDT.isBefore(cutoffmorningApptEnd) || easternEndLDT.isAfter(cutoffeveningApptEnd)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments must be scheduled within business hours:\n" + "\t\t\t8:00 AM - 10:00 PM ET");
            alert.showAndWait();
            return;
        }
        if(!(cutoffStartDate.isEqual(cutoffEndDate))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments must start and end on the same day");
            alert.showAndWait();
            return;
        }

        int customerID = 10111;
        /** RUNTIME ERROR: This error is generated if the Customer combo box does not contain a value. */
        try {
            String customer = addapptCustComboBox.getValue().toString();
            customerID = CustomerFinder.findCustomerID(customer).getCustomerID();
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

        /** LOGICAL ERROR: This error is generated if the customer has an appointment during the starting or ending of the proposed appointment. */
        ObservableList<Appointment> customerAppts = AppointmentFinder.findAppointmentsByCustomer(customerID);
        ObservableList<Appointment> conflictingAppts = FXCollections.observableArrayList();
        String testName = "";
        int testID = 0;
        LocalDateTime testStart = currentDateTime;
        LocalDateTime testEnd = currentDateTime;
        for(Appointment testAppt : customerAppts) {
            if(
                    (testAppt.getApptStart().isBefore(endDateTime) && testAppt.getApptStart().isAfter(startDateTime)) ||
                            (testAppt.getApptStart().isBefore(startDateTime) && testAppt.getApptEnd().isAfter(endDateTime)) ||
                            (testAppt.getApptEnd().isAfter(startDateTime) && testAppt.getApptEnd().isBefore(endDateTime))) {
                conflictingAppts.add(testAppt);
                testName = addapptCustComboBox.getValue().toString();
                testID = testAppt.getApptID();
                testStart = testAppt.getApptStart();
                testEnd = testAppt.getApptEnd();

            }
        }
        if(conflictingAppts.size() > 0) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Scheduling conflict!");
            alert2.setContentText(
                    testName + " has an appointment scheduled during this time:\n" +
                    "\tAppt. ID: " + testID + "\n" +
                    "\t Start:  " + testStart + "\n" +
                    "\t End:  " + testEnd + "\n" +
                    "\tPlease choose another start and end time");
            alert2.showAndWait();
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save the new appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentQuery.addAppointment(title, description, location, type, easternStartLDT, easternEndLDT, customerID, userID, contactID);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
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

