package au.amidja.core.pwd.generator;

public class PasswordGenerator {


    private boolean allowSpecialCharacters = false ;
    
    private static final int maxspecialCharacters = 1;

    private static String specialCharacters = "!@$%*-_+:";
    
    public PasswordGenerator() {
		super();	
	}
    
    public PasswordGenerator(boolean withSpecialChars) {
		super();
		this.allowSpecialCharacters=withSpecialChars;
	}

        
    
    
    public String getRandomPassword(int randomPwdLength) {
    	
        StringBuffer returnPwd = new StringBuffer(randomPwdLength);       

        try {
        	CharacterType[] charTypes = {CharacterType.DIGIT, CharacterType.LOWERCASE, CharacterType.UPPERCASE};        	
            StringBuffer pwdCharSet = getCharacters(charTypes);
            
            for (int inx = 0; inx < randomPwdLength; inx++) {             
            	int selChar = (int) (Math.random() * (pwdCharSet.length() - 1));
            	returnPwd.append(pwdCharSet.charAt(selChar));
                
            	/*
                if (allowSpecialCharacters){
                    if (specialCharacters.indexOf("" + pwdCharSet.charAt(selChar)) > -1){
                        specialCharactersCount ++;
                        if (specialCharactersCount > maxspecialCharacters){
                            while (specialCharacters.indexOf("" + pwdCharSet.charAt(selChar)) != -1){
                                selChar = (int) (Math.random() * (pwdCharSet.length() - 1));
                            }
                        }
                    }
                } */
                                   
            }
            
           insertCharType(CharacterType.NONALPHANUMERIC, returnPwd);
            
        } catch (Exception e) {
            
        }
        return returnPwd.toString();
    }

    private void insertCharType(CharacterType charTypes, StringBuffer generatedPwd){
    	int charIndex = (int) (Math.random() * (generatedPwd.length() - 1));
    	generatedPwd.replace(charIndex, charIndex+1, "" + charTypes.getRandomChar());
    	
    }
    
    private static StringBuffer getCharacters(CharacterType[] charTypes) {    	               
        String returnVal = CharacterType.getRandomizedCharacters(charTypes);
        return new StringBuffer(returnVal);               
    }
    
    
    public static void main(String[] args){
    	CharacterType[] charTypes = {CharacterType.DIGIT, CharacterType.LOWERCASE, CharacterType.UPPERCASE, CharacterType.NONALPHANUMERIC};
    	
    	System.out.println( "randomized characters = " + PasswordGenerator.getCharacters(charTypes));
    	System.out.println( "randomized characters = " + PasswordGenerator.getCharacters(charTypes));
    	PasswordGenerator pwdGen = new PasswordGenerator();
    	for (int i=0; i < 10; i++){
    		String password = pwdGen.getRandomPassword(10);
    		System.out.println( "randomized password = " + password + " , length = " + password.length());	
    	}
    	
    	
    }
}
