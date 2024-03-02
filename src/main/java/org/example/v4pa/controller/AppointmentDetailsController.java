package org.example.v4pa.controller;

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
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentDetailsController implements Initializable {

    Stage stage;
    Parent scene;

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
    private TableColumn<?, ?> apptdetailsEndDateTimeColumn;

    @FXML
    private TableColumn<Appointment, String> apptdetailsLocationColumn;

    @FXML
    private Button apptdetailsReportsButton;

    @FXML
    private TableColumn<?, ?> apptdetailsStartDateTimeColumn;

    @FXML
    private TableColumn<Appointment, String> apptdetailsTitleColumn;

    @FXML
    private TableColumn<Appointment, String> apptdetailsTypeColumn;

    @FXML
    private TableColumn<Appointment, Integer> apptdetailsUserIDColumn;
    @FXML
    private TableView<Appointment> apptdetailsTableView;

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

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/edit-appointment-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayMonthlyAppts(ActionEvent event) {


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

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Appointment> appointments = AppointmentQuery.getAllAppointments();
        apptdetailsTableView.setItems(appointments);

        apptdetailsApptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptdetailsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptdetailsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptdetailsLocationColumn.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptdetailsContactColumn.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptdetailsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptdetailsCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptdetailsUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));
    }
}

