package au.amidja.core.rest.error;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import au.amidja.core.common.exception.defined.DefinedError;
import au.amidja.core.common.exception.defined.DefinedErrorType;

@JsonInclude(Include.NON_EMPTY)
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 4770184748729481228L;

    private final String code;
    private final String message;
    private final Map<String, String> data;
     
    public ErrorMessage(String message) {
        this.message = message;
        this.code = "";
        this.data = Collections.emptyMap();
    }
    
    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message == null ? "" : message;
        this.data = Collections.emptyMap();
    }
    
    public ErrorMessage(String code, String message, Map<String, String> data) {
        this.code = code;
        this.message = message == null ? "" : message;
        this.data = data;
    }

    public ErrorMessage(DefinedErrorType definedErrorType) {
        if (definedErrorType == null || definedErrorType.getError() == null) {
            throw new IllegalArgumentException("definedError cannot be null");
        }

        this.code = definedErrorType.getError().getCode();
        this.message = definedErrorType.getError().getUserMessage();
        this.data = Collections.emptyMap();
    }

    public ErrorMessage(DefinedError definedError) {
        if (definedError == null) {
            throw new IllegalArgumentException("definedError cannot be null");
        }

        this.code = definedError.getCode();
        this.message = definedError.getUserMessage();
        this.data = Collections.emptyMap();
    }
    
    public ErrorMessage(DefinedErrorType definedErrorType, Map<String, String> data) {
        if (definedErrorType == null || definedErrorType.getError() == null) {
            throw new IllegalArgumentException("definedError cannot be null");
        }

        this.code = definedErrorType.getError().getCode();
        this.message = definedErrorType.getError().getUserMessage();
        this.data = data;
    }

    public ErrorMessage(DefinedError definedError, Map<String, String> data) {
        if (definedError == null) {
            throw new IllegalArgumentException("definedError cannot be null");
        }

        this.code = definedError.getCode();
        this.message = definedError.getUserMessage();
        this.data = data;
    }    

    /** @return the code */
    public String getCode() { return code;}

    /** @return the message */
    public String getMessage() { return message;}

    /** @return data */
    public Map<String, String> getData() { return data; }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Error [code=" + code + ", message=" + message + "]";
    }
}
