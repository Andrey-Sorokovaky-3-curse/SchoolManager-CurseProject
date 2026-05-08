package pro.sorokovsky.schoolmanagerbackend.exception.position;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ExistsByPhoneNumberException extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.employee.phone.exists";

    public ExistsByPhoneNumberException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ExistsByPhoneNumberException() {
        super(MESSAGE_CODE);
    }
}
