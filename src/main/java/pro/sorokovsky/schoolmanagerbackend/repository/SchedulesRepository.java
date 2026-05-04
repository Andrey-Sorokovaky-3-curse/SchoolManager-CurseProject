package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ScheduleEntity;

@Repository
public interface SchedulesRepository extends CrudRepository<ScheduleEntity, Integer> {
}
