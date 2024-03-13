package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class creates the UserQuery class that pulls User-related information from the database. */
public abstract class UserQuery {

    /** This method pulls every user from the database. */
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                User u = new User(userID, name, password);
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /** This method pulls a specific user based on the userName.
     * @param userName the name to determine the user from the total users.
     * */
    public static User findUserID(String userName) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if (testUser.getUserName().equals(userName)) {
                return testUser;
            }
        }
        return null;
    }

    /** This method pulls a specific user password based on the userName.
     * @param userName the name to determine the password from the total users.
     * */
    public static String findUserPassword(String userName) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if (testUser.getUserName().equals(userName)) {
                String password = testUser.getUserPassword();
                return password;
            }
        }
        return null;
    }

    /** This method pulls a specific user based on the userPassword.
     * @param password the pw to determine the user from the total users.
     * */
    public static String findUserNameWithPassword(String password) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if(testUser.getUserPassword().equals(password)) {
                String userName = testUser.getUserName();
                return userName;
            }
        }
        return null;
    }

    /** This method pulls a specific user based on the userID.
     * @param userID the ID to determine the user from the total users.
     * */
    public static User findUserName(int userID) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if (testUser.getUserID() == (userID)) {
                return testUser;
            }
        }
        return null;
    }

//    public static ObservableList<User> selectUser(int userID) throws SQLException {
//        ObservableList<User> userList = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM users WHERE User_ID = ?";
//        try {
//            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//            ps.setInt(1, userID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("User_Name");
//                String password = rs.getString("Password");
//                User u = new User(userID, name, password);
//                userList.add(u);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userList;
//    }
}
