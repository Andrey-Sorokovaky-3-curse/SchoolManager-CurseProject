package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;

import java.util.List;

@Repository
public interface EmployeesRepository extends CrudRepository<EmployeeEntity, Integer> {
    @Query("SELECT DISTINCT e FROM EmployeeEntity e JOIN e.positions po JOIN e.passports pa WHERE po.name ILIKE CONCAT('%', :name, '%') ")
    List<EmployeeEntity> findByPositionName(@Param("name") String name);
}
