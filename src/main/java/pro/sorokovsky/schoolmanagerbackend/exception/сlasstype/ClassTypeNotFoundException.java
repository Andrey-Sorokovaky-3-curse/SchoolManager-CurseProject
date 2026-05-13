package pro.sorokovsky.schoolmanagerbackend.exception.сlasstype;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class ClassTypeNotFoundException extends NotFoundException {
    private final static String MESSAGE_CODE = "errors.class-type.not-found";

    public ClassTypeNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ClassTypeNotFoundException() {
        super(MESSAGE_CODE);
    }
}
