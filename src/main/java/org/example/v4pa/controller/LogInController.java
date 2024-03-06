package org.example.v4pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import org.example.v4pa.dao.UserQuery;
import org.example.v4pa.model.User;

import java.util.Objects;
import java.util.Optional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField loginPasswordText;

    @FXML
    private Button loginSubmitButton;

    @FXML
    private TextField loginUsernameText;

    @FXML
    private ChoiceBox<String> loginTimeZoneChoiceBox;

    private String[] timeZones = {"MST", "EST", "GMT"};

    @FXML
    void onActionDisplayApptDetailsFromLogIn(ActionEvent event) throws IOException {

        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        if (!(username.equals(UserQuery.findUserID(username).getUserName()) && password.equals(UserQuery.findUserID(username).getUserPassword()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid Username and Password");
            alert.showAndWait();
            return;
        }
        else {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTimeZoneChoiceBox.getItems().addAll(timeZones);
        loginTimeZoneChoiceBox.setOnAction(this::setTimeZone);
    }

    public void setTimeZone(ActionEvent event) {
        String timeZoneChoice = loginTimeZoneChoiceBox.getValue();
        if(timeZoneChoice == "MST") {
            // Set UTC -7
        }
        if(timeZoneChoice == "EST") {
            // Set UTC -5
        }
        if(timeZoneChoice == "GMT") {
            // Set UTC +0
        }
    }
}