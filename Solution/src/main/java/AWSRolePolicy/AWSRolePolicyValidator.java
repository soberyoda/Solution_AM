package AWSRolePolicy;

import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AWSRolePolicyValidator{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final PolicyNameValidator policyNameValidator;
    private final JsonValidator jsonValidator;
    public AWSRolePolicyValidator(PolicyNameValidator policyNameValidator, JsonValidator jsonValidator){
        this.policyNameValidator = policyNameValidator;
        this.jsonValidator = jsonValidator;
    }
    private boolean isJsonNotEmpty(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }

    private JsonNode parse(String src){
        if(!isJsonNotEmpty(src)) throw new IllegalArgumentException("JSON data is empty or null");
        try {
            return objectMapper.readTree(src);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }



}
