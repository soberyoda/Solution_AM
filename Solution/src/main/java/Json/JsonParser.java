package Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean isJsonNotEmpty(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }
    public static JsonNode parse(String src){
        if(!isJsonNotEmpty(src)) throw new IllegalArgumentException("JSON data is empty or null");
        try {
            return objectMapper.readTree(src);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }
}
