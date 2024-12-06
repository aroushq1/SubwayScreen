package ca.ucalgary.edu.ensf380;

import java.util.Date;

/**
 * The DataRetriever class is responsible for retrieving data from an external source using an API.
 * It stores the necessary parameters for the API request including the API key, endpoint, geographical coordinates, and date.
 * This class provides methods to set and get these parameters.
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class DataRetriever {
    
    private Short apiKey;
    private String ENDPOINT;
    private double longitude;
    private double latitude;
    private Date date;
    
    //Constructor
    /**
     * Constructs a DataRetriever with the specified endpoint.
     * 
     * @param ENDPOINT the endpoint URL for the API
     */
    public DataRetriever(String ENDPOINT) {
        this.ENDPOINT = ENDPOINT;
    }
    
    //Getters and Setters
    /**
     * Returns the API key used for authentication.
     * 
     * @return the API key
     */
    public Short getApiKey() {
        return apiKey;
    }

    /**
     * Sets the API key used for authentication.
     * 
     * @param apiKey the new API key
     */
    public void setApiKey(Short apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Returns the endpoint URL for the API.
     * 
     * @return the endpoint URL
     */
    public String getENDPOINT() {
        return ENDPOINT;
    }

    /**
     * Sets the endpoint URL for the API.
     * 
     * @param eNDPOINT the new endpoint URL
     */
    public void setENDPOINT(String eNDPOINT) {
        this.ENDPOINT = eNDPOINT;
    }

    /**
     * Returns the longitude coordinate for the data request.
     * 
     * @return the longitude coordinate
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude coordinate for the data request.
     * 
     * @param longitude the new longitude coordinate
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the latitude coordinate for the data request.
     * 
     * @return the latitude coordinate
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude coordinate for the data request.
     * 
     * @param latitude the new latitude coordinate
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the date for which data is being requested.
     * 
     * @return the date for the data request
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date for which data is being requested.
     * 
     * @param date the new date for the data request
     */
    public void setDate(Date date) {
        this.date = date;
    }
}