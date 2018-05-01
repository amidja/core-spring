package au.amidja.core.common.exception.defined;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDefinedClientException extends RuntimeException {
	
	private static final long serialVersionUID = -3929978846201708885L;
		
        
	/**
	 * Constructor
	 * 
	 * @param message
	 */	
	public AbstractDefinedClientException(final String message) {
		super(message);		
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public AbstractDefinedClientException(String message, Throwable cause) {
        super(message, cause);
    }
	
	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public AbstractDefinedClientException(Throwable cause) {
        super(cause);
    }
	
	protected abstract <T extends DefinedErrorType> List<T> getErrors();
	
	/**
     * Covert errors list to String.
     * 	
     * @param errors
     * @return message
     */
	protected static <T extends DefinedErrorType> String convertErrors(List<T> errors) {
		StringBuilder buff = new StringBuilder();
		for (DefinedErrorType e : errors) {
			if (buff.length() != 0) {
				buff.append(", ");
			}
			buff.append(e.getError().getDescription());			
		}
		return buff.toString();
	}
	
	/**
	 * 
	 * @return exception Details
	 */
	public List<DefinedError> getExceptionDetails() {
		List<DefinedErrorType> types = getErrors();
		List<DefinedError> definedErrors = new ArrayList<DefinedError>();
		for (DefinedErrorType et : types) {
			definedErrors.add(et.getError());
		}
		
		return definedErrors;		
	}	
}

