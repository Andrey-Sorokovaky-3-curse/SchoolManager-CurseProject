package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassTypeEntity;

@Repository
public interface ClassTypesRepository extends CrudRepository<ClassTypeEntity, Integer> {
}
