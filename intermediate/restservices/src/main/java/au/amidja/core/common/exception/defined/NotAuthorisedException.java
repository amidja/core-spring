package au.amidja.core.common.exception.defined;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotAuthorisedException extends AbstractDefinedClientException {

    private static final long serialVersionUID = -6347876882230548394L;

    private List<NotAuthorisedErrorType> errors;
 
    /**
	 * Constructor
	 * 
	 * @param message
	 */	
	public NotAuthorisedException(final String message) {
		super(message);		
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public NotAuthorisedException(String message, Throwable cause) {
        super(message, cause);
    }


    
    /**
     * Constructor
     * 
     * @param cause
     * @param errors
     */
    public NotAuthorisedException(final Throwable cause, final List<NotAuthorisedErrorType> errors) {
        super(convertErrors(errors), cause);
        this.errors = errors;
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public NotAuthorisedException(final List<NotAuthorisedErrorType> errors) {
        super(convertErrors(errors));
        this.errors = errors;
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public NotAuthorisedException(final NotAuthorisedErrorType error) {
        super(error.getError().getDescription());
        this.errors = new ArrayList<NotAuthorisedErrorType>(Arrays.asList(error));
    }

    /**
     * Constructor
     * 
     * @param errors
     */
    public NotAuthorisedException(final NotAuthorisedErrorType error, final String message) {
        super(message);
        this.errors = new ArrayList<NotAuthorisedErrorType>(Arrays.asList(error));
    }
    
    @SuppressWarnings("unchecked")
	@Override	
    public List<NotAuthorisedErrorType> getErrors() {
        return errors;
    }

    /**
     * An enum represent the different JWK Error Types   * 
     */
    public enum NotAuthorisedErrorType implements DefinedErrorType {

        AUTH_ERROR_UNKNOWN_USER(new DefinedError("1", "NOTAUTH", "Unknown User.", "Not a recognised PRODA User."));
    	
        public static final String errorCode = "NOTAUTH";

        private static final Map<String, NotAuthorisedErrorType> lookup =
                new HashMap<String, NotAuthorisedErrorType>();

        static {
            for (final NotAuthorisedErrorType c : EnumSet.allOf(NotAuthorisedErrorType.class))
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
        private NotAuthorisedErrorType(final DefinedError error) {
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
        public static NotAuthorisedErrorType get(final int code) {
            return lookup.get(Integer.toString(code));
        }

    }
}
