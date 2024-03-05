package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    public int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivisionID;
    private int customerCountryID;


    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID, int customerCountryID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionID = customerDivisionID;
        this.customerCountryID = customerCountryID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    public int getCustomerCountryID() {
        return customerCountryID;
    }

    public void setCustomerCountryID(int customerCountryID) {
        this.customerCountryID = customerCountryID;
    }

    @Override
    public String toString() {
        return (customerName);
    }
}
