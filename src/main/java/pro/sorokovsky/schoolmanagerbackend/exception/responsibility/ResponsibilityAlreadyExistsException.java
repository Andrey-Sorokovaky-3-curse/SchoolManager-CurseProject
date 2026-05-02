package pro.sorokovsky.schoolmanagerbackend.exception.responsibility;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ResponsibilityAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.responsibility.already-exists";

    public ResponsibilityAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ResponsibilityAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
