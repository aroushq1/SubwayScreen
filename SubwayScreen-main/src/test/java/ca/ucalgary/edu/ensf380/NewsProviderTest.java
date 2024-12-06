package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Unit tests for the NewsProvider class.
 */
public class NewsProviderTest {

    private NewsProvider newsProvider;
    private NewsAPI mockNewsAPI;
    private final String endpoint = "http://example.com/api/news";

    /**
     * Sets up a new NewsProvider object before each test.
     */
    @BeforeEach
    public void setUp() {
        newsProvider = new NewsProvider(endpoint, "Test Headline", "Test Content");
        mockNewsAPI = mock(NewsAPI.class);
    }

    /**
     * Tests the setHeadline and getHeadline methods.
     */
    @Test
    public void testSetAndGetHeadline() {
        String newHeadline = "New Headline";
        newsProvider.setHeadline(newHeadline);
        assertEquals(newHeadline, newsProvider.getHeadline(), "The headline should be 'New Headline'.");
    }

    /**
     * Tests the setContent and getContent methods.
     */
    @Test
    public void testSetAndGetContent() {
        String newContent = "New Content";
        newsProvider.setContent(newContent);
        assertEquals(newContent, newsProvider.getContent(), "The content should be 'New Content'.");
    }

    /**
     * Tests the display method with a mock NewsAPI response.
     * @throws Exception when exception occurs
     */
    @Test
    public void testDisplay() throws Exception {
        String jsonResponse = "{\"articles\": [" +
                "{\"title\": \"Mock Title\", \"description\": \"Mock Description\", \"urlToImage\": \"https://example.com/image.jpg\"}" +
                "]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mockJsonNode = objectMapper.readTree(jsonResponse);

        // Mocking the getNewsData method to return the mock JSON response
        when(mockNewsAPI.getNewsData()).thenReturn(mockJsonNode);

        // Replace the real getNewsData method with the mocked one
        newsProvider = new NewsProvider(endpoint) {
            @Override
            public JsonNode getNewsData() throws IOException, InterruptedException {
                return mockJsonNode;
            }
        };

        // Call the display method and verify the news panel components
        newsProvider.display();
        JPanel newsPanel = newsProvider.getNewsPanel();
        assertEquals(3, newsPanel.getComponentCount(), "The news panel should contain 3 components.");

        // Verify the title label
        JLabel titleLabel = (JLabel) newsPanel.getComponent(0);
        assertEquals("Mock Title", titleLabel.getText(), "The title label should display 'Mock Title'.");

        // Verify the description label
        JLabel descriptionLabel = (JLabel) newsPanel.getComponent(1);
        assertEquals("<html><div style='text-align: center;'>Mock Description</div></html>", descriptionLabel.getText(), "The description label should display 'Mock Description'.");

        // Verify the image label
        JLabel imageLabel = (JLabel) newsPanel.getComponent(2);
        assertTrue(imageLabel.getIcon() instanceof ImageIcon, "The image label should contain an ImageIcon.");
    }

    /**
     * Tests the display method with no articles found.
     * @throws Exception when exception occurs
     */
    @Test
    public void testDisplayNoArticlesFound() throws Exception {
        String jsonResponse = "{\"articles\": []}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mockJsonNode = objectMapper.readTree(jsonResponse);

        // Mocking the getNewsData method to return the mock JSON response with no articles
        when(mockNewsAPI.getNewsData()).thenReturn(mockJsonNode);

        // Replace the real getNewsData method with the mocked one
        newsProvider = new NewsProvider(endpoint) {
            @Override
            public JsonNode getNewsData() throws IOException, InterruptedException {
                return mockJsonNode;
            }
        };

        // Call the display method and verify the news panel components
        newsProvider.display();
        JPanel newsPanel = newsProvider.getNewsPanel();
        assertEquals(0, newsPanel.getComponentCount(), "The news panel should contain no components when no articles are found.");
    }
}

