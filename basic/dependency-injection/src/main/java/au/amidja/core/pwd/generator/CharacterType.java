package au.amidja.core.pwd.generator;

public enum CharacterType implements CharacterValidationType {
	LOWERCASE("lowercase", "abcdefghijklmnopqrstuvwxyz", ValidationErrorType.LOWERCASE_MISSING),
	UPPERCASE("uppercase", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", ValidationErrorType.UPPERCASE_MISSING),
	DIGIT("digit", "0123456789", ValidationErrorType.UPPERCASE_MISSING),
	NONALPHANUMERIC("non-alphanumeric", "`~!@#$%^&*()-_=+[{]}\\|;:'\"<,>./?", ValidationErrorType.NONALPHANUMERIC_MISSING);
		
	private  String name;
	
	private String validCharacters;
	
	private ValidationErrorType errorType;  
	
	private CharacterType(String name, String validCharacters, ValidationErrorType errorType) {
		this.name = name;
		this.validCharacters = validCharacters;
		this.errorType = errorType;
	}
	
	public static String getCharacters(CharacterType[] charTypes){
		
		if (charTypes == null || charTypes.length ==0 ){
			throw new IllegalArgumentException("charTypes cannot be empty or null");
		}
		
		StringBuffer returnValueBuffer =new StringBuffer();
		for (CharacterType charType : charTypes){
			returnValueBuffer.append(charType.getValidCharacters());
		}
		return returnValueBuffer.toString();
	}
	
	public static String getRandomizedCharacters(CharacterType[] charTypes){
		StringBuffer randomizedChars = new StringBuffer(getCharacters(charTypes));

		int currLen = randomizedChars.length();
        
        StringBuffer returnRandomized = new StringBuffer(randomizedChars.length());
        for (int inx = 0; inx < currLen; inx++) {
            int charIndex = (int) (Math.random() * (randomizedChars.length() - 1));
            returnRandomized.append(randomizedChars.charAt(charIndex));            
            randomizedChars.deleteCharAt(charIndex);
        }
        
        return returnRandomized.toString();               
	}
	
	public char getRandomChar(){
		int charIndex = (int) (Math.random() * (validCharacters.length() - 1));
		return this.getValidCharacters().charAt(charIndex);
	}
	
	@Override
	public String getName() {return name;}
	
	@Override
	public String getValidCharacters() {return validCharacters;}
	
	@Override		
	public ValidationErrorType getValidationErrorType() { return errorType; }
}