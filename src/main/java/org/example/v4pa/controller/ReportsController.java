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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.dao.ContactQuery;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.helper.AppointmentFinder;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** This class creates a Reports view of the app.
 *
 */
public class ReportsController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML
    private Button reportsApptDetailsButton;
    @FXML
    private ComboBox<Contact> reportsContactComboBox;
    @FXML
    private TableView<Appointment> reportsTableView;
    @FXML
    private TableColumn<Appointment, Integer> reportsApptIDColumn;
    @FXML
    private TableColumn<Appointment, Integer> reportsCustomerIDColumn;
    @FXML
    private TableColumn<Appointment, String> reportsDescriptionColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsEndDateColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsStartDateColumn;
    @FXML
    private TableColumn<Appointment, String> reportsTitleColumn;
    @FXML
    private TableColumn<Appointment, String> reportsTypeColumn;
    @FXML
    private Button reportsContactScheduleReportButton;
    @FXML
    private Button reportsCustDetailsButton;
    @FXML
    private Button reportsCustomersAddedReportButton;
    @FXML
    private ChoiceBox<String> reportsTotalApptsApptTypeDropdown;
    @FXML
    private Button reportsTotalApptsReportButton;
    @FXML
    private TextField reportsTotalApptsByTypeText;
    @FXML
    private TextField reportsTotalCustomersText;
    private String [] reportsMeetingTypes = {"Announcement", "Brainstorm Session", "Customer Engagement", "Review"};

    @FXML
    void onActionDisplayApptDetailsFromReports(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayCustDetailsFromReports(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionTotalApptsByTypeGenerateReport(ActionEvent event) {

        String apptType = "";
        /** LOGICAL ERROR: This error is generated if the Type combo box field is not selected. */
        try {
            apptType = reportsTotalApptsApptTypeDropdown.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an Appointment Type");
            alert.showAndWait();
            return;
        }
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByType = FXCollections.observableArrayList();

        for(Appointment testAppointment : appointmentList) {
            if(testAppointment.getApptType().toString().contains(apptType)) {
                appointmentsByType.add(testAppointment);
            }
        }
        int numberOfAppointments = 0;
        numberOfAppointments = appointmentsByType.size();

        reportsTotalApptsByTypeText.setText(String.valueOf(numberOfAppointments));
    }

    @FXML
    void onActionContactScheduleGenerateReport(ActionEvent event) {

        String contact = "";
        /** LOGICAL ERROR: This error is generated if the Type combo box field is not selected. */
        try {
            contact = reportsContactComboBox.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Contact");
            alert.showAndWait();
            return;
        }

        int contactID = ContactQuery.findContactID(contact).getContactID();
        ObservableList<Appointment> appointmentsByContact = AppointmentFinder.findAppointmentsByContact(contactID);

        reportsTableView.setItems(appointmentsByContact);

        reportsApptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        reportsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        reportsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        reportsStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        reportsEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        reportsCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
    }
    @FXML
    void onActionTotalCustomers(ActionEvent event) {

        ObservableList<Customer> customerList = CustomerQuery.getAllCustomers();
        int numberOfCustomers = 0;
        numberOfCustomers = customerList.size();

        reportsTotalCustomersText.setText(String.valueOf(numberOfCustomers));

    }

    /** This method initializes the values of the contact schedule table view.
     * The appt type combo box is pre-populated based on the four meeting types and the contact combo box is pre-populated based on the contact query.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsTotalApptsApptTypeDropdown.getItems().addAll(reportsMeetingTypes);
        reportsContactComboBox.setItems(ContactQuery.getAllContacts());


    }
}

