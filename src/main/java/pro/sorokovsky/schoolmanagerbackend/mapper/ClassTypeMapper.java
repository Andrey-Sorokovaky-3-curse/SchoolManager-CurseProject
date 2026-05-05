package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.GetClassType;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassTypeEntity;

@Component
public class ClassTypeMapper {
    public GetClassType toGet(ClassTypeEntity entity) {
        return new GetClassType(entity.getId(), entity.getName(), entity.getDescription());
    }
}
