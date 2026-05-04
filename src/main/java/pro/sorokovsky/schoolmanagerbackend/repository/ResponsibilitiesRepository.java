package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ResponsibilityEntity;

@Repository
public interface ResponsibilitiesRepository extends CrudRepository<ResponsibilityEntity, Integer> {
}
