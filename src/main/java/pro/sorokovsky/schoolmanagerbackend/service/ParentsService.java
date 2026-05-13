package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.CreateParent;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.UpdateParent;
import pro.sorokovsky.schoolmanagerbackend.entity.ParentEntity;
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
        var newParent = ParentEntity
                .builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .gender(user.getGender())
                .address(user.getAddress())
                .job(parent.job())
                .phoneNumber(parent.phoneNumber())
                .build();
        return repository.save(newParent);
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
