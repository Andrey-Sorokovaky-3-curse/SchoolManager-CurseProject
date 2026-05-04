package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.SubjectEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.SubjectsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectsService {
    private final SubjectsRepository repository;

    public List<SubjectEntity> getByTeacher(Integer teacherId) {
        return repository.findAllByTeacherId(teacherId);
    }
}
