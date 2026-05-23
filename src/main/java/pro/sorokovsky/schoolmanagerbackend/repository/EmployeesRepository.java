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
    List<EmployeeEntity> findAllByPositionsId(Integer positionsId);

    boolean existsByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = "DELETE FROM Employees WHERE Id = :id OR UserId = :id", nativeQuery = true)
    void deleteById(@NonNull @Param("id") Integer id);

    List<EmployeeEntity> findAll();
}
