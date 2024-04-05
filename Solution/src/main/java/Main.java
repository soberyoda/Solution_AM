import AWSRolePolicy.AWSRolePolicyJsonValidator;
import AWSRolePolicy.AWSRolePolicyNameValidator;
import AWSRolePolicy.AWSRolePolicyValidator;
import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;

public class Main {
    public static void main(String...args){
        String pathToJson = "Solution/src/main/resources/validJson.json";
        JsonValidator jsonValidator = new AWSRolePolicyJsonValidator();
        PolicyNameValidator policyNameValidator = new AWSRolePolicyNameValidator();
        AWSRolePolicyValidator awsRolePolicyValidator = new AWSRolePolicyValidator(jsonValidator, policyNameValidator);
        System.out.println(awsRolePolicyValidator.validate(pathToJson));
    }
}
