package pro.sorokovsky.schoolmanagerbackend.exception.pupil;

import pro.sorokovsky.schoolmanagerbackend.exception.base.BadRequestException;

public class MotherGenderException extends BadRequestException {
    private static final String MESSAGE_CODE = "errors.pupil.mother";

    public MotherGenderException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public MotherGenderException() {
        super(MESSAGE_CODE);
    }
}
