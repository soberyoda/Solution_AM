package AWSRolePolicy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AWSRolePolicyJsonValidatorTest {
    @Test
    void testMultipleValidCases(){
        List<String> multipleTestCases = JsonTestCasesGenerator.generateValidTestCases();

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