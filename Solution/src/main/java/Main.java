import AWSRolePolicy.AWSRolePolicyJsonValidator;
import AWSRolePolicy.AWSRolePolicyNameValidator;
import AWSRolePolicy.AWSRolePolicyValidator;
import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import Json.JsonToStringFromFile;

public class Main {
    public static void main(String...args){
        String content = JsonToStringFromFile.readFile("Solution/src/main/resources/validJson.json");
        JsonValidator jsonValidator = new AWSRolePolicyJsonValidator();
        PolicyNameValidator policyNameValidator = new AWSRolePolicyNameValidator();
        AWSRolePolicyValidator awsRolePolicyValidator = new AWSRolePolicyValidator(policyNameValidator, jsonValidator);
        System.out.println(awsRolePolicyValidator.validate(content));
    }
}
