package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ResponsibilityAlreadyException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.positions.has-responsibility";

    public ResponsibilityAlreadyException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ResponsibilityAlreadyException() {
        super(MESSAGE_CODE);
    }
}
