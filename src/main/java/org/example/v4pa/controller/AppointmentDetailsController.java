package org.example.v4pa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.helper.AppointmentFinder;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class creates an Appointment Details view of the app.
 * RUNTIME ERRORS: This form will generate errors if the user pushes the edit or delete buttons without first selecting an appointment from the table. */
public class AppointmentDetailsController implements Initializable {

    Stage stage;
    Parent scene;

    int userID = 0;

    @FXML
    private ToggleGroup apptToggleGroup;
    @FXML
    private Button apptdetailsAddButton;
    @FXML
    private TableColumn<Appointment, Integer> apptdetailsApptIDColumn;
    @FXML
    private RadioButton apptdetailsByMonthRadioButton;
    @FXML
    private RadioButton apptdetailsByWeekRadioButton;
    @FXML
    private RadioButton apptdetailsViewAllRadioButton;
    @FXML
    private TableColumn<Contact, String> apptdetailsContactColumn;
    @FXML
    private Button apptdetailsCustDetailsButton;
    @FXML
    private TableColumn<Appointment, Integer> apptdetailsCustIDColumn;
    @FXML
    private Button apptdetailsDeleteButton;
    @FXML
    private TableColumn<Appointment, String> apptdetailsDescriptionColumn;
    @FXML
    private Button apptdetailsEditButton;
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptdetailsEndDateTimeColumn;
    @FXML
    private TableColumn<Appointment, String> apptdetailsLocationColumn;
    @FXML
    private Button apptdetailsReportsButton;
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptdetailsStartDateTimeColumn;
    @FXML
    private TableColumn<Appointment, String> apptdetailsTitleColumn;
    @FXML
    private TableColumn<Appointment, String> apptdetailsTypeColumn;
    @FXML
    private TableColumn<Appointment, Integer> apptdetailsUserIDColumn;
    @FXML
    private TableView<Appointment> apptdetailsTableView;
    @FXML
    private Label apptdetailsSelectApptLabel;

    LocalDateTime currentDateTime = LocalDateTime.now();

