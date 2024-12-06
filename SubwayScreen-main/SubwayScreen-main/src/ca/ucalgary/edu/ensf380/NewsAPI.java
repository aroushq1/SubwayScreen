package ca.ucalgary.edu.ensf380;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The NewsAPI class extends DataRetriever and
 * interacts with news APIs. This class is designed to retrieve news data
 * from a specified endpoint.
 * 
 */
public class NewsAPI extends DataRetriever { 

    private final ObjectMapper objectMapper;
    private final String keyword;
    private final HttpClient httpClient;

    /**
     * Constructs a NewsAPI instance with the specified endpoint and keyword.
     *
     * @param ENDPOINT The endpoint URL for the news data API.
     * @param keyword The keyword to filter news articles.
     */
    public NewsAPI(String ENDPOINT, String keyword) {
        super(ENDPOINT);
        this.objectMapper = new ObjectMapper(); // Instantiate ObjectMapper
        this.keyword = keyword;
        this.httpClient = HttpClient.newHttpClient(); // Default HttpClient
    }

    /**
     * Constructs a NewsAPI instance with the specified endpoint, keyword, and HttpClient.
     *
     * @param ENDPOINT The endpoint URL for the news data API.
     * @param keyword The keyword to filter news articles.
     * @param httpClient The HttpClient to use for making requests.
     */
    public NewsAPI(String ENDPOINT, String keyword, HttpClient httpClient) {
        super(ENDPOINT);
        this.objectMapper = new ObjectMapper(); // Instantiate ObjectMapper
        this.keyword = keyword;
        this.httpClient = httpClient;
    }

    /**
     * Retrieves and parses the news data from the API.
     * 
     * @return JsonNode containing the news data
     * @throws IOException if there is an issue with data retrieval or parsing
     * @throws InterruptedException if interruption occurs
     */
    public JsonNode getNewsData() throws IOException, InterruptedException {
        String jsonResponse = fetchDataFromAPI();
        return objectMapper.readTree(jsonResponse);
    }

    /**
     * Filters news articles based on the keyword.
     * 
     * @param articles JsonNode containing the articles to be filtered
     * @return List of filtered articles as JsonNode
     */
    public List<JsonNode> filterArticles(JsonNode articles) {
        List<JsonNode> filteredArticles = new ArrayList<>();
        for (JsonNode article : articles) {
            String title = article.get("title").asText().toLowerCase();
            String description = article.get("description").asText().toLowerCase();
            if (title.contains(keyword.toLowerCase()) || description.contains(keyword.toLowerCase())) {
                filteredArticles.add(article);
            }
        }
        return filteredArticles;
    }

    /**
     * method to print out news headlines.
     */
    public void printNewsHeadlines() {
        try {
            JsonNode response = getNewsData();
            JsonNode articles = response.get("articles");

            List<JsonNode> filteredArticles = filterArticles(articles);

            for (JsonNode article : filteredArticles) {
                String title = article.get("title").asText();
                String description = article.get("description").asText();
                String url = article.get("url").asText();
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("URL: " + url);
                System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches data from the API.
     * 
     * @return The JSON response from the API as a String.
     * @throws IOException if there is an issue with data retrieval.
     * @throws InterruptedException if interruption occurs.
     */
    public String fetchDataFromAPI() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(getENDPOINT()))
            .header("Accept", "application/json")
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (!response.body().startsWith("{") && !response.body().startsWith("[")) {
            throw new IOException("Invalid JSON response");
        }

        return response.body();
    }
}
