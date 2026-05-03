package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class PositionNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.position.not-found";

    public PositionNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public PositionNotFoundException() {
        super(MESSAGE_CODE);
    }
}
