package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;

@Repository
public interface EmployeesRepository extends CrudRepository<EmployeeEntity, Integer> {
}
