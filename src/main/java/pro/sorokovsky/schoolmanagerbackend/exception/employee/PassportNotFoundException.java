package pro.sorokovsky.schoolmanagerbackend.exception.employee;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class PassportNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.passport.not-found";

    public PassportNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public PassportNotFoundException() {
        super(MESSAGE_CODE);
    }
}
