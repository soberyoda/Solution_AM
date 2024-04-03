import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AWSIAMRolePolicyValidator implements JsonValidator {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final PolicyNameValidator policyNameValidator;
    public AWSIAMRolePolicyValidator(PolicyNameValidator policyNameValidator){
        this.policyNameValidator = policyNameValidator;
    }
    @Override
    public boolean validateJson(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }

}
