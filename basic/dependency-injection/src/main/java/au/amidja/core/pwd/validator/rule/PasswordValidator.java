package au.amidja.core.pwd.validator.rule;

import java.util.List;

import au.amidja.core.pwd.generator.Password;

public class PasswordValidator implements PasswordRule{

  private final List<PasswordRule> passwordRules;

  /**
   * Creates a new password validator
   *
   * @param  rules  to validate
   */
  public PasswordValidator(final List<PasswordRule> rules){
    this.passwordRules = rules;
  }

  /**
   * Validates the supplied password data against the rules in this validator.
   *
   * @param  password  to validate
   *
   * @return  rule result
   */
  @Override
public RuleResult validate(final Password password){
    final RuleResult result = new RuleResult(true);
    for (final PasswordRule rule : this.passwordRules) {
      final RuleResult rr = rule.validate(password);
      if (!rr.isValid()) {
        result.setValid(false);
        result.getDetails().addAll(rr.getDetails());
        return result;
      }
    }
    return result;
  }

  /**
   * Validates the supplied password data against the rules in this validator.
   *
   * @param  password  to validate
   * @return  rule result
   */
  public RuleResult validateAgainstAllRules(final Password password){
	  final RuleResult finalResult = new RuleResult(true);
	  for (final PasswordRule rule : this.passwordRules) {
	      final RuleResult ruleResult = rule.validate(password);
	      if (!ruleResult.isValid()) {
	    		finalResult.setValid(false);
	            finalResult.getDetails().addAll(ruleResult.getDetails());
	      }
	    }
	    return finalResult;
  }

  /**
   * Validates the supplied password data against the rules in this validator.
   *
   * @param  password  to validate
   * @param  allowedToFail  number of rules that can fail
   * @return  rule result
   */
  public RuleResult validate(final Password password, final int allowedToFail){
    final RuleResult result = new RuleResult(true);
    int noFailed = 0;

    for (final PasswordRule rule : this.passwordRules) {
      final RuleResult rr = rule.validate(password);
      if (!rr.isValid()) {
    	if (noFailed >= allowedToFail){
    		result.setValid(false);
            result.getDetails().addAll(rr.getDetails());
            return result;
    	}
    	noFailed++;
      }
    }
    return result;
  }
}