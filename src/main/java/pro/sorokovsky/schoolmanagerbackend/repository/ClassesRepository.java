package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;

@Repository
public interface ClassesRepository extends CrudRepository<ClassEntity, Integer> {
}
