package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Locale;

public class Appointment {
    private int apptID;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private LocalDateTime apptStart;
    private LocalDateTime apptEnd;
    private int apptCustomerID;
    private int apptUserID;
    private int apptContactID;


    public Appointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, LocalDateTime apptStart, LocalDateTime apptEnd, int apptCustomerID, int apptUserID, int apptContactID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
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

    public LocalDateTime getApptStart() {
        return apptStart;
    }

    public void setApptStart(LocalDateTime apptStart) {
        this.apptStart = apptStart;
    }

    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    public void setApptEnd(LocalDateTime apptEnd) {
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

}
