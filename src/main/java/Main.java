public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("DIGITRANSIT_API_KEY");
        if (apiKey == null) {
            throw new Exception("DIGITRANSIT_API_KEY environment variable not set");
        }

        DigitransitStopClient digitransitStopClient = new DigitransitStopClient(apiKey);
        StopService stopService = new StopService(digitransitStopClient);
        String text = stopService.fetchStop();
        System.out.println(text);
    }
}
