package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {

    public static int apptCounter = 1;

    private int apptID;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private int apptStart;
    private int apptEnd;
    private int apptCustomerID;
    private int apptUserID;
    private int apptContactID;

    private static ObservableList<Contact> associatedContact = FXCollections.observableArrayList();
    private static ObservableList<Customer> associatedCustomer = FXCollections.observableArrayList();
    private static ObservableList<User> associatedUser = FXCollections.observableArrayList();

    public Appointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, int apptCustomerID, int apptUserID, int apptContactID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
//        this.apptStart = apptStart;
//        this.apptEnd = apptEnd;
//        this.apptCreateDate = apptCreateDate;
//        this.apptCreatedBy = apptCreatedBy;
//        this.apptUpdateDate = apptUpdateDate;
//        this.apptLastUpdatedBy = apptLastUpdatedBy;
        this.apptCustomerID = apptCustomerID;
        this.apptUserID = apptUserID;
        this.apptContactID = apptContactID;
    }

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public int getApptStart() {
        return apptStart;
    }

    public void setApptStart(int apptStart) {
        this.apptStart = apptStart;
    }

    public int getApptEnd() {
        return apptEnd;
    }

    public void setApptEnd(int apptEnd) {
        this.apptEnd = apptEnd;
    }

    public int getApptCustomerID() {
        return apptCustomerID;
    }

    public void setApptCustomerID(int apptCustomerID) {
        this.apptCustomerID = apptCustomerID;
    }

    public int getApptUserID() {
        return apptUserID;
    }

    public void setApptUserID(int apptUserID) {
        this.apptUserID = apptUserID;
    }

    public int getApptContactID() {
        return apptContactID;
    }

    public void setApptContactID(int apptContactID) {
        this.apptContactID = apptContactID;
    }

    public ObservableList<Customer> getAssociatedCustomer() { return associatedCustomer; }
    public ObservableList<User> getAssociatedUser() { return associatedUser; }
    public ObservableList<Contact> getAssociatedContact() { return associatedContact; }

    public static ObservableList<Customer> addAssociatedCustomer(Customer customer) {
        ObservableList<Customer> associatedCustomers = FXCollections.observableArrayList();
        associatedCustomers.add(customer);
        return associatedCustomers;
    }
    public void addAssociatedUser(User user) {
        associatedUser.add(user);
    }
    public void addAssociatedContact(Contact contact) {
        associatedContact.add(contact);
    }
}
