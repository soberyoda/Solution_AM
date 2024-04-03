import Interfaces.PolicyNameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AWSRolePolicyNameValidator implements PolicyNameValidator {
    private static final Logger logger = LoggerFactory.getLogger(AWSRolePolicyNameValidator.class);
    @Override
    public boolean isValidPolicyName(String policyName) {
        try {
            if (policyName == null || policyName.isEmpty()) {
                throw new IllegalArgumentException("Policy name is null or empty");
            }
            String regex = "[\\w+=,.@-]+";
            int maxLen = 128;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(policyName);
            if (!matcher.matches() || policyName.length() > maxLen) {
                throw new IllegalArgumentException("Policy name does not match the required pattern or exceeds maximum length.");
            }
            return true;
        }catch (IllegalArgumentException e){
            logger.error("Policy name validation failed" + e.getMessage());
            return false;
        }
    }
}
