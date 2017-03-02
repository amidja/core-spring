package au.amidja.core.pwd.validator.rule;


/**
 * Interface for rules implementing character enforcement.
 */
public interface PasswordCharacterRule extends PasswordRule{

	
  /**
   * Sets the upper bounder for the number of characters allowed.
   *
   * @param  n  the number of characters allowed where n > 0
   */
  void setMaxNumberOfCharacters(int n);

  
  /**
   * Returns the upper bounder for the number of characters allowed.
   */
  int getMaxNumberOfCharacters();
	

  /**
   * Sets the number of characters to require in a password.
   *
   * @param  n  number of characters to require where n > 0
   */
  void setNumberOfCharacters(int n);


  /**
   * Returns the number of characters which must exist in order for a password
   * to meet the requirements of this rule.
   *
   * @return  number of characters to require
   */
  int getNumberOfCharacters();


  /**
   * Returns the characters that are considered valid for this rule.
   *
   * @return  valid characters
   */
  String getValidCharacters();
  
  /**
   * Sets the character type to be used 
   * 
   * @param characterType
   */
  //void setCharacterType(CharacterType characterType);
  
  /**
   * Returns the character type used in validation
   * 
   * @param characterType
   * @return
   */
  //CharacterType getCharacterType();
}
