package pro.sorokovsky.schoolmanagerbackend.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;

import java.util.List;

@Repository
public interface EmployeesRepository extends CrudRepository<EmployeeEntity, Integer> {
    @Query("SELECT DISTINCT e FROM EmployeeEntity e JOIN e.positions po JOIN e.passports pa WHERE po.id = :id")
    List<EmployeeEntity> findByPosition(@Param("id") Integer id);

    boolean existsByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("DELETE FROM EmployeeEntity e WHERE e.id = :id")
    void deleteById(@NonNull @Param("id") Integer id);

    List<EmployeeEntity> findAll();
}
