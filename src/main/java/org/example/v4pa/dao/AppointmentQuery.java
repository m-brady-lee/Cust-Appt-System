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

    public static int addAppointment(String apptTitle, String apptDescription, String apptLocation, String apptType, int apptStart, int apptEnd, String apptCreatedBy, int custID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, apptTitle);
        ps.setString(2, apptDescription);
        ps.setString(3, apptLocation);
        ps.setString(4, apptType);
        ps.setInt(5, apptStart);
        ps.setInt(6, apptEnd);
        //createDate 7 How do you pull the date / time issue for when the form is added / updated?
        ps.setString(8, apptCreatedBy);
        // lastUpdate 9 Set to null
        // lastUpdatedBy 10 Set to null
        ps.setInt(11, custID);
        ps.setInt(12, userID);
        ps.setInt(13, contactID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int editAppointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, int apptStart, int apptEnd, String apptLastUpdatedBy, int custID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?) WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, apptTitle);
        ps.setString(2, apptDescription);
        ps.setString(3, apptLocation);
        ps.setString(4, apptType);
        ps.setInt(5, apptStart);
        ps.setInt(6, apptEnd);
        // lastUpdate 7 - How do you pull the date / time issue for when the form is added / updated?
        ps.setString(8, apptLastUpdatedBy);
        ps.setInt(9, custID);
        ps.setInt(10, userID);
        ps.setInt(11, contactID);
        ps.setInt(12, apptID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
    public static int update(int fruitId, String fruitName) throws SQLException {
        String sql = "UPDATE FRUITS SET Fruit_Name = ? WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, fruitId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int delete(int fruitId) throws SQLException {
        String sql = "DELETE FROM FRUITS WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, fruitId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM FRUITS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + "\n");
        }
    }

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            int color_id = rs.getInt("Color_ID");
            String fruitName = rs.getString("Fruit_Name");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + " | ");
            System.out.print(color_id + "\n");
        }
    }

}
