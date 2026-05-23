package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.schedule.CreateSchedule;
import pro.sorokovsky.schoolmanagerbackend.entity.ScheduleEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.schedule.ClassBusyException;
import pro.sorokovsky.schoolmanagerbackend.exception.schedule.ScheduleNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.subject.SubjectNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.SchedulesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulesService {
    private final SchedulesRepository repository;
    private final ClassesService classesService;
    private final SubjectsService subjectsService;

    public List<ScheduleEntity> getAll() {
        return repository.findAll();
    }

    public Optional<ScheduleEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<ScheduleEntity> getByClass(Integer classId) {
        return repository.findAllByClazzId(classId);
    }

    @Transactional
    public ScheduleEntity create(CreateSchedule schedule) {
        if (repository.existsBySubject(schedule.date(), schedule.startTime(), schedule.classId()) == 1) {
            throw new ClassBusyException();
        } else if (repository.existsByClass(schedule.date(), schedule.startTime(), schedule.subjectId()) == 1) {
            throw new ClassBusyException();
        } else {
            return repository.save(
                    ScheduleEntity
                            .builder()
                            .subject(subjectsService.getById(schedule.subjectId()).orElseThrow(SubjectNotFoundException::new))
                            .clazz(classesService.getById(schedule.classId()).orElseThrow(ClassNotFoundException::new))
                            .date(schedule.date())
                            .dateOfWeek(schedule.dateOfWeek())
                            .startTime(schedule.startTime())
                            .endTime(schedule.endTime())
                            .build()
            );
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ScheduleNotFoundException();
        }
    }
}
