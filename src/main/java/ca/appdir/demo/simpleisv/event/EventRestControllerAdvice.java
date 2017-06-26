package ca.appdir.demo.simpleisv.event;

import ca.appdir.demo.simpleisv.SimpleIsvException;
import ca.appdir.demo.simpleisv.event.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static ca.appdir.demo.simpleisv.event.model.Response.ErrorCode.UNKNOWN_ERROR;

@ControllerAdvice
public class EventRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(createFailureResponse());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(createFailureResponse());
    }

    @ExceptionHandler({SimpleIsvException.class})
    public ResponseEntity<Object> handleSimpleIsvException(SimpleIsvException ex, WebRequest webRequest) {
        return ResponseEntity.ok(createFailureResponse());
    }

    private Response createFailureResponse() {
        return Response.builder()
                       .message("Request failed")
                       .success(false)
                       .errorCode(UNKNOWN_ERROR)
                       .build();
    }

}
