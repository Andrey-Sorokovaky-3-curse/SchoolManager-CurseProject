package pro.sorokovsky.schoolmanagerbackend.contract.schedule;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;
import java.sql.Time;

@Schema(description = "Новий розклад", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateSchedule(
        Integer subjectId,
        Integer classId,

        @Schema(
                description = "Дата проведення уроку",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "2026-05-16"
        )
        Date date,

        @Schema(
                description = "День тижня",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer dateOfWeek,

        @Schema(
                description = "Дата проведення уроку",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "11:00"
        )
        Time startTime,

        @Schema(
                description = "Дата проведення уроку",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "11:45"
        )
        Time endTime
) {
}
