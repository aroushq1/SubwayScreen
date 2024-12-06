package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Unit tests for the DataRetriever class.
 */
public class DataRetrieverTest {
    private DataRetriever dataRetriever;
    private final String endpoint = "http://api.example.com/data";

    /**
     * Sets up a new DataRetriever object before each test.
     */
    @BeforeEach
    public void setUp() {
        dataRetriever = new DataRetriever(endpoint);
    }

    /**
     * Tests the getApiKey method.
     */
    @Test
    public void testGetApiKey() {
        Short expected = null;
        Short actual = dataRetriever.getApiKey();
        assertEquals(expected, actual, "The API key should initially be null.");
    }

    /**
     * Tests the setApiKey method.
     */
    @Test
    public void testSetApiKey() {
        Short newApiKey = 12345;
        dataRetriever.setApiKey(newApiKey);
        Short actual = dataRetriever.getApiKey();
        assertEquals(newApiKey, actual, "The API key should be updated to 12345.");
    }

    /**
     * Tests the getENDPOINT method.
     */
    @Test
    public void testGetENDPOINT() {
        String expected = endpoint;
        String actual = dataRetriever.getENDPOINT();
        assertEquals(expected, actual, "The endpoint should be 'http://api.example.com/data'.");
    }

    /**
     * Tests the setENDPOINT method.
     */
    @Test
    public void testSetENDPOINT() {
        String newEndpoint = "http://api.newexample.com/data";
        dataRetriever.setENDPOINT(newEndpoint);
        String actual = dataRetriever.getENDPOINT();
        assertEquals(newEndpoint, actual, "The endpoint should be updated to 'http://api.newexample.com/data'.");
    }

    /**
     * Tests the getLongitude method.
     */
    @Test
    public void testGetLongitude() {
        double expected = 0.0;
        double actual = dataRetriever.getLongitude();
        assertEquals(expected, actual, "The initial longitude should be 0.0.");
    }

    /**
     * Tests the setLongitude method.
     */
    @Test
    public void testSetLongitude() {
        double newLongitude = -114.0719;
        dataRetriever.setLongitude(newLongitude);
        double actual = dataRetriever.getLongitude();
        assertEquals(newLongitude, actual, "The longitude should be updated to -114.0719.");
    }

    /**
     * Tests the getLatitude method.
     */
    @Test
    public void testGetLatitude() {
        double expected = 0.0;
        double actual = dataRetriever.getLatitude();
        assertEquals(expected, actual, "The initial latitude should be 0.0.");
    }

    /**
     * Tests the setLatitude method.
     */
    @Test
    public void testSetLatitude() {
        double newLatitude = 51.0447;
        dataRetriever.setLatitude(newLatitude);
        double actual = dataRetriever.getLatitude();
        assertEquals(newLatitude, actual, "The latitude should be updated to 51.0447.");
    }

    /**
     * Tests the getDate method.
     */
    @Test
    public void testGetDate() {
        Date expected = null;
        Date actual = dataRetriever.getDate();
        assertEquals(expected, actual, "The initial date should be null.");
    }

    /**
     * Tests the setDate method.
     */
    @Test
    public void testSetDate() {
        Date newDate = new Date();
        dataRetriever.setDate(newDate);
        Date actual = dataRetriever.getDate();
        assertEquals(newDate, actual, "The date should be updated to the current date.");
    }
}

