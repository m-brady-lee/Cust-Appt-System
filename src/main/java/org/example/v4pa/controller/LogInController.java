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
import javafx.scene.input.KeyEvent;
import org.example.v4pa.dao.UserQuery;
import org.example.v4pa.model.User;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.*;
import java.util.*;
import java.io.IOException;
import java.net.URL;

/** This class creates the Login view of the app.
 * RUNTIME ERRORS: This form will generate errors if the username and password fields do not match a username/password combination in the database.
 * This view and all warning boxes are also translated into English or French depending on the user's language settings. */
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

    ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
    ZonedDateTime localStartZDT = currentDateTime.atZone(localZoneID);
    ZonedDateTime easternStartZDT = localStartZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
    LocalDateTime easternStartLDT = easternStartZDT.toLocalDateTime();
    LocalDate easternLD = easternStartLDT.toLocalDate();
    LocalTime easternLT = easternStartLDT.toLocalTime();

    @FXML
    void onActionDisplayApptDetailsFromLogIn(ActionEvent event) throws IOException {

        String username = "";
        String password = "";
        ResourceBundle rb = ResourceBundle.getBundle("/Nat");

        //Login attempts, dates and timestamps

        //Filename and item variables
        String filename = "src/main/java/org/example/v4pa/files/login_activity.txt";
//
//        //Create FileWriter object
        FileWriter fwriter = new FileWriter(filename, true);
//        //Create and Open file
        PrintWriter outputFile = new PrintWriter(fwriter);
//
//        System.out.println("File written and closed");
        try {
            username = loginUsernameText.getText();
            password = loginPasswordText.getText();
        } catch (NullPointerException e) {
        }

        /** LOGICAL ERROR: This error occurs if the username and password do not match any username/password combo in the database. */
        if (UserQuery.findUserID(username) == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText(rb.getString("valid_user_pw"));
            alert.showAndWait();
            outputFile.println("Login Attempt Failure-\tUsername: " + username + "\tPassword: " + password + "\tDate: " + easternLD + "\tTimestamp(ET): " + easternLT);
            //Close file
            outputFile.close();
            return;
        } else if (!(UserQuery.findUserID(username) == null) && (UserQuery.findUserPassword(username) == null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText(rb.getString("valid_user_pw"));
            alert.showAndWait();
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            outputFile.println("Login Attempt Failure-\tUsername: " + username + "\tPassword: " + password + "\tDate: " + easternLD + "\tTimestamp(ET): " + easternLT);
            //Close file
            outputFile.close();
            return;
        } else if (UserQuery.findUserNameWithPassword(password) == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText(rb.getString("valid_user_pw"));
            alert.showAndWait();
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            outputFile.println("Login Attempt Failure-\tUsername: " + username + "\tPassword: " + password + "\tDate: " + easternLD + "\tTimestamp(ET): " + easternLT);
            //Close file
            outputFile.close();
            return;
        } else if (!(UserQuery.findUserNameWithPassword(password) == null) && (UserQuery.findUserID(username) == null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText(rb.getString("valid_user_pw"));
            alert.showAndWait();
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            outputFile.println("Login Attempt Failure-\tUsername: " + username + "\tPassword: " + password + "\tDate: " + easternLD + "\tTimestamp(ET): " + easternLT);
            //Close file
            outputFile.close();
            return;
        }
//
        if (((username.equals(UserQuery.findUserID(username).getUserName())) && (password.equals(UserQuery.findUserID(username).getUserPassword())))) {
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

                outputFile.println("Login Attempt Success-\tUsername: " + username + "\tPassword: " + password + "\tDate: " + easternLD + "\tTimestamp(ET): " + easternLT);
                //Close file
                outputFile.close();
            } catch (NullPointerException e) {
            }
        }
    }

    /** This method initializes the Login form fields to pre-populate in English or French based on User's language settings. */
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
