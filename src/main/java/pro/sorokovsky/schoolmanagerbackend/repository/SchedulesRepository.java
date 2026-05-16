package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ScheduleEntity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface SchedulesRepository extends CrudRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findAll();

    boolean existsByDateAndStartTimeAndClazzId(Date date, Time startTime, Integer classId);

    boolean existsByDateAndStartTimeAndSubjectId(Date date, Time startTime, Integer subjectId);

    boolean existsById(@NonNull Integer id);

    List<ScheduleEntity> findAllByClazzId(Integer clazzId);
}
