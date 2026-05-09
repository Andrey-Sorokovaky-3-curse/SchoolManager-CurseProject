package pro.sorokovsky.schoolmanagerbackend.exception.employee;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class EmployeeAlreadyPositionException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.employee.already-position";

    public EmployeeAlreadyPositionException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public EmployeeAlreadyPositionException() {
        super(MESSAGE_CODE);
    }
}
