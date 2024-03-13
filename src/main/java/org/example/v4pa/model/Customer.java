package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates the Customer class for storing Customer objects. */
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
    /**
     *
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * @param customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * @param customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     *
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    /**
     * @param customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    /**
     *
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    /**
     * @param customerPostalCode to set
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }
    /**
     *
     * @return customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /**
     * @param customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    /**
     *
     * @return customerDivisionID
     */
    public int getCustomerDivisionID() {
        return customerDivisionID;
    }
    /**
     * @param customerDivisionID to set
     */
    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }
    /**
     *
     * @return customerCountryID
     */
    public int getCustomerCountryID() {
        return customerCountryID;
    }
    /**
     * @param customerCountryID to set
     */
    public void setCustomerCountryID(int customerCountryID) {
        this.customerCountryID = customerCountryID;
    }
    /**
     *
     * @return customerName
     */
    @Override
    public String toString() {
        return (customerName);
    }
}
