package pro.sorokovsky.schoolmanagerbackend.exception.clazz;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ClassAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.class.exists";

    public ClassAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ClassAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
