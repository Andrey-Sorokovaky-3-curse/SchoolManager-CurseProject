package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class PositionAlreadyExistsException extends AlreadyExistsException {
    private final static String MESSAGE_CODE = "errors.position.already-exists";

    public PositionAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public PositionAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
