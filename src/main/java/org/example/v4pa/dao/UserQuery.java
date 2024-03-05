package org.example.v4pa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.model.Customer;
import org.example.v4pa.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {

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

    public static User findUserID(String userName) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if (testUser.getUserName().contains(userName)) {
                return testUser;
            }
        }
        return null;
    }

    public static User findUserName(int userID) {
        ObservableList<User> userList = getAllUsers();
        for(User testUser : userList) {
            if (testUser.getUserID() == (userID)) {
                return testUser;
            }
        }
        return null;
    }

    public static ObservableList<User> selectUser(int userID) throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                User u = new User(userID, name, password);
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
