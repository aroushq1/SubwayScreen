package ca.ucalgary.edu.ensf380;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TrainInfo class.
 * These tests include checking the functionality of private methods using reflection.
 */
public class TrainInfoTest {

    private TrainInfo trainInfo;

    /**
     * Sets up the test environment by initializing a TrainInfo instance.
     */
    @BeforeEach
    public void setUp() {
        trainInfo = new TrainInfo();
    }

    /**
     * Tests the clearOutFolder method of TrainInfo using reflection.
     * Verifies that the folder is cleared of all files.
     * 
     * @throws Exception if an error occurs while invoking the method or creating files
     */
    @Test
    public void testClearOutFolder() throws Exception {
        // Use reflection to access the private method
        Method clearOutFolderMethod = TrainInfo.class.getDeclaredMethod("clearOutFolder");
        clearOutFolderMethod.setAccessible(true);

        // Create a temporary folder and file to test
        File tempFolder = new File("subwayscreen-main/out");
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        File tempFile = new File(tempFolder, "testFile.txt");
        if (tempFile.createNewFile()) {
            assertTrue(tempFile.exists(), "Temporary file should exist before clearing.");
        }

        // Call the private method to clear the folder
        clearOutFolderMethod.invoke(trainInfo);

        // Check if the folder is empty
        File[] files = tempFolder.listFiles();
        assertNotNull(files, "File list should not be null.");
        assertEquals(0, files.length, "The out folder should be empty after clearing.");
    }

    /**
     * Tests the getLastModified method of TrainInfo using reflection.
     * Verifies that the newest file in the specified folder is returned.
     */
    @Test
    public void testGetLastModified() {
        // Access the private method using reflection
        try {
            Method getLastModifiedMethod = TrainInfo.class.getDeclaredMethod("getLastModified", String.class);
            getLastModifiedMethod.setAccessible(true);

            // Create temporary files
            File tempFolder = new File("subwayscreen-main/out");
            if (!tempFolder.exists()) {
                tempFolder.mkdirs();
            }
            File oldestFile = new File(tempFolder, "oldestFile.txt");
            File newestFile = new File(tempFolder, "newestFile.txt");

            if (oldestFile.createNewFile() && newestFile.createNewFile()) {
                // Set modification times
                oldestFile.setLastModified(System.currentTimeMillis() - 10000); // 10 seconds ago
                newestFile.setLastModified(System.currentTimeMillis()); // Now
            }

            // Invoke the private method
            File result = (File) getLastModifiedMethod.invoke(trainInfo, "subwayscreen-main/out");

            // Assert the result
            assertNotNull(result, "The result should not be null.");
            assertEquals("newestFile.txt", result.getName(), "The newest file should be returned.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not be thrown.");
        }
    }
}
