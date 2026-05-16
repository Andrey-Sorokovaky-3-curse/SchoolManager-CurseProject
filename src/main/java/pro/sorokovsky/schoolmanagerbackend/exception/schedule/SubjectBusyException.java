package pro.sorokovsky.schoolmanagerbackend.exception.schedule;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class SubjectBusyException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.schedule.subject";

    public SubjectBusyException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public SubjectBusyException() {
        super(MESSAGE_CODE);
    }
}
