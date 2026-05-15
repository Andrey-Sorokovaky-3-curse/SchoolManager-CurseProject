package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.ClassesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassesService {
    private final ClassesRepository repository;

    public List<ClassEntity> getByStudyYear(Integer studyYear) {
        return repository.findAllByStudyYear(studyYear);
    }

    public List<ClassEntity> getByClassTypeId(Integer classTypeId) {
        return repository.findAllByClassTypeId(classTypeId);
    }

    public Optional<ClassEntity> getById(Integer integer) {
        return repository.findById(integer);
    }
}
