package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleModel {
    private Long id;
    private Date date;
    private String dayOfWeek;
    private SubjectModel subject;
    private LocalTime startTime;
    private LocalTime endTime;
}
