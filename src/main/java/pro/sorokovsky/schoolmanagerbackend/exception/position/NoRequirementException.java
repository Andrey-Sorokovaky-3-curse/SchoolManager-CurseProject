package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class NoRequirementException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.positions.no-requirement";

    public NoRequirementException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public NoRequirementException() {
        super(MESSAGE_CODE);
    }
}
