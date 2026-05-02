package pro.sorokovsky.schoolmanagerbackend.exception.requirement;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class RequirementNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.requirement.not-found";

    public RequirementNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public RequirementNotFoundException() {
        super(MESSAGE_CODE);
    }
}
