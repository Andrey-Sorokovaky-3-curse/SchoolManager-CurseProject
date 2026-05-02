package pro.sorokovsky.schoolmanagerbackend.exception.requirement;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class RequirementAlreadyExistsException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.requirement.already-exists";

    public RequirementAlreadyExistsException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public RequirementAlreadyExistsException() {
        super(MESSAGE_CODE);
    }
}
