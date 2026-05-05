package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.CreateResponsibility;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.UpdateResponsibility;
import pro.sorokovsky.schoolmanagerbackend.entity.ResponsibilityEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.responsibility.ResponsibilityNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ResponsibilityMapper;
import pro.sorokovsky.schoolmanagerbackend.repository.ResponsibilitiesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResponsibilitiesService {
    private final ResponsibilitiesRepository repository;
    private final ResponsibilityMapper mapper;

    public Optional<ResponsibilityEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<ResponsibilityEntity> search(String term) {
        if (term == null || term.isBlank()) {
            return List.of();
        }
        return repository.search(term);
    }

    public ResponsibilityEntity create(CreateResponsibility responsibility) {
        return repository.save(ResponsibilityEntity.builder()
                .name(responsibility.name()).description(responsibility.description()).build());
    }

    public ResponsibilityEntity update(Integer id, UpdateResponsibility responsibility) {
        final var candidate = getById(id).orElseThrow(ResponsibilityNotFoundException::new);
        if (responsibility.name() != null) {
            candidate.setName(responsibility.name());
        }
        if (responsibility.description() != null) {
            candidate.setDescription(responsibility.description());
        }
        return repository.save(candidate);
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResponsibilityNotFoundException();
        }
    }
}
