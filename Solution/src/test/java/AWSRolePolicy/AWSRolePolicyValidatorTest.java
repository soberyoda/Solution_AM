package AWSRolePolicy;

import static org.junit.Assert.*;

import org.junit.Test;
/*
    YOU HAVE TO CHANGE PATHS !
*/
public class AWSRolePolicyValidatorTest {

    @Test
    public void testValidPolicy() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertTrue(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\validJson2.json"));// <-- You have to change Path!
    }

    @Test
    public void testInvalidJson() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\invalidJson.json"));// <-- You have to change Path!
    }

    @Test
    public void testInvalidPolicyName() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\invalidPolicyName.json"));// <-- You have to change Path!
    }

    @Test
    public void testWildcardResource() {
        AWSRolePolicyValidator validator = new AWSRolePolicyValidator(new AWSRolePolicyJsonValidator(), new AWSRolePolicyNameValidator());
        assertFalse(validator.validate("D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\policyWithWildCard.json"));// <-- You have to change Path!
    }
}
