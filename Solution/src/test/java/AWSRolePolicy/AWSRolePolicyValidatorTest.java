package AWSRolePolicy;

import static org.junit.Assert.*;

import Interfaces.JsonValidator;
import Interfaces.PolicyNameValidator;
import org.junit.Test;

public class AWSRolePolicyValidatorTest {

    @Test
    public void testValidPolicy() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertTrue(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\validJson2.json"));
    }

    @Test
    public void testInvalidJson() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\invalidJson.json"));
    }

    @Test
    public void testInvalidPolicyName() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\invalidPolicyName.json"));
    }

    @Test
    public void testWildcardResource() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\policyWithWildCard.json"));
    }
}
