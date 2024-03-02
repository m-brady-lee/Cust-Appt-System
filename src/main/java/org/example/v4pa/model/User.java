package org.example.v4pa.model;

public class User {
    public static int userCounter = 1;
    private int userID;
    private String userName;
    private String userPassword;
    private int userCreateDate;
    private String userCreatedBy;
    private int userLastUpdate;
    private String userLastUpdatedBy;

    public User(int userID, String userName, String userPassword, int userCreateDate, String userCreatedBy, int userLastUpdate, String userLastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userCreateDate = userCreateDate;
        this.userCreatedBy = userCreatedBy;
        this.userLastUpdate = userLastUpdate;
        this.userLastUpdatedBy = userLastUpdatedBy;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(int userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public String getUserCreatedBy() {
        return userCreatedBy;
    }

    public void setUserCreatedBy(String userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
    }

    public int getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(int userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public String getUserLastUpdatedBy() {
        return userLastUpdatedBy;
    }

    public void setUserLastUpdatedBy(String userLastUpdatedBy) {
        this.userLastUpdatedBy = userLastUpdatedBy;
    }
}
