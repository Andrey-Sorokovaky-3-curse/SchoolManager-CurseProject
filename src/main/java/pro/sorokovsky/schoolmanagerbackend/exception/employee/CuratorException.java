package pro.sorokovsky.schoolmanagerbackend.exception.employee;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class CuratorException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.curator";
    public CuratorException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public CuratorException() {
        super(MESSAGE_CODE);
    }
}
