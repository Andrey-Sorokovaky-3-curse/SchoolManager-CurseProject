package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.GetResponsibility;
import pro.sorokovsky.schoolmanagerbackend.entity.ResponsibilityEntity;

@Component
public class ResponsibilityMapper {
    public GetResponsibility toGet(ResponsibilityEntity entity) {
        return new GetResponsibility(entity.getId(), entity.getName(), entity.getDescription());
    }
}
