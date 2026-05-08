package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.position.CreatePosition;
import pro.sorokovsky.schoolmanagerbackend.contract.position.UpdatePosition;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.position.PositionNotFoundException;
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

    public PositionEntity update(Integer id, UpdatePosition position) {
        final var candidate = getById(id).orElseThrow(PositionNotFoundException::new);
        if (position.name() != null) {
            candidate.setName(position.name());
        }
        if (position.salary() != null) {
            candidate.setSalary(position.salary());
        }
        return repository.save(candidate);
    }

    public void deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PositionNotFoundException();
        }
    }
}
