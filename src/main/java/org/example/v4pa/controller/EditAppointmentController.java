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
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class creates the Edit Appointment view of the app.
 * RUNTIME ERRORS: This form will generate a number of errors if certain fields do not meet the correct data format/criteria.
 * For example, appointment start/end times and dates cannot overlap. All fields are required. */
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
    LocalTime currentTime = LocalTime.now();
    LocalDate currentDate = LocalDate.now();
    LocalDateTime currentDateTime = LocalDateTime.of(currentDate,currentTime);

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

        LocalDate startDate = editapptStartDatePicker.getValue();
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
            startTime = LocalTime.parse(editapptStartTimeText.getText());
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
        ZonedDateTime cutoffmorningETZone = cutoffmorningApptStart.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime cutoffmorningLocalTime = cutoffmorningETZone.withZoneSameInstant(localZoneID);
        LocalTime cutoffmorningLocalLDT = cutoffmorningLocalTime.toLocalTime();
        LocalDateTime cutoffeveningApptStart = cutoffStartDate.atTime(22, 0);
        ZonedDateTime cutoffeveningETZone = cutoffeveningApptStart.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime cutoffeveningLocalTime = cutoffeveningETZone.withZoneSameInstant(localZoneID);
        LocalTime cutoffeveningLocalLDT = cutoffeveningLocalTime.toLocalTime();
        ZonedDateTime utcStartZDT = localStartZDT.withZoneSameInstant(ZoneId.of("GMT"));
        LocalDateTime utcStartLDT = utcStartZDT.toLocalDateTime();

        /** LOGICAL ERROR: This error is generated if the user tries to schedule an appointment outside of business hours. */
        if(easternStartLDT.isBefore(cutoffmorningApptStart) || easternStartLDT.isAfter(cutoffeveningApptStart)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments must be scheduled within business hours:\n" + "\n\t\t\t08:00 - 22:00 (ET)\n" + "\t\t\t" + cutoffmorningLocalLDT + " - " + cutoffeveningLocalLDT + " (Local Time)");
            alert.showAndWait();
            return;
        }

        LocalDate endDate = editapptEndDatePicker.getValue();
        LocalTime endTime = currentTime;
        /** RUNTIME ERROR: This error is generated if the End Time is less than 00:00 or greater than 24:00. */
        try {
            endTime = LocalTime.parse(editapptEndTimeText.getText());
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
        ZonedDateTime utcEndZDT = localEndZDT.withZoneSameInstant(ZoneId.of("GMT"));
        LocalDateTime utcEndLDT = utcEndZDT.toLocalDateTime();

        /** LOGICAL ERROR: These errors are generated if the user tries to schedule an appointment outside of business hours. */
        if(easternEndLDT.isBefore(cutoffmorningApptEnd) || easternEndLDT.isAfter(cutoffeveningApptEnd)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments must be scheduled within business hours:\n" + "\n\t\t\t08:00 - 22:00 (ET)\n" + "\t\t\t" + cutoffmorningLocalLDT + " - " + cutoffeveningLocalLDT + " (Local Time)");
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
            String customer = editapptCustComboBox.getValue().toString();
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

        /** LOGICAL ERROR: This error is generated if the customer has an appointment during the starting or ending of the proposed appointment. */
        ObservableList<Appointment> customerAppts = AppointmentFinder.findAppointmentsByCustomer(customerID);
        ObservableList<Appointment> conflictingAppts = FXCollections.observableArrayList();
        String testName = "";
        int testID = 0;
        LocalDateTime testStart = currentDateTime;
        LocalDateTime testEnd = currentDateTime;
        for(Appointment testAppt : customerAppts) {
            if(
                    (testAppt.getApptStart().isBefore(utcEndLDT) && testAppt.getApptStart().isAfter(utcStartLDT)) ||
                            (testAppt.getApptStart().isBefore(utcStartLDT) && testAppt.getApptEnd().isAfter(utcEndLDT)) ||
                            (testAppt.getApptEnd().isAfter(utcStartLDT) && testAppt.getApptEnd().isBefore(utcEndLDT))) {
                conflictingAppts.add(testAppt);
                testName = editapptCustComboBox.getValue().toString();
                testID = testAppt.getApptID();
                testStart = testAppt.getApptStart();
                testEnd = testAppt.getApptEnd();
            }
        }

        ZonedDateTime utcOldApptStartZDT = testStart.atZone(ZoneId.of("GMT"));
        ZonedDateTime localOldApptStartZDT = utcOldApptStartZDT.withZoneSameInstant(localZoneID);
        LocalDateTime localOldApptStartLDT = localOldApptStartZDT.toLocalDateTime();
        ZonedDateTime utcOldApptEndZDT = testEnd.atZone(ZoneId.of("GMT"));
        ZonedDateTime localOldApptEndZDT = utcOldApptEndZDT.withZoneSameInstant(localZoneID);
        LocalDateTime localOldApptEndLDT = localOldApptEndZDT.toLocalDateTime();
        if(conflictingAppts.size() > 0) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Scheduling conflict!");
            alert2.setContentText(testName + " has another appointment scheduled during this time:\n" +
                    "\n\t\tAppt. ID: " + testID + "\n" +
                    "\t\tStart:  " + localOldApptStartLDT + "\n" +
                    "\t\tEnd:  " + localOldApptEndLDT + "\n" +
                    "\n\tPlease choose another start and end time");
            alert2.showAndWait();
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save the appointment edits?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentQuery.updateAppointment(id, title, description, location, type, utcStartLDT, utcEndLDT, customerID, userID, contactID);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editapptTypeComboBox.getItems().addAll(editapptMeetingTypes);
        editapptContactComboBox.setItems(ContactQuery.getAllContacts());
        editapptCustComboBox.setItems(CustomerQuery.getAllCustomers());
        editapptUserComboBox.setItems(UserQuery.getAllUsers());
    }

    /** This method sends the appointment information through the Appointment Details controller to the Edit Appointment controller and pre-populates the fields in the form.
     * The method also converts the UTC time into the local time zone and displays the Start Date/Time in local time zone.
     * @param appointment the appointment with the information that is preloaded into the Edit Appointment form.
     */
    public void sendAppointment(Appointment appointment) {
        editapptApptIDText.setText(String.valueOf(appointment.getApptID()));
        editapptTitleText.setText(appointment.getApptTitle());
        editapptDescriptionText.setText(appointment.getApptDescription());
        editapptLocationText.setText(appointment.getApptLocation());
        editapptTypeComboBox.setValue(appointment.getApptType());
        LocalDateTime utcStartLDT = appointment.getApptStart();
        ZonedDateTime utcStartZDT = utcStartLDT.atZone(ZoneId.of("GMT"));
        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime localStartZDT = utcStartZDT.withZoneSameInstant(localZoneID);
        editapptStartTimeText.setText(String.valueOf(localStartZDT.toLocalTime()));
        editapptStartDatePicker.setValue(localStartZDT.toLocalDate());
        LocalDateTime utcEndLDT = appointment.getApptEnd();
        ZonedDateTime utcEndZDT = utcEndLDT.atZone(ZoneId.of("GMT"));
        ZonedDateTime localEndZDT = utcEndZDT.withZoneSameInstant(localZoneID);
        editapptEndTimeText.setText(String.valueOf(localEndZDT.toLocalTime()));
        editapptEndDatePicker.setValue(localEndZDT.toLocalDate());
        int customerID = appointment.getApptCustomerID();
        editapptCustComboBox.setValue(CustomerFinder.findCustomerName(customerID));
        int userID = appointment.getApptUserID();
        editapptUserComboBox.setValue(UserQuery.findUserName(userID));
        int contactID = appointment.getApptContactID();
        editapptContactComboBox.setValue(ContactQuery.findContactName(contactID));
    }
}
