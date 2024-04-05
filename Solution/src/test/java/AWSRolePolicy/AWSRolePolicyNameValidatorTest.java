package AWSRolePolicy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AWSRolePolicyNameValidatorTest {
    @Test
    void testJsonPolicyNameIsValid(){
        List<String> multipleTestCases = JsonTestCasesGenerator.generateValidTestCases();

        for(String jsonString: multipleTestCases){
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(jsonString);
            }catch (Exception e){
                fail(e.getMessage());
            }
            AWSRolePolicyNameValidator validator = new AWSRolePolicyNameValidator();
            boolean isValid = validator.isValidPolicyName(jsonNode.get("PolicyName").asText());
            assertTrue(isValid);
        }
    }
    @Test
    void testJsonPolicyNameIsInvalid(){
        List<String> multipleTestCases = JsonTestCasesGenerator.generateTestCasesHaveInvalidPolicyName();

        for(String jsonString: multipleTestCases){
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try{
                jsonNode = objectMapper.readTree(jsonString);
            }catch (Exception e){
                fail(e.getMessage());
            }
            AWSRolePolicyNameValidator validator = new AWSRolePolicyNameValidator();
            boolean isValid = validator.isValidPolicyName(jsonNode.get("PolicyName").asText());
            assertFalse(isValid);
        }
    }

}