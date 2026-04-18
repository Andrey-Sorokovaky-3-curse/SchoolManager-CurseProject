package pro.sorokovsky.schoolmanagerbackend.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    @Getter
    private final HttpStatus statusCode;

    public HttpException(String messageCode, HttpStatus httpStatus, Throwable cause) {
        super(messageCode, cause);
        this.statusCode = httpStatus;
    }

    public HttpException(String messageCode, HttpStatus httpStatus) {
        super(messageCode);
        this.statusCode = httpStatus;
    }
}
