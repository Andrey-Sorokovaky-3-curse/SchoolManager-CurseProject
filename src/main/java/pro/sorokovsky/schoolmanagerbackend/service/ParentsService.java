package pro.sorokovsky.schoolmanagerbackend.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.CreateParent;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.UpdateParent;
import pro.sorokovsky.schoolmanagerbackend.entity.ParentEntity;
import pro.sorokovsky.schoolmanagerbackend.entity.Roles;
import pro.sorokovsky.schoolmanagerbackend.exception.parent.ParentAlreadyExistsByPhoneNumber;
import pro.sorokovsky.schoolmanagerbackend.exception.parent.ParentAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.parent.ParentNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.ParentsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentsService {
    private final ParentsRepository repository;
    private final UsersService usersService;
    private final EntityManager entityManager;

    public List<ParentEntity> getAll() {
        return repository.findAll();
    }

    public Optional<ParentEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<ParentEntity> getByPupil(Integer pupilId) {
        return repository.findAllByPupil(pupilId);
    }

    @Transactional
    public ParentEntity create(CreateParent parent) {
        if(getById(parent.userId()).isPresent()) {
            throw new ParentAlreadyExistsException();
        }
        if (repository.existsByPhoneNumber(parent.phoneNumber())) {
            throw new ParentAlreadyExistsByPhoneNumber();
        }
        var user = usersService.getById(parent.userId()).orElseThrow(UserNotFoundException::new);
        var sql = """  
            INSERT INTO Parents(UserId, Job, PhoneNumber) VALUES (:userId, :job, :phoneNumber);
            UPDATE Users SET Role = :role WHERE Id = :userId;
            """;
        entityManager.createNativeQuery(sql)
                .setParameter("userId", user.getId())
                .setParameter("job", parent.job())
                .setParameter("phoneNumber", parent.phoneNumber())
                .setParameter("role", Roles.PARENT.value())
                .executeUpdate();
        entityManager.flush();
        entityManager.clear();
        return getById(user.getId()).orElseThrow(ParentNotFoundException::new);
    }

    @Transactional
    public ParentEntity update(Integer id, UpdateParent parent) {
        if (repository.existsByPhoneNumber(parent.phoneNumber())) {
            throw new ParentAlreadyExistsByPhoneNumber();
        }
        var candidate = repository.findById(id).orElseThrow(ParentNotFoundException::new);
        if (parent.phoneNumber() != null) {
            candidate.setPhoneNumber(parent.phoneNumber());
        }
        if (parent.job() != null) {
            candidate.setJob(parent.job());
        }
        return repository.save(candidate);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
