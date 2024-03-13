package org.example.v4pa.model;

/** This class creates the Country class for storing Country objects. */
public class Country {
    private int countryCounter = 1;
    private int countryID;
    private String countryName;

    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }
    /**
     *
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * @param countryID to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    /**
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * @param countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    /**
     *
     * @return countryName
     */
    @Override
    public String toString(){
        return(countryName);
    }
}
