package org.example.v4pa.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDetailsController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button custdetailsAddButton;

    @FXML
    private TableColumn<Customer, String> custdetailsAddressColumn;

    @FXML
    private Button custdetailsApptDetailsButton;

    @FXML
    private TableColumn<Customer, Integer> custdetailsCustIDColumn;

    @FXML
    private Button custdetailsDeleteButton;

    @FXML
    private TableColumn<Customer, Integer> custdetailsDivisionIDColumn;

    @FXML
    private Button custdetailsEditButton;

    @FXML
    private TableColumn<Customer, String> custdetailsNameColumn;

    @FXML
    private TableColumn<Customer, Integer> custdetailsPhoneColumn;

    @FXML
    private TableColumn<Customer, Integer> custdetailsPostalCodeColumn;
    @FXML
    private TableView<Customer> custdetailsTableView;

    @FXML
    private Button custdetailsReportsButton;

    @FXML
    void onActionDisplayAddCustMenu(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/add-customer-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDisplayApptDetailsFromCustDetails(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/appointment-details-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method displays the Edit Customer menu.
     If a customer is not selected, an Error message will display. */
    @FXML
    void onActionDisplayEditCustMenu(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/view/edit-customer-view.fxml"));
            loader.load();

            EditCustomerController EditController = loader.getController();
            EditController.sendCustomer(custdetailsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Customer to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    void onActionDisplayReportsFromCustDetails(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/org/example/view/reports-view.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteCust(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> customers = CustomerQuery.getAllCustomers();
        custdetailsTableView.setItems(customers);

        custdetailsCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        custdetailsNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custdetailsAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custdetailsPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custdetailsPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custdetailsDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerDivisionID"));
    }
}

