package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SchedulesRepository extends CrudRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findAll();

    @Query(value = "SELECT IIF(EXISTS (" +
            "SELECT 1 FROM Schedules " +
            "WHERE CAST(Date AS DATE) = CAST(:date AS DATE) " +
            "AND CAST(StartTime AS TIME) = CAST(:startTime AS TIME) " +
            "AND ClassId = :classId" +
            "), 1, 0)",
            nativeQuery = true)
    int existsByClass(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("classId") Integer classId
    );

    @Query(value = "SELECT IIF(EXISTS (" +
            "SELECT 1 FROM Schedules " +
            "WHERE CAST(Date AS DATE) = CAST(:date AS DATE) " +
            "AND CAST(StartTime AS TIME) = CAST(:startTime AS TIME) " +
            "AND SubjectId = :subjectId" +
            "), 1, 0)",
            nativeQuery = true)
    int existsBySubject(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("subjectId") Integer subjectId
    );

    boolean existsById(@NonNull Integer id);

    List<ScheduleEntity> findAllByClazzId(Integer clazzId);
}
