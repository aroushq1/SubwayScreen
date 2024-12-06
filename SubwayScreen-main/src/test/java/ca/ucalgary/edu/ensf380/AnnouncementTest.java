package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit tests for the Announcement class.
 */
public class AnnouncementTest {
    private Announcement announcement;
    private TrainInfo mockTrainInfo;

    /**
     * Sets up a new Announcement object with mock dependencies before each test.
     */
    @BeforeEach
    public void setUp() {
        mockTrainInfo = mock(TrainInfo.class);
        announcement = Mockito.spy(new Announcement(1, mockTrainInfo));
    }

    private void setField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    private Object getField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private Object callMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] args) throws Exception {
        Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(object, args);
    }

    private Object callMethod(Object object, String methodName) throws Exception {
        return callMethod(object, methodName, new Class<?>[0], new Object[0]);
    }

    /**
     * Tests the updateStationInfo method when the station code is found.
     * @throws Exception when exception is found
     */
    @Test
    public void testUpdateStationInfoFound() throws Exception {
        // Mocking private methods using Mockito
        doNothing().when(announcement).populateTrainToStationMap();

        Map<Integer, String> trainToStationMap = new HashMap<>();
        trainToStationMap.put(1, "STN1");
        setField(announcement, "trainToStationMap", trainToStationMap);

        Map<String, String> stationNamesAndType = new HashMap<>();
        stationNamesAndType.put("STN1", "Station One");
        setField(announcement, "stationNamesAndType", stationNamesAndType);

        // Invoke private method
        callMethod(announcement, "updateStationInfo");

        // Access private field
        String stationName = (String) getField(announcement, "stationName");
        assertEquals("Station One", stationName, "The station name should be 'Station One'.");
    }

    /**
     * Tests the updateStationInfo method when the station code is not found.
     * @throws Exception when exception is found
     */
    @Test
    public void testUpdateStationInfoNotFound() throws Exception {
        // Mocking private methods using Mockito
        doNothing().when(announcement).populateTrainToStationMap();

        Map<Integer, String> trainToStationMap = new HashMap<>();
        trainToStationMap.put(1, "STN2");
        setField(announcement, "trainToStationMap", trainToStationMap);

        Map<String, String> stationNamesAndType = new HashMap<>();
        stationNamesAndType.put("STN1", "Station One");
        setField(announcement, "stationNamesAndType", stationNamesAndType);

        // Invoke private method
        callMethod(announcement, "updateStationInfo");

        // Access private field
        String stationName = (String) getField(announcement, "stationName");
        assertEquals("Unknown", stationName, "The station name should be 'Unknown'.");
    }

    /**
     * Tests the createAnnouncement method for a known station.
     * @throws Exception when exception is found
     */
    @Test
    public void testCreateAnnouncementKnownStation() throws Exception {
        // Set private field value
        setField(announcement, "stationName", "Station One");
        String expected = "Next stop: Station One.";
        String actual = (String) callMethod(announcement, "createAnnouncement");
        assertEquals(expected, actual, "The announcement should be 'Next stop: Station One.'.");
    }

    /**
     * Tests the createAnnouncement method for an unknown station.
     * @throws Exception when exception is found
     */
    @Test
    public void testCreateAnnouncementUnknownStation() throws Exception {
        // Set private field value
        setField(announcement, "stationName", "Unknown");
        String expected = "No current station available for the train.";
        String actual = (String) callMethod(announcement, "createAnnouncement");
        assertEquals(expected, actual, "The announcement should be 'No current station available for the train.'.");
    }

    /**
     * Tests the periodicUpdate method to ensure it updates the announcement.
     * @throws Exception when exception is found
     * @throws InterruptedException when interruption occurs 
     */
    @Test
    public void testPeriodicUpdate() throws InterruptedException, Exception {
        // Mocking private methods to avoid actual file operations
        doNothing().when(announcement).populateTrainToStationMap();
        doNothing().when(announcement).updateStationInfo();
        doNothing().when(announcement).createAndSpeakAnnouncement();

        Thread updateThread = new Thread(() -> {
            try {
                callMethod(announcement, "periodicUpdate");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        updateThread.start();
        Thread.sleep(15000); // Wait for at least one update cycle
        updateThread.interrupt(); // Stop the thread

        // Verify that the update methods were called at least once
        verify(announcement, atLeastOnce()).populateTrainToStationMap();
        verify(announcement, atLeastOnce()).updateStationInfo();
        verify(announcement, atLeastOnce()).createAndSpeakAnnouncement();
    }
}