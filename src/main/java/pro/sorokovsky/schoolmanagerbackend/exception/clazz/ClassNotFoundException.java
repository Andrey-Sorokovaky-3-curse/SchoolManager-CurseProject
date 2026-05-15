package pro.sorokovsky.schoolmanagerbackend.exception.clazz;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class ClassNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.class.not-found";

    public ClassNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ClassNotFoundException() {
        super(MESSAGE_CODE);
    }
}
