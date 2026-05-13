package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.PupilEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.ParentsRepository;
import pro.sorokovsky.schoolmanagerbackend.repository.PupilsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PupilsService {
    private final PupilsRepository repository;
    private final ParentsService parentsService;

    public List<PupilEntity> getByClassId(Integer classId){
        return repository.findAllByClass(classId);
    }

    public List<PupilEntity> getAll() {
        return repository.findAll();
    }

    public Optional<PupilEntity> getById(Integer id) {
        return repository.findById(id);
    }
}
