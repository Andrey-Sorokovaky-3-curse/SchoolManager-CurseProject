package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.schedule.GetSchedule;
import pro.sorokovsky.schoolmanagerbackend.entity.ScheduleEntity;

@RequiredArgsConstructor
@Component
public class ScheduleMapper {
    private final SubjectMapper subjectMapper;
    private final ClassMapper classMapper;

    public GetSchedule toGet(ScheduleEntity entity) {
        return new GetSchedule(
                entity.getId(),
                subjectMapper.toGet(entity.getSubject()),
                classMapper.toGet(entity.getClazz()),
                entity.getDate(),
                entity.getDateOfWeek(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
