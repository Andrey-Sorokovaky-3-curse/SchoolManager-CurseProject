package pro.sorokovsky.schoolmanagerbackend.exception.base;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String messageCode, Throwable cause) {
        super(messageCode, STATUS, cause);
    }

    public NotFoundException(String messageCode) {
        super(messageCode, STATUS);
    }
}
