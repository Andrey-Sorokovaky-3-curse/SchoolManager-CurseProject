package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.user.GetUser;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public GetUser toGet(UserEntity entity) {
        return new GetUser(
                entity.getId(),
                entity.getLogin(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getAddress()
        );
    }
}
