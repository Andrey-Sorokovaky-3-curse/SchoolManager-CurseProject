package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;

import java.util.List;

@Repository
public interface ClassesRepository extends CrudRepository<ClassEntity, Integer> {
    List<ClassEntity> findAllByStudyYear(Integer studyYear);
    List<ClassEntity> findAllByClassTypeId(Integer classTypeId);
    List<ClassEntity> findAll();

    @Query(
            value = "SELECT CAST(" +
                    "   IIF(EXISTS (" +
                    "       SELECT 1 FROM Classes " +
                    "       WHERE CONCAT(StudyYear, '-', Letter) = :name" +
                    "" +
                    "   ), 1, 0) AS BIT)" +
                    "",
            nativeQuery = true)
    boolean existsByName(@Param("name") String name);

    boolean existsById(@NonNull Integer id);
}
