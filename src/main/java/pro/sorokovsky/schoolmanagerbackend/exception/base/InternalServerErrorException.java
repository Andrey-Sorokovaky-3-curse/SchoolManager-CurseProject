package pro.sorokovsky.schoolmanagerbackend.exception.base;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpException {
    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException(String messageCode, Throwable cause) {
        super(messageCode, STATUS, cause);
    }

    public InternalServerErrorException(String messageCode) {
        super(messageCode, STATUS);
    }
}
