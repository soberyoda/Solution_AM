package Interfaces;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonValidator {
    boolean validateJson(JsonNode jsonNode, String jsonData);
}
