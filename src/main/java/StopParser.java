import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StopParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getStopJsonNode(String json) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        String name = jsonNode.get("data").get("stop").get("name").asText();
        return name;
    }
}
