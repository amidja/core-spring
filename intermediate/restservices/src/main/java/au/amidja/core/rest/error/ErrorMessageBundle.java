package au.amidja.core.rest.error;

import java.io.Serializable;
import java.util.List;

public class ErrorMessageBundle implements Serializable {

    private static final long serialVersionUID = -4026620560225791258L;

    private final ErrorMessage[] errors;
    private final String reference;
    private final String status;
    private final String url;

    
    public ErrorMessageBundle(String status, String url, ErrorMessage[] errors) {
        this(status, url, "N/A", errors);
    }

    public ErrorMessageBundle(String status, List<ErrorMessage> errors) {
        this(status, toArray(errors));
    }

    public ErrorMessageBundle(String status, ErrorMessage[] errors) {
        this(status, null, errors);
    }

    public ErrorMessageBundle(String status, String url, List<ErrorMessage> errors) {
        this(status, url, toArray(errors));
    }

    public ErrorMessageBundle(String status, String url, String message) {
        this(status, url, new ErrorMessage[] {new ErrorMessage(message)});
    }

    public ErrorMessageBundle(String status, String url, String reference, ErrorMessage[] errors) {
        this.status = status;
        this.url = url;
        this.reference = reference;
        this.errors = errors;
    }

    public ErrorMessageBundle(String status, String url, String reference, List<ErrorMessage> errors) {
        this(status, url, reference, toArray(errors));
    }

    public ErrorMessageBundle(String status, String url, String reference, String message) {
        this(status, url, reference, new ErrorMessage[] {new ErrorMessage(message)});
    }

    public ErrorMessageBundle(String status, String url, String reference, String code, String message) {
        this(status, url, reference, new ErrorMessage[] {new ErrorMessage(code, message)});
    }

    public ErrorMessageBundle(String status, String url, String reference, ErrorMessage error) {
        this(status, url, reference, new ErrorMessage[] {error});
    }

    /**
     * @return the errors
     */
    public ErrorMessage[] getErrors() { return this.errors;}

    /** @return the reference */
    public String getReference() { return this.reference; }

    /** @return the status */
    public String getStatus() { return this.status; }

    /** @return the url */
    public String getUrl() { return this.url; }

    private static ErrorMessage[] toArray(List<ErrorMessage> errors) {
        return errors == null ? null : errors.toArray(new ErrorMessage[errors.size()]);
    }
}