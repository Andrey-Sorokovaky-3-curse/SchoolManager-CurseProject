package pro.sorokovsky.schoolmanagerbackend.exception.pupil;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class PupilAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.pupil.exists";

    public PupilAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public PupilAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
