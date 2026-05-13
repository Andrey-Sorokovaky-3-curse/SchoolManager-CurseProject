package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassTypeEntity;

import java.util.List;

@Repository
public interface ClassTypesRepository extends CrudRepository<ClassTypeEntity, Integer> {
    @Override
    List<ClassTypeEntity> findAll();

    boolean existsById(Integer id);
}
