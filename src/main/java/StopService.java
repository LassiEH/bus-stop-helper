public class StopService {
    private final DigitransitStopClient client;

    public StopService(DigitransitStopClient client) {
        this.client = client;
    }

    public String fetchStop(String stopId) throws Exception {
        String graphql =
                """
                {
                    stop(id: "%s") {
                      name
                      stoptimesWithoutPatterns(numberOfDepartures: 1) {
                         serviceDay
                         realtimeDeparture
                         scheduledDeparture
                      }
                    }
                }
               """.formatted(stopId);
        return client.postStopQuery(graphql);
    }
}
