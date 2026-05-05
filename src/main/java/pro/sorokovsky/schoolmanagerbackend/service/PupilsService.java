package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.PupilEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.PupilsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilsService {
    private final PupilsRepository repository;

    public List<PupilEntity> getByClassId(Integer classId){
        return repository.findAllByClass(classId);
    }
}
