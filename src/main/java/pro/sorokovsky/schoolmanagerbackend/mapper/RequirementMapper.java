package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.entity.RequirementEntity;

@Component
public class RequirementMapper {
    public GetRequirement toGet(RequirementEntity entity) {
        return new GetRequirement(entity.getId(), entity.getName(), entity.getDescription());
    }
}
