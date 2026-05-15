package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.subject.CreateSubject;
import pro.sorokovsky.schoolmanagerbackend.contract.subject.UpdateSubject;
import pro.sorokovsky.schoolmanagerbackend.entity.SubjectEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.EmployeeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.subject.SubjectNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.SubjectsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectsService {
    private final SubjectsRepository repository;
    private final EmployeesService employeesService;

    public List<SubjectEntity> getByTeacher(Integer teacherId) {
        return repository.findAllByTeacherId(teacherId);
    }

    public List<SubjectEntity> getAll() {
        return repository.findAll();
    }

    public Optional<SubjectEntity> getById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public SubjectEntity create(CreateSubject subject) {
        var employee = employeesService.getById(subject.teacherId()).orElseThrow(EmployeeNotFoundException::new);
        return repository.save(SubjectEntity.builder().name(subject.name()).description(subject.description()).teacher(employee).build());
    }

    public SubjectEntity update(Integer id, UpdateSubject subject) {
        var candidate = getById(id).orElseThrow(SubjectNotFoundException::new);
        if (subject.name() != null) {
            candidate.setName(subject.name());
        }
        if (subject.description() != null) {
            candidate.setDescription(subject.description());
        }
        if (subject.teacherId() != null) {
            candidate.setTeacher(employeesService.getById(subject.teacherId()).orElseThrow(EmployeeNotFoundException::new));
        }
        return repository.save(candidate);
    }

    public void deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new SubjectNotFoundException();
        }
    }
}
