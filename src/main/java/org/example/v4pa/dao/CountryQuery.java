package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Country;
import org.example.v4pa.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("country");
                Country newCountry = new Country (countryID, country);
                countryList.add(newCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public static Country findCountryID(String countryName) {
        ObservableList<Country> countryList = getAllCountries();
        for(Country testCountry : countryList) {
            if (testCountry.getCountryName().contains(countryName)) {
                return testCountry;
            }
        }
        return null;
    }

    public static Country findCountryName(int countryID) {
        ObservableList<Country> countryList = getAllCountries();
        for(Country testCountry : countryList) {
            if (testCountry.getCountryID() == (countryID)) {
                return testCountry;
            }
        }
        return null;
    }
}
