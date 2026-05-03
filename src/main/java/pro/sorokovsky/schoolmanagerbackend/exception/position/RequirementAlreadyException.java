package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class RequirementAlreadyException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.positions.has-requirement";

    public RequirementAlreadyException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public RequirementAlreadyException() {
        super(MESSAGE_CODE);
    }
}
