import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DigitransitStopClient {

    private final String apiKey;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public DigitransitStopClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String postStopQuery(String graphql) throws Exception {

        var payload = java.util.Map.of("query", graphql);
        String body = MAPPER.writeValueAsString(payload);

        URI uri = URI.create("https://api.digitransit.fi/routing/v2/waltti/gtfs/v1");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .header("digitransit-subscription-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }
}
