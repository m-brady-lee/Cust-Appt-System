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
import org.example.v4pa.dao.CountryQuery;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.dao.FirstLevelDivisionQuery;
import org.example.v4pa.dao.JDBC;
import org.example.v4pa.model.Country;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class creates the Add Customer view of the app.
 * RUNTIME ERRORS: This form will generate errors if each field is not filled out. All fields are required. */
public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addcustomerAddressText;
    @FXML
    private Button addcustomerCancelButton;
    @FXML
    private ChoiceBox<Country> addcustomerCountryDropdown;
    @FXML
    private Label addcustomerStateProvinceLabel;
    @FXML
    private TextField addcustomerIDText;
    @FXML
    private TextField addcustomerNameText;
    @FXML
    private TextField addcustomerPhoneText;
    @FXML
    private TextField addcustomerPostalCodeText;
    @FXML
    private Button addcustomerSaveButton;
    @FXML
    private ComboBox<FirstLevelDivision> addcustomerProvinceComboBox;

    @FXML
    void onActionCancelNewCustToCustDetails(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will discard all changes. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSaveNewCustToCustDetails(ActionEvent event) throws IOException, SQLException {

        String name = addcustomerNameText.getText();
        /** LOGICAL ERROR: This error is generated if the name field is blank or contains any number values. */
        if (name.isBlank() || name.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for Name");
            alert.showAndWait();
            return;
        }

        int divisionID = 10;
        /** RUNTIME ERROR: This error is generated if the province combo box does not contain a value. */
        try {
            String province = addcustomerProvinceComboBox.getValue().toString();
            divisionID = FirstLevelDivisionQuery.findDivisionID(province).getDivisionID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Province/State must be selected");
            alert.showAndWait();
            return;
        }

        String address = addcustomerAddressText.getText();
        /** LOGICAL ERROR: This error is generated if the address field is blank. */
        if (address.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Address cannot be blank");
            alert.showAndWait();
            return;
        }

        String postalCode = addcustomerPostalCodeText.getText();
        /** LOGICAL ERROR: This error is generated if the postalCode field is blank. */
        if (postalCode.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Postal Code cannot be blank");
            alert.showAndWait();
            return;
        }

        String phoneNumber = addcustomerPhoneText.getText();
        /** LOGICAL ERROR: This error is generated if the phoneNumber field is blank. */
        if (phoneNumber.isBlank() || phoneNumber.matches(".*[a-z].*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid Phone Number");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save the new customer?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        CustomerQuery.addCustomer(name, address, postalCode, phoneNumber, divisionID);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /** This method initializes the values of the Country and Province combo boxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addcustomerCountryDropdown.setItems(CountryQuery.getAllCountries());
        addcustomerCountryDropdown.setOnAction(this::setStateOrProvince);
        addcustomerProvinceComboBox.setItems(FirstLevelDivisionQuery.getAllDivisions());

    }

    /** This method sets unique values of the Province combo box based on the Country the user selects. */
    public void setStateOrProvince(ActionEvent event) {
        String customerCountry = String.valueOf(addcustomerCountryDropdown.getValue());

        if(customerCountry.contains("U.S")) {
            addcustomerStateProvinceLabel.setText("State");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(1);
            addcustomerProvinceComboBox.setItems(divisions);
        }
        if(customerCountry.contains("UK")) {
            addcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(2);
            addcustomerProvinceComboBox.setItems(divisions);
        }
        if(customerCountry.contains("Canada")) {
            addcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(3);
            addcustomerProvinceComboBox.setItems(divisions);
        }
    }
}

