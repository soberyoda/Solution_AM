package AWSRolePolicy;

import Json.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static AWSRolePolicy.JsonTestCasesGenerator.generateValidTestCases;
import static org.junit.jupiter.api.Assertions.*;

class AWSRolePolicyJsonValidatorTest {
    @Test
    public void testValidatePolicyDocument() {
        List<String> testCases = generateValidTestCases();
        AWSRolePolicyJsonValidator validator = new AWSRolePolicyJsonValidator();
        for (String testCase : testCases) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(testCase);
            }catch (Exception e){
                fail(e.getMessage());
            }
            boolean isValid = validator.validatePolicyDocument(jsonNode);
            System.out.println("Test case: " + testCase);
            System.out.println("Validation result: " + isValid);
            assertTrue(isValid);
        }
    }
    @Test
    void testMultipleValidCases(){
        List<String> multipleTestCases = generateValidTestCases();

        for(String jsonString: multipleTestCases){
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(jsonString);
            }catch (Exception e){
                fail(e.getMessage());
            }
            AWSRolePolicyJsonValidator validator = new AWSRolePolicyJsonValidator();
            boolean isValid = validator.isJsonValid(jsonNode);
            assertTrue(isValid);
        }
    }
    @Test
    void testJsonMissingRequiredFields(){
        List<String> multipleTestCases = JsonTestCasesGenerator.generateTestCasesMissingRequiredFields();

        for(String jsonString: multipleTestCases){
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(jsonString);
            }catch (Exception e){
                fail(e.getMessage());
            }
            AWSRolePolicyJsonValidator validator = new AWSRolePolicyJsonValidator();
            boolean isValid = validator.hasRequiredFields(jsonNode);
            assertFalse(isValid);
        }
    }
    @Test
    void testInvalidFieldTypes(){
        List<String> multipleTestCases = JsonTestCasesGenerator.generateTestCasesHaveInvalidFieldTypes();

        for(String jsonString: multipleTestCases){
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(jsonString);
            }catch (Exception e){
                fail(e.getMessage());
            }
            AWSRolePolicyJsonValidator validator = new AWSRolePolicyJsonValidator();
            boolean isValid = validator.haveFieldsAppropriateTypes(jsonNode);
            assertFalse(isValid);
        }
    }

}