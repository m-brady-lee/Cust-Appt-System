package org.example.v4pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML
    private Button reportsApptDetailsButton;
    @FXML
    private ComboBox<String> reportsContactComboBox;
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
    private TextField reportsTotalApptsText;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsTotalApptsApptTypeDropdown.getItems().addAll(reportsMeetingTypes);
    }
}

