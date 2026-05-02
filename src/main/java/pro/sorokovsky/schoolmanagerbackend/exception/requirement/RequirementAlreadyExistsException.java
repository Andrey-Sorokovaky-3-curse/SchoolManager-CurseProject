package pro.sorokovsky.schoolmanagerbackend.exception.requirement;

import pro.sorokovsky.schoolmanagerbackend.exception.base.ConflictException;

public class RequirementAlreadyExistsException extends ConflictException {
    private static final String MESSAGE_CODE = "errors.requirement.already-exists";

    public RequirementAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public RequirementAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
