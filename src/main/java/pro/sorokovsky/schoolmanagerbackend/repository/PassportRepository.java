package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pro.sorokovsky.schoolmanagerbackend.entity.PassportEntity;

public interface PassportRepository extends CrudRepository<PassportEntity, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Passport WHERE Id = :id", nativeQuery = true)
    void deleteById(@Param("id") @NonNull Integer id);
}
