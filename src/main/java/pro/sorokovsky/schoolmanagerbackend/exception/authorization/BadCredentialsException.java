package pro.sorokovsky.schoolmanagerbackend.exception.authorization;

import pro.sorokovsky.schoolmanagerbackend.exception.base.BadRequestException;

public class BadCredentialsException extends BadRequestException {
    private static final String MESSAGE_CODE = "errors.bad-credentials";

    public BadCredentialsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public BadCredentialsException() {
        super(MESSAGE_CODE);
    }
}
