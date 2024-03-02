package org.example.v4pa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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



    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getDivisionCountryID() {
        return divisionCountryID;
    }

    public void setDivisionCountryID(int divisionCountryID) {
        this.divisionCountryID = divisionCountryID;
    }

    @Override
    public String toString() {
        return (divisionName);
    }
}
