package ca.ucalgary.edu.ensf380;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

/**This is a test class for AdvertisementDatabase. It uses mock objects to simulate interaction with a database*/
public class AdvertisementDatabaseTest {

    private AdvertisementDatabase adDatabaseMock;

    /**Sets up the mock AdvertisementDatabase object*/
    @Before
    public void setup() {
        adDatabaseMock = mock(AdvertisementDatabase.class);
    }

    /**This method tests that the connection is initialized
     * @throws SQLException if there is an error
     */
    @Test
    public void initializeConnectionTest() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        when(adDatabaseMock.initializeConnection()).thenReturn(mockConnection);

        Connection connection = adDatabaseMock.initializeConnection();

        assertNotNull("The connection should not be null", connection);
        verify(adDatabaseMock).initializeConnection();
    }

    /**This method makes sure that the method selectAd returns the ad, path, and mediatype that is expected
     * @throws SQLException if there is an error
     * */
    @Test
    public void selectAdTest() throws SQLException {
        String expectedAd = "Ad Title, Ad Path, Ad MediaType\n";
        
        when(adDatabaseMock.selectAd()).thenReturn(expectedAd);
        
        String actualAd = adDatabaseMock.selectAd();
        
        assertEquals("The expected Ad doesn't match the actual Ad", expectedAd, actualAd);
    }

    /**This method makes sure that the method selectPath returns the filepath that is expected from the database Query
     * @throws SQLException if there is an error
     */
    @Test
    public void selectPathTest() throws SQLException {
        String expectedPath = "C:random\\path\n";
        
        when(adDatabaseMock.selectPath()).thenReturn(expectedPath);
        
        String actualPath = adDatabaseMock.selectPath();
        
        assertEquals("The expected and actual paths do not match", expectedPath, actualPath);
    }

    /**This method makes sure that the method selectMediaTypeAndId returns the mediaType and id that is expected
     * @throws SQLException if there is an error
     */
    @Test
    public void selectMediaTypeAndIdTest() throws SQLException {
        String expectedType = "1, JPEG\n";
        
        when(adDatabaseMock.selectMediaTypeAndId()).thenReturn(expectedType);
        
        String actualType = adDatabaseMock.selectMediaTypeAndId();
        
        assertEquals("The expected and actual media types and IDs do not match", expectedType, actualType);
    }
}
