package AWSRolePolicy;

import Interfaces.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AWSJsonValidator implements JsonValidator{
    private static final Logger logger = LoggerFactory.getLogger(AWSJsonValidator.class);
    private boolean isJsonNotEmpty(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }
    private boolean hasRequiredFields(JsonNode jsonNode) {
        return jsonNode.has("PolicyDocument")
                && jsonNode.has("PolicyName")
                && jsonNode.get("PolicyDocument").has("Statement");
    }
    private boolean haveFieldsAppropriateTypes(JsonNode jsonNode) {
        return jsonNode.get("PolicyDocument").isObject()
                && jsonNode.get("PolicyName").isTextual()
                && jsonNode.get("PolicyDocument").get("Statement").isArray();
    }
    @Override
    public boolean validateJson(JsonNode jsonNode, String jsonData) {
        try{
            if(!isJsonNotEmpty(jsonData)){
                throw new IllegalArgumentException("JSON data is empty or null");
            }
            if(!hasRequiredFields(jsonNode)){
                throw new IllegalArgumentException("Required fields are missing in the JSON.");
            }
            if(!haveFieldsAppropriateTypes(jsonNode)){
                throw new IllegalArgumentException("Required fields have inappropriate types.");
            }
            return true;
        }catch (IllegalArgumentException e){
            logger.error("Validation failed: " + e.getMessage());
            return false;
        }
    }
}
