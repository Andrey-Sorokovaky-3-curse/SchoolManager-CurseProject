package pro.sorokovsky.schoolmanagerbackend.exception.parent;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ParentAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.parents.already-exists";

    public ParentAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ParentAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
