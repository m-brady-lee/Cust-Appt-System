package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionQuery {

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

    public static FirstLevelDivision findDivisionID(String divisionName) {
        ObservableList<FirstLevelDivision> divisionList = getAllDivisions();
            for(FirstLevelDivision testDivision : divisionList) {
                if (testDivision.getDivisionName().contains(divisionName)) {
                    return testDivision;
                }
            }
            return null;
    }

    public static FirstLevelDivision findDivisionName(int divisionID) {
        ObservableList<FirstLevelDivision> divisionList = getAllDivisions();
        for(FirstLevelDivision testDivision : divisionList) {
            if (testDivision.getDivisionID() == (divisionID)) {
                return testDivision;
            }
        }
        return null;
    }

    public static void selectAll () throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            System.out.print(divisionID + " | ");
            System.out.print(divisionName + "\n");
        }
    }
}
