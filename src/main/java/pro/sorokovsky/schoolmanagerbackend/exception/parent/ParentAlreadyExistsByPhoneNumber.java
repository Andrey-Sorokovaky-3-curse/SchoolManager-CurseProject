package pro.sorokovsky.schoolmanagerbackend.exception.parent;

import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;

public class ParentAlreadyExistsByPhoneNumber extends AlreadyExistsException {
    private static final String MESSAGE_CODE = "errors.parents.phone.exists";

    public ParentAlreadyExistsByPhoneNumber(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ParentAlreadyExistsByPhoneNumber() {
        super(MESSAGE_CODE);
    }
}
