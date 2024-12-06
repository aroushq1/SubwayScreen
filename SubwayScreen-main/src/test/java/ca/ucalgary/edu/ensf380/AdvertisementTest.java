package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Advertisement class.
 */
public class AdvertisementTest {
    private Advertisement ad;

    /**
     * Sets up a new Advertisement object before each test.
     */
    @BeforeEach
    public void setUp() {
        ad = new Advertisement("/path/to/ad.mp4");
    }

    /**
     * Tests the getFilePath method.
     */
    @Test
    public void testGetFilePath() {
        String expected = "/path/to/ad.mp4";
        String actual = ad.getFilePath();
        assertEquals(expected, actual, "The file path should be /path/to/ad.mp4");
    }

    /**
     * Tests the setFilePath method.
     */
    @Test
    public void testSetFilePath() {
        String newFilePath = "/new/path/to/ad.mp4";
        ad.setFilePath(newFilePath);
        String actual = ad.getFilePath();
        assertEquals(newFilePath, actual, "The file path should be updated to /new/path/to/ad.mp4");
    }

    /**
     * Tests the getDisplayDuration method.
     */
    @Test
    public void testGetDisplayDuration() {
        int expected = 10;
        int actual = ad.getDisplayDuration();
        assertEquals(expected, actual, "The display duration should be 10 seconds");
    }
}

