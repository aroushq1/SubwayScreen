package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Unit tests for the NewsAPI class.
 */
public class NewsAPITest {

    private NewsAPI newsAPI;
    private HttpClient mockClient;
    private HttpResponse<String> mockResponse;
    private final String endpoint = "http://example.com/api/news";
    private final String keyword = "test";

    /**
     * Sets up a new NewsAPI object and mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        mockClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
        newsAPI = new NewsAPI(endpoint, keyword, mockClient);
    }

    /**
     * Tests the getNewsData method.
     * 
     * @throws Exception if there is an issue with data retrieval or parsing
     */
    @Test
    public void testGetNewsData() throws Exception {
        String jsonResponse = "{\"articles\": [{\"title\": \"Test Article\", \"description\": \"This is a test.\"}]}";
        when(mockResponse.body()).thenReturn(jsonResponse);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        JsonNode newsData = newsAPI.getNewsData();
        assertNotNull(newsData, "The news data should not be null.");
        assertEquals("Test Article", newsData.get("articles").get(0).get("title").asText(), "The title should be 'Test Article'.");
    }

    /**
     * Tests the filterArticles method.
     * 
     * @throws IOException if there is an issue with parsing the JSON data
     */
    @Test
    public void testFilterArticles() throws IOException {
        String jsonResponse = "{\"articles\": [" +
                "{\"title\": \"Test Article\", \"description\": \"This is a test.\"}," +
                "{\"title\": \"Other Article\", \"description\": \"Not relevant.\"}" +
                "]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode articlesNode = objectMapper.readTree(jsonResponse).get("articles");

        List<JsonNode> filteredArticles = newsAPI.filterArticles(articlesNode);
        assertEquals(1, filteredArticles.size(), "There should be one filtered article.");
        assertEquals("Test Article", filteredArticles.get(0).get("title").asText(), "The filtered article's title should be 'Test Article'.");
    }

    /**
     * Tests the fetchDataFromAPI method.
     * 
     * @throws Exception if there is an issue with data retrieval
     */
    @Test
    public void testFetchDataFromAPI() throws Exception {
        String jsonResponse = "{\"articles\": [{\"title\": \"Test Article\", \"description\": \"This is a test.\"}]}";
        when(mockResponse.body()).thenReturn(jsonResponse);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        String response = newsAPI.fetchDataFromAPI();
        assertNotNull(response, "The response should not be null.");
        assertTrue(response.contains("Test Article"), "The response should contain 'Test Article'.");
    }
}