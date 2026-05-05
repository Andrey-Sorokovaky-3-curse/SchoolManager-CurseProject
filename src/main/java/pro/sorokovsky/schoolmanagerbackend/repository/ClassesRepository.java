package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;

import java.util.List;

@Repository
public interface ClassesRepository extends CrudRepository<ClassEntity, Integer> {
    List<ClassEntity> findAllByStudyYear(Integer studyYear);
}
