package org.example.v4pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import org.example.v4pa.dao.UserQuery;
import org.example.v4pa.model.User;

import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LogInController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Label loginCustApptSystemLabel;
    @FXML
    private Label loginLocationLabel;
    @FXML
    private Label loginPasswordLabel;
    @FXML
    private Label loginUsernameLabel;
    @FXML
    private Label loginUserLocationField;
    @FXML
    private TextField loginPasswordText;
    @FXML
    private Button loginSubmitButton;
    @FXML
    private TextField loginUsernameText;


    LocalDate londonDate = LocalDate.of(2024, 03, 20);
    LocalTime londonTime = LocalTime.of(01, 00);
    ZoneId londonZoneID = ZoneId.of("Europe/London");
    ZonedDateTime londonZDT = ZonedDateTime.of(londonDate, londonTime, londonZoneID);
    ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
    Instant londonToGMTInstant = londonZDT.toInstant();
    ZonedDateTime gmtToLocalZDT = londonToGMTInstant.atZone(localZoneID);


    @FXML
    void onActionDisplayApptDetailsFromLogIn(ActionEvent event) throws IOException {

        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();
        ResourceBundle rb = ResourceBundle.getBundle("/Nat");

        if (!(username.equals(UserQuery.findUserID(username).getUserName()) && password.equals(UserQuery.findUserID(username).getUserPassword()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText(rb.getString("valid_user_pw"));
            alert.showAndWait();
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
                loader.load();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

                AppointmentDetailsController ApptDetailsController = loader.getController();
                User user = UserQuery.findUserID(username);
                ApptDetailsController.sendUserInfo(user);
            }
            catch (NullPointerException e) {
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("/Nat");
            loginCustApptSystemLabel.setText(rb.getString("customer_appt_system"));
            loginLocationLabel.setText(rb.getString("location"));
            loginUsernameLabel.setText(rb.getString("username"));
            loginPasswordLabel.setText(rb.getString("password"));
            loginSubmitButton.setText(rb.getString("submit"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loginUserLocationField.setText(String.valueOf(localZoneID));



    }

}