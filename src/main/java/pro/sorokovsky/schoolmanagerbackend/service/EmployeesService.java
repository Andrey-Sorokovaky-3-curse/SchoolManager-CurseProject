package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.CreateEmployee;
import pro.sorokovsky.schoolmanagerbackend.contract.passport.CreatePassport;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;
import pro.sorokovsky.schoolmanagerbackend.entity.PassportEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.EmployeeAlreadyPositionException;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.EmployeeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.ExistsByPhoneNumberException;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.PassportNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.position.PositionNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.EmployeesRepository;
import pro.sorokovsky.schoolmanagerbackend.repository.PassportRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository repository;
    private final UsersService usersService;
    private final PassportRepository passportRepository;
    private final PositionsService positionsService;

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

    @Transactional
    public EmployeeEntity addPassport(Integer id, CreatePassport passport) {
        final var candidate = getById(id).orElseThrow(EmployeeNotFoundException::new);
        final var createdPassport = passportRepository.save(
                PassportEntity
                        .builder()
                        .name(passport.name())
                        .data(passport.data())
                        .employee(candidate)
                        .build()
        );
        candidate.getPassports().add(createdPassport);
        return repository.save(candidate);
    }

    @Transactional
    public EmployeeEntity removePassport(Integer id, Integer passportId) {
        if (passportRepository.existsById(passportId)) {
            passportRepository.deleteById(passportId);
            return getById(id).orElseThrow(EmployeeNotFoundException::new);
        } else {
            throw new PassportNotFoundException();
        }
    }

    @Transactional
    public EmployeeEntity addPosition(Integer id, Integer positionId) {
        final var candidate = getById(id).orElseThrow(EmployeeNotFoundException::new);
        candidate.getPositions()
                .stream().filter(position -> position.getId().equals(positionId))
                .findFirst()
                .ifPresent(position -> {throw new EmployeeAlreadyPositionException();});
        final var position = positionsService.getById(positionId).orElseThrow(PositionNotFoundException::new);
        candidate.getPositions().add(position);
        return repository.save(candidate);
    }

    @Transactional
    public EmployeeEntity removePosition(Integer id, Integer positionId) {
        final var candidate = getById(id).orElseThrow(EmployeeNotFoundException::new);
        final var hasPosition = candidate.getPositions().stream()
                .anyMatch(position -> position.getId().equals(positionId));
        if (hasPosition) {
            candidate.getPositions().removeIf(position -> position.getId().equals(positionId));
            return repository.save(candidate);
        } else {
            throw new PositionNotFoundException();
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException();
        }
    }
}
