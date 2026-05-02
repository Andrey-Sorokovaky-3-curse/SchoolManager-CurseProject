package pro.sorokovsky.schoolmanagerbackend.exception.responsibility;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class ResponsibilityNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.responsibility.not-found";

    public ResponsibilityNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ResponsibilityNotFoundException() {
        super(MESSAGE_CODE);
    }
}
