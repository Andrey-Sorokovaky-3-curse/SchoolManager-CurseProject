package pro.sorokovsky.schoolmanagerbackend.exception.base;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends HttpException {
    private static final HttpStatus STATUS = HttpStatus.CONFLICT;

    public AlreadyExistsException(String messageCode, Throwable cause) {
        super(messageCode, STATUS, cause);
    }

    public AlreadyExistsException(String messageCode) {
        super(messageCode, STATUS);
    }
}
