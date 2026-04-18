package pro.sorokovsky.schoolmanagerbackend.exception.base;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String messageCode, Throwable cause) {
        super(messageCode, STATUS, cause);
    }

    public BadRequestException(String messageCode) {
        super(messageCode, STATUS);
    }
}
