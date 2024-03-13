package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Contact;
import org.example.v4pa.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class creates the ContactQuery class that pulls Contact-related information from the database. */
public abstract class ContactQuery {

    /** This method pulls every contact from the database. */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact c = new Contact(contactID, name, email);
                contactList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    /** This method pulls a specific contact based on the contact name.
     * @param contactName the name to determine the contact from the total contactList.
     * */
    public static Contact findContactID(String contactName) {
        ObservableList<Contact> contactList = getAllContacts();
        for(Contact testContact : contactList) {
            if (testContact.getContactName().contains(contactName)) {
                return testContact;
            }
        }
        return null;
    }

    /** This method pulls a specific contact based on the contact ID.
     * @param contactID the ID to determine the contact from the total contactList.
     * */
    public static Contact findContactName(int contactID) {
        ObservableList<Contact> contactList = getAllContacts();
        for(Contact testContact : contactList) {
            if (testContact.getContactID() == (contactID)) {
                return testContact;
            }
        }
        return null;
    }

//    public static ObservableList<Contact> selectContact(int contactID) throws SQLException {
//        ObservableList<Contact> contactList = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
//        try {
//            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//            ps.setInt(1, contactID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("Contact_Name");
//                String email = rs.getString("Email");
//                Contact c = new Contact(contactID, name, email);
//                contactList.add(c);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return contactList;
//    }
}
