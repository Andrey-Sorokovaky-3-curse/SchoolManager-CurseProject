package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.position.GetPosition;
import pro.sorokovsky.schoolmanagerbackend.entity.PositionEntity;

@Component
@RequiredArgsConstructor
public class PositionMapper {
    private final RequirementMapper requirementMapper;
    private final ResponsibilityMapper responsibilityMapper;

    public GetPosition toGet(PositionEntity entity) {
        return new GetPosition(
                entity.getId(),
                entity.getName(),
                entity.getSalary(),
                entity.getRequirements().stream().map(requirementMapper::toGet).toList(),
                entity.getResponsibilities().stream().map(responsibilityMapper::toGet).toList()
                );
    }
}
