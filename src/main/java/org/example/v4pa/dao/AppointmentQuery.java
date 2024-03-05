package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AppointmentQuery {

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                // Need to add in Start and End times
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointment a = new Appointment(apptID, title, description, location, type, customerID, userID, contactID);
                appointmentList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    public static int addAppointment(String apptTitle, String apptDescription, String apptLocation, String apptType, int custID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, apptTitle);
        ps.setString(2, apptDescription);
        ps.setString(3, apptLocation);
        ps.setString(4, apptType);
//        ps.setInt(5, apptStart);
//        ps.setInt(6, apptEnd);
        ps.setInt(5, custID);
        ps.setInt(6, userID);
        ps.setInt(7, contactID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateAppointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, int custID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, apptTitle);
        ps.setString(2, apptDescription);
        ps.setString(3, apptLocation);
        ps.setString(4, apptType);
//        ps.setInt(5, apptStart);
//        ps.setInt(6, apptEnd);
        ps.setInt(5, custID);
        ps.setInt(6, userID);
        ps.setInt(7, contactID);
        ps.setInt(8, apptID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int deleteAppointment (int appointmentID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }


    public static ObservableList<Appointment> selectAppointment(int appointmentID) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
//                startDateTime
//                endDateTime
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointment a = new Appointment(customerID, title, description, location, type, customerID, userID, contactID);
                appointmentList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

}
