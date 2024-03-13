package org.example.v4pa.model;

/** This class creates the Contact class for storing Contact objects. */
public class Contact {
    private int contactCounter = 1;
    private int contactID;
    private String contactName;
    private String contactEmail;

    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /**
     * @return  contactID
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * @param contactID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * @return  contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * @param contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * @return  contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * @param contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return (contactName);
    }
}
