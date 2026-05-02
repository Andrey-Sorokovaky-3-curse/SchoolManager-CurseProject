package pro.sorokovsky.schoolmanagerbackend.exception.user;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.user.not-found";

    public UserNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public UserNotFoundException() {
        super(MESSAGE_CODE);
    }
}
