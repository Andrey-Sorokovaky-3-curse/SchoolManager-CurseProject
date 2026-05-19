package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;

import java.util.List;

@Repository
public interface PositionsRepository extends CrudRepository<PositionEntity, Integer> {
    @Query("SELECT p FROM PositionEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :term, '%') ) ")
    List<PositionEntity> findAllByTerm(@Param("term") String term);

    List<PositionEntity> findAll();
}
