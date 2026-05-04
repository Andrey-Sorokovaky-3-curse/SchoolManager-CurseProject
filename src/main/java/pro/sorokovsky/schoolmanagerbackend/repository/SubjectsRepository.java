package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.SubjectEntity;

@Repository
public interface SubjectsRepository extends CrudRepository<SubjectEntity, Integer> {
}