    @FXML
    void onActionDisplayAddApptMenu(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/add-appointment-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayCustDetailsFromApptDetails(ActionEvent event) throws IOException{

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayEditApptMenu(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/view/edit-appointment-view.fxml"));
            loader.load();

            EditAppointmentController EditApptController = loader.getController();
            EditApptController.sendAppointment(apptdetailsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an Appointment to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    void onActionDisplayMonthlyAppts(ActionEvent event) {
        ObservableList<Appointment> monthlyAppointments = AppointmentFinder.findMonthlyAppointments(currentDateTime);

        apptdetailsTableView.setItems(monthlyAppointments);
    }

    @FXML
    void onActionDisplayAllAppts(ActionEvent event) {
        ObservableList<Appointment> appointmentsList = AppointmentQuery.getAllAppointments();

        apptdetailsTableView.setItems(appointmentsList);
    }

    @FXML
    void onActionDisplayReportsFromApptDetails(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/reports-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayWeeklyAppts(ActionEvent event) {
        ObservableList<Appointment> weeklyAppointments = AppointmentFinder.findWeeklyAppointments(currentDateTime);

        apptdetailsTableView.setItems(weeklyAppointments);
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        /** LOGICAL ERROR: This error occurs if no appointment is selected for deletion. */
        if (apptdetailsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an Appointment to delete.");
            alert.showAndWait();
            return;
        }
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected appointment. Are you sure you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String appointmentName = String.valueOf(apptdetailsTableView.getSelectionModel().getSelectedItem().getApptTitle());
                AppointmentQuery.deleteAppointment(apptdetailsTableView.getSelectionModel().getSelectedItem().getApptID());
                if(apptdetailsByWeekRadioButton.isSelected()) {
                    apptdetailsTableView.setItems(AppointmentFinder.findWeeklyAppointments(currentDateTime));
                    Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
                    alertDelete.setTitle("Confirmation");
                    alertDelete.setContentText(appointmentName + " has been deleted.");
                    alertDelete.showAndWait();
                } else if (apptdetailsByMonthRadioButton.isSelected()) {
                    apptdetailsTableView.setItems(AppointmentFinder.findMonthlyAppointments(currentDateTime));
                    Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
                    alertDelete.setTitle("Confirmation");
                    alertDelete.setContentText(appointmentName + " has been deleted.");
                    alertDelete.showAndWait();
                } else {
                    ObservableList<Appointment> appointmentsList = AppointmentQuery.getAllAppointments();
                    apptdetailsTableView.setItems(appointmentsList);
                    Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
                    alertDelete.setTitle("Confirmation");
                    alertDelete.setContentText(appointmentName + " has been deleted.");
                    alertDelete.showAndWait();
                }
            }

        } catch (NullPointerException e) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** This method sends the user information through the Log In controller to the Appointment Details controller and verifies if a user has any upcoming appointments in 15 minutes.
     * The method also converts the UTC time into the local time zone and displays the appointment ID and Start Date/Time in local time zone.
     * @param user the user with the information to find all appointments that have the same User ID associated with it.
     */
    public void sendUserInfo(User user) {
        userID = user.getUserID();

        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime localStartZDT = currentDateTime.atZone(localZoneID);
        ZonedDateTime utcStartZDT = localStartZDT.withZoneSameInstant(ZoneId.of("GMT"));
        LocalDateTime utcStartLDT = utcStartZDT.toLocalDateTime();
        ObservableList<Appointment> upcomingAppts = AppointmentFinder.findUpcomingAppointments(utcStartLDT, userID);
        int numberOfUpcomingAppts = 0;
        numberOfUpcomingAppts = upcomingAppts.size();
        int apptID = 0;
        LocalDateTime apptStart = currentDateTime;
        // Write code to display ALL upcoming appointments with the for loop. Currently just displaying the last upcoming appt on the list.
        for(Appointment testAppointment : upcomingAppts) {
            apptID = testAppointment.getApptID();
            apptStart = testAppointment.getApptStart();
        }
        utcStartLDT = apptStart;
        utcStartZDT = utcStartLDT.atZone(ZoneId.of("GMT"));
        localStartZDT = utcStartZDT.withZoneSameInstant(localZoneID);

        if(numberOfUpcomingAppts > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Notice");
            alert.setContentText("You have an upcoming appointment! \n\n" +
                    "\tAppointment ID:    " + apptID + "\n" +
                    "\tStart Date/Time:    " + localStartZDT.toLocalDateTime() + "\n");
            alert.showAndWait();
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Notice");
            alert.setContentText("There are no upcoming appointments.");
            alert.showAndWait();
            return;
        }
    }

    /** This method initializes the values of the appointment details table view.
     * LAMBDA - The lambda allows the user to see in detail the Appointment Title, Start Date and Start Time in local time.
     * This makes it easier for a user to view appointment details as a preview. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointment> appointments = AppointmentFinder.findWeeklyAppointments(currentDateTime);
        apptdetailsTableView.setItems(appointments);

        apptdetailsApptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptdetailsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptdetailsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptdetailsLocationColumn.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptdetailsContactColumn.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptdetailsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptdetailsStartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptdetailsEndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptdetailsCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptdetailsUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));


        apptdetailsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldAppt, newAppt) -> {
            if (newAppt != null) {
                ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
                LocalDateTime utcStartLDT = newAppt.getApptStart();
                ZonedDateTime utcStartZDT = utcStartLDT.atZone(ZoneId.of("GMT"));
                ZonedDateTime localStartZDT = utcStartZDT.withZoneSameInstant(localZoneID);
                apptdetailsSelectApptLabel.setText(newAppt.getApptTitle().toUpperCase() + "\nStart Date:\t\t" + localStartZDT.toLocalDate() + "\nStart Time:\t\t" + localStartZDT.toLocalTime());
            }
        });


    }

}

