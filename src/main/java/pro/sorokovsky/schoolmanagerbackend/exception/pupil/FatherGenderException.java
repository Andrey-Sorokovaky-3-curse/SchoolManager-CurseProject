package pro.sorokovsky.schoolmanagerbackend.exception.pupil;

import pro.sorokovsky.schoolmanagerbackend.exception.base.BadRequestException;

public class FatherGenderException extends BadRequestException {
    private static final String MESSAGE_CODE = "errors.pupil.father";

    public FatherGenderException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public FatherGenderException() {
        super(MESSAGE_CODE);
    }
}
