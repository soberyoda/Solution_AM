import AWSRolePolicy.AWSRolePolicyJsonValidator;
import AWSRolePolicy.AWSRolePolicyNameValidator;
import AWSRolePolicy.AWSRolePolicyValidator;
import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;

public class Main {
    public static void main(String...args){
        String pathToJson = args[0]; // <-- OR PATH TO YOUR JSON FILE
        JsonValidator jsonValidator = new AWSRolePolicyJsonValidator();
        PolicyNameValidator policyNameValidator = new AWSRolePolicyNameValidator();
        AWSRolePolicyValidator awsRolePolicyValidator = new AWSRolePolicyValidator(jsonValidator, policyNameValidator);
        System.out.println(awsRolePolicyValidator.validate(pathToJson));
    }
}
