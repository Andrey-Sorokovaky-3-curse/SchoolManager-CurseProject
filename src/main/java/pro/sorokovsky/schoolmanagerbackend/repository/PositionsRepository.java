package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;

@Repository
public interface PositionsRepository extends CrudRepository<PositionEntity, Integer> {
}
