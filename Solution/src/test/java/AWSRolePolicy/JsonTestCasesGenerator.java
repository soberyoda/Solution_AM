package AWSRolePolicy;

import java.util.List;
import java.util.ArrayList;

public class JsonTestCasesGenerator {
    public static List<String> generateValidTestCases(){
        List<String> testCases = new ArrayList<>();
        testCases.add("{\"PolicyName\":\"TestPolicy\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"*\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy2\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"FF\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy3\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"res\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy4\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"FF\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy5\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Deny\",\"Resource\":\"FF\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy4\",\"PolicyDocument\":{\"Statement\":[{\"Effect\":\"Allow\",\"Resource\":\"FF\"}]}}");

        return testCases;
    }
    public static List<String> generateTestCasesMissingRequiredFields(){
        List<String> testCases = new ArrayList<>();
        testCases.add("\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"*\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy2\",\"\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"***\"}]}}");
        testCases.add("{\"PolicyNe\":\"TestPolicy3\",\"PolicyDoment\":{\"Statement\": null}}");
        testCases.add("{\"\":\"TestPolicy4\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\"}]}}");
        testCases.add("{\"\":\"\",\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\"}]}}");
        testCases.add("{\"PolicyName\":\"\",\"\":{\"Statement\":[{\"Effect\":\"Allow\"}]}}");

        return testCases;
    }
    public static List<String> generateTestCasesHaveInvalidFieldTypes(){
        List<String> testCases = new ArrayList<>();
        testCases.add("{\"PolicyName\": [] ,\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"*\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy2\",\"PolicyDocument\":[{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"***\"}]}]}");
        testCases.add("{\"PolicyName\":\"TestPolicy3\",\"PolicyDocument\":{\"Statement\":[]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy4\",\"PolicyDocument\":{\"Statement\": null }}");
        testCases.add("{\"PolicyName\": {} ,\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\"}]}}");
        testCases.add("{\"PolicyName\":\"TestPolicy4\",\"PolicyDocument\":{\"Statement\": {} }}");
        return testCases;
    }
    public static List<String> generateTestCasesHaveInvalidPolicyName(){
        List<String> testCases = new ArrayList<>();
        testCases.add("{\"PolicyName\": [] ,\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"*\"}]}}");
        testCases.add("{\"PolicyName\":\"''\",\"PolicyDocument\":[{\"Statement\":[{\"Action\":\"s3:GetObject\",\"Effect\":\"Allow\",\"Resource\":\"***\"}]}]}");
        testCases.add("{\"PolicyName\":\"TestPolicy3!\",\"PolicyDocument\":{\"Statement\":[]}}");
        testCases.add("{\"PolicyName\":\"##\",\"PolicyDocument\":{\"Statement\": null }}");
        testCases.add("{\"PolicyName\": {} ,\"PolicyDocument\":{\"Statement\":[{\"Action\":\"s3:GetObject\"}]}}");
        testCases.add("{\"PolicyName\":\"A#A!\",\"PolicyDocument\":{\"Statement\": {} }}");
        return testCases;
    }
}
