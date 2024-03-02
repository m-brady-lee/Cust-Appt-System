package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public abstract class CustomerQuery {

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, first_level_divisions.Division_ID, Country_ID  \n" +
                "FROM customers, first_level_divisions\n" +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID";
        // Add Country ID to Customer model
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

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            int color_id = rs.getInt("Color_ID");
            String fruitName = rs.getString("Fruit_Name");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + " | ");
            System.out.print(color_id + "\n");
        }
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
