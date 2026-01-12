public class StopService {
    private final DigitransitStopClient client;

    public StopService(DigitransitStopClient client) {
        this.client = client;
    }

    public String fetchStop() throws Exception {
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
        return client.postStopQuery(graphql);
    }
}
