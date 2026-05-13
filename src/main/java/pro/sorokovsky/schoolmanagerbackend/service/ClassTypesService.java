package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.CreateClassType;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.UpdateClassType;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassTypeEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.сlasstype.ClassTypeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.ClassTypesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassTypesService {
    private final ClassTypesRepository repository;

    public List<ClassTypeEntity> getAll() {
        return repository.findAll();
    }

    public Optional<ClassTypeEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public ClassTypeEntity create(CreateClassType classType) {
        return repository.save(ClassTypeEntity.builder().name(classType.name()).description(classType.description()).build());
    }

    public ClassTypeEntity update(Integer id, UpdateClassType classType) {
        final var candidate = getById(id).orElseThrow(ClassTypeNotFoundException::new);
        if (classType.name() != null) {
            candidate.setName(candidate.getName() + classType.name());
        }
        if (classType.description() != null) {
            candidate.setDescription(candidate.getDescription() + classType.description());
        }
        return repository.save(candidate);
    }

    public void deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ClassTypeNotFoundException();
        }
    }
}
