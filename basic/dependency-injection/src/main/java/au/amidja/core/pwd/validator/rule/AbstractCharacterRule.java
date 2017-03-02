package au.amidja.core.pwd.validator.rule;

import java.util.LinkedHashMap;
import java.util.Map;

import au.amidja.core.pwd.generator.CharacterType;
import au.amidja.core.pwd.generator.Password;
import au.amidja.core.pwd.generator.ValidationErrorType;


/**
 * Provides common implementation for password character rules.
 */
public abstract class AbstractCharacterRule implements PasswordCharacterRule{

  /** Number of characters to require. Default value is 1. */
  protected int numCharacters = 1;
  
  /** Number of characters allowed. Default value is Integer.MAX_VALUE */
  protected int maxNumCharacters = Integer.MAX_VALUE;

  /** {@inheritDoc} */
  @Override
  public void setNumberOfCharacters(final int n){
    if (n > 0 && n <= maxNumCharacters) {
      numCharacters = n;
    } else {
      throw new IllegalArgumentException("argument must be greater than zero");
    }
  }
  
  /** {@inheritDoc} */
  @Override
  public void setMaxNumberOfCharacters(int n){
	  if (n > 0 && n >= numCharacters) {
	      maxNumCharacters = n;
	    } else {
	      throw new IllegalArgumentException("argument must be greater than zero");
	    }
  }
  
  
  /** {@inheritDoc} */
  @Override
  public int getNumberOfCharacters(){
    return numCharacters;
  }  
  
  
  /** {@inheritDoc} */
  @Override
  public int getMaxNumberOfCharacters(){
    return numCharacters;
  }  
  
  /**
   * Returns the number of the type of characters in the supplied password for
   * the implementing class.
   *
   * @param  password  to get character count from
   *
   * @return  number of characters
   */
  protected abstract int getNumberOfCharacterType(final Password password);


  
  /**
   * Returns the ValidationErrorType associated with a Character Rule 
   *
   * @return  validation error type
   */
  protected abstract ValidationErrorType getValidationErrorType();

  
  /**
   * Returns the type of character managed by this rule.
   *
   * @return  name of a character type, e.g. "digits."
   */
  protected abstract CharacterType getCharacterType();


  /** {@inheritDoc} */
  @Override
  public RuleResult validate(final Password password){
    if (getNumberOfCharacterType(password) >= numCharacters && getNumberOfCharacterType(password) <= maxNumCharacters) {
      return new RuleResult(true);
    } else {
      RuleResultDetail resultDetail= new RuleResultDetail(getValidationErrorType(), createRuleResultDetailParameters(password));
      return new RuleResult(false, resultDetail);
    }
  }


  /**
   * Creates the parameter data for the rule result detail.
   *
   * @param  p  password
   *
   * @return  map of parameter name to value
   */
  protected Map<String, ?> createRuleResultDetailParameters(final Password p){
    final Map<String, Object> m = new LinkedHashMap<String, Object>();
    m.put("minimumRequired", numCharacters);
    m.put("characterType", getCharacterType());
    m.put("validCharacterCount", getNumberOfCharacterType(p));
    m.put("validCharacters", getValidCharacters());
    return m;
  }
}