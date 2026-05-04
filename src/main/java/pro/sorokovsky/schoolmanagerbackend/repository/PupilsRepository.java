package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ParentEntity;

@Repository
public interface PupilsRepository extends CrudRepository<ParentEntity, Integer> {
}
