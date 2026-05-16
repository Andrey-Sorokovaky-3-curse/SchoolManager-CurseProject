package pro.sorokovsky.schoolmanagerbackend.exception.schedule;

import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;

public class ScheduleNotFoundException extends NotFoundException {
    private static final String MESSAGE_CODE = "errors.schedule.not-found";

    public ScheduleNotFoundException(Throwable cause) {
        super(MESSAGE_CODE, cause);
    }

    public ScheduleNotFoundException() {
        super(MESSAGE_CODE);
    }
}
