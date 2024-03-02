package org.example.v4pa.controller;

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
import org.example.v4pa.model.Country;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    Parent scene;
    Stage stage;

    @FXML
    private TextField editcustomerAddressText;
    @FXML
    private Button editcustomerCancelButton;
    @FXML
    private Label editcustomerStateProvinceLabel;
    @FXML
    private ComboBox<Country> editcustomerCountryComboBox;
    @FXML
    private TextField editcustomerIDText;
    @FXML
    private TextField editcustomerNameText;
    @FXML
    private TextField editcustomerPhoneText;
    @FXML
    private TextField editcustomerPostalCodeText;
    @FXML
    private Button editcustomerSaveButton;
    @FXML
    private ComboBox<FirstLevelDivision> editcustomerProvinceComboBox;
    private String[] countries = {"Canada", "United Kingdom", "United States"};

    @FXML
    void onActionCancelEditCustToCustDetails(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will discard all changes. Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSaveEditCustToCustDetails(ActionEvent event) throws IOException, SQLException {

        int id = 0;
        try {
            id = Integer.parseInt(editcustomerIDText.getText());
        } catch (NumberFormatException e) {
        }

        String name = editcustomerNameText.getText();
        /** LOGICAL ERROR: This error is generated if the name field is blank or contains any number values. */
        if (name.isBlank() || name.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for Name");
            alert.showAndWait();
            return;
        }

        /** LOGICAL ERROR: This error is generated if the country combo box field is not selected. */
        try {
            String country = editcustomerCountryComboBox.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Country must be selected");
            alert.showAndWait();
            return;
        }

        int divisionID = 10;
        /** RUNTIME ERROR: This error is generated if the province combo box does not contain a value. */
        try {
            String province = editcustomerProvinceComboBox.getValue().toString();
            divisionID = FirstLevelDivisionQuery.findDivisionID(province).getDivisionID();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Province/State must be selected");
            alert.showAndWait();
            return;
        }

        String address = editcustomerAddressText.getText();
        /** LOGICAL ERROR: This error is generated if the address field is blank. */
        if (address.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Address cannot be blank");
            alert.showAndWait();
            return;
        }

        String postalCode = editcustomerPostalCodeText.getText();
        /** LOGICAL ERROR: This error is generated if the postalCode field is blank. */
        if (postalCode.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Postal Code cannot be blank");
            alert.showAndWait();
            return;
        }

        String phoneNumber = editcustomerPhoneText.getText();
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

            CustomerQuery.updateCustomer(id, name, address, postalCode, phoneNumber, divisionID);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/org/example/view/customer-details-view.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editcustomerCountryComboBox.setItems(CountryQuery.getAllCountries());
        editcustomerCountryComboBox.setOnAction(this::setStateOrProvince);
        editcustomerProvinceComboBox.setItems(FirstLevelDivisionQuery.getAllDivisions());

    }

    public void setStateOrProvince(ActionEvent event) {
        String customerCountry = String.valueOf(editcustomerCountryComboBox.getValue());

        if (customerCountry.contains("U.S")) {
            editcustomerStateProvinceLabel.setText("State");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(1);
            editcustomerProvinceComboBox.setItems(divisions);
        }
        if (customerCountry.contains("UK")) {
            editcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(2);
            editcustomerProvinceComboBox.setItems(divisions);
        }
        if (customerCountry.contains("Canada")) {
            editcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(3);
            editcustomerProvinceComboBox.setItems(divisions);
        }
    }

    public void sendCustomer(Customer customer) {

        editcustomerIDText.setText(String.valueOf(customer.getCustomerID()));
        editcustomerNameText.setText(customer.getCustomerName());
        int countryID = customer.getCustomerCountryID();
        editcustomerCountryComboBox.setValue(CountryQuery.findCountryName(countryID));
        editcustomerAddressText.setText(customer.getCustomerAddress());
        editcustomerPostalCodeText.setText(customer.getCustomerPostalCode());
        editcustomerPhoneText.setText(customer.getCustomerPhone());
        int divisionID = customer.getCustomerDivisionID();
        editcustomerProvinceComboBox.setValue(FirstLevelDivisionQuery.findDivisionName(divisionID));

        String customerCountry = String.valueOf(editcustomerCountryComboBox.getValue());
        if (customerCountry.contains("U.S")) {
            editcustomerStateProvinceLabel.setText("State");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(1);
            editcustomerProvinceComboBox.setItems(divisions);
        }
        if (customerCountry.contains("UK")) {
            editcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(2);
            editcustomerProvinceComboBox.setItems(divisions);
        }
        if (customerCountry.contains("Canada")) {
            editcustomerStateProvinceLabel.setText("Province");
            ObservableList<FirstLevelDivision> divisions = FirstLevelDivisionQuery.selectDivision(3);
            editcustomerProvinceComboBox.setItems(divisions);
        }
    }
}

