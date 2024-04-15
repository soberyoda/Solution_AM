package AWSRolePolicy;

import Interfaces.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;



public class AWSRolePolicyJsonValidator implements JsonValidator{
    public boolean hasRequiredFields(JsonNode jsonNode) {
        return jsonNode.has("PolicyDocument")
                && jsonNode.has("PolicyName")
                && jsonNode.get("PolicyDocument").has("Statement");
    }
    public boolean haveFieldsAppropriateTypes(JsonNode jsonNode) {
        return jsonNode.get("PolicyDocument").isObject()
                && jsonNode.get("PolicyName").isTextual()
                && jsonNode.get("PolicyDocument").get("Statement").isArray()
                && !jsonNode.get("PolicyDocument").get("Statement").isEmpty();
//                && jsonNode.get("PolicyDocument").get("Statement").get(0).isObject();
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
            e.printStackTrace();
            return false;
        }
    }
}
