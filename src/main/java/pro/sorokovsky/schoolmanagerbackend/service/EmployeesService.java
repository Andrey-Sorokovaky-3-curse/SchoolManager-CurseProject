package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.EmployeesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository repository;

    public List<EmployeeEntity> getByPosition(String name) {
        return repository.findByPositionName(name);
    }
}
