package pro.sorokovsky.schoolmanagerbackend.exception.schedule;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ClassBusyException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.schedule.class-busy";

    public ClassBusyException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ClassBusyException() {
        super(MESSAGE_CODE);
    }
}
