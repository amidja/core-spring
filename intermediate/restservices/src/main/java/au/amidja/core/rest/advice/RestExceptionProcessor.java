package au.amidja.core.rest.advice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import au.amidja.core.common.exception.defined.BaseClientException;
import au.amidja.core.common.exception.defined.DefinedError;
import au.amidja.core.rest.error.ErrorMessage;
import au.amidja.core.rest.error.ErrorMessageBundle;

@ControllerAdvice
public class RestExceptionProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionProcessor.class);
	
    @ExceptionHandler(BaseClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageBundle handleDefinedClientException(HttpServletRequest req, BaseClientException ex) {
        if (LOG.isErrorEnabled()) {
            LOG.error("handleDefinedClientException: Unexpected Exception.", ex);
        }

        ErrorMessage[] errors = getDefinedErrors(ex.getExceptionDetails());        

        return new ErrorMessageBundle(String.valueOf(HttpStatus.BAD_REQUEST.value()), getRequestURI(req), errors);
    }

    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorMessageBundle handleException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        if (LOG.isErrorEnabled()) {
            LOG.error("handleException: HttpMessageNotReadableException Exception.", ex);
        }
        String errorCode = "INPUTERR";
        String errorMessage = "";
        String reference = "N/A";
        String errorURL = getRequestURI(req);
        
        if (ex.getCause() instanceof UnrecognizedPropertyException) {
        	UnrecognizedPropertyException uex = (UnrecognizedPropertyException) ex.getCause();
        	errorMessage = String.format("Unrecognised property: [%s].", uex.getPropertyName());
        }

        ErrorMessageBundle errorInfo =
                new ErrorMessageBundle(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorURL, reference, errorCode, errorMessage);
        return errorInfo;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ErrorMessageBundle handleException(HttpServletRequest req, HttpMediaTypeNotSupportedException ex) {
        if (LOG.isErrorEnabled()) {
            LOG.error("handleException: HttpMediaTypeNotSupportedException Exception.", ex);
        }
        String errorCode = "INPUTERR";
        String errorMessage = ex.getLocalizedMessage();
        String reference = "N/A";
        String errorURL = getRequestURI(req);
        
        if (ex.getCause() instanceof UnrecognizedPropertyException) {
        	UnrecognizedPropertyException uex = (UnrecognizedPropertyException) ex.getCause();
        	errorMessage = String.format("Unrecognised property: [%s].", uex.getPropertyName());
        }

        ErrorMessageBundle errorInfo =
                new ErrorMessageBundle(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorURL,
                        reference, errorCode, errorMessage);
        return errorInfo;
    }

    
    /*
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest req, ConstraintViolationException ex) {
        if (LOG.isErrorEnabled()) {
            LOG.error("handleException: ConstraintViolationException detected.", ex);
        }
                
        RestError[] errors = new RestError[1];
        errors[0] = new RestError(ex.getMessage());
        String errorURL = getRequestURI(req);
        ErrorInfo errorInfo =
                new ErrorInfo(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorURL, errors);
        return errorInfo;
    }
    */
    
    /*
    protected RestError[] getErrors(String errorCode, List<String> messages) {
        RestError[] errors = new RestError[messages.size()];
        
        for (int i = 0; i < errors.length; i++) {
            errors[i] = new RestError(errorCode, messages.get(i));
        }
        return errors;
    }
    */

    protected ErrorMessage[] getDefinedErrors(List<DefinedError> definedErrorList) {
        ErrorMessage[] errors = new ErrorMessage[definedErrorList.size()];
        for (int i = 0; i < errors.length; i++) {
            if (definedErrorList.get(i).getData() == null || definedErrorList.get(i).getData().isEmpty()) {
                errors[i] = new ErrorMessage(definedErrorList.get(i).getCode(),
                        definedErrorList.get(i).getUserMessage());
            } else {
                errors[i] = new ErrorMessage(definedErrorList.get(i).getCode(),
                        definedErrorList.get(i).getUserMessage(),
                        definedErrorList.get(i).getData());
            }
        }
        return errors;
    }
    
    /*
     * Returns the request method and URI associated with the request.
     */
    protected String getRequestURI(HttpServletRequest req) {
        String requestUrl = "Undefined";

        if (req != null && req.getRequestURL() != null) {
            requestUrl = String.format("%s  %s", req.getMethod(), req.getRequestURI());
        }

        return requestUrl;
    }


}
