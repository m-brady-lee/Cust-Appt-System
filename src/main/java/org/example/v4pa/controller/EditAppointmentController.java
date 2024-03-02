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

public class EditAppointmentController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML
    private TextField editapptApptIDText;

    @FXML
    private Button editapptCancelButton;

    @FXML
    private ComboBox<String> editapptContactComboBox;

    @FXML
    private ComboBox<String> editapptCustComboBox;

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
    private ComboBox<String> editapptUserComboBox;

    private String [] editapptMeetingTypes = {"Announcement", "Brainstorm Session", "Customer Engagement", "Review"};

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
    void onActionSaveEditApptToApptDetails(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editapptTypeComboBox.getItems().addAll(editapptMeetingTypes);
    }
}
