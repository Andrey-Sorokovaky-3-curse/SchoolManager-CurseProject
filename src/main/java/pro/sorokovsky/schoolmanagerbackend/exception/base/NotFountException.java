package pro.sorokovsky.schoolmanagerbackend.exception.base;

import org.springframework.http.HttpStatus;

public class NotFountException extends HttpException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFountException(String messageCode, Throwable cause) {
        super(messageCode, STATUS, cause);
    }

    public NotFountException(String messageCode) {
        super(messageCode, STATUS);
    }
}
