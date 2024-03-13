package org.example.v4pa.model;

/** This class creates the User class for storing User objects. */
public class User {
    private int userID;
    private String userName;
    private String userPassword;

    public User(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }
    /**
     * @param userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     *
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * @param userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     *
     * @return userName
     */
    @Override
    public String toString() {
        return (userName);
    }
}
