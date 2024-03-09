package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.PropertyPermission;

public abstract class CustomerQuery {

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, first_level_divisions.Division_ID, Country_ID  \n" +
                "FROM customers, first_level_divisions\n" +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");
                Customer c = new Customer(customerID, name, address, postalCode, phone, divisionID, countryID);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static int addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhone);
        ps.setInt(5, customerDivisionID);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhone);
        ps.setInt(5, customerDivisionID);
        ps.setInt(6, customerID);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static ObservableList<Customer> selectCustomer(int customerID) throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, first_level_divisions.Division_ID, Country_ID  \n" +
        "FROM customers, first_level_divisions\n" +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID AND Customer_ID = ?";;
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");
                Customer c = new Customer(customerID, name, address, postalCode, phone, divisionID, countryID);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static ObservableList<Customer> selectCustomer(String customerName) throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, first_level_divisions.Division_ID, Country_ID  \n" +
                "FROM customers, first_level_divisions\n" +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID AND Customer_Name = ?";;
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Customer_ID");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");
                Customer c = new Customer(id, customerName, address, postalCode, phone, divisionID, countryID);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

}
