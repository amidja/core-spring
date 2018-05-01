package au.amidja.core.common.exception.defined;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseClientException extends AbstractDefinedClientException {

    private static final long serialVersionUID = 1L;
    
    protected List<BaseClientErrorType> errors;
            
    /**
	 * Constructor
	 * 
	 * @param message
	 */	
	public BaseClientException(final String message) {
		super(message);		
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public BaseClientException(String message, Throwable cause) {
        super(message, cause);
    }

    
    /**
     * Constructor
     * 
     * @param cause
     * @param errors
     */
    public BaseClientException(final Throwable cause, final List<BaseClientErrorType> errors) {
        super(convertErrors(errors), cause);
        this.errors = errors;
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public BaseClientException(final List<BaseClientErrorType> errors) {
        super(convertErrors(errors));
        this.errors = errors;
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public BaseClientException(final BaseClientErrorType error) {
        super(error.getError().getDescription());
        this.errors = new ArrayList<BaseClientErrorType>(Arrays.asList(error));
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public BaseClientException(final BaseClientErrorType error, final String message) {
        super(message);
        this.errors = new ArrayList<BaseClientErrorType>(Arrays.asList(error));
    }

    /**
     * 
     */
    @Override
	@SuppressWarnings("unchecked")
    public List<? extends DefinedErrorType> getErrors() {
        return (List<BaseClientErrorType>) errors;
    }

    /**
     * An enum represent the different errors* 
     */
    public enum BaseClientErrorType implements DefinedErrorType {
       	    	
    	UNKNOWN_ERROR(new DefinedError("00", "UNKNOWN_ERROR", "unknown error.", "unknown error.")),
    	INVALID_INPUT(new DefinedError("01", "INVALID_INPUT", "invalid input provided.","invalid input provided."));
    	
        public static final String errorCode = "ERROR";

        private static final Map<String, BaseClientErrorType> lookup =
                new HashMap<String, BaseClientErrorType>();

        static {
            for (final BaseClientErrorType c : EnumSet.allOf(BaseClientErrorType.class))
                lookup.put(c.getError().getId(), c);
        }

        private DefinedError error;

        /**
         * Constructor
         * 
         * @param id
         * @param description
         * @param userMessage
         */
        private BaseClientErrorType(final DefinedError error) {
            this.error = error;
        }

        /**
         * {@InheritDoc}
         */
        @Override
		public DefinedError getError() {
            return error;
        }

        /**
         * Returns the AccountErrorType represented by the passed code
         * 
         * @param code
         * @return AccountErrorType
         */
        public static BaseClientErrorType get(final int code) {
            return lookup.get(Integer.toString(code));
        }

    }
}
