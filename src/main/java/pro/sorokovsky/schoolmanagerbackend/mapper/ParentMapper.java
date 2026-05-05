package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.GetParent;
import pro.sorokovsky.schoolmanagerbackend.entity.ParentEntity;

@Component
public class ParentMapper {
    public GetParent toGet(ParentEntity entity) {
        if (entity == null) return null;
        return new GetParent(
                entity.getId(),
                entity.getLogin(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getAddress(),
                entity.getJob(),
                entity.getPhoneNumber()
        );
    }
}
