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

    public boolean validate(String src){
        if(!jsonValidator.isJsonValid(parse(src))) return false;
        JsonNode node = parse(src);
        assert node != null;
        if(!policyNameValidator.isValidPolicyName(node.get("PolicyName").asText())) return false;
        JsonNode statements = node.path("PolicyDocument").path("Statement");
        for(JsonNode statement : statements){
            if(!statement.has("Resource")){
                return false;
            }
            String resource = statement.path("Resource").asText();
            if(resource.equals("*")) return false;
        }
        return true;
    }



}
