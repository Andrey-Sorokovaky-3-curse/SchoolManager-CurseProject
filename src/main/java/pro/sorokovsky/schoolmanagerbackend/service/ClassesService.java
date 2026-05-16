package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.classes.CreateClass;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassCreatedAtIncorrectException;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.EmployeeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.сlasstype.ClassTypeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.ClassesRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassesService {
    private final ClassesRepository repository;
    private final ClassTypesService classTypesService;
    private final EmployeesService employeesService;

    public List<ClassEntity> getByStudyYear(Integer studyYear) {
        return repository.findAllByStudyYear(studyYear);
    }

    public List<ClassEntity> getByClassTypeId(Integer classTypeId) {
        return repository.findAllByClassTypeId(classTypeId);
    }

    public List<ClassEntity> getAll() {
        return repository.findAll();
    }

    public Optional<ClassEntity> getById(Integer integer) {
        return repository.findById(integer);
    }

    @Transactional
    public ClassEntity create(CreateClass newClass) {
        if (repository.existsByName("%d-%s".formatted(newClass.studyYear(), newClass.letter()))) {
            throw new ClassAlreadyExistsException();
        }
        var classType = classTypesService.getById(newClass.classTypeId()).orElseThrow(ClassTypeNotFoundException::new);
        var curator = employeesService.getById(newClass.curatorId()).orElseThrow(EmployeeNotFoundException::new);
        if (newClass.createdAtYear() > Year.now().getValue() || newClass.createdAtYear() < 0) {
            throw new ClassCreatedAtIncorrectException();
        }
        return repository
                .save(
                        ClassEntity
                                .builder()
                                .curator(curator)
                                .classType(classType)
                                .letter(newClass.letter())
                                .studyYear(newClass.studyYear())
                                .createdAtYear(newClass.createdAtYear())
                                .build()
                );
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ClassNotFoundException();
        }
    }
}
