package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.RequirementEntity;

import java.util.List;

@Repository
public interface RequirementsRepository extends CrudRepository<RequirementEntity, Integer> {
    @Query("SELECT r FROM RequirementEntity r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :term, '%') ) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :term, '%') )")
    List<RequirementEntity> search(@Param("term") String term);

    boolean existsById(@NonNull Integer id);
}
