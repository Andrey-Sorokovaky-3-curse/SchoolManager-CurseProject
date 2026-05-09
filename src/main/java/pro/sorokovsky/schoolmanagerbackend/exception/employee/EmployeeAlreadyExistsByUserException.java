package pro.sorokovsky.schoolmanagerbackend.exception.employee;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class EmployeeAlreadyExistsByUserException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.employee.already-exists";

    public EmployeeAlreadyExistsByUserException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public EmployeeAlreadyExistsByUserException() {
        super(MESSAGE_CODE);
    }
}
