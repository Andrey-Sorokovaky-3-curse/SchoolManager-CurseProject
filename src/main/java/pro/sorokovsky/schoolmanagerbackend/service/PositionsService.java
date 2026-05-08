package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.position.CreatePosition;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.PositionsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionsService {
    private final PositionsRepository repository;

    public Optional<PositionEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<PositionEntity> getByTerm(String term) {
        return repository.findAllByTerm(term);
    }

    public PositionEntity create(CreatePosition position) {
        return repository.save(
                PositionEntity
                        .builder()
                        .name(position.name())
                        .salary(position.salary())
                        .responsibilities(List.of())
                        .requirements(List.of())
                        .build()
        );
    }
}
