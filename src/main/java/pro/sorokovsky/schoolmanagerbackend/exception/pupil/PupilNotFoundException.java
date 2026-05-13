package pro.sorokovsky.schoolmanagerbackend.exception.pupil;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class PupilNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.pupil.not-found";

    public PupilNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public PupilNotFoundException() {
        super(MESSAGE_CODE);
    }
}
