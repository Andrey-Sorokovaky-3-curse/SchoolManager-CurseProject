package pro.sorokovsky.schoolmanagerbackend.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.CreateEmployee;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;
import pro.sorokovsky.schoolmanagerbackend.contract.passport.CreatePassport;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;
import pro.sorokovsky.schoolmanagerbackend.entity.PassportEntity;
import pro.sorokovsky.schoolmanagerbackend.entity.Roles;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.*;
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
    private final EntityManager entityManager;

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
        if (repository.existsById(employee.userId())) {
            throw new EmployeeAlreadyExistsByUserException();
        }
        final var user = usersService.getById(employee.userId()).orElseThrow(UserNotFoundException::new);
       final var insertSql = """
               INSERT INTO Employees (UserId, PhoneNumber) VALUES (:userId, :phoneNumber);
               """;
       final var updateSQL = "UPDATE Users SET Role=:role WHERE Id = :userId;";
       entityManager.createNativeQuery(insertSql)
               .setParameter("userId", employee.userId())
               .setParameter("phoneNumber", employee.phoneNumber())
               .executeUpdate();
        entityManager.createNativeQuery(updateSQL)
                .setParameter("role", Roles.EMPLOYEE.value())
                .setParameter("userId", employee.userId())
                .executeUpdate();
        entityManager.flush();
        entityManager.clear();
        return repository.findById(user.getId()).orElseThrow(EmployeeNotFoundException::new);
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

    public List<EmployeeEntity> getAll() {
        return repository.findAll();
    }
}
