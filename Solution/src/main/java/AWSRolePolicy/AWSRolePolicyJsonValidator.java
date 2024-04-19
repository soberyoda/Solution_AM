package AWSRolePolicy;

import Interfaces.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AWSRolePolicyJsonValidator implements JsonValidator{
    public boolean hasRequiredFields(JsonNode jsonNode) {
        return jsonNode.has("PolicyDocument")
                && jsonNode.has("PolicyName")
                && jsonNode.get("PolicyDocument").has("Statement");
    }
    public boolean validateStatementEffects(JsonNode statementArray) {
        for (JsonNode statement : statementArray) {
            if (!statement.has("Effect")) {
                return false;
            }
            String effect = statement.get("Effect").asText();
            if (!effect.equals("Allow") && !effect.equals("Deny")) {
                return false;
            }
        }
        return true;
    }
    public boolean validatePolicyDocument(JsonNode jsonNode){
        String policyDocument = jsonNode.get("PolicyDocument").toString();
        System.out.println(policyDocument);
        System.out.println("Policy Document: " + policyDocument);

        String allowedCharsPattern = "[\\u0009\\u000A\\u000D\\u0020-\\u00FF]+";
        Pattern pattern = Pattern.compile(allowedCharsPattern);
        Matcher matcher = pattern.matcher(policyDocument);
        boolean patternMatch = matcher.matches();
//        System.out.println("Pattern Match: " + patternMatch);

        boolean notEmpty = !policyDocument.isEmpty();
//        System.out.println("Not Empty: " + notEmpty);

        boolean withinLength = policyDocument.length() <= 131072;
//        System.out.println("Within Length: " + withinLength);

        boolean isObject = jsonNode.get("PolicyDocument").isObject();
//        System.out.println("Is Object: " + isObject);

        return patternMatch && notEmpty && withinLength && isObject;
    }
    public boolean haveFieldsAppropriateTypes(JsonNode jsonNode) {
        return validatePolicyDocument(jsonNode)
                && jsonNode.get("PolicyName").isTextual()
                && jsonNode.get("PolicyDocument").get("Statement").isArray()
                && !jsonNode.get("PolicyDocument").get("Statement").isEmpty()
                && validateStatementEffects(jsonNode.get("PolicyDocument").get("Statement"));
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
