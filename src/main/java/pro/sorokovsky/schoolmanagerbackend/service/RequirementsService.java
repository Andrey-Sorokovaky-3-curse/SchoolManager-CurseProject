package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.CreateRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.UpdateRequirement;
import pro.sorokovsky.schoolmanagerbackend.entity.RequirementEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.requirement.RequirementNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.RequirementsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequirementsService {
    private final RequirementsRepository repository;

    public Optional<RequirementEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<RequirementEntity> search(String term) {
        if(term == null || term.isBlank()) {
            return List.of();
        }
        return repository.search(term);
    }

    @Transactional
    public RequirementEntity create(CreateRequirement requirement) {
        return repository.save(RequirementEntity.builder().name(requirement.name()).description(requirement.description()).build());
    }

    @Transactional
    public RequirementEntity update(Integer id, UpdateRequirement requirement) {
        final var candidate = getById(id).orElseThrow(RequirementNotFoundException::new);
        if (requirement.name() != null) {
            candidate.setName(requirement.name());
        }
        if (requirement.description() != null) {
            candidate.setDescription(requirement.description());
        }
        return repository.save(candidate);
    }

    @Transactional
    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
