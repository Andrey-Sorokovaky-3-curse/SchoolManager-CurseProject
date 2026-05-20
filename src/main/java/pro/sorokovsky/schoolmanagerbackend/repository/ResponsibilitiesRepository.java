package pro.sorokovsky.schoolmanagerbackend.repository;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ResponsibilityEntity;

import java.util.List;

@Repository
public interface ResponsibilitiesRepository extends CrudRepository<ResponsibilityEntity, Integer> {
    @Query("SELECT r FROM ResponsibilityEntity r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :term, '%') ) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :term, '%') )")
    List<ResponsibilityEntity> search(@Param("term") String term);

    boolean existsById(@NonNull Integer id);

    List<ResponsibilityEntity> findAll();
}
