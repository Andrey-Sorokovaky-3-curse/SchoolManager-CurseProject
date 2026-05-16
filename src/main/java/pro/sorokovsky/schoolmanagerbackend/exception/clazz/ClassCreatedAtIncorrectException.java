package pro.sorokovsky.schoolmanagerbackend.exception.clazz;

import pro.sorokovsky.schoolmanagerbackend.exception.base.BadRequestException;

public class ClassCreatedAtIncorrectException extends BadRequestException {
    private static final String MESSAGE_CODE = "errors.class.created-at.incorrect";

    public ClassCreatedAtIncorrectException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ClassCreatedAtIncorrectException() {
        super(MESSAGE_CODE);
    }
}
