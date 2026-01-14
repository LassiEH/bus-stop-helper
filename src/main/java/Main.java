public class Main {
    final static String tienristi = "LINKKI:207725";
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("DIGITRANSIT_API_KEY");
        if (apiKey == null) {
            throw new Exception("DIGITRANSIT_API_KEY environment variable not set");
        }

        DigitransitStopClient digitransitStopClient = new DigitransitStopClient(apiKey);
        StopService stopService = new StopService(digitransitStopClient);
        StopParser stopParser = new StopParser();
        String text = stopService.fetchStop(tienristi);
        System.out.println(text);

        String parsed = stopParser.getStopJsonNode(text);
        System.out.println(parsed);
    }
}
