import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AWSRolePolicyValidator{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final PolicyNameValidator policyNameValidator;
    private final JsonValidator jsonValidator;
    public AWSRolePolicyValidator(PolicyNameValidator policyNameValidator, JsonValidator jsonValidator){
        this.policyNameValidator = policyNameValidator;
        this.jsonValidator = jsonValidator;
    }



}
