package ca.appdir.demo.simpleisv.event.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String accountIdentifier;
    private String userIdentifier;
    private ErrorCode errorCode;
    private String message;
    private boolean success;

    public enum ErrorCode {
        USER_ALREADY_EXISTS,
        USER_NOT_FOUND,
        ACCOUNT_NOT_FOUND,
        MAX_USERS_REACHED,
        UNAUTHORIZED,
        OPERATION_CANCELLED,
        CONFIGURATION_ERROR,
        INVALID_RESPONSE,
        TRANSPORT_ERROR,
        UNKNOWN_ERROR,
        NOT_FOUND

    }
}
