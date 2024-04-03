package AWSRolePolicy;

import Interfaces.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;



public class AWSRolePolicyJsonValidator implements JsonValidator{
    private boolean hasRequiredFields(JsonNode jsonNode) {
        return jsonNode.has("PolicyDocument")
                && jsonNode.has("PolicyName")
                && jsonNode.get("PolicyDocument").has("Statement");
    }
    private boolean haveFieldsAppropriateTypes(JsonNode jsonNode) {
        return jsonNode.get("PolicyDocument").isObject()
                && jsonNode.get("PolicyName").isTextual()
                && jsonNode.get("PolicyDocument").get("Statement").isArray()
                && jsonNode.get("PolicyDocument").get("Statement").size() != 0;
    }
    @Override
    public boolean isJsonValid(JsonNode jsonNode) {
        try{
            if(!hasRequiredFields(jsonNode)){
                throw new IllegalArgumentException("Required fields are missing in the JSON.");
            }
            if(!haveFieldsAppropriateTypes(jsonNode)){
                throw new IllegalArgumentException("Required fields have inappropriate types.");
            }
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
