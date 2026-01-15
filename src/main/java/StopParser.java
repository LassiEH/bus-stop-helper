import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.Instant;


public class StopParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertTime(long epochSecond) {
        long test_date = 1768428000;
        int test_time = 66363;
        Instant ins = Instant.ofEpochSecond(epochSecond);
        System.out.println(ins);
        LocalDateTime aa = LocalDateTime.ofInstant(ins, ZoneId.systemDefault());
        //Date today = new Date(test_date);
        System.out.println(aa);


        return aa.toString();
    }

    public long fetchDeparture(JsonNode node) {
        JsonNode stopTimeData = node.get("data").get("stop").get("stoptimesWithoutPatterns");
        long serviceDay = stopTimeData.get(0).get("serviceDay").asLong();
        int secondsToActualDeparture = stopTimeData.get(0).get("realtimeDeparture").asInt();
        return (serviceDay + secondsToActualDeparture);
    }

    public JsonNode getStopJsonNode(String json) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        String name = jsonNode.get("data").get("stop").get("name").asText();
        return jsonNode;
    }
}
