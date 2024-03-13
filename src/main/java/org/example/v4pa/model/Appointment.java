package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Locale;

/** This class creates the Appointment class for storing Appointment objects. */
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

    /**
     * @return the apptID
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * @param apptID the id to set
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * @return the apptTitle
     */
    public String getApptTitle() {
        return apptTitle;
    }
    /**
     * @param apptTitle to set
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }
    /**
     * @return the apptDescription
     */
    public String getApptDescription() {
        return apptDescription;
    }
    /**
     * @param apptDescription to set
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }
    /**
     * @return the apptLocation
     */
    public String getApptLocation() {
        return apptLocation;
    }
    /**
     * @param apptLocation to set
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }
    /**
     * @return the apptType
     */
    public String getApptType() {
        return apptType;
    }
    /**
     * @param apptType to set
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }
    /**
     * @return the apptStart
     */
    public LocalDateTime getApptStart() {
        return apptStart;
    }
    /**
     * @param apptStart to set
     */
    public void setApptStart(LocalDateTime apptStart) {
        this.apptStart = apptStart;
    }
    /**
     * @return the apptEnd
     */
    public LocalDateTime getApptEnd() {
        return apptEnd;
    }
    /**
     * @param apptEnd to set
     */
    public void setApptEnd(LocalDateTime apptEnd) {
        this.apptEnd = apptEnd;
    }
    /**
     * @return the apptCustomerID
     */
    public int getApptCustomerID() {
        return apptCustomerID;
    }
    /**
     * @param apptCustomerID to set
     */
    public void setApptCustomerID(int apptCustomerID) {
        this.apptCustomerID = apptCustomerID;
    }
    /**
     * @return the apptUserID
     */
    public int getApptUserID() {
        return apptUserID;
    }
    /**
     * @param apptUserID to set
     */
    public void setApptUserID(int apptUserID) {
        this.apptUserID = apptUserID;
    }
    /**
     * @return the apptContactID
     */
    public int getApptContactID() {
        return apptContactID;
    }
    /**
     * @param apptContactID to set
     */
    public void setApptContactID(int apptContactID) {
        this.apptContactID = apptContactID;
    }

}
