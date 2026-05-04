package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.RequirementEntity;

@Repository
public interface RequirementsRepository extends CrudRepository<RequirementEntity, Integer> {
}
