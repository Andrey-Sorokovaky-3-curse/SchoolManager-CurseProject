package pro.sorokovsky.schoolmanagerbackend.exception.subject;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class SubjectNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.subject.not-found";

    public SubjectNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public SubjectNotFoundException() {
        super(MESSAGE_CODE);
    }
}
