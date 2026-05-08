package pro.sorokovsky.schoolmanagerbackend.exception.employee;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class EmployeeNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.employee.not-found";

    public EmployeeNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public EmployeeNotFoundException() {
        super(MESSAGE_CODE);
    }
}
