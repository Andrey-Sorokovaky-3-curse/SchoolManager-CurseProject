package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.contract.position.CreatePosition;
import pro.sorokovsky.schoolmanagerbackend.contract.position.UpdatePosition;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;
import pro.sorokovsky.schoolmanagerbackend.exception.position.*;
import pro.sorokovsky.schoolmanagerbackend.exception.requirement.RequirementNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.responsibility.ResponsibilityNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.repository.PositionsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionsService {
    private final PositionsRepository repository;
    private final RequirementsService requirementsService;
    private final ResponsibilitiesService responsibilitiesService;

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

    @Transactional
    public PositionEntity addRequirement(Integer id, Integer requirementId) {
        final var candidate = getById(id).orElseThrow(PositionNotFoundException::new);
        candidate.getRequirements()
                .stream()
                .filter(requirement -> requirement.getId().equals(requirementId))
                .findFirst()
                .ifPresent(requirement -> {throw new RequirementAlreadyException();});
        final var requirement = requirementsService.getById(requirementId).orElseThrow(RequirementNotFoundException::new);
        candidate.getRequirements().add(requirement);
        return repository.save(candidate);
    }

    @Transactional
    public PositionEntity removeRequirement(Integer id, Integer requirementId) {
        final var candidate = getById(id).orElseThrow(PositionNotFoundException::new);
        final var hasRequirement = candidate.getRequirements().stream()
                .anyMatch(requirement -> requirement.getId().equals(requirementId));
        if (hasRequirement) {
            candidate.getRequirements().removeIf(requirement -> requirement.getId().equals(requirementId));
            return repository.save(candidate);
        } else {
            throw new NoRequirementException();
        }

    }

    public List<PositionEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public PositionEntity addResponsibility(Integer id, Integer responsibilityId) {
        final var candidate = getById(id).orElseThrow(PositionNotFoundException::new);
        candidate.getResponsibilities().stream()
                .filter(responsibility -> responsibility.getId().equals(responsibilityId))
                .findFirst()
        .ifPresent(responsibility -> {throw new ResponsibilityAlreadyException();});
        final var responsibility = responsibilitiesService.getById(responsibilityId)
                .orElseThrow(ResponsibilityNotFoundException::new);
        candidate.getResponsibilities().add(responsibility);
        return repository.save(candidate);
    }

    @Transactional
    public PositionEntity removeResponsibility(Integer id, Integer responsibilityId) {
        final var candidate = getById(id).orElseThrow(PositionNotFoundException::new);
        final var hasResponsibility = candidate.getResponsibilities().stream()
                .anyMatch(responsibility -> responsibility.getId().equals(responsibilityId));
        if (hasResponsibility) {
            candidate.getResponsibilities().removeIf(responsibility -> responsibility.getId().equals(responsibilityId));
            return repository.save(candidate);
        } else {
            throw new NoResponsibilityException();
        }
    }

    public void deleteById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PositionNotFoundException();
        }
    }
}
