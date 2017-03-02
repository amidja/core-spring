package au.amidja.core.pwd.validator.rule;

import au.amidja.core.pwd.generator.CharacterType;
import au.amidja.core.pwd.generator.Password;
import au.amidja.core.pwd.generator.ValidationErrorType;

/**
 * Rule for determining if a password contains the correct number of 
 * characters for particular CharacterType.
 *
 */
public class CharacterRule extends AbstractCharacterRule{

   /** CharacterType to use in validation */
   protected CharacterType characterType;
   
   /**
    * Creates a new character rule.
    *
    * @param  num  number of lowercase characters to enforce
    * @param  characterType  type used in validation
    */
   public CharacterRule(final CharacterType characterType){     
     this.characterType = characterType;     
   }
   
   
  /**
   * Creates a new character rule.
   *
   * @param  num  number of lowercase characters to enforce
   * @param  characterType  type used in validation
   */
  public CharacterRule(final CharacterType characterType, final int num){
    setNumberOfCharacters(num);
    this.characterType = characterType;
    
  }


  /** {@inheritDoc} */
  @Override
  public String getValidCharacters(){
    return characterType.getValidCharacters();
  }


  /** {@inheritDoc} */
  @Override
  protected int getNumberOfCharacterType(final Password password){	  
	  switch (this.characterType) {
	    case LOWERCASE:
	    	return password.getNumberOfLowercase();
	    case UPPERCASE:
	    	return password.getNumberOfUppercase();
	    case NONALPHANUMERIC:
	    	return password.getNumberOfNonAlphanumeric();
	    case DIGIT:
	    	return password.getNumberOfDigits();
	    default:
	    	return 0;	    	
	}    
  }
  
  /** {@inheritDoc} */
  @Override  
  protected ValidationErrorType getValidationErrorType() {
	  return this.characterType.getValidationErrorType();
  }
  
  @Override
  protected CharacterType getCharacterType() {
	return characterType;
  }
  
  /** {@inheritDoc} */
  @Override
  public String toString(){
    return String.format( "%s@%h::numberOfCharacters=%s",  characterType.getName(), hashCode(), numCharacters);
  }
}