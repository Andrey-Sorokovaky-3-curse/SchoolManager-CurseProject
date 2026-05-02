package pro.sorokovsky.schoolmanagerbackend.exception.user;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.user.already-exists";

    public UserAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public UserAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
