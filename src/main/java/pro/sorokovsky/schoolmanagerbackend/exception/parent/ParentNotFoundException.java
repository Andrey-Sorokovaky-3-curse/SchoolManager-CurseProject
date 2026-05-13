package pro.sorokovsky.schoolmanagerbackend.exception.parent;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class ParentNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.parents.not-found";

    public ParentNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ParentNotFoundException() {
        super(MESSAGE_CODE);
    }
}
