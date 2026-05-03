package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class NoResponsibilityException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.positions.no-responsibility";

    public NoResponsibilityException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public NoResponsibilityException() {
        super(MESSAGE_CODE);
    }
}
