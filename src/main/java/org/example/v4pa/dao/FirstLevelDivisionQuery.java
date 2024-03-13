package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class creates the FirstLevelDivisionQuery class that pulls FirstLevelDivision-related information from the database. */
public abstract class FirstLevelDivisionQuery {

    /** This method pulls all of the first level divisions associated with a specific country ID.
     * @param countryID the ID to determine the list of first level divisions.
     * */
    public static ObservableList<FirstLevelDivision> selectDivision (int countryID) {
        ObservableList<FirstLevelDivision> selectedDivision = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String divisionName = rs.getString("Division");
                int divisionID = rs.getInt("Division_ID");
                FirstLevelDivision divisionObject = new FirstLevelDivision(divisionID, divisionName, countryID);
                selectedDivision.add(divisionObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedDivision;
        //return Observable List of Divisions
    }

    /** This method pulls every first level division from the database. */
    public static ObservableList<FirstLevelDivision> getAllDivisions() {
        ObservableList<FirstLevelDivision> divisionList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                FirstLevelDivision divisions = new FirstLevelDivision(divisionID, division, countryID);
                divisionList.add(divisions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionList;
    }

    /** This method pulls a specific first level division based on the divisionName.
     * @param divisionName the name to determine the firstleveldivision from the total divisionList.
     * */
    public static FirstLevelDivision findDivisionID(String divisionName) {
        ObservableList<FirstLevelDivision> divisionList = getAllDivisions();
            for(FirstLevelDivision testDivision : divisionList) {
                if (testDivision.getDivisionName().contains(divisionName)) {
                    return testDivision;
                }
            }
            return null;
    }

    /** This method pulls a specific first level division based on the divisionID.
     * @param divisionID the ID to determine the firstleveldivision from the total divisionList.
     * */
    public static FirstLevelDivision findDivisionName(int divisionID) {
        ObservableList<FirstLevelDivision> divisionList = getAllDivisions();
        for(FirstLevelDivision testDivision : divisionList) {
            if (testDivision.getDivisionID() == (divisionID)) {
                return testDivision;
            }
        }
        return null;
    }
}
