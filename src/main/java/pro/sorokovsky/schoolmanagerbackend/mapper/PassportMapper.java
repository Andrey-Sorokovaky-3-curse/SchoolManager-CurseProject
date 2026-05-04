package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.passport.GetPassport;
import pro.sorokovsky.schoolmanagerbackend.entity.PassportEntity;

@Component
public class PassportMapper {
    public GetPassport toGet(PassportEntity entity) {
        return new GetPassport(entity.getId(), entity.getName(), entity.getData());
    }
}
