package AWSRolePolicy;

import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import Json.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

public class AWSRolePolicyValidator{
    private final PolicyNameValidator policyNameValidator;
    private final JsonValidator jsonValidator;
    public AWSRolePolicyValidator(JsonValidator jsonValidator, PolicyNameValidator policyNameValidator){
        this.jsonValidator = jsonValidator;
        this.policyNameValidator = policyNameValidator;
    }
    public boolean validate(String src){
        if(!jsonValidator.isJsonValid(JsonParser.parse(src))) return false;
        JsonNode node = JsonParser.parse(src);
        assert node != null;
        if(!policyNameValidator.isValidPolicyName(node.get("PolicyName").asText())) return false;
        JsonNode statements = node.path("PolicyDocument").path("Statement");
        for(JsonNode statement : statements){
            if(!statement.has("Resource")){
                return false;
            }
            String resource = statement.path("Resource").asText().trim(); // single asterisk
            if(resource.equals("*")) return false;
        }
        return true;
    }

}
