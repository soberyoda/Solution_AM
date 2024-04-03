import Interfaces.JsonValidator;

public class AWSIAMRolePolicyValidator implements JsonValidator {
    @Override
    public boolean validate(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }
}
