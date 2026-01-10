import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StopFetcher {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("DIGITRANSIT_API_KEY");
        System.out.println(apiKey);

        String graphql =
                """
                {
                    stop(id: "LINKKI:207725") {
                      name
                      stoptimesWithoutPatterns(numberOfDepartures: 5) {
                         serviceDay
                         realtimeDeparture
                         scheduledDeparture
                      }
                    }
                }
               """;

        var payload = java.util.Map.of("query", graphql);
        String body = MAPPER.writeValueAsString(payload);

        URI uri = URI.create("https://api.digitransit.fi/routing/v2/waltti/gtfs/v1");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .header("digitransit-subscription-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);
        System.out.println(response.body());

    }
}
