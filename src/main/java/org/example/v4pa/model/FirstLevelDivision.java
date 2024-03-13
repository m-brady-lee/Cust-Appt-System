package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class creates the FirstLevelDivision class for storing First Level Division objects. */
public class FirstLevelDivision {
    private int divisionCounter = 1;
    private int divisionID;
    private String divisionName;
    private int divisionCountryID;

    public FirstLevelDivision(int divisionID, String divisionName, int divisionCountryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.divisionCountryID = divisionCountryID;
    }

    /**
     *
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * @param divisionID to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**
     *
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * @param divisionName to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     *
     * @return divisionCountryID
     */
    public int getDivisionCountryID() {
        return divisionCountryID;
    }
    /**
     * @param divisionCountryID to set
     */
    public void setDivisionCountryID(int divisionCountryID) {
        this.divisionCountryID = divisionCountryID;
    }
    /**
     *
     * @return divisionName
     */
    @Override
    public String toString() {
        return (divisionName);
    }
}
