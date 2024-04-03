import Interfaces.PolicyNameValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AWSIAMRolePolicyNameValidator implements PolicyNameValidator {
    @Override
    public boolean isValidPolicyName(String policyName) {
        if(policyName == null || policyName.isEmpty()){
            return false;
        }
        String regex = "[\\w+=,.@-]+";
        int maxLen = 128;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(policyName);
        return matcher.matches() && policyName.length() <= maxLen;
    }
}
