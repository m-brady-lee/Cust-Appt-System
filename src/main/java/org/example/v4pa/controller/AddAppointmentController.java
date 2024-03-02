package org.example.v4pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private ComboBox<String> addapptContactComboBox;
    @FXML
    private ComboBox<String> addapptCustComboBox;
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
    private ComboBox<String> addapptUserComboBox;
    private String [] addapptMeetingTypes = {"Announcement", "Brainstorm Session", "Customer Engagement", "Review"};

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
    void onActionSaveAddApptToApptDetails(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addapptTypeComboBox.getItems().addAll(addapptMeetingTypes);
    }
}

