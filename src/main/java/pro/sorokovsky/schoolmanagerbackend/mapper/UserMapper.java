package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.GetUser;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;
import pro.sorokovsky.schoolmanagerbackend.model.Roles;
import pro.sorokovsky.schoolmanagerbackend.model.UserModel;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserEntity toEntity(UserModel model) {
        final var role = model
                .getAuthorities()
                .stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(authority -> authority.getAuthority().substring("ROLE_".length()))
                .findFirst().orElse(Roles.USER.value());
        return UserEntity
                .builder()
                .id(model.getId())
                .login(model.getLogin())
                .password(model.getPassword())
                .role(role)
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .middleName(model.getMiddleName())
                .birthday(model.getBirthday().toLocalDate())
                .gender(model.getGender())
                .address(model.getAddress())
                .build();
    }

    public GetUser toGet(UserModel model) {
        return new GetUser(
                model.getId(),
                model.getLogin(),
                model.getFirstName(),
                model.getLastName(),
                model.getMiddleName(),
                model.getBirthday(),
                model.getGender(),
                model.getAddress()
        );
    }

    public UserModel toModel(UserEntity entity) {
        return UserModel
                .builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .role(entity.getRole())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .middleName(entity.getMiddleName())
                .birthday(Date.valueOf(entity.getBirthday()))
                .gender(entity.getGender())
                .address(entity.getAddress())
                .build();
    }
}
