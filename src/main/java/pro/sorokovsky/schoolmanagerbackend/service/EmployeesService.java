package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.CreateEmployee;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.position.ExistsByPhoneNumberException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository repository;
    private final UsersService usersService;

    public List<EmployeeEntity> getByPosition(Integer positionId) {
        return repository.findByPosition(positionId);
    }

    public Optional<EmployeeEntity> getById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public EmployeeEntity create(CreateEmployee employee) {
        final var existsPhone = repository.existsByPhoneNumber(employee.phoneNumber());
        if (existsPhone) {
            throw new ExistsByPhoneNumberException();
        }
        final var user = usersService.getById(employee.userId()).orElseThrow(UserNotFoundException::new);
        return repository.save(
                EmployeeEntity
                        .builder()
                        .id(user.getId())
                        .login(user.getLogin())
                        .password(user.getPassword())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .middleName(user.getMiddleName())
                        .gender(user.getGender())
                        .birthday(user.getBirthday())
                        .address(user.getAddress())
                        .role(user.getRole())
                        .phoneNumber(employee.phoneNumber())
                        .positions(List.of())
                        .passports(List.of())
                        .build()
        );
    }
}
