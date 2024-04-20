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
    public boolean validatePolicyDocument(JsonNode jsonNode){
        String policyDocument = jsonNode.get("PolicyDocument").toString();
        System.out.println("Policy Document: " + policyDocument);

        String allowedCharsPattern = "[\\u0009\\u000A\\u000D\\u0020-\\u00FF]+";
        Pattern pattern = Pattern.compile(allowedCharsPattern);
        Matcher matcher = pattern.matcher(policyDocument);
        boolean patternMatch = matcher.matches();
        boolean notEmpty = !policyDocument.isEmpty();
        boolean withinLength = policyDocument.length() <= 131072;
        boolean isObject = jsonNode.get("PolicyDocument").isObject();
        return patternMatch && notEmpty && withinLength && isObject;
    }
    public boolean validateStatementEffectsAndActions(JsonNode statementArray) {
        for (JsonNode statement : statementArray) {
            if (!statement.has("Effect") || !statement.has("Action")) {
                return false;
            }
            String effect = statement.get("Effect").asText();
            if (!effect.equals("Allow") && !effect.equals("Deny")) {
                return false;
            }
        }
        return true;
    }
    public boolean validateVersionFormat(JsonNode jsonNode){
        String version = jsonNode.get("PolicyDocument").get("Version").asText();
        String allowedPattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(allowedPattern);
        Matcher matcher = pattern.matcher(version);
        if(!matcher.matches()){
            System.out.println("matcher fail");
        }
        return matcher.matches();
    }
    public boolean haveFieldsAppropriateTypes(JsonNode jsonNode) {
        return validatePolicyDocument(jsonNode)
                && jsonNode.get("PolicyName").isTextual()
                && jsonNode.get("PolicyDocument").get("Statement").isArray()
                && !jsonNode.get("PolicyDocument").get("Statement").isEmpty()
                && validateStatementEffectsAndActions(jsonNode.get("PolicyDocument").get("Statement"))
                && validateVersionFormat(jsonNode);
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
