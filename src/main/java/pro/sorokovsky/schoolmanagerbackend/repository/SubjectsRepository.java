package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.SubjectEntity;

import java.util.List;

@Repository
public interface SubjectsRepository extends CrudRepository<SubjectEntity, Integer> {
    List<SubjectEntity> findAllByTeacherId(Integer teacherId);
}
