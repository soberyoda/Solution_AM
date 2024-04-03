package Interfaces;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonValidator {
    boolean isJsonValid(JsonNode jsonNode);
}
