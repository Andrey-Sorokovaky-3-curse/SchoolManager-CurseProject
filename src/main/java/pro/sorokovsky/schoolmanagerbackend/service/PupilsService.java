package pro.sorokovsky.schoolmanagerbackend.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.pupil.CreatePupil;
import pro.sorokovsky.schoolmanagerbackend.entity.Gender;
import pro.sorokovsky.schoolmanagerbackend.entity.PupilEntity;
import pro.sorokovsky.schoolmanagerbackend.entity.Roles;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.parent.ParentNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.pupil.FatherGenderException;
import pro.sorokovsky.schoolmanagerbackend.exception.pupil.MotherGenderException;
import pro.sorokovsky.schoolmanagerbackend.exception.pupil.PupilAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.pupil.PupilNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.PupilsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PupilsService {
    private final PupilsRepository repository;
    private final ParentsService parentsService;
    private final ClassesService classesService;
    private final UsersService usersService;
    private final EntityManager entityManager;

    public List<PupilEntity> getByClassId(Integer classId){
        return repository.findAllByClass(classId);
    }

    public List<PupilEntity> getAll() {
        return repository.findAll();
    }

    public Optional<PupilEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<PupilEntity> getByParent(Integer parentId) {
        return repository.findAllByParent(parentId);
    }


    @Transactional
    public PupilEntity create(CreatePupil pupil) {
        if(getById(pupil.userId()).isPresent()) {
            throw new PupilAlreadyExistsException();
        }

        var user = usersService.getById(pupil.userId()).orElseThrow(UserNotFoundException::new);
        var clazz = classesService.getById(pupil.classId()).orElseThrow(ClassNotFoundException::new);
        var mother = parentsService.getById(pupil.motherId()).orElseThrow(ParentNotFoundException::new);
        var father = usersService.getById(pupil.fatherId()).orElseThrow(ParentNotFoundException::new);
        if (mother.getGender() != Gender.FEMALE) throw new MotherGenderException();
        if (father.getGender() != Gender.MALE) throw new FatherGenderException();
        final var sql = """
            INSERT INTO Pupils(UserId, FatherId, MotherId, ClassId, ExtraInformation)
            VALUES (:userId, :fatherId, :motherId, :classId, :extraInformation);
            UPDATE Users SET Role=:role WHERE Id = :userId;
""";
        entityManager.createNativeQuery(sql)
                .setParameter("userId", user.getId())
                .setParameter("fatherId", father.getId())
                .setParameter("motherId", mother.getId())
                .setParameter("classId", clazz.getId())
                .setParameter("extraInformation", pupil.extraInformation())
                .setParameter("role", Roles.PUPIL);
        return getById(user.getId()).orElseThrow(PupilNotFoundException::new);
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PupilNotFoundException();
        }
    }
}
